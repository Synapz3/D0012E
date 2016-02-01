package lab2;

public class Impl1 {
	public int arr_size = 128; 
	public element[] element_arr = new element[128];
	public int elements_in_arr = 0;
	public double threshold = 0.5;
	public int max_collision_chain = 0;
	int current_collision_chain = 0;
	public int collision_occured = 0;	
	
	public int hash(int elem){
		//System.out.println((int)(((elem * Math.pow(Math.PI, 5))*17 % 5)%91)*12050327 % arr_size);
		//System.out.println((int)(Math.pow((double)2, (double)11))%arr_size);
		return (int)((((elem * Math.pow(Math.PI, 5))*17 % 5)%91)*12050327 % arr_size);
	}
	
	//FIRST IMPLIMENTATION
	//Look for empty slot downward
	int find_empty_down(int hash) {
		for (int i = 1; i < arr_size; i++) {
			if (element_arr[Math.abs((hash - i)% arr_size)] == null) {
				current_collision_chain = i;
				return Math.abs(hash - i)% arr_size;
			}
		}
		return -1;
	}
	//Look for empty slot upward
	int find_empty_up(int hash) {
			for (int i = 1; i < arr_size; i++) {
				if (element_arr[Math.abs((hash+i) % arr_size)] == null) {
					current_collision_chain = i;
					return (hash+i) % arr_size;
				}
			}
			return -1;
		}
	int find_empty_1(int hash) {
			int re = 0;
			//Choose function
			if (element_arr[hash].L_down < element_arr[hash].L_up) {
				re = find_empty_down(hash);
				collision_occured += 1;
				if(re < -1){
					System.out.println("NOOOOOO_up");
				}
				if (re != -1)
					element_arr[hash].L_down += 1;
			}
			else {
				re = find_empty_up(hash);
				collision_occured += 1;
				if(re < -1){
					System.out.println("NOOOOOO");
				}
				if (re != -1)
					element_arr[hash].L_up += 1;
			}
			if(re != -1){
				if(current_collision_chain > max_collision_chain)
					max_collision_chain = current_collision_chain;
			}
			
			return re;

		}
	void rehash(int capacity){
		element[] tmp  = element_arr;
		int tmp_arr_size = arr_size;
		element_arr = new element[capacity];
		arr_size = capacity;
		elements_in_arr = 0;
		for(int i=0;i<tmp_arr_size;i++){

			if(tmp[i] != null){
				insert_element(tmp[i].key);
			}
		}
		
	}
	//Insert an element with the first implementation
	//OBS! our function will by default use find_empty_up if
	//only one number has been inserted before it on the same home_address  CHECK WITH LAB SUPERVISOR!!

	public boolean insert_element(int elem) {
		/*if((double)elements_in_arr / (double)arr_size > threshold){
			rehash(arr_size * 2);
		}*/
		int pos = hash(elem);
		//Is the possition occupied?
		if (element_arr[pos] != null) {
		//If yes look for a empty slot
			pos = find_empty_1(pos);
			if (pos == -1) {
				//Array full
				return false;
			}
		}
		//Insert the element
		element_arr[pos] = new element();
		element_arr[pos].key = elem;
		element_arr[pos].init = true;
		elements_in_arr += 1;
		
		return true;
	}
	
	
}

