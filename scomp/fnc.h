#ifndef FNC_H
#define FNC_H

#define MONITORING_TIME 5
#define NUMB_CHILDS 3

void get_new_candidates(const char *directory, int *current_number, int *max_diff);
void delegate_candidate(int lastCandidate, int copiedCandDiff, int child, int fd[][2]);
void available_child(int *child, int fd);

//Checking for new files in input directory
void check_files_child_ls(int *newFilePipe, int *newFilePipeMidChildren);
void check_files_child_wc(int *newFilePipe, int *newFilePipeMidChildren);

//For each to copy files for a candidate
void copy_files(int n, char *input_directory, char *output_directory);

#endif