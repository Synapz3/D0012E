
import java.util.Random;
import graph.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Created by Synapz3 on 2015-12-09.
 */
public class lab3 {
    private static Random rand = new Random();
   //code goes here :)
   static File file1;
    static File file2;


    public static void Dijkstr(Graph graph, Node A, Node B, int d) {
        EdgeQueue edq = new EdgeQueue(d);
        int[][] memory = new int[graph.nodes.size()][3];
        //[x][0] = booleab int Added think "c"
        //[x][1] = int tot waith
        //[x][2] = int parent id

        for (int i = 0; i<graph.nodes.size(); i++){
            memory[i][0] = 0;
            memory[i][1] = 0;
            memory[i][2] = -1;
        }
        Node curent = A;

        while (curent != B) {
            //System.out.println("id " + curent.id);
            for (Edge edge : curent.edges) {
                edq.insert(edge, memory[curent.id][1]);
            }
            Kant temp = edq.pop();
            while ((memory[temp.getEdge().connections[1].id][0] == 1) &&
                    (memory[temp.getEdge().connections[0].id][0] == 1)) {
                temp = edq.pop();
            }
            int id = temp.getEdge().connections[1].id;
            if (memory[id][0] == 0) {
                memory[id][0] = 1;
                memory[id][1] = temp.totCost();
                memory[id][2] = curent.id;
                curent = temp.getEdge().connections[1];
            } else {
                id = temp.getEdge().connections[0].id;
                memory[id][0] = 1;
                memory[id][1] = temp.totCost();
                memory[id][2] = curent.id;
                curent = temp.getEdge().connections[0];
            }
        }

       
        return;

        //Find shortest path with help from EdgeQueue. Uses edgeQueue as priority queue.
        //From A:
        /*
        Adds the cildren of A to the queue and sorts the edges by size. Then runs the first edge in the queue in the same way. Runs until B is found.
         */
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        {
            int node = 10000;
            int d = 1000;
            int edge = node;
            int numOfD = edge*4;
            int randomMax = node;


            Random rand = new Random(); //Generate two different random values.
            //int rand1 = rand.nextInt(randomMax);
            //int rand2 = rand.nextInt(randomMax);
            //while (rand1 == rand2) {
             //   rand2 = rand.nextInt(randomMax);
            //}
            int rand1 = 0;
            int rand2 = 9208;


            for (;edge <= numOfD;edge+=1000){
                System.out.println("Building Graph...");
                Graph gr = new Graph(node, d, edge);
                System.out.println("Calculating average with edge: " + edge + " as value ...");
                long temp = 0;
                int numToAverage = 1;
                for (int o = 0; o < numToAverage; o++){
                    long startTime = System.currentTimeMillis();
                    Dijkstr(gr,gr.nodes.get(rand1),gr.nodes.get(rand2), d);
                    //DijkstrArr.Run_DijkstrArr(gr,gr.nodes.size(),gr.nodes.get(rand1),gr.nodes.get(rand2));
                    long testTime = System.currentTimeMillis() - startTime;
                    temp+=testTime;

                }
                if (numToAverage == 0) {
                    long testTimeaverage = temp;
                } else {
                    long testTimeaverage = temp/numToAverage;



                System.out.println("Test time: " + testTimeaverage);


                try {

                    file1 = new File("D "+numOfD+".txt");
                    // if file doesnt exists, then create it
                    if (!file1.exists()) {
                        file1.createNewFile();
                    }

                    // Write text on  txt file.
                    FileWriter fw1 = new FileWriter(file1, true);
                    BufferedWriter bw1 = new BufferedWriter(fw1);
                    bw1.write( edge + " \n");
                    bw1.close();

                    file2 = new File("TIME "+numOfD+".txt");
                    // if file doesnt exists, then create it
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }

                    // Write text on  txt file.
                    FileWriter fw2 = new FileWriter(file2, true);
                    BufferedWriter bw2 = new BufferedWriter(fw2);
                    bw2.write(testTimeaverage + " \n");
                    bw2     .close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }









            /*
            int node = 40000;
            int d = 10;
            int edge = node;
            int numOfD = node*2;
            int randomMax = node;



            Random rand = new Random(); //Generate two different random values.
            int rand1 = rand.nextInt(randomMax);
            int rand2 = rand.nextInt(randomMax);
            while (rand1 == rand2) {
                rand2 = rand.nextInt(randomMax);
            }
            System.out.println("");
            System.out.println("");
            System.out.println("         graph...");
            Graph gr = new Graph(node, d, edge);


            System.out.println("Nodes: " + gr.nodes.get(4).edges);


            for (;d <= numOfD; d+=10) {

                System.out.println("D: " + d + ": Using random integers: " + rand1 + " and " + rand2 + ".");

                long startTime = System.currentTimeMillis();
                new DijkstrArr(node, gr.nodes.get(rand1), gr.nodes.get(rand2), d); // use the random values to match nodes
                long testTime = System.currentTimeMillis() - startTime;
                System.out.println("Test time: " + testTime);

                try {

                    file = new File("Nodes "+node+", D "+d+", Edges "+edge+", Number_of_Runs "+numOfD+".txt");
                    // if file doesnt exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    // Write text on  txt file.
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(testTime+" \n");
                    bw.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
*/
            }

        }
    }
}

