package graph;
import java.util.ArrayList;

/**
 * Created by Synapz3 on 2016-01-28.
 */
public class EdgeQueue {
    private int d;
    private Kant[] kants;
    private int size;
    private int elements = 0;
    private int nextFree = 0;
    private int lastAdded = -1;
    private ArrayList<Integer> inCurrentLayer;
    private int shouldBeInLayer = 0;
    private int numLayers = 1;
    public EdgeQueue(int d,int maxSize){
        this.d = d;
        size = maxSize;
        kants = new Kant[size];
        inCurrentLayer = new ArrayList<Integer>();
        inCurrentLayer.add(0);
    }


    public void insert(Edge edge, int cost) {
        //sort queue
        Kant toAdd = new Kant(edge, cost);
        kants[nextFree] = toAdd;
        ensureSmallest(nextFree);
        if(nextFree == 0){
            inCurrentLayer.clear();
            nextFree = 1;
            inCurrentLayer.add(nextFree);
            shouldBeInLayer = (int)Math.pow(d,numLayers);
        }
        else {
            if (isParentFull(nextFree)) {

                int granParentPos = getParent(getParent(nextFree));
                int checkForFree = granParentPos;

                while (checkForFree >= 0) {
                    for(int i=0;i<inCurrentLayer.size();i += d){
                        if(!isParentFull(inCurrentLayer.get(i)) ){

                        }
                    }
                    //If you should start a new layer
                    int[] parentsChildren = getChildren(getParent(nextFree));
                    for(int pos:parentsChildren){
                        if(kants[pos] == null){

                        }
                    }
                }


                for (int i = 1; i <= d; i++) {
                    if (kants[granParentPos + i] == null) {
                        nextFree = granParentPos + i;
                        return;
                    }
                }
                //If we should start a new layer
                nextFree = (getParent(nextFree) + d + 1);
            } else {
                nextFree += (d + 1);
            }
        }

        //if()
        //kants[pos] = toAdd;

        /*
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
       }*/

    }
    private boolean isParentFull(int pos){
        int parentPos = getParent(pos);
        for(int i=1; i<=d;i++){
            if(kants[parentPos+i] == null){
                return false;
            }
        }
        return true;
    }
    private void ensureSmallest(int pos){
        int where = pos;
        int parentPos;
        while(where >= 0) {
            parentPos = getParent(where);
            if(kants[parentPos].totCost() > kants[where].totCost()) {
                Kant parent = kants[parentPos];
                kants[parentPos] = kants[where];
                kants[where] = parent;
                where -= (d+1);
            }
            else{
                return;
            }
        }
    }
    private int[] getChildren(int pos){
        int[] tmp = new int[d];
        for(int i = 1;i<=d;i++){
            tmp[i-1] = (d*pos)+i;
        }
        return tmp;
    }
    private int getParent(int pos){
        return (int)Math.floor((pos-1)/d);
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


