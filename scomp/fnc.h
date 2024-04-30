#ifndef FNC_H
#define FNC_H

#define NUMB_CHILDS 3

void get_new_candidates(const char *directory, int *current_number, int *max_diff);
void delegate_candidate(int lastCandidate, int copiedCandDiff, int child, int fd[][2]);
void available_child(int *child, int fd);

#endif