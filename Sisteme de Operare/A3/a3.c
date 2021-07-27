#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/mman.h>

#define PIPE_NAME "RESP_PIPE_61612"
#define SHM_KEY 10429
int main(){

   int fd=-1;
   int fd2=-1;
   char s[]={0x07,0x43,0x4f,0x4e,0x4e,0x45,0x43, 0x54};
   char creat[]={0x0a,0x43,0x52,0x45,0x41,0x54,0x45,0x5f,0x53,0x48,0x4d};
   char suc[]={0x07,0x53,0x55,0x43,0x43,0x45,0x53,0x53};
   char err[]={0x05,0x45,0x52,0x52,0x4f,0x52};	
   char creat2[]={0x0c,0x57,0x52,0x49,0x54,0x45,0x5f,0x54,0x4f,0x5f,0x53,0x48,0x4d};
   char creat3[]={0x08,0x4d,0x41,0x50,0x5f,0x46,0x49,0x4c,0x45};
   char r;
   int shmId;
   unsigned int dim;
   char *sharedInt =NULL;
	
	if(mkfifo(PIPE_NAME, 0600) != 0){
		perror("ERROR\n");
		perror("cannot create pipe");
	return 1;
	}

	fd2 = open("REQ_PIPE_61612",O_RDONLY);
	if(fd2 == -1) {
		perror("cannot open the request pipe");
		return 1;
	}

	fd = open(PIPE_NAME, O_WRONLY);
	if(fd == -1) {
		perror("Could not open FIFO");
		return 1;
	}

	
	write(fd, &s, sizeof(s));
	int size;
	char p1[]={0x04,0x50,0x49,0x4e,0x47};
	char p2[]={0x04,0x50,0x4f,0x4e,0x47};
	while(1){

		read(fd2,&r,sizeof(char));
		size=r;

		char req[size+1];
		read(fd2,&req,size);
		req[size]='\0';
		
		if(strcmp(req,"PING")==0){
			write(fd,&p1,sizeof(p1));
			write(fd,&p2,sizeof(p2));	
			unsigned int nr=61612;
			write(fd,&nr,sizeof(nr));
		}

		if(strcmp(req,"CREATE_SHM")==0){
			
			
			read(fd2,&dim,sizeof(unsigned int));

			shmId = shmget(SHM_KEY,dim, IPC_CREAT | 0664);
			if(shmId < 0) {
				write(fd,&creat,sizeof(creat));
				write(fd,&err,sizeof(err));
			}
			else{	
				write(fd,&creat,sizeof(creat));
				write(fd,&suc,sizeof(suc));
			}
		}
		
		if(strcmp(req,"WRITE_TO_SHM")==0){
			unsigned int val;
			unsigned int off;
			read(fd2,&off,sizeof(unsigned int ));
			read(fd2,&val,sizeof(unsigned int));
			
			
			
			if (0<=off && off<=4980088-4){
				sharedInt= (char *)shmat(shmId,NULL, 0);
		
				if(sharedInt == (void*)-1){
					perror("Could not attach to shm");
					return 1;
				}
				sharedInt =sharedInt + off;
				*(unsigned int *)sharedInt=val;
				
				write(fd,&creat2,sizeof(creat2));
				write(fd,&suc,sizeof(suc));
			}else{
				write(fd,&creat2,sizeof(creat2));
				write(fd,&err,sizeof(err));
			}
			
		}
		if(strcmp(req,"MAP_FILE")==0){
	
		int fd3;
		off_t size1;
		char *data = NULL;
		char t;	
		int size3;

			
			read(fd2,&t,sizeof(char));
			size3=t;

			char st[size3+1];
			read(fd2,&st,size3);
			st[size3]='\0';
			printf("%s ",st);
			
			
				
			fd3 = open(st,O_RDONLY);

			if(fd3 == -1) {
				write(fd,&creat3,sizeof(creat3));
				write(fd,&err,sizeof(err));
			}

			size1 = lseek(fd3, 0, SEEK_END);
			lseek(fd3, 0, SEEK_SET);

			data = (char*)mmap(NULL, size1, PROT_READ, MAP_PRIVATE, fd3, 0);
			if(data == (void*)-1) {

				
				close(fd3);
			}
			else
			{
				write(fd,&creat3,sizeof(creat3));
				write(fd,&err,sizeof(suc));
			}
		}
		if(strcmp(req,"READ_FROM_FILE_OFFSET")==0){
			exit(1);
		}
		if(strcmp(req,"READ_FROM_FILE_SECTION")==0){
			exit(1);
		}

		if(strcmp(req,"READ_FROM_LOGICAL_SPACE_OFFSET")==0){
			exit(1);
		}
		
		if(strcmp(req,"EXIT")==0){
			exit(1);
		}


	}
	
	close(fd);
	close(fd2);
return 0;
}
