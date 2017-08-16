11-9-tiny : 11-9-tiny.o myheader.o
	gcc -o 11-9-tiny 11-9-tiny.o myheader.o

11-9-tiny.o : 11-9-tiny.c myheader.h
	gcc -c 11-9-tiny.c;
myheader.o : myheader.c myheader.h
	gcc -c myheader.c;

clean :
	rm 11-9-tiny 11-9-tiny.o myheader.o
