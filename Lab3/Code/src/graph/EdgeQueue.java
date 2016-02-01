package graph;
import java.util.ArrayList;

/**
 * Created by Synapz3 on 2016-01-28.
 */
public class EdgeQueue {
    private int d;
    private Kant[] kants;
    private int size;
    private int fild;

    public EdgeQueue(int d){
        this.d = d;
        size = 10;
        kants = new Kant[size];
        fild = 0;
    }


    public void insert(Edge edge, int cost) {
      //sort queue

        if (fild+1 == size) kants = increse();

        fild++;
        kants[fild] = new Kant(edge, cost);

       for (int i = fild; true;){
           if (fild <= 1) return;

           int parent = i/d;
           if (parent <1) parent = 1;
           //System.out.println("parent " + parent);
           int ff = kants[parent].totCost();
           if (kants[i].totCost() < kants[parent].totCost()){
               Kant temp = kants[parent];
               kants[parent] = kants[i];
               kants[i] = temp;
               i = parent;
           } else {
               return;
           }
       }

    }

    public Kant pop () {
        Kant tmp = kants[1];

        int prev = 1;
        int min=1;
        for (int i = 1; i <= fild; i = i*d){
            min = i;
            for (int n = 0; n < d; n++){
                if (n+i > fild) break;
                if (kants[i+n].totCost() < kants[min].totCost()) min = i+n;
            }
            kants[prev] = kants[min];
            prev = min;
        }

        ArrayList<Kant> kant = new ArrayList<Kant>();
        kants[min] = null;
        min++;
        int t = fild;
        fild--;
        while (min <= t){
            kant.add(kants[min]);
            kants[min] = null;
            min++;
            fild--;
        }

        for (Kant k : kant) {
            insert(k.getEdge(),k.totCost()-k.getEdge().weight);
        }

        // remove kants[1] and shift array
        return tmp;
    }


    private Kant[] increse(){

        Kant[] temp = new Kant[size+10];

        for (int i = 1; i < size; i++){
            temp[i] = kants[i];
        }
        size = size + 10;
        return temp;
    }

}


