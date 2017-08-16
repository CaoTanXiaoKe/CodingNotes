adder : adder.o myheader.o
	gcc -o adder adder.o myheader.o

adder.o : adder.c myheader.h
	gcc -c adder.c
myheader.o : myheader.c myheader.h
	gcc -c myheader.c;

clean :
	rm adder adder.o myheader.o
