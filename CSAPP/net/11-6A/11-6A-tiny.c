/* $begin tinymain */
/*
 * tiny.c - A simple, iterative HTTP/1.0 Web server that uses the 
 *     GET method to serve static and dynamic content.
 */
#include "myheader.h"

void echo(int fd);

int main(int argc, char **argv) 
{
    int listenfd, connfd;
    char hostname[MAXLINE], port[MAXLINE];
    socklen_t clientlen;
    struct sockaddr_storage clientaddr;

    /* Check command line args */
    if (argc != 2) {
	fprintf(stderr, "usage: %s <port>\n", argv[0]);
	exit(1);
    }

    int portNum = atoi(argv[1]); 
    listenfd = Open_listenfd(portNum);
    while (1) {
	clientlen = sizeof(clientaddr);
	connfd = Accept(listenfd, (SA *)&clientaddr, &clientlen);
	echo(connfd);  
	Close(connfd);
    }
}


/*
 * doit - handle one HTTP request/response transaction
 */

void echo(int fd) 
{
    char buf[MAXLINE], temp[MAXLINE];
    int filesize = 0; 
    rio_t rio;

    /* Read request line and headers */
    Rio_readinitb(&rio, fd);
    Rio_readlineb(&rio, temp, MAXLINE); 
    strcat(buf, temp);
    while(strcmp(temp, "\r\n"))
    {
        Rio_readlineb(&rio, temp, MAXLINE); 
        strcat(buf, temp);
    }

    filesize = strlen(buf); 
    sprintf(temp, "HTTP/1.0 200 OK\r\n");  
    sprintf(temp, "%sServer: Tiny Web Server\r\n", temp);
    sprintf(temp, "%sConnection: close\r\n", temp);
    sprintf(temp, "%sContent-length: %d\r\n", temp, filesize);
    sprintf(temp, "%sContent-type: text/plain\r\n\r\n", temp);
    Rio_writen(fd, temp, strlen(temp)); 
    Rio_writen(fd, buf, strlen(buf)); 
    
    printf("%s", temp); 
    printf("%s", buf); 

    return;
}
