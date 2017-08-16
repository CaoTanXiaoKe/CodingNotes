11-11-tiny : 11-11-tiny.o myheader.o
	gcc -o 11-11-tiny 11-11-tiny.o myheader.o

11-11-tiny.o : 11-11-tiny.c myheader.h
	gcc -c 11-11-tiny.c;
myheader.o : myheader.c myheader.h
	gcc -c myheader.c;

test :
	(cd cgi-bin; make)

clean :
	rm 11-11-tiny 11-11-tiny.o myheader.o
