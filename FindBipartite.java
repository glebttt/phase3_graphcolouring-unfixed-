import java.util.ArrayList;
import java.util.BitSet;
import java.util.Timer;

public class FindBipartite {

    public static boolean isBipartite(ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> graphAsList) {
        long startTime = System.currentTimeMillis();
        ArrayList<ArrayList<Integer>> colors = new ArrayList<>();
        for (int i = 0; i < 2; i++) {colors.add(new ArrayList<>());}
        System.out.println(graph);
        int firstVertex = graph.get(0).get(0);
        colors.get(0).add(firstVertex); // Colors the first vertex in the graph red
        graphAsList.remove(graph.get(0).get(0));
        colors.get(1).add(graph.get(0).get(1)); //Colors the vertex connected to the first vertex blue
        graphAsList.remove(graph.get(0).get(1));
        for (int i = 1; i < graph.size(); i++) { //loops through the remainder of the graph
            if (graph.get(i).contains(firstVertex)) { //checks if a vertex is connected to the first vertex
                // Colors all neighbors of the first vertex blue
                if (graph.get(i).get(0) != firstVertex) {
                    colors.get(1).add(graph.get(i).get(0));
                    graphAsList.remove(graph.get(i).get(0));
                } else {
                    colors.get(1).add(graph.get(i).get(1));
                    graphAsList.remove(graph.get(i).get(1));
                }
            }
        }
        int c = 0;
        int otherC = 1;
        System.out.println(graph.size());
        int breakCounter = 0;
        while(!(graphAsList.isEmpty()) && (System.currentTimeMillis() - startTime < 120000)) {
            for (int j = 0; j < colors.get(otherC).size(); j++) {
                for (int i = 0; i < graph.size(); i++) {
                    if (graph.get(i).contains(colors.get(otherC).get(j))) {
                        if (graph.get(i).get(0) != colors.get(otherC).get(j) && !(colors.get(c).contains(graph.get(i).get(0))) && !(colors.get(c).contains(graph.get(i).get(1)))) {
                            colors.get(c).add(graph.get(i).get(0));
                            graphAsList.remove(graph.get(i).get(0));
                            System.out.println(graphAsList);
                            breakCounter = 0;
                        } else if (!(colors.get(c).contains(graph.get(i).get(0))) && !(colors.get(c).contains(graph.get(i).get(1)))) {
                            colors.get(c).add(graph.get(i).get(1));
                            graphAsList.remove(graph.get(i).get(1));
                            System.out.println(graphAsList);
                            breakCounter = 0;
                        }
                    }
                    else{
                        breakCounter += 1;
                    }

                    //System.out.println(breakCounter);


                }
            }
            if (c == 0) {
                c++;
                otherC--;
            } else {
                c--;
                otherC++;
            }
        }
        System.out.println("Graph: "+graph);
        System.out.println("Red "+colors.get(0));
        System.out.println("Blue "+colors.get(1));

        for (int i = 0; i<graph.size(); i++){
            if (colors.get(1).contains(graph.get(i).get(0)) && colors.get(1).contains(graph.get(i).get(1)))
                return false;
            if (colors.get(0).contains(graph.get(i).get(0)) && colors.get(0).contains(graph.get(i).get(1)))
                return false;
        }
        return true;

        //just in case
         /* for (int j = 0; j < colors.get(0).size(); j++) {
                    for (int i = 0; i < graph.size(); i++) {
                        if (graph.get(i).contains(colors.get(0).get(j))) {
                            if (graph.get(i).get(0) != colors.get(0).get(j) && !(colors.get(1).contains(graph.get(i).get(0))) && !(colors.get(1).contains(graph.get(i).get(1)))) {
                                colors.get(1).add(graph.get(i).get(0));
                            } else if (!(colors.get(1).contains(graph.get(i).get(0))) && !(colors.get(1).contains(graph.get(i).get(1)))) {
                                colors.get(1).add(graph.get(i).get(1));
                            }
                        }
                    }
                }
        for (int j = 0; j < colors.get(1).size(); j++) {
            for (int i = 0; i < graph.size(); i++) {
                if (graph.get(i).contains(colors.get(1).get(j))) {
                    if (graph.get(i).get(0) != colors.get(1).get(j) && !(colors.get(0).contains(graph.get(i).get(0)))) {
                        colors.get(0).add(graph.get(i).get(0));
                    } else if (!(colors.get(0).contains(graph.get(i).get(0)))) {
                        colors.get(0).add(graph.get(i).get(1));
                    }
                }
            }
            }
        for (int j = 0; j < colors.get(0).size(); j++) {
            for (int i = 0; i < graph.size(); i++) {
                if (graph.get(i).contains(colors.get(0).get(j))) {
                    if (graph.get(i).get(0) != colors.get(0).get(j) && !(colors.get(1).contains(graph.get(i).get(0))) && !(colors.get(1).contains(graph.get(i).get(1)))) {
                        colors.get(1).add(graph.get(i).get(0));
                    } else if (!(colors.get(1).contains(graph.get(i).get(0))) && !(colors.get(1).contains(graph.get(i).get(1)))) {
                        colors.get(1).add(graph.get(i).get(1));
                    }
                }
            }
        }*/
    }

