package main;

import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] number = createArr(1000);
		
	}
	static int[] createArr(int size){
		Random rand = new Random();
		int[] arr = new int[size];
		for (int i=0;i<size; i++){
			arr[i] = rand.nextInt(((100-0)+1) + 0);
		}
		return arr;
	}
	//Returns a sorted vecotr of the size arrIn.length
	static Vector<Integer> insertionsort(int[] arrIn){
		Vector<Integer> Sorted = new Vector<Integer>(arrIn.length);
		//For each card in the deck
		for(int i=0;i<arrIn.length;i++){
			//For each card in the sorted deck
			for(int k=0;k<Sorted.capacity();k++){
				if(Sorted.elementAt(k) == null){
					Sorted.insertElementAt(arrIn[i], k);
				}
				else if(arrIn[i] < Sorted.elementAt(k)){
					Sorted.insertElementAt(arrIn[i], k);
				}
			}
		}
		return Sorted;
	}
	static Vector<Integer> binsertionsort(int[] arrIn){
		Vector<Integer> Sorted = new Vector<Integer>(arrIn.length);
		//For each card in the deck
		for(int i=0;i<arrIn.length;i++){
			if(Sorted.size() == 0){
				Sorted.insertElementAt(arrIn[i], 0);
				continue;
			}
			int middleIndex = (arrIn.length-1)/2;
			while(true){
				if(Sorted.elementAt(middleIndex) >)
			}
			
			
			
			
			
			
			
			for(int k=0;k<Sorted.capacity();k++){
				if(Sorted.elementAt(k) == null){
					Sorted.insertElementAt(arrIn[i], k);
				}
				else if(arrIn[i] < Sorted.elementAt(k)){
					Sorted.insertElementAt(arrIn[i], k);
				}
			}
		}
		return Sorted;
	}

	static Vector<Integer> binInsert(int add, Vector<Integer> sorted){
		if (sorted.lenth == 1){
			if (add <= sorted.elementAt(0)){
				return sorted.insertElementAt(add, 0);
			} else {
				return sorted.insertElementAt(add,0);
			}
		} else {
			binInsert()
		}
	
	}
	
}
