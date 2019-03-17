//#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#define NULL 0

int main (void) {
        if (fork() ==0){ 
		//this creates the child process.
		printf("I am the child with pid %d and my parents has ppid %d. \n", getpid(), getppid());
		//sleep for one second and then exits.
		sleep(1);
		exit(0);

      	}
	
//parent will sleep for 30 seconds. during this time, child is in the zombie state.
printf("I am the parent and my id is %d. \n", getppid());
sleep(30);

}
