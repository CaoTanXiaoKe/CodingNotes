11-6B-tiny : 11-6B-tiny.o myheader.o
	gcc -o 11-6B-tiny 11-6B-tiny.o myheader.o

11-6B-tiny.o : 11-6B-tiny.c myheader.h
	gcc -c 11-6B-tiny.c
myheader.o : myheader.c myheader.h
	gcc -c myheader.c

clean : 
	rm 11-6B-tiny 11-6B-tiny.o myheader.o
