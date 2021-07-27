#include <stdio.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
typedef struct head {
	char magic[2];
	int heder_size;
	int version;
	int no_section;
}Heder;

char* tokan(char* strings)
{
   int i=0;
   char *token[2];

   token[i]=strtok(strings,"=");
   while( token[i] != NULL ){
     i++;
     token[i] = strtok(NULL, "=");
   }

  return token[1];
}
int sizeGreat(char *value,const char *path)
{

    int fd;
    off_t size;
    int val;

    val=atoi(value);

    fd = open(path, O_RDONLY);
    if(fd == -1) {
	printf("ERROR\n");
        perror("Could not open input file in function sizeGreat");
        exit(1);
    }
	
    size = lseek(fd, 0, SEEK_END);
  
	if (size>val)
	  return 1;
		else
	  return 0;

    close(fd);
}

void listDir(const char *path)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];
    struct stat statbuf;

    dir = opendir(path);
    if(dir == NULL) {
	printf("ERROR\n");
        perror("Could not open directory");
        return;
    }
    while((entry = readdir(dir)) != NULL) {
        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
            snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
            if(lstat(fullPath, &statbuf) == 0) {
                printf("%s\n", fullPath);
            }
        }
    }
    closedir(dir);
}
void listDirSize(char*valu,const char *path)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];   
    struct stat statbuf;

    dir = opendir(path);
    if(dir == NULL) {
	printf("ERROR\n");
        perror("Could not open directory");
        return;
    }
    while((entry = readdir(dir)) != NULL) {
        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
            snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
	    if(lstat(fullPath, &statbuf) == 0) 
            if (sizeGreat(valu,fullPath)>0 && !(S_ISDIR(statbuf.st_mode))){
		  printf("%s \n",fullPath);
            }
        }
    }
    closedir(dir);
}

void listRec(const char *path)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];
    struct stat statbuf;	
	
    dir = opendir(path);
    if(dir == NULL) {
	printf("ERROR\n");
        perror("Could not open directory");
        return;
    }
   	
    while((entry = readdir(dir)) != NULL) {
        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
            snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
            if(lstat(fullPath, &statbuf) == 0) {
               	printf("%s \n", fullPath);
                if(S_ISDIR(statbuf.st_mode)){
                    listRec(fullPath);
                }
            }
        }
    }
    closedir(dir);
}
void listDirName(char * val,const char *path)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];

    dir = opendir(path);
    if(dir == NULL) {
	printf("ERROR\n");
        perror("Could not open directory");
        return;
    }
    while((entry = readdir(dir)) != NULL) {
        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
            snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
            if(strstr(entry->d_name,val)!=NULL)
            	 printf("%s \n",fullPath);
        }
    }
    closedir(dir);
}
void listRecName(char *valu,const char *path)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];
    struct stat statbuf;	

    dir = opendir(path);
    if(dir == NULL) {
	printf("ERROR\n");
        perror("Could not open directory");
        return;
    }
   	
    while((entry = readdir(dir)) != NULL) {
        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
            snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
	    	if(strstr(entry->d_name,valu)!=NULL)
            	 printf("%s \n",fullPath);
	  
                if(lstat(fullPath, &statbuf) == 0) 
                if(S_ISDIR(statbuf.st_mode)){
                    listRecName(valu,fullPath);
                
            }
            
        }
    }
    closedir(dir);
}
void listRecSize(char *valu,const char *path)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];
    struct stat statbuf;	
	
    dir = opendir(path);
    if(dir == NULL) {
	printf("ERROR\n");
        perror("Could not open directory");
        return;
    }
   	
    while((entry = readdir(dir)) != NULL) {
        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
            snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
	     
	    if(lstat(fullPath, &statbuf) == 0) 
            if (sizeGreat(valu,fullPath)>0 && !(S_ISDIR(statbuf.st_mode))){
		  printf("%s \n",fullPath);
	    }
                if(lstat(fullPath, &statbuf) == 0) 
                if(S_ISDIR(statbuf.st_mode)){
                    listRecSize(valu,fullPath);
                
            }
            
        }
    }
    closedir(dir);
}