    public static boolean runBipartite(int vertices){

        ArrayList<Integer> graphAsList = new ArrayList<Integer>();
        ReadGraph rg = new ReadGraph();
        ArrayList<ArrayList<Integer>> graph = rg.passDynamicArray();
        for (int i = 1; i<=vertices; i++){graphAsList.add(i);}
        boolean isBipartite = isBipartite(graph, graphAsList);
        if (isBipartite == true) System.out.println("The chromatic number is 2");
        else{
            System.out.println("false");
        }
        //System.out.println(isBipartite(graph, graphAsList));

       return isBipartite;
    }


    /*public static void main (String args[]){

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(10);
        for(int i = 0; i<10; i++){
            graph.add(new ArrayList<>(2));
        }
        ArrayList<Integer> graphAsList = new ArrayList<Integer>();

        //Simple bipartite
       /* graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(2).add(2);
        graph.get(2).add(4);
        graph.get(3).add(1);
        graph.get(3).add(5);
        graph.get(4).add(5);
        graph.get(4).add(6);
        graph.get(5).add(1);
        graph.get(5).add(7);
        //graph.get(6).add(1);
        //graph.get(6).add(4);

        //Circular
        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(2).add(3);
        graph.get(2).add(4);
        graph.get(3).add(4);
        graph.get(3).add(5);
        graph.get(4).add(5);
        graph.get(4).add(6);
        graph.get(5).add(6);
        graph.get(5).add(7);
        graph.get(6).add(7);
        graph.get(6).add(8);
        graph.get(7).add(8);
        graph.get(7).add(9);
        graph.get(8).add(9);
        graph.get(8).add(10);
        graph.get(9).add(10);
        graph.get(9).add(1);

        //Tree
        /*graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(1);
        graph.get(1).add(4);
        graph.get(2).add(1);
        graph.get(2).add(8);
        graph.get(3).add(6);
        graph.get(3).add(4);
        graph.get(4).add(2);
        graph.get(4).add(3);
        graph.get(5).add(6);
        graph.get(5).add(7);
        graph.get(6).add(6);
        graph.get(6).add(5);*/

        //Tree
       /* graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(1);
        graph.get(1).add(3);
        graph.get(2).add(1);
        graph.get(2).add(4);
        graph.get(3).add(2);
        graph.get(3).add(5);
        graph.get(4).add(2);
        graph.get(4).add(6);
        graph.get(5).add(2);
        graph.get(5).add(7);
        graph.get(6).add(6);
        graph.get(6).add(10);
        graph.get(7).add(6);
        graph.get(7).add(11);
        graph.get(8).add(6);
        graph.get(8).add(12);
        graph.get(9).add(4);
        graph.get(9).add(8);
        graph.get(10).add(4);
        graph.get(10).add(9);
        graph.get(11).add(8);
        graph.get(11).add(13);
        graph.get(12).add(8);
        graph.get(12).add(14);*/

        //Star
        /*for (int i = 0; i<8; i++){
            graph.get(i).add(2);
        }
        graph.get(0).add(1);
        graph.get(1).add(3);
        graph.get(2).add(4);
        graph.get(3).add(5);
        graph.get(4).add(6);
        graph.get(5).add(7);
        graph.get(6).add(8);
        graph.get(7).add(9);*/

        //Tree
        /*graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(1);
        graph.get(1).add(3);
        graph.get(2).add(3);
        graph.get(2).add(9);
        graph.get(3).add(3);
        graph.get(3).add(12);
        graph.get(4).add(3);
        graph.get(4).add(11);
        graph.get(5).add(4);
        graph.get(5).add(2);
        graph.get(6).add(4);
        graph.get(6).add(5);
        graph.get(7).add(4);
        graph.get(7).add(6);
        graph.get(8).add(6);
        graph.get(8).add(8);
        graph.get(9).add(5);
        graph.get(9).add(7);
        graph.get(10).add(9);
        graph.get(10).add(10);
        graph.get(11).add(10);
        graph.get(11).add(13);
        graph.get(12).add(10);
        graph.get(12).add(14);
        graph.get(13).add(10);
        graph.get(13).add(15);

        for (int i = 1; i<=10; i++){graphAsList.add(i);}
        System.out.println(isBipartite(graph, graphAsList));
    }*/
}

