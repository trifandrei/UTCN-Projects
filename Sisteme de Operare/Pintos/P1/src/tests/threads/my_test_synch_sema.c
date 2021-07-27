#include <stdio.h>
#include "tests/threads/tests.h"
#include "threads/init.h"
#include "threads/malloc.h"
#include "threads/synch.h"
#include "threads/thread.h"
#include "devices/timer.h"



void sem_function(void *aux)
{
	struct semaphore *sema = (struct semaphore *) aux;

	printf("Initializing thread: %d\n", thread_current()->tid);
	sema_down(sema);
	printf("Running thread: %d\n", thread_current()->tid);
	sema_up(sema);
	printf("Ending thread: %d\n", thread_current()->tid);

}

void my_test_synch_sema(void)
{
	printf("START\n");

	struct semaphore sema;
	sema_init_name(&sema, 1, "init_name1");
	for(int i = 0; i < 10; i++){
		thread_create("th1", PRI_DEFAULT, sem_function, &sema);
	}
	timer_sleep(5000);
}