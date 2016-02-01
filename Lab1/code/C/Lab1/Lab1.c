#include <stdio.h>
#include <sys/time.h>
#include <pthread.h>
#include <unistd.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>

void insertionsort(int* l, int count) {
	int i;
	for(i = 0; i<count; i++) {
		int ins_el = *(l + i);
		int j = i - 1;
		while(j >= 0 && *(l+j) > ins_el) {
			*(l+j+1) = *(l+j);
			j = j - 1;
		}

		*(l+j+1) = ins_el;
	}
}

int binary_search(int* l, int value, int start, int end) {
	while(start != end) {
		if(start > end) return start;

		int middle = (int)((start+end)/2);
		if(*(l+middle) > value) end = middle - 1;
		else if (*(l+middle) < value) start = middle + 1;
		else return middle;
	}

	if(*(l+start) > value) return start;
	else return start + 1;
}

void binsertionsort(int* l, int count) {
	int i;
	for(i = 1; i<count; i++) {
		int ins_el = *(l+i);
		int start = 0;
		int end = i - 1;

		int insert_index = binary_search(l, ins_el, start, end);
		for(; end>=insert_index; end--) {
			*(l+end+1) = *(l+end);
		}

		*(l+insert_index) = ins_el;
	}
}

void mergesort(int* l, int count, int k, void (*algo)(int*,int)) {
	if(count <= k) {
		(*algo)(l, count);
	} else {
		int middle = count / 2;
		int* left = (int*)malloc(sizeof(int)*middle);
		int* right = (int*)malloc(sizeof(int)*(count-middle));
		int i;
		for(i = 0; i<middle; i++) {
			*(left+i) = *(l+i);
		}
		for(i = 0; i<(count-middle); i++) {
			*(right+i) = *(l+middle+i);
		}

		mergesort(left, middle, k, algo);
		mergesort(right, count-middle, k, algo);

		i = 0; int j = 0, k = 0, lel = middle, ril = count-middle;

		while(i < lel && j < ril) {
			if(*(left+i) < *(right+j)) {
				*(l+k) = *(left+i++);
			} else {
				*(l+k) = *(right+j++);
			}

			k++;
		}
		while(i < lel) {
			*(l+k++) = *(left+i++);
		}
		while(j < ril) {
			*(l+k++) = *(right+j++);
		}

		free(left);
		free(right);
	}

}


int* random_array(int size, int highest) {
	int* arr = (int*)malloc(sizeof(int)*size);
	int i;
	for(i = 0; i<size; i++) {
		*(arr+i) = rand() % highest;
	}

	return arr;
}

unsigned long test(int* l, int size, int k, void (*algo)(int*, int)) {

	struct timeval start, stop;
	gettimeofday(&start, NULL);
	mergesort(l, size, k, algo);
	gettimeofday(&stop, NULL);
	return ((stop.tv_sec - start.tv_sec) * 1000000 + stop.tv_usec - start.tv_usec) / 1000;
}

typedef struct test_t {
	int k;
	int ins_t;
	int bins_t;
} test_s;

typedef struct thread_arg_t {
	int i;
	test_s* store;
	int* arr;
	int* copy;
	int max_k;
	int n_cores;
	int jump_size;
	int size;
} thread_arg_s;

void sort_stuff(void* arrgs) {
	thread_arg_s* args = (thread_arg_s*)arrgs;
	int j,i;
	for(j = 1, i = 0; j<=args->max_k; j += args->n_cores*args->jump_size, i += args->n_cores) {
		int k =  j + args->jump_size*args->i;
		int whr = (i + args->i);

		test_s tst;
		tst.k = k;

		memcpy(args->copy, args->arr, sizeof(int)*args->size);
		tst.ins_t = test(args->copy, args->size, k, &insertionsort);
		memcpy(args->copy, args->arr, sizeof(int)*args->size);
		tst.bins_t = test(args->copy, args->size, k, &binsertionsort);

		printf("k: %d, ins_t: %d, bins_t: %d, thread: %d\n", tst.k, tst.ins_t, tst.bins_t, args->i);
		*(args->store + whr) = tst;
	}

	free(args->copy);
	free(args);
}

int main(int argc, char** argv) {

	int size = 1000000;
	int max_k = 100000;
	int jump_size = 500;
	int* arr = random_array(size, 10000000);
	test_s* tests = (test_s*)malloc(sizeof(test_s)*size/jump_size);

	int n_cores = sysconf(_SC_NPROCESSORS_ONLN);
	pthread_t pth[16];
	int pth_ret[16];

	int j;
	for(int j = 0; j<n_cores; j++) {
		thread_arg_s* args = (thread_arg_s*)malloc(sizeof(thread_arg_s));
		args->i = j;
		args->arr = arr;
		args->store = tests;
		args->max_k = max_k;
		args->n_cores = n_cores;
		args->jump_size = jump_size;
		args->size = size;
		args->copy = random_array(size, 10000000);
		pth_ret[j] = pthread_create(&pth[j], NULL, (void*)sort_stuff, (void*)args);
	}

	for(j = 0; j<n_cores; j++) {
		pthread_join(pth[j], NULL);
	}

	char buffer[50];
	sprintf(buffer, "tests-%d-%d-%d.csv", size, max_k, jump_size);
	FILE* fp = fopen(buffer, "w+");
	for(j = 0; j<size/jump_size; j++) {
		test_s tst = *(tests+j);
		fprintf(fp, "%d,%d,%d\n", tst.k, tst.ins_t, tst.bins_t);
	}

	fclose(fp);
	free(tests);
	free(arr);
}
