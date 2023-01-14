import java.util.ArrayList;

public class FindGrid {

    public static int findNumOfConnections(ArrayList<ArrayList<Integer>> graph, int vertex){
        int connections = 0;
        for (int i = 0; i<graph.size(); i++){
            if (graph.get(i).contains(vertex)){
                connections++;
            }
        }
       // System.out.println(connections);
        return connections;
    }

    public static boolean cliqueOfFour(ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> graphAsList){
        int threes = 0;
        int fives = 0;
        int eights = 0;

        for (int i = 1; i<=graphAsList.size(); i++){
            int numOfConnections = findNumOfConnections(graph, i);

            if (numOfConnections == 3){
                threes++;
            }
            if (numOfConnections == 5){
                fives++;
            }
            if (numOfConnections == 8){
                eights++;
            }
        }
       // System.out.println("threes "+threes+" fives "+fives+" eights "+eights);

        if (threes == 4 && (fives == graphAsList.size()/2 || fives == 0) && eights == graphAsList.size() - threes - fives){
            return true;
        }
        return false;
    }

    public static void runGridGraph (){

        ReadGraph rg = new ReadGraph();

        ArrayList<ArrayList<Integer>> graph = rg.passDynamicArray();

        ArrayList<Integer> graphAsList = new ArrayList<>();
        for (int i = 1; i<=rg.n; i++){
            graphAsList.add(i);
        }


        if(cliqueOfFour(graph, graphAsList) == true){
            System.out.println("CHROMATIC NUMBER IS 4");
        }


    }
}
