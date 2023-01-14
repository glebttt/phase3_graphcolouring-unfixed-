package phase3_graphcolouring.unfixed;

import java.util.ArrayList;

class CombinationsOfGraph
{
    public static int count = 0;

    static int x = 0;
    public static ArrayList<ArrayList<Integer>> storeResult = new ArrayList<ArrayList<Integer>>();

    public static void runCombiGraph(int vertexNum) {
        getCombinations(vertexNum);
        //System.out.println(storeResult);
    }



    public static void swap(ArrayList<Integer> storeEdges, int a, int b){
        int temp = storeEdges.get(a);
        storeEdges.set(a, storeEdges.get(b));
        storeEdges.set(b, temp);
    }

    public static ArrayList<Integer> generateArrayList(int numVertices){
        ArrayList<Integer> storeEdges = new ArrayList<Integer>();
        for(int i = 1; i <= numVertices; i++){
            storeEdges.add(i);
        }
        return storeEdges;
    }

    public static ArrayList<ArrayList<Integer>> generateCombinations(ArrayList<Integer> storeEdges, int currentIndex, ArrayList<ArrayList<Integer>> storeCombinations){
        if(currentIndex == storeEdges.size() - 1){
            storeCombinations.add(storeEdges);
            storeResult.add(new ArrayList<>());
            for (int whatever = 0; whatever < storeEdges.size(); whatever++) {
                storeResult.get(x).add(storeEdges.get(whatever));
            }
            x++;
            //System.out.println(storeCombinations + " here");
            //System.out.println(storeEdges);
            //System.out.println(storeResult);
            return storeCombinations;
        }
        for(int i = currentIndex; i < storeEdges.size(); i++){
            swap(storeEdges, i, currentIndex);
            storeCombinations = generateCombinations(storeEdges, currentIndex + 1, storeCombinations);
            count++;
            swap(storeEdges, i, currentIndex);
        }
        return storeCombinations;
    }


    public static ArrayList<ArrayList<Integer>> getCombinations(int numVertices){
        ArrayList<ArrayList<Integer>> storeCombinations = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> storeEdges = generateArrayList(numVertices);
        generateCombinations(storeEdges, 0, storeCombinations);
        //System.out.println(storeResult);

        return null;
    }

}
