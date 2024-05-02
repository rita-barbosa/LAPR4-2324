us2001_prog: get_new_candidates.o delegate_work.o check_new_files.o copy_files.o main.o 
	gcc get_new_candidates.o delegate_work.o check_new_files.o copy_files.o main.o -z noexecstack -o us2001_prog

get_new_candidates.o: get_new_candidates.c
	gcc -g -Wall -Wextra -fanalyzer -c get_new_candidates.c  -o get_new_candidates.o

delegate_work.o: delegate_work.c
	gcc -g -Wall -Wextra -fanalyzer -c delegate_work.c  -o delegate_work.o

copy_files.o: copy_files.c
	gcc -g -Wall -Wextra -fanalyzer -c copy_files.c  -o copy_files.o

check_new_files.o: check_new_files.c
	gcc -g -Wall -Wextra -fanalyzer -c check_new_files.c  -o check_new_files.o

main.o: main.c fnc.h
	gcc -g -Wall -Wextra -fanalyzer -c main.c  -o main.o

run: us2001_prog 
	./us2001_prog files output 2 5

clean:
	rm -f *.o us2001_prog
