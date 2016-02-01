package graph;

import graph.Edge;

/**
 * Created by grammers on 2016-01-28.
 */
public class Kant {

        private Edge edge;
        private int totCost;

        public Kant(Edge edge, int cost){
            this.edge = edge;
            this.totCost = cost + edge.weight;
        }

        public Edge getEdge() {
            return edge;
        }

        public int totCost() {
            return totCost;
        }
    }

