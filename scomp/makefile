prog: get_new_candidates.o delegate_work.o main.o
	gcc get_new_candidates.o delegate_work.o main.o -z noexecstack -o prog

get_new_candidates.o: get_new_candidates.c
	gcc -g -Wall -Wextra -fanalyzer -c get_new_candidates.c  -o get_new_candidates.o

delegate_work.o: delegate_work.c
	gcc -g -Wall -Wextra -fanalyzer -c delegate_work.c  -o delegate_work.o

main.o: main.c fnc.h
	gcc -g -Wall -Wextra -fanalyzer -c main.c  -o main.o

run: prog
	./prog

clean:
	rm -f *.o prog