void parse(const char* path){
 	Heder*p;
	int fd1 = -1;
   	ssize_t size=0;
	int i=0;
	char sec_name[13]; 
	int sec_type;
	int sec_offset;
	int sec_size;
	
    	fd1 = open(path, O_RDONLY);
    	if(fd1 == -1) {
      	  perror("Could not open input file");
          return ;
    	}
	p=(Heder *)malloc(sizeof(Heder));

        size=read(fd1,&p->magic, 2);
	if(size < 0) {
	   perror("Could not read from input file");
	   free(p);
	   exit(1);
	}

	 if(strcmp(p->magic,"wv")!=0){
	   printf("ERROR\n");
	   printf("wrong magic\n");
	   free(p);
	   exit(1);
	}
	
	size=read(fd1,&p->heder_size, 2);
	if(size < 0) {
	   perror("Could not read from input file");
           free(p);
	   exit(1);
	}
	size=read(fd1,&p->version, 1);
	if(size < 0) {
	   perror("Could not read from input file");
	   free(p);
	   exit(1);
	}
	
	 if(!(p->version>=20 && p->version<=131)){
	   printf("ERROR\n");
	   printf("wrong version\n");
           free(p);
 	   exit(1);
	}
	size=read(fd1,&p->no_section, 1);
	if(size < 0) {
	   perror("Could not read from input file");
	   free(p);
	   exit(1);
	}
	
	 if(!(p->no_section>=4 && p->no_section<=10)){
	   printf("ERROR\n");
	   printf("wrong sect_nr\n");
	   free(p);
	   exit(1);
	}
	for(i=0;i<p->no_section;i++){
		size=read(fd1,&sec_name,13);	
		size=read(fd1,&sec_type,4);
		if((sec_type!=12) && (sec_type!=37) && (sec_type!=60) && (sec_type!=68) && (sec_type!=79) && (sec_type!=99)){
			printf("ERROR\n");
	   		printf("wrong sect_types\n");
		       	free(p); 
		        exit(1);		
		}
		size=read(fd1,&sec_offset,4);
		size=read(fd1,&sec_size,4);
		
	}
	size = lseek(fd1, 6, SEEK_SET);
	printf("SUCCESS\n");
	printf("version=%d\n",p->version);
	printf("nr_sections=%d\n",p->no_section);
	for(i=0;i<p->no_section;i++){
		size=read(fd1,&sec_name,13);	
		size=read(fd1,&sec_type,4);
		size=read(fd1,&sec_offset,4);
		size=read(fd1,&sec_size,4);
		printf("section%d: %s %d %d\n",i+1,sec_name,sec_type,sec_size);
		
	}
	free(p);
}

int main(int argc, char **argv){
char *tok;
char *tok2;

   if(argc>=2){
	if(strcmp(argv[1], "variant") == 0)
            printf("61612\n");	
	else
	if(strcmp(argv[1], "list") == 0 && strncmp(argv[2], "p", 1)== 0){
		tok=tokan(argv[2]);
		printf("SUCCESS\n");
		listDir(tok);
	}
	else
	if(strcmp(argv[1], "list") == 0 && strncmp(argv[2], "s", 1)== 0 && strncmp(argv[3], "p", 1)== 0){
		tok=tokan(argv[3]);
		tok2=tokan(argv[2]);
		printf("SUCCESS\n");
		listDirSize(tok2,tok);
	}
	else
	if(strncmp(argv[1], "l", 1)== 0 && strncmp(argv[2], "r", 1)== 0 && strncmp(argv[3], "p", 1)== 0){
		tok=tokan(argv[3]);
		printf("SUCCESS\n");
		listRec(tok);
	  }
	 
	   else 
	   if(strncmp(argv[2], "s", 1)== 0 && strncmp(argv[3], "r", 1)== 0){
		tok=tokan(argv[4]);
		tok2=tokan(argv[2]);
		printf("SUCCESS\n");
		listRecSize(tok2,tok);
	  }
	   else
	    if(strncmp(argv[2], "r", 1)== 0 && strncmp(argv[3], "s", 1)== 0){
		tok=tokan(argv[4]);
		tok2=tokan(argv[3]);
		printf("SUCCESS\n");
		listRecSize(tok2,tok);
	  }
	else
	  if(strncmp(argv[2], "n", 1)==0 && strncmp(argv[3],"r",1)==0){
		tok=tokan(argv[2]);
		tok2=tokan(argv[4]);
		printf("SUCCESS\n");
		listRecName(tok,tok2);
	
	}
	else
	   if(strncmp(argv[2], "r", 1)==0 && strncmp(argv[3],"n",1)==0){
		tok=tokan(argv[3]);
		tok2=tokan(argv[4]);
		printf("SUCCESS\n");
		listRecName(tok,tok2);
	
	}
	else
	   if(strncmp(argv[1], "l", 1)==0 && strncmp(argv[2],"n",1)==0){
		tok=tokan(argv[2]);
		tok2=tokan(argv[3]);
		printf("SUCCESS\n");
		listDirName(tok,tok2);
	
	}
	else 
	  if(strncmp(argv[1],"parse",5)==0){
		tok=tokan(argv[2]);
		parse(tok);						
	}
	else
	 if(strncmp(argv[2],"parse",5)==0){
		tok=tokan(argv[1]);
		parse(tok);						
	}
}
   	 return 0;

}
