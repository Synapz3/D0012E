package lab2;
import java.util.*;
public class Main {
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int max = 1000000000;
		int min = 0;
		int arr_size = 10000;
		int[] numb_arr = new int[arr_size];
		Random rand = new Random();
		for(int i=0;i<arr_size;i++){
			numb_arr[i] = rand.nextInt((max - min) + 1) + min;
		}
		
		
		
		run_impl2(numb_arr,10000);
		System.out.println("-----------------------");
		/*run_impl2(numb_arr,100);
		System.out.println("-----------------------");
		run_impl2(numb_arr,1000);
		System.out.println("-----------------------");
		run_impl2(numb_arr,10000);
		System.out.println("-----------------------");
		run_impl2(numb_arr,100000);
		System.out.println("-----------------------");
		run_impl2(numb_arr,1000000);
		/*System.out.println("-----------------------");
		run_impl1(numb_arr,arr_size);
		System.out.println("-----------------------");
		run_impl1(numb_arr,arr_size);
		System.out.println("-----------------------");
		run_impl1(numb_arr,arr_size);
		System.out.println("-----------------------");
		run_impl1(numb_arr,arr_size);
		System.out.println("-----------------------");
		run_impl1(numb_arr,arr_size);
		System.out.println("-----------------------");
		run_impl1(numb_arr,arr_size);
		System.out.println("-----------------------");
		run_impl1(numb_arr,arr_size);*/
		//run_impl2(numb_arr,arr_size);
	}
	static void run_impl1(int[] rand_arr,int size){
		//OBS CHECK THE ERROR, THE ARRAY ISN't expanding (Because of weird threshold value)
		
		
		Impl1 k = new Impl1();
		k.arr_size = size;
		k.element_arr = new element[size];
		k.threshold = 0.8;
		
		int probes = 0;
		
		System.out.println("Running.....");
		long start_time = System.currentTimeMillis();
		for(int i=0;i<rand_arr.length;i++){
			if(!k.insert_element(rand_arr[i])){
				probes = i;
				break;
			}
		}
		long stop_time  = System.currentTimeMillis();
		if(probes == 0){
			System.out.println("Probes (inserts): "+rand_arr.length);
		}
		else{
			System.out.println("Probes (inserts): "+probes);
		}
		System.out.println("Runtime: "+(stop_time-start_time)+" ms");
		System.out.println("HashTable size: "+k.arr_size);
		System.out.println("max_collision_chain: "+k.max_collision_chain);
		System.out.println("insertion_collisions: "+k.collision_occured);
	}
	static void run_impl2(int [] rand_arr, int size){
		Impl2 k = new Impl2();
		k.arr_size = size;
		k.element_arr = new impl2_element[size];
		k.threshold = 0.8;
		k.c = 128;
		
		int probes = 0;
		
		//2^element % size = 
		System.out.println("Running.....");
		long start_time = System.currentTimeMillis();
		for(int i=0;i<rand_arr.length;i++){
			k.insert_element(rand_arr[i]);
			if(k.elements_in_arr == size){
				probes = i;
				break;
			}
		}
		long stop_time  = System.currentTimeMillis();
		if(probes == 0){
			System.out.println("probes: "+rand_arr.length);
		}
		else{
			System.out.println("probes"+probes);
		}
		System.out.println("Runtime: "+(stop_time-start_time)+" ms");
		System.out.println("HashTable size: "+k.arr_size);
		System.out.println("c: "+k.c);
		System.out.println("max_collision_chain: "+k.c +" (irrelevant)");
		System.out.println("insertion_collisions: "+k.collision_occured);
		System.out.println("rehashes made: "+k.hash_times);
		System.out.println("Insertions failed: "+k.fail_count);
		
		
		
	}
	static void print_func(Impl2 k){
		for(int i=0; i<k.arr_size;i++){
			if(k.element_arr[i] != null)
				System.out.println(k.hash(k.element_arr[i].key) +" : "+ k.element_arr[i].key);
			else
				System.out.println("null");
		}
	}
}
class element{
	int key = 0;
	boolean init = false;
	int L_up   = 0;
	int L_down = 0;
}
class CsvFileWriter {
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
		
	//CSV file header
	private static final String FILE_HEADER = "Hashtable_size,rand_arr,probes(inserts),runtime,max_collision_chain,insertion_collisions";
}