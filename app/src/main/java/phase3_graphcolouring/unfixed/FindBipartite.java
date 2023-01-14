package phase3_graphcolouring.unfixed;

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
}

