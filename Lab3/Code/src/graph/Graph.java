package graph;

import java.util.ArrayList;
import java.util.Random;
import java.lang.*;

public class Graph{
    public ArrayList<Node> nodes = new ArrayList<Node>();
    private Random  rand = new Random();
    private ArrayList<Node> possibleNodes = new ArrayList<>();
    private int totalEdges = 0;
    private int d;

    /**
     *
     * @param nrNodes
     * @param d
     * @param nrEdges >= nrNodes && < nrNodes*d
     */
    public Graph(int nrNodes, int d, int nrEdges){
        //INSERT MAXedge logic here!!!!!
        if(nrEdges < nrNodes-1) {
            throw new IllegalStateException("YOU CAN'T RUN THOSE NUMBERS!   not enough edges to make all connections, minimum: "+(nrNodes-1));
        }
        if(d < 2){
            throw new IllegalStateException("YOU CAN'T RUN THOSE NUMBERS!   d is less than 2");
        }
        this.d = d;
        for (int i = 0; i <nrNodes; i++) {
            Node tmp = new Node(i);
            nodes.add(tmp);
            possibleNodes.add(tmp);
        }
        for (int i = 0; i < nrNodes-1; i++){
            addedge(i,i+1);
            totalEdges++;
        }
        while(totalEdges<nrEdges){
            if(possibleNodes.size() <2){
                break;
            }
            int nodeA_index = getAvailableNode(rand.nextInt(possibleNodes.size()));
            int nodeB_index = getAvailableNode(rand.nextInt(possibleNodes.size()));

            if(nodeA_index == -1 || nodeB_index==-1){
                return;
            }

            Node nodeA = possibleNodes.get(nodeA_index);
            Node nodeB = possibleNodes.get(nodeB_index);
            while(true){
                if(nodeA.connectedTo(nodeB) || nodeA == nodeB){
                    nodeB_index = getAvailableNode((nodeB_index+1)%possibleNodes.size());
                    if(nodeB_index == -1){
                        return;
                    }
                    nodeB = possibleNodes.get(nodeB_index);
                }
                else{
                    addedge(nodeA_index,nodeB_index);
                    totalEdges++;
                    if(possibleNodes.get(nodeA_index).edges.size() >= d){
                        possibleNodes.remove(nodeA);
                    }
                    if(possibleNodes.get(possibleNodes.indexOf(nodeB)).edges.size() >= d){
                        possibleNodes.remove(nodeB);
                    }
                    break;
                }
            }
        }
    }
    private int getAvailableNode(int start){
        int nodeIndex = start;
        if(possibleNodes.get(start).edges.size() < d){
            return nodeIndex;
        }
        else{
            for(int i=1;i<possibleNodes.size();i++){
                //Does the node have availible connections?
                if(possibleNodes.get((nodeIndex+i)%possibleNodes.size()).edges.size() < d){
                    return (nodeIndex+i)%possibleNodes.size();
                }
            }
            return -1;
        }
    }
    private void addedge(int a, int b){
        Edge edge = new Edge();
        edge.weight = rand.nextInt(99) + 1;
        edge.connections[0] = possibleNodes.get(a);
        edge.connections[1] = possibleNodes.get(b);
        possibleNodes.get(a).edges.add(edge);
        possibleNodes.get(b).edges.add(edge);
    }
}
