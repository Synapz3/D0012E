package graph;


/**
 * Created by ironedde on 2015-12-09.
 */
public class Edge{
    public int weight;
    public Node[] connections = new Node[2];

    public Node getOtherEnd(Node node){
        if(connections[0] == null || connections[1] == null){
            return null;
        }
        else{
            return connections[0] == node ? connections[1]  : connections[0];
        }
    }
}
