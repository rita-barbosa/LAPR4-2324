#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
#include <stdbool.h>
#include "fnc.h"

//variable indicating reception of signal to terminate
volatile sig_atomic_t time_to_terminate = 0;

void handle_signal(int signo) {
    if (signo == SIGUSR1) {
        write(STDOUT_FILENO, "\nALERT: New file detected.\n", 27);
    } else if (signo == SIGINT) {
        write(STDOUT_FILENO, "\nOrder to terminate.", 21);
        time_to_terminate = 1;
    }
}

int main()
{
    // VARIABLES
    int i, n, j, availableChild = 0, lastCandidate = 0, copiedCandDiff = 0, candidate = 0;
    pid_t child_p[NUMB_CHILDS + 1];
    struct sigaction act;
    int fd[NUMB_CHILDS + 1][2]; // fd[0][x] -> is the file descriptor for the shared pipe
    char *directory = "files";   // Directory to search (current directory in this case)

    // SETUP SIGNAL - SIGUSR1
    memset(&act, 0, sizeof(struct sigaction));
    act.sa_handler = handle_signal;
    act.sa_flags = SA_SIGINFO;
    if (sigaction(SIGUSR1, &act, NULL) == -1)
    {
        perror("sigaction");
        return 1;
    }

    if (sigaction(SIGINT, &act, NULL) == -1)
    {
        perror("sigaction");
        return 1;
    }

    child_p[0] = fork(); // will create monitor child
    if (child_p[0] < 0)
    {
        printf("ERROR: Couldn't create monitor child.");
        return 1;
    }
    if (child_p[0] == 0)
    {
        // CHILD THAT WILL PERIODICALLY MONITOR THE INPUT DIRECTORY
        int newFileLine = 0; //last line refering to a file
        char returnNumberInChar; //line refering to a file after input directory check-in
        int returnNumber = 0; //line refering to a file after input directory check-in
        int status;
        int newFilePipe[2]; //pipe between last sub-child and parent-child
        int newFilePipeMidChildren[2]; //pipe between subchildren

        if (chdir(directory) == -1){
            perror("chdir");
            exit(1);
        }

        if (pipe(newFilePipe) == -1) {
                perror("pipe");
                exit(1);
        }

        if (pipe(newFilePipeMidChildren) == -1) {
            perror("pipe");
            exit(1);
        }


        while(!time_to_terminate){
            for(i = 0; i < 2; ++i){
                pid_t pid = fork();
                if(pid == 0){
                    if(i == 0) {
                        check_files_child_ls(newFilePipe, newFilePipeMidChildren);
                    } else {
                        check_files_child_wc(newFilePipe, newFilePipeMidChildren);
                    }
                    perror("execlp");
                    exit(1);
                }
            }

            //monitoring child will not write or read on sub-children pipe
            close(newFilePipeMidChildren[0]);
            close(newFilePipeMidChildren[1]);
            close(newFilePipe[1]); //monitoring child will not write on new file pipe

            //waits for the termination of all children
            for(i = 0; i < 2; ++i){
                wait(&status);
                if (!WIFEXITED(status) && WEXITSTATUS(status) != 0) {
                    printf("Something wrong happened with the new file sub-child process.\n");
                }
            }

            //reads number of files currently on the input directory
            n = read(newFilePipe[0], &returnNumberInChar, sizeof(returnNumberInChar));
            if (n < 0){
                perror("Couldn't read all the information.");
                exit(1);
            }

            returnNumber = (int)returnNumberInChar - '0';

            printf("Number of files in the directory: %d\n", returnNumber);

            if (returnNumber > newFileLine) {
                newFileLine = returnNumber;
                printf("INFO: Monitor child going to signal.\n");
                kill(getppid(), SIGUSR1);
            }else{
                printf("INFO: No new files in input folder.\n");
            }

            sleep(MONITORING_TIME);
        }

        close(newFilePipe[0]); //monitoring child will no longer read from new file pipe
        exit(0);
    }

    // CREATING CHILDS + CONFIGURING THEIR PIPES
    if (pipe(fd[0]) == -1) // opens shared pipe
    {
        perror("Pipe failed");
        exit(1);
    }

    for (i = 1; i <= NUMB_CHILDS; i++)
    {
        if (pipe(fd[i]) == -1)
        {
            perror("Pipe failed");
            exit(1);
        }

        child_p[i] = fork();
        if (child_p[i] < 0)
        {
            printf("ERROR: Couldn't create monitor child.");
            return 1;
        }
        if (child_p[i] == 0)
        {
            // CHILD WORKERS - the following code was just so that i could test the pipes and make sure everything was right
            while (true)
            {
                close(fd[i][1]);
                n = read(fd[i][0], &candidate, sizeof(candidate));
                if (n != sizeof(candidate))
                {
                    printf("ERROR: Child couldn't read all information from PIPE[%d].\n", i);
                }
                printf("\n[START]\n");
                printf("Child[%d] will work on files of candidate: %d\n", i, candidate);
                printf("[FINISH]\n\n");
                // close(fd[i][0]);

                // SHARED PIPE - para avisar quando terminou o trabalho
                close(fd[0][0]);
                n = write(fd[0][1], &i, sizeof(i));
                if (n != sizeof(i))
                {
                    printf("ERROR: Child couldn't write all information to shared pipe.\n");
                }
            }
            close(fd[0][1]);
        }
    }

    printf("INFO: waiting for signal\n");
    pause();

    get_new_candidates(directory, &lastCandidate, &copiedCandDiff); // it identifies if files of a new candidate were added
    int childworker = 0;
    close(fd[0][1]);
    while (copiedCandDiff != 0)
    {
        childworker++;
        if (childworker <= NUMB_CHILDS)
        {
            close(fd[childworker][0]);
            delegate_candidate(lastCandidate, copiedCandDiff, childworker, fd); // will delegate work to each child worker;
            copiedCandDiff--;
        }
        else
        {
            available_child(&availableChild, fd[0][0]);                            // will wait for an available child notice
            delegate_candidate(lastCandidate, copiedCandDiff, availableChild, fd); // will delegate work to available child;
            copiedCandDiff--;
        }
    }

    for (i = 1; i <= NUMB_CHILDS; i++)
    {
        available_child(&availableChild, fd[0][0]); // will wait for the notice of all childs to ensure all of them finished their work
    }
    close(fd[0][0]); // will close the shared pipe read file descriptor

    // This will terminate all children by sending a SIGKILL signal.
    for (j = 0; j < (NUMB_CHILDS + 1); j++)
    {
        kill(child_p[j], SIGKILL);
        printf("ALERT: Parent terminated child [%d]\n", child_p[j]);
    }

    return 0;
}