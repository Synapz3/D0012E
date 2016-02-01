// Lab2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>

using namespace std;

int findPos(int adress_we_want, int probearray[], const int arraySize) {
	int i = 0;
	//int arraySize = sizeof(probearray);
	while (i < arraySize) {
		if (probearray[(adress_we_want + i) % arraySize] ==NULL) {
			return ((adress_we_want + i) % arraySize);
		}
		else if (probearray[(adress_we_want - i) % arraySize] == NULL){
			return ((adress_we_want - i) % arraySize);
		}
		i++;
	}
	return -1;
}

bool keyFix(int adress_we_want, int pos, int probearray[], int element, int offset, int arraySize) {
	//int arraySize = sizeof(probearray);
	for (int i = 0; i < offset; i++) {
		int possition = (adress_we_want + i) % arraySize;
		int possition2 = (adress_we_want - i) % arraySize;
		if (probearray[possition] != NULL) {
			int temp = probearray[possition];
			probearray[possition] = element;
			probearray[pos] = temp;
			return true;
		}
		else if (probearray[possition2] != NULL) {
			int temp = probearray[possition2];
			probearray[possition2] = element;
			probearray[pos] = temp;
			return true;
		}
	}
	return false;
}

void run(int poss, int probearray[], int element, const int arraySize) {
	int empty = findPos(poss, probearray, arraySize);
	probearray[empty] = element;
}

void run2(int adress_we_want, int offset, int probearray[], int element, int arraySize) {
	int pos = findPos(adress_we_want, probearray, arraySize);
	if (pos == -1) {
		return;
	} else if (abs(pos - adress_we_want) <= offset) {
		probearray[pos] = element;
	} else {
		bool fixd = keyFix(adress_we_want, pos, probearray, element, offset, arraySize);
		if (fixd) {
			//const int sizeOfArray = arraySize;
			//const int temp = arraySize;
			int newArray[10];
			for (int i = 0; i < arraySize; i++) {
				run(adress_we_want, newArray, probearray[i], arraySize);
			}
			probearray = newArray;
			run2(adress_we_want, offset, probearray, element, arraySize);
		}
	}
}

void print(int array[], int arraySize) {
	//int size = sizeof(array);
	for (int i = 0; i < arraySize; i++) {
		cout << array[i] << endl;
	}
}

int main()
{
	const int arraySize = 10;
	int element = 5;
	int poss = 5;
	int probearray[arraySize] = NULL;
	int offset = 3;


	run(poss, probearray, element, arraySize);
	//run2(poss, offset, probearray, element);

	print(probearray, arraySize);

	//cout << "test" << endl;
	system("pause");
	return 0;
}

/*
x = position we want to use
i = offset from x
m = array size (mod m)
element = what will be inserted
probearray = pointer ro the beginning of the array

*/


