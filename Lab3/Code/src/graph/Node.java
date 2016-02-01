package graph;

import java.util.ArrayList;

/**
 * Created by ironedde on 2015-12-09.
 */
public class Node{
    public int id;
    public ArrayList<Edge> edges = new ArrayList<Edge>();
    Node node;

    public Node(int id){
        this.id = id;
    }

    public Edge connectTo(int weight,Node node){
        Edge tmp = new Edge();
        tmp.connections[0] = this;
        tmp.connections[1] = node;
        tmp.weight = weight;
        edges.add(tmp);
        this.node = node;
        return tmp;
    }

    public String toString(){
        String tmp ="This node is connected to: ";
        for(int i=0;i<edges.size();i++){
            if(edges.get(i).connections[0].id != id){
                tmp = tmp + edges.get(i).connections[0].id+"; ";
            }
            else{
                tmp = tmp + edges.get(i).connections[1].id+"; ";
            }
        }
        return (tmp);
    }
    public boolean connectedTo(Node node){
        for(int i = 0;i<edges.size();i++){
            if(edges.get(i).getOtherEnd(this).id == node.id){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }
}


