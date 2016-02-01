/**
 * Created by Samuel on 2015-12-23.
 */
import graph.*;

import java.util.ArrayList;

public class DijkstrArr {
    private Object[][] memory;
    //0,0= The cost to reach the node (sum of weights)
    //0,1= heap index
    //0,2= added

    private int size;
    private int d;
    private ArrayList<Node> heap;
    private int heapSize;

    /**
     * Search first the closest path between a and b
     * @param size Number of nodes
     * @param a Start node
     * @param b End node
     * @param d Max number of connections
     */
    public DijkstrArr(int size, Node a, Node b, int d){
        //set up






        memory = new Object[size][3];


        this.size = size;

        for (int i = 0; i < size; i++){
            memory[i][2] = false;
        }

        //add the first node
        memory[a.id][2] = true;
        memory[a.id][0] = 0;
        memory[a.id][0] = 0;
        this.d = d;


        heap = new ArrayList<Node>();      //set a start size for the heap
        heapSize = size;
        heap.add(a);
        addKid(a);                  //start adding kids


        //loop from a to find b
        int temp = 0;

        for (int i = 0; i <= size; i++) {
            temp = min();
            if (memory[temp][1] == null) {
                System.out.println("error null pointer");
                return;
            } else if (heap.get((int) memory[temp][1]) == b) {
                //print(temp);
                return;
            }

            addKid(heap.get((int)memory[temp][1]));

        }

    }



    /**
     * add all the connection ids that is not connected, search all connections the node has
     * @param parent the parent nod
     */
    private void addKid(Node parent){

        ArrayList<Edge> edge = parent.getEdges();           //get the connections
        for (int i = 0; i < edge.size(); i++){
            Node kid; //It's a child, okay!
            if (edge.get(i).connections[0] == parent){      //find the connection that lead to an new node
                kid = edge.get(i).connections[1];
            } else{
                kid = edge.get(i).connections[0];
            }
            if ((boolean) memory[kid.id][2] == false) {         //check if added



                heap.add(kid);
                int temp = (int) memory[parent.id][0] + edge.get(i).weight;     //calculate the weight to get to new node

                if (memory[kid.id][0] == null || (int) memory[kid.id][0] > temp) {      //add info to the memory array
                    memory[kid.id][0] = temp;
                    memory[kid.id][1] = heap.size()-1;
                }
            }
        }
    }


    /**
     * find the minmal vaited node
     * @return id to the smolest to node
     */
    private int min(){
        int min = 0;
        while (memory[min][0] == null || (boolean)memory[min][2]){
            min++;
            if (min >= size){
                return min-1;
            }
        }
        for (int i = min + 1; i < size; i++){
            if (memory[i][0] != null) {
                if ((int) memory[i][0] < (int) memory[min][0]){
                    if ((boolean) memory[i][2] == false) {
                        min = i;
                    }
                }

            }
        }
        memory[min][2] = true;
        return min;
    }

    private void print(int dest){

        System.out.print(memory[dest][0]);
    }

}
