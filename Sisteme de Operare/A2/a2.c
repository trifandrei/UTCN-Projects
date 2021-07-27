#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "a2_helper.h"
#include <pthread.h>
#include <semaphore.h>

int ok=2;
int ok2=2;
int ok3=2;
int ok4;
sem_t mutex;
void *thread_functionP21(void *var)
{
 	while(1)
  	{
	    if(ok == 1) 
     	 break;
  	}
  
   info(BEGIN,2,1);
  
    info(END,2,1);
	ok=2;

  pthread_exit(NULL);
}

void *thread_functionP22(void *var){
   
     
 	while(1)
  	{
	    if(ok3 == 2) 
     	 break;
  	}
	
    info(BEGIN,2,2);
   
    info(END,2,2);
    ok4=1;
  pthread_exit(NULL);
}
void *thread_functionP23(void *var){

     info(BEGIN,2,3);
     ok=1;
    while(1)
     {
       if(ok == 2)	 
         break;
     } 
    info(END,2,3);

  pthread_exit(NULL);
}
void *thread_functionP24(void *var){
  
    info(BEGIN,2,4);
   
    info(END,2,4);

  pthread_exit(NULL);
}
void *thread_function(void *var){
	    
    int tid=*((int *)var);
    sem_wait(&mutex);
  
    info(BEGIN,5,tid);
   
       info(END,5,tid);
    sem_post(&mutex);
  
  pthread_exit(NULL);
}
void *thread_functionT6(void *var)
{
		    
    int tid=*((int *)var);
 	
  
   info(BEGIN,8,tid);
  
    info(END,8,tid);
   ok3=2;

  pthread_exit(NULL);
}
void *thread_functionT5(void *var){

     	    
     int tid=*((int *)var);
     info(BEGIN,8,tid);
     
   
    info(END,8,tid);

  pthread_exit(NULL);
}
void *thread_function1(void *var){
  int tid=*((int *)var);
    info(BEGIN,8,tid);
   
    info(END,8,tid);

  pthread_exit(NULL);
}


int main(){
    init();
    	
	pid_t pid2; pid_t pid3; pid_t pid4; pid_t pid5; pid_t pid6; pid_t pid7; pid_t pid8; 
	pthread_t th[4];
	pthread_t th2[35];
	pthread_t th3[6];
	int id[35];
	int i;
    info(BEGIN, 1, 0);
     
   	
     pid2=fork();
     wait(NULL);


  	

     if(pid2==0)
       {
	info(BEGIN,2,0);
	
	 pid4=fork();
 	 wait(NULL);
	
    	 if(pid4==0)
	   {
	    info(BEGIN,4,0);
            pid6=fork();
	    wait(NULL);

	    if(pid6==0)
	      {
		info(BEGIN,6,0);	
		info(END,6,0);	
		exit(1);
	      }
	    pid7=fork();
	    wait(NULL);

	    if(pid7==0)
	      {
	 	info(BEGIN,7,0);
		pid8=fork();
		wait(NULL);

		if(pid8==0)
		  {
		    info(BEGIN,8,0);
		     for( i=0;i<6;i++){
		       id[i]=i+1;
			 if(i==5)
		         pthread_create(&(th3[i]), NULL, thread_functionT6,(void *)&id[i] );
		 	else
		      	if(i==4 )
	          	 pthread_create(&(th3[i]), NULL, thread_functionT5,(void *)&id[i] );
			else
			 pthread_create(&(th3[i]), NULL, thread_function1,(void *)&id[i] );
	          }
		 			
		  for( i=0;i<6;i++){
	
	            pthread_join(th3[i], NULL); 
	          }
		    info(END,8,0);
		    exit(1);	
		  }
	         info(END,7,0);
		 exit(1);	 
		}
	      info(END,4,0);
	      exit(1);
	    }
	
	    pthread_create(&(th[0]), NULL, thread_functionP21,NULL);
  	    pthread_create(&(th[1]), NULL, thread_functionP22,NULL);
  	    pthread_create(&(th[2]), NULL, thread_functionP23,NULL);
	    pthread_create(&(th[3]), NULL, thread_functionP24,NULL);	
	

	for( i=0;i<4;i++){
	
	  pthread_join(th[i], NULL); 
	}
	
	 info(END,2,0);
	 exit(1);
	}
	else
	{
      	  pid3=fork();
       	  wait(NULL);
	  if(pid3==0)
	    {
              info(BEGIN,3,0);
     	      pid5=fork();
              wait(NULL);
    	      if(pid5==0)
	        {
	          info(BEGIN,5,0);
		  sem_init(&mutex,0,4);
	       	  for( i=0;i<35;i++){
		    id[i]=i+1;
		    /* if(i==12)
		     pthread_create(&(th2[i]), NULL, thread_functionP13,(void *)&id[i] );
		 	else
		      if(i==13  )
	          	 pthread_create(&(th2[i]), NULL, thread_functionP4,(void *)&id[i] );
			else*/
			 pthread_create(&(th2[i]), NULL, thread_function,(void *)&id[i] );
	          }
		 			
		  for( i=0;i<35;i++){
	
	            pthread_join(th2[i], NULL); 
	          }
		 sem_destroy(&mutex);
	          info(END,5,0);	
	          exit(1);
	        }
	       info(END,3,0);
	       info(END,1,0);
	    }
     	}

    
    return 0;
}
