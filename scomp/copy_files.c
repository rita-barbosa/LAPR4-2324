#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <dirent.h>
#include <string.h>
#include <sys/stat.h>

void copy_files(int n, char *input_directory, char *output_directory){
    DIR *dir;
    FILE *input_file, *output_file;
    struct dirent *entry;
    char subdirectory[15];

    sprintf(subdirectory, "candidate%d", n);

    char output_subdirectory[215];
    sprintf(output_subdirectory, "%s/%s", output_directory, subdirectory);
    mkdir(output_subdirectory, 0777);

    // Open the directory
    dir = opendir(input_directory);
    if (!dir)
    {
        perror("opendir");
        exit(EXIT_FAILURE);
    }

    // Iterate over the directory entries
    while ((entry = readdir(dir)) != NULL)
    {

        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0) {
            continue;
        }

        if (entry->d_name[0] - '0' == n)
        {
            // Construct source file path
            char source_file_path[257];
            sprintf(source_file_path, "%s/%s", input_directory, entry->d_name);
            
            // Construct destination file path
            char dest_file_path[272]; 
            sprintf(dest_file_path, "%s/%s/%s", output_directory, subdirectory, entry->d_name);
            
            input_file = fopen(source_file_path, "rb");
            if (input_file == NULL) {
                perror("fopen");
                exit(1);
            }  

            // Open destination file for writing
            output_file = fopen(dest_file_path, "wb");
            if (output_file == NULL) {
                perror("fopen");
                exit(1);
            } 

            // Copy file contents
            char buffer[10];
            size_t bytes_read;
            while ((bytes_read = fread(buffer, 1, sizeof(buffer), input_file)) > 0) {
                fwrite(buffer, 1, bytes_read, output_file);
            }
            
            // Close files
            fclose(input_file);
            fclose(output_file);
            
            printf("The file %s from %s was copied to %s\n", entry->d_name, input_directory, subdirectory);
        }

}

    // Close the directory
    closedir(dir);

}