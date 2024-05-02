#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <regex.h>
#include <string.h>

void get_new_candidates(char *directory, int *current_number, int *max_diff)
{
    DIR *dir;
    struct dirent *entry;
    regex_t regex;
    int reti;
    int last_number = *current_number; // Variable to store the last registered number

    // Compile the regular expression pattern
    reti = regcomp(&regex, "^([0-9]+)-[^\\.]+\\..+$", REG_EXTENDED);
    if (reti)
    {
        fprintf(stderr, "Could not compile regex\n");
        exit(EXIT_FAILURE);
    }

    // Open the directory
    dir = opendir(directory);
    if (!dir)
    {
        perror("opendir");
        exit(EXIT_FAILURE);
    }

    // Iterate over the directory entries
    while ((entry = readdir(dir)) != NULL)
    {
        // Match the filename with the regular expression
        reti = regexec(&regex, entry->d_name, 0, NULL, 0);
        if (!reti)
        {
            // Extract the number from the filename
            char *num_str = strtok(entry->d_name, "-");
            int num = atoi(num_str);

            // Check if this number is greater than the current max number
            if (num > *current_number)
            {
                // Update max_diff and current_number
                *max_diff = num - last_number;
                *current_number = num;
            }
        }
        else if (reti != REG_NOMATCH)
        {
            fprintf(stderr, "Regex match failed\n");
            exit(EXIT_FAILURE);
        }
    }

    // Close the directory
    closedir(dir);

    // Free the compiled regex
    regfree(&regex);
}