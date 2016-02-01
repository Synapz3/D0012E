/**
 * Created by Samuel on 2015-12-23.
 */
import graph.*;

import java.util.ArrayList;

public class DijkstrArr {
    static ArrayList<Node> setQ;
    static ArrayList<Node> completeList;
    static int distance[];
    static Node prev[];

    /**
     * Search first the closest path between a and b
     * @param size Number of nodes
     * @param a Start node
     * @param b End node
     * @param d Max number of connections
     */
    public static String Run_DijkstrArr(Graph gr, int size, Node a, Node b){
        distance = new int[size];
        prev = new Node[size];
        setQ = new ArrayList<>();
        completeList = new ArrayList<>();
        for (int i = 0;i<size;i++){
            prev[i] = null;
            distance[i] = 2147483647;
            setQ.add(i,gr.nodes.get(i));
            completeList.add(i,gr.nodes.get(i));
        }
        distance[setQ.indexOf(a)] = 0;
        boolean firstRun = true;
        while(setQ.size() >0){
            int selectedIndex = 0;
            int selectedIndex_distance = 0;
            if(firstRun){
                firstRun = false;
            }else{
                int minVal = 0;
                int tmp = 0;
                for(int i = 0;i<setQ.size();i++){
                    tmp = distance[completeList.indexOf(setQ.get(i))];
                    if (minVal > tmp){
                        minVal = tmp;
                        selectedIndex = i;
                        selectedIndex_distance = completeList.indexOf(setQ.get(i));
                    }
                }
            }
            Node selectedNode = setQ.get(selectedIndex);
            if(selectedNode == b){
                return "DONE";
            }
            setQ.remove(selectedIndex);
            for(int i = 0;i<selectedNode.edges.size();i++){
                int alt = distance[selectedIndex_distance] + selectedNode.edges.get(i).weight;
                int otherEnd = completeList.indexOf(selectedNode.edges.get(i).getOtherEnd(selectedNode));
                if(alt < distance[otherEnd]){
                    distance[otherEnd] = alt;
                    prev[otherEnd] = selectedNode;
                }
            }

        }
        return "DONE";
    }

}
