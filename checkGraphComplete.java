import java.util.ArrayList;
import java.util.Arrays;

public class checkGraphComplete {

    public static boolean checkGraphComplete(int v) {
        ReadGraph rg = new ReadGraph();
        Integer[] storeEdgeArray = rg.storeEdgeArray;
        int counterForEdes = 0;
            int[][] array = new int[v][2];
            ArrayList<Integer> greedyAlgorithm = new ArrayList<Integer>();
            for (int x = v; x > 0; x--) {
                int count = 0;
                for (int y = 0; y < storeEdgeArray.length; y++) {
                    if (x == storeEdgeArray[y]) {
                        count++;
                    }
                }
                array[x - 1][0] = count;
                array[x - 1][1] = x;
                if(count == (v - 1)){
                    counterForEdes ++;
                    //System.out.println("this has enough edges");
                }
            }
            if(counterForEdes == v){
                return true;
            }


            return false;
        }
    }

