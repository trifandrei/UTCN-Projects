#include <stdio.h>
#include "tests/threads/tests.h"
#include "threads/init.h"
#include "threads/malloc.h"
#include "threads/synch.h"
#include "threads/thread.h"
#include "devices/timer.h"

void my_function (void *aux)
{
 	print_thread_info(thread_current());
}

void my_test(void)
{
	thread_create("my_thread_0", PRI_DEFAULT, my_function, NULL);
	thread_create("my_thread_1", PRI_DEFAULT, my_function, NULL);
	thread_create("my_thread_2", PRI_DEFAULT, my_function, NULL);
	thread_create("my_thread_3", PRI_DEFAULT, my_function, NULL);
	thread_create("my_thread_4", PRI_DEFAULT, my_function, NULL);
	thread_create("my_thread_5", PRI_DEFAULT, my_function, NULL);
}
