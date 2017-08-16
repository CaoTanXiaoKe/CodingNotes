11-13-tiny : 11-13-tiny.o myheader.o
	gcc -o 11-13-tiny 11-13-tiny.o myheader.o

11-13-tiny.o : 11-13-tiny.c myheader.h
	gcc -c 11-13-tiny.c;
myheader.o : myheader.c myheader.h
	gcc -c myheader.c;

test :
	(cd cgi-bin; make)

clean :
	rm 11-13-tiny 11-13-tiny.o myheader.o
