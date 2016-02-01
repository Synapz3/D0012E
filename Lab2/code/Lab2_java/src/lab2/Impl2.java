package lab2;

public class Impl2 {
	public int arr_size = 128; 
	public impl2_element[] element_arr = new impl2_element[128];
	public int c = 5;
	public int elements_in_arr = 0;
	public double threshold = 0.5;
	private boolean tried_insert_once = false;
	
	public int hash_times = 0;
	public int collision_occured = 0;
	public int fail_count = 0;
	
	
	public int hash(int elem){
		return (int)((((elem * Math.pow(Math.PI, 5))*17 % 5)%91)*12050327 % arr_size);
		//return (int)(Math.pow((double)3, (double)elem) % arr_size);
	}
	int find_empty_linear(int the_hash){
		for(int i=1; i<arr_size;i++){
			if(element_arr[(the_hash + i) % arr_size] == null){
				//Yay! found an empty spot
				return (the_hash + i) % arr_size;
			}
		}
		//No empty spot was found
		return -1;
	}
	int find_element(int the_hash,int j){
		for(int i=0; i<c;i++){
			if( Math.abs(((the_hash + i) % arr_size) - the_hash) < c){
				if(Math.abs(j - hash(element_arr[((the_hash + i) % arr_size)].key)) < c){
					return (the_hash + i) % arr_size;
				}
			}
		}
		return -1;
	}
	public boolean insert_element(int elem) {
		if(elements_in_arr / arr_size > threshold){
			rehash(arr_size * 2);
		}
		int pos = hash(elem);
		int j;
		int j_prime;
		if(element_arr[pos] == null){
			element_arr[pos] = new impl2_element();
			element_arr[pos].key = elem;
			return true;
		}
		else{
			collision_occured += 1;
			j = find_empty_linear(pos);
			if(j == -1){
				return false;
				//This means that the array is full!!
			}
			else if(Math.abs(j - pos) < c){
				//Was j in range?
				element_arr[j] = new impl2_element();
				element_arr[j].key = elem;
				return true;
			}
			else{
				//if it wasn't
				j_prime = find_element(pos,j);
				if(j_prime == -1){
					//If this is our second try, give up
					if(tried_insert_once){
						fail_count += 1;
						return false;
					}
					rehash(arr_size);
					tried_insert_once = true;
					//If we didn't find a spot we try again after the rehash
					boolean re = insert_element(elem);
					//If we didn't find a spot after the second try, we give up
					tried_insert_once = false;
					return re;

				}
				else{
					//Insert and switches location of the elements
					int tmp_elem = element_arr[j_prime].key;
					element_arr[j_prime].key = elem;
					element_arr[j] = new impl2_element();
					element_arr[j].key = tmp_elem;
					return true;
				}
				
			}
		}
	}
	void rehash(int capacity){
		hash_times += 1;
		impl2_element[] tmp  = element_arr;
		int tmp_arr_size = arr_size;
		element_arr = new impl2_element[capacity];
		arr_size = capacity;
		elements_in_arr = 0;
		for(int i=0;i<tmp_arr_size;i++){
			if(tmp[i] != null){
				insert_element(tmp[i].key);
			}
		}
		
	}

}
class impl2_element{
	int key = 0;
}