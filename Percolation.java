package Assignment1_Coursera;
//FULL SITE REPRESENTATION...
//one representation of the full site would be - 1 as it is connected to the top!
//other could be just 0; and making an algorithm to find the root of it
//if the root is one of sites present in the top row, then

//OPEN
//whenever u open a site, you need to check all of its adjacent
//open sites and make them connected!
//TODO import edu.princeton.cs.algs4.Assignment1_Coursera.WeightedQuickUnionUF;

import java.util.Scanner;

public class Percolation {
    int[][] grid;
    int n;    //this is the size of the grid
    //create an array of open and blocked sites to represent the openness and blockness
    int[] states;   //all the states of all the indexes! open/ blocked/ full
    WeightedQuickUnionUF uf;    //TODO fix this by importing the required class

    //initialise the n by n grid
    public Percolation(int n) {
        uf = new WeightedQuickUnionUF(n);
        this.n = n;    //initialise the size field with n..
        grid = new int[n][n];
        states = new int[n*n];

        int counter = 1;     //value from which indexing will start
        for(int i = 0; i < n; i++) {     //TODO check error if from 1 to n
            for(int j = 0; j < n; j++){   //error if from 1 to n
                grid[i][j] = counter;       //index from 1 to n^2
                states[counter-1] = -1;   //all the sites are initially blocked!
                System.out.print(grid[i][j] + " ");
                System.out.print(states[counter-1] + " ");
                if((j+1) % n == 0 && j != 0) {   //j+1 as j is never gonna be equal to n.
                    System.out.println();
                }
                counter++;
            }
        }
    }


    /**
     * Prints the current grid on the screen
     * FIXME just delete this before submitting as can affect the performance
     */
    public void printGrid() {
        //iterate overall the entries of the 2d array
        int counter = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
                System.out.print(states[counter] + " ");

                if(((j+1) % n == 0) && j != 0) {
                    System.out.println();   //print an extra line
                }
            }
        }

    }

    /**
     * Opens the site (row,col) if it is not already open.
     * you need to check each of the adjacent open sites, if even
     * one of the sites is full, then this site is also gonna be full!
     * @param row This is the row's index
     * @param col This is column's index
     * @throws IllegalArgumentException if row and col are not in range
     */
    public void open(int row, int col) {
        if(row > n || col > n) {
            throw new IllegalArgumentException();
        }
        else {
            int thisSite = grid[row][col];    //these are the values(of indexing) 0..n^2
            int aboveThis = grid[row-1][col];
            int belowThis = grid[row+1][col];
            int right = grid[row][col+1];
            int left = grid[row][col-1];

            if (thisSite == -1) {     //if this site is blocked ..
//                thisSite = 0;      //open it !
                //iterate over all of the adjacent sites, and make it connected with them
                //if the above site is full, then connect them and make this site also full
                //ABOVE
                if(aboveThis == 1 || aboveThis == 0){  //if it is open
                    if(aboveThis == 1){
                        thisSite = 1;
                    }else thisSite = 0;
                    uf.union(thisSite, aboveThis);   //connect both sites
                }
                //BELOW
                if(belowThis == 1 || belowThis == 0) {  //if the site below is 0 or 1
                    if(belowThis == 1) {   //if the current site is full, then the one connected to it will also be full
                        thisSite = 1;   //definitely gonna be 1
                    } else thisSite = 0;
                    uf.union(thisSite, belowThis);   //connect this and below
                }
                //LEFT
                if(left == 1 || left == 0) {
                    if(left == 1){
                        thisSite = 1;   //left has to be full
                    } else thisSite = 0;  //just open; not full
                    uf.union(thisSite, left);
                }
                //RIGHT
                if(right == 1 || right == 0) {
                    if(right == 1) {
                        thisSite = 1;
                    }
                    uf.union(thisSite, right);   // we have union them that means their roots are now the same!
                }
            }
        }
    }

    /**
     * Is the given site open or not! (Open Site or Full Site)
     * @param row
     * @param col
     * @return true if it is open
     * @throws IllegalArgumentException if row and col are not in range
     */
    public boolean isOpen(int row, int col) {
        if(row > n || col > n){
            throw new IllegalArgumentException();
        }
        else {
            if (grid[row][col] == 0 || grid[row][col] == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        Percolation percolation = new Percolation(n);
    }




}
