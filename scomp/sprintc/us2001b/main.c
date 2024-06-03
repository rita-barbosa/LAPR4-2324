#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
#include <stdbool.h>
#include <dirent.h>
#include <errno.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include<semaphore.h>
#include "fnc.h"

volatile sig_atomic_t time_to_terminate = 0;
volatile sig_atomic_t end_child = 0;

void handle_signal(int signo)
{
    if (signo == SIGUSR1)
    {
        write(STDOUT_FILENO, "\nALERT: New file detected.\n", 27);
    }
    else if (signo == SIGINT)
    {
        // write(STDOUT_FILENO, "\nOrder to terminate.\n", 22);
        time_to_terminate = 1;
    }
    else if (signo == SIGUSR2)
    {
        end_child = 1;
    }
}

// VERSION: 7
// 04/05/2024
// Group: 2DG2
int main(int argc, char *argv[])
{
    // GETS CONFIG INFORMATION FROM PARAMETERS
    //==Work done by: Rita Barbosa======================================================
    Configuration config;
    if (argc < 5)
    {
        fprintf(stderr, "Usage: %s <input_dir> <output_dir> <num_workers> <time_interval>\n", argv[0]);
        return 1;
    }
    sscanf(argv[1], "%s", config.input_directory);
    sscanf(argv[2], "%s", config.output_directory);
    sscanf(argv[3], "%d", &config.num_worker_children);
    sscanf(argv[4], "%d", &config.time_interval);
    //===================================================================================

    // CREATES THE OUTPUT DIRECTORY IF IT DOESN'T EXIST
    //==Work done by: Ana Guterres======================================================
    DIR *dir_output;
    dir_output = opendir(config.output_directory);
    if (!dir_output)
    {
        printf("The %s directory doesn't exist!\nCreating the directory!\n", config.output_directory);
        if (errno == ENOENT)
        {
            // If the output directory doesn't exist, it will be created
            if (mkdir(config.output_directory, 0777) == -1)
            {
                perror("mkdir");
                exit(EXIT_FAILURE);
            }

            dir_output = opendir(config.output_directory);
            if (!dir_output)
            {
                perror("opendir");
                exit(EXIT_FAILURE);
            }
        }
        else
        {
            perror("opendir");
            exit(EXIT_FAILURE);
        }
    }
    //===================================================================================

    int i, j, n, status, candidate = -1;
    pid_t child_p[config.num_worker_children + 1];
    struct sigaction act;
    int fd[config.num_worker_children][2];
    int shared_p[2];

    //int *shm_counter=NULL;          //share memory -- check this!!
    int fd2;
    int *shared_memory;
    sem_t *sem1, *sem2, *sem3;      //semaphores to be used

    //===================================================================================

    //CREATES THE SHARED MEMORY SPACE AND WRITES THE INFORMATION ON THE ARRAY ON THE SHARED MEMORY
    //==Work done by: Ana Guterres======================================================
    
    /* creates/opens shared memory area */
    if((fd2 = shm_open("/shm_us2001b",O_CREAT|O_RDWR,S_IRUSR|S_IWUSR)) == -1){
        perror("shmopen");
        exit(1);
    }

    /* defines size of shm */
    if(ftruncate(fd2, config.num_worker_children * sizeof(int)) == -1){
        perror("ftruncate");
        exit(2);
    }
    
    /* maps shm into address space */
    if((shared_memory = mmap(0, config.num_worker_children * sizeof(int),PROT_READ|PROT_WRITE,MAP_SHARED,fd2,0)) == MAP_FAILED){
        perror("mmap");
        exit(3);
    }

    for(int l = 0; l < config.num_worker_children; l++){ //--> THIS CODE IS FOR TESTING ONLY --> REMOVE LATER!!!
        shared_memory[l] = 0;
        printf("shared_memory[%u] = %d\n", l, shared_memory[l]);
    }

    /* creates sem */
    if((sem1 = sem_open("/sem1",O_CREAT,0644,0))==SEM_FAILED){
        perror("sem_open");
        exit(4);
    }

    /* creates sem */
    if((sem2 = sem_open("/sem2",O_CREAT,0644,1))==SEM_FAILED){
        perror("sem_open");
        exit(5);
    }

    /* creates sem */
    if((sem3 = sem_open("/sem3",O_CREAT,0644,0))==SEM_FAILED){
        perror("sem_open");
        exit(6);
    }
    //===================================================================================


    // SETS UP SIGNALS TO BE HANDLED
    //==Work done by: Rita Barbosa======================================================
    memset(&act, 0, sizeof(struct sigaction));
    act.sa_handler = handle_signal;
    act.sa_flags = SA_SIGINFO /*| SA_RESTART*/;

    if (sigaction(SIGUSR1, &act, NULL) == -1 || sigaction(SIGINT, &act, NULL) == -1 || sigaction(SIGUSR2, &act, NULL) == -1)
    {
        perror("sigaction");
        return 1;
    }

    if (pipe(shared_p) == -1)
    {
        perror("pipe");
        return 1;
    }
    //===================================================================================

    // CHILD WORKERS WILL COPY THE FILES FROM THE INPUT TO THE OUTPUT DIRECTORY
    //==Work done by: Ana Guterres======================================================
    for (i = 0; i < config.num_worker_children; i++)
    {
        if (pipe(fd[i]) == -1)
        {
            perror("pipe");
            return 1;
        }

        child_p[i + 1] = fork();

        if (child_p[i + 1] == 0)
        {
            close(fd[i][1]);    // Close write end of pipe in child worker
            close(shared_p[0]); // Close read end of shared pipe in child worker
            while (!end_child)
            {
                // if (sem_wait(sem1) == -1) { //check this --> as this is not complete it gives error
                //     perror("sem_wait");
                //     exit(100);
                // }

                // Read from pipe to get candidate value
                n = read(fd[i][0], &candidate, sizeof(candidate));
                // if (sem_wait(sem2) == -1) {
                //     perror("sem_wait");
                //     exit(201);
                // }
                int candidateMemory = shared_memory[i];

                // printf("shared memory %d: %d\n", i, shared_memory[i]);
                //printf("The candidate number now is: %d\n", candidateMemory);

                if (sem_post(sem2) == -1) {
                    perror("sem_wait");
                    exit(202);
                }

                if (candidateMemory == -1)
                {
                    break;
                }
                if (n != sizeof(candidate))
                {
                    perror("Error: child worker couldn't read from pipe.");
                    exit(i + 1);
                }
                
                copy_files(candidateMemory, config.input_directory, config.output_directory); // Copies the files from the input directoty to the ouput directory
            
                if (sem_wait(sem2) == -1) {
                    perror("sem_wait");
                    exit(201);
                }

                shared_memory[i] = -1;  

                if (sem_post(sem2) == -1) {
                    perror("sem_wait");
                    exit(202);
                }  

                n = write(shared_p[1], &i, sizeof(i));  // Write to shared pipe to notify parent that work is done
                if (candidate == -1)
                {
                    break;
                }
                if (n != sizeof(i))
                {
                    perror("Error: child worker couldn't write to pipe.");
                    exit(i + 1);
                }
                candidate = -1;
            }
            close(fd[i][0]);    // Close read end of pipe in child
            close(shared_p[1]); // Close write end of shared pipe in child
            exit(i);
        }
        else if (child_p[i + 1] == -1)
        {
            perror("ERROR: Couldn't create child worker.");
            return 1;
        }
    }
    //===================================================================================

    // CHILD THAT WILL PERIODICALY VERIFY IF THERE IS ANY NEW FILES IN THE INPUT DIRECTORY
    //==Work done by: Matilde Varela======================================================
    child_p[0] = fork();
    if (child_p[0] == 0)
    {
        // CHILD THAT WILL PERIODICALLY MONITOR THE INPUT DIRECTORY
        int newFileLine = 0;     // last line refering to a file
        int returnNumber = 0;    // line refering to a file after input directory check-in

        while (!time_to_terminate)
        {
            returnNumber = check_files_child(config.input_directory);

            if (returnNumber > newFileLine)
            {
                newFileLine = returnNumber;
                printf("INFO: Monitor child going to signal.\n");

                if (sem_post(sem3) == -1) { 
                    perror("sem_post");
                    exit(301);
                }

                kill(getppid(), SIGUSR1);
            }
            else
            {
                printf("INFO: No new files in input folder.\n");
            }
            sleep(config.time_interval);
        }
        exit(0);
    }
    else if (child_p[0] == -1)
    {
        perror("fork");
        return 1;
    }
    //===================================================================================

    // PARENT WILL DELEGATE WORK TO THE CHILD WORKERS
    //==Work done by: Rita Barbosa======================================================
    int availableChild = 0, lastCandidate = 0, copiedCandDiff = 0, reported_child = 0;
    close(shared_p[i]); // Close write end of shared pipe in parent
    while (!time_to_terminate)
    {
        printf("\nINFO: waiting for signal\n");
        pause();

        if (time_to_terminate)
        {
            printf("\n>>>Time to terminate<<<\n");
            break;
        }
        else
        {
            // if (sem_wait(sem3) == -1) { 
            //     perror("sem_wait");
            //     exit(302);
            // }

            get_new_candidates(config.input_directory, &lastCandidate, &copiedCandDiff);
            int childworker = 0;

            //  if (sem_wait(sem1) == -1) { //check this --> it gives error
            //         perror("sem_wait");
            //         exit(101);
            // }

            while (copiedCandDiff != 0)
            {
                if (childworker < config.num_worker_children)
                {
                    close(fd[childworker][0]);                                          // Close read end of pipe for parent
                    delegate_candidate(lastCandidate, copiedCandDiff, childworker, fd); // Pass the pipe for this child worker

                    //just for testing the write and read from shared_memory
                    // if (sem_wait(sem2) == -1) {
                    //     perror("sem_wait");
                    //     exit(203);
                    // }

                    int can_aux = lastCandidate - (copiedCandDiff - 1);
                    shared_memory[childworker] = can_aux;
                    // printf("INFO: Parent will delegate candidate %d to Child[%d]\n", can_aux, (childworker + 1));

                    if (sem_post(sem2) == -1) {
                        perror("sem_wait");
                        exit(204);
                    }

                    copiedCandDiff--;

                }
                else
                {
                    // Close write end of shared pipe
                    available_child(&availableChild, shared_p[0]);                         // Wait for an available child notice
                    delegate_candidate(lastCandidate, copiedCandDiff, availableChild, fd); // Pass the pipe for the available child

                    //just for testing the write and read from shared_memory

                    // if (sem_wait(sem2) == -1) {
                    //         perror("sem_wait");
                    //         exit(205);
                    // } 

                    // if (sem_wait(sem1) == -1) {
                    //         perror("sem_wait");
                    //         exit(105);
                    // }

                    // for (int e = 0; e < config.num_worker_children; e++)
                    // {
                    //     if (shared_memory[e] == -1)
                    //     {
                    //         printf("Parent received notice from child: %d\n", e + 1);

                    //         int can_aux = lastCandidate - (copiedCandDiff - 1);
                    //         shared_memory[e] = can_aux;
                    //         printf("INFO: Parent will delegate candidate %d to Child[%d]\n", can_aux, (e + 1));
                    //         copiedCandDiff--;

                    //         printf("shared memory %d: %d\n", e, shared_memory[e]);
                    //         break;
                    //     }
                    // }

                    int can_aux = lastCandidate - (copiedCandDiff - 1);
                    shared_memory[availableChild] = can_aux;
                    // printf("INFO: Parent will delegate candidate %d to Child[%d]\n", can_aux, (availableChild + 1));

                    if (sem_post(sem2) == -1) {
                        perror("sem_wait");
                        exit(206);
                    }

                    copiedCandDiff--;
                }
                childworker++;

                //set up of the sempahore1 --> to notify the children that tey can start working
                if (sem_post(sem1) == -1) {
                    perror("sem_post");
                    exit(102);
                }
            }  
        }

        // GENERATES REPORT OF THE PROCESSED CANDIDATES
        //==Work done by: José Afonso======================================================
        generate_report(reported_child, config.output_directory);
        reported_child = lastCandidate;
        //===================================================================================
    }
    //===================================================================================

    // CLOSES PIPES; TERMINATES CHILDREN AND WAITS FOR THEM TO BE FINISHED
    //==Work done by: Rita Barbosa======================================================
    close(shared_p[0]);
    candidate = -1;
    for (j = 0; j < config.num_worker_children + 1; j++)
    {
        if (j < config.num_worker_children)
        {
            close(fd[j][1]);
        }
        kill(child_p[j], SIGUSR2);
        printf("ALERT: Parent sent signal to terminate child [%d]\n", child_p[j]);
    }
    for (j = 0; j < (config.num_worker_children + 1); j++)
    {
        waitpid(child_p[j], &status, 0);
        if (WIFEXITED(status))
        {
            printf("[Child %d] - Exit Status:%d \n", child_p[j], WEXITSTATUS(status));
        }
    }

     // REMOVE THE SHARE MEMORY AND THE SEMAPHORES
    //==Work done by: Ana Guterres======================================================
    close(fd2);
    munmap(shared_memory , config.num_worker_children * sizeof(int));

    /* removes share memory and semaphores */
    shm_unlink("/shm_us2001b");
    sem_unlink("/sem1");
    sem_unlink("/sem2");
    sem_unlink("/sem3");
    //===================================================================================

    closedir(dir_output);

    return 0;
}


