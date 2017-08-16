11-6A-tiny : 11-6A-tiny.o myheader.o
	gcc -o 11-6A-tiny 11-6A-tiny.o myheader.o

11-6A-tiny.o : 11-6A-tiny.c myheader.h
	gcc -c 11-6A-tiny.c;
myheader.o : myheader.c myheader.h
	gcc -c myheader.c;

clean :
	rm 11-6A-tiny 11-6A-tiny.o myheader.o
