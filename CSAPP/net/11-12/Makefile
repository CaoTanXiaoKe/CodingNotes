11-12-tiny : 11-12-tiny.o myheader.o
	gcc -o 11-12-tiny 11-12-tiny.o myheader.o

11-12-tiny.o : 11-12-tiny.c myheader.h
	gcc -c 11-12-tiny.c;
myheader.o : myheader.c myheader.h
	gcc -c myheader.c;

test :
	(cd cgi-bin; make)

clean :
	rm 11-12-tiny 11-12-tiny.o myheader.o
