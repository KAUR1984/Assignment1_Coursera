package Assignment1_Coursera;

import java.util.Scanner;

public class WeightedQuickUnionUF {
    int[] id;
    int[] size;
    WeightedQuickUnionUF(int n) {
        id = new int[n];
        size = new int[n];
        for(int i = 0; i < id.length; i++){
            id[i] = i;
            size[i] = 1;
        }
    }

    //find the roots
    private int root(int i) {
        while(id[i] != i){        //TODO Just a Single change !!!
            id[i] = id[id[i]];    //TODO change the parent of the current to its grandparent
            i = id[i];
        }
        return i;
    }

    //connected
    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    //union
    //assume it to be already connected
    //change the parent of the root of the smaller one to the root of the bigger one
    public void union(int p, int q){
        if(size[p] < size[q]){
            id[root(p)] = root(q);
            size[q] += size[p];    //add the size of the smaller one to the bigger one
        }else{
            id[root(q)] = root(p);
            size[p] += size[q];
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        WeightedQuickUnionUF wqup = new WeightedQuickUnionUF(n);

        while(in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();

            if(!wqup.connected(p,q)){
                wqup.union(p,q);
                System.out.println(p + " " + q + " now connected!");
            }
            else{
                System.out.println("already connected !");
            }

        }
    }
}
