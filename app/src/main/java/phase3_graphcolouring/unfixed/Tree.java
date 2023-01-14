package phase3_graphcolouring.unfixed;

import java.util.ArrayList;
import java.util.Arrays;

public class Tree {
    public boolean isTree = false;
    public Tree (ColEdge[] e, Integer[] storeEdgeArray) {
        runTree(e, storeEdgeArray);

    }
    public void runTree(ColEdge[] e, Integer[] storeEdgeArray){
        int count=0;
        int v = e.length;
        int[][] array = new int[v][2];
        for(int x = v; x > 0; x--){
            count=0;
            for(int y = 0; y < storeEdgeArray.length; y++){
                if(x == storeEdgeArray[y]){
                    count ++;
                }
            }
            array[x-1][0] = count;
            array[x-1][1] = x;

        }
        Arrays.sort(array, (a, b) -> Integer.compare(b[0],a[0]));



        ArrayList <Integer> connectedElements = new ArrayList<Integer>(); //Arraylist to contain every connection to a certain number, it updates many times.
        ArrayList <Integer> secondElements = new ArrayList<Integer>(); //Arraylist that will be used to check if theree are duplicated inside connectedElements

        //I now create two arraylists, each containing the left or the right side of the connections. I created this beacause I need e[].u and e[].v in arraylist form.
        int[] eleft = new int[e.length];
        int[] eright = new int[e.length];
        ArrayList <Integer> left = new ArrayList<Integer>();
        ArrayList <Integer> right = new ArrayList<Integer>();
        for(int i=0; i<e.length; i++) {
            eleft[i] = e[i].u;
        }
        for(int i=0; i<e.length; i++) {
            eright[i] = e[i].v;
        }
        for(int i=0; i<eleft.length; i++) {
            left.add(eleft[i]);
        }
        for(int i=0; i<eright.length; i++) {
            right.add(eright[i]);
        }

        ArrayList <Integer> templeft = new ArrayList<Integer>(); //this Arraylist is to save the data in "left", for when I will reset it
        ArrayList <Integer> tempright = new ArrayList<Integer>(); //this Arraylist is to save the data in "right", for when I will reset it
        templeft.addAll(left);
        tempright.addAll(right);

        int solll =0; //This variable will increase if there are duplicates in connectedElemenets, which would mean the graph is not linear. As soon as solll != 0, the code breaks;
        int r=0; //This variable increases with each loop, and as soon as it reaches a certain value there is no need to continue the loop.
        int c =0; //This variable increases with each loop, and as soon as it reaches a certain value there is no need to continue the loop.
        int g=(array[0][1]); //g will be changed for each loop, and it is the number for which its connections are found. The first value is the vertice with most connections.
        int s=0; //s also changes everytime, and it represents each past value of g. It is here to avoid that the loop finds the same exact connection each time

        //this for loop contains all the rest of the code. It varies through each vertice, from the one that is most connected to the least.
        for (int l=0; l<array.length; l++) {

           // System.out.println("number " + (array[l][1]));

            g= array[l][1]; //the value of g has to have with each loop.

                //this do-while loop stops as soon as int c reaches templeft.size()*templeft.size(), that way every possibility will be checked
                do {
                    //  inside the do-while loop the first list of connections is created:
                    //  In a graph in which the edges are: 1 2, 2 3, 3 4, 3 6, 1 6, 1 7, 1 8,
                    //  g at first will be 1, since it has the biggest number of connections, and s will remain 0.
                    //  as soon as g is found either in left or right, g becomes the number 1 is first connected to (which is 2), and s will become 1.
                    //  and so the loop starts again, with g=2 and s=1.

                    for (int i=0; i< left.size(); i++) {

                        if (left.get(i) == g) {
                            if (right.get(i) !=s) {
                                connectedElements.add(right.get(i)); //the first connection (in the example 2) is added to connectedElements
                                s=left.get(i);
                                g=right.get(i);
                            }
                        }
                        if (right.get(i) == g) {
                            if (left.get(i) !=s) {
                                connectedElements.add(left.get(i));
                                s=right.get(i);
                                g=left.get(i);
                            }
                        }
                        c++;
                    }

                } while (c<=left.size()*left.size());

                //now I have the first list of possible connections. I now have to find the next g.
                //If collectedElements is empty, then the next g will be array[l][1], so I add it on the first position of the list.
                //I make it so g equals the last number of the list.


                connectedElements.add(0, array[l][1]);
                g=connectedElements.get(connectedElements.size()-1);
                connectedElements.remove(0);

                //I then remove the array[l][1] that I've added.
                //Then I check if there aren't other elements array[l][1] in the list. In which case, it is not linear, so I increase solll.

                if (connectedElements.contains(array[l][1])) {
                    solll++;
                }
                //I now check for other duplicates, by adding only once each element of connectedElements to secondElements.

                for(int i=0; i<connectedElements.size(); i++){
                    if(!secondElements.contains(connectedElements.get(i))){
                        secondElements.add(connectedElements.get(i));
                    }
                }

                //If secondElements is smaller than connectedElements, that means there were duplicates, so I increase solll.

                if (secondElements.size()<connectedElements.size()) {
                    solll++;
                }
                //If solll>0, there's no need to continue and the loop can break.

                if (solll>0) {
                    break;
                }
                //I reset the value of c
                c=0;
                
           
            //Again, if solll>0, there's no need to continue and the loop can break.
            if (solll>0) {
                break;
            }

            //System.out.println(connectedElements);
            connectedElements.clear(); //I need to make sure that connectedElements is empty before starting a new loop.
            secondElements.clear(); //I need to make sure that secondElements is empty before starting a new loop.
        }

        if (solll!=0) {
            isTree=false;
        }
        if (solll==0) {
            isTree = true;
        }


    }
}

