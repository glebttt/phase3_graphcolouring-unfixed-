import java.util.ArrayList;
import java.util.Arrays;

class upperBound {

    /*
     * here we actually start with the greedy algorithm, cause we start with the
     * highest connected vertice and go down to the lowest
     * however to improve that we also start with every other vertice once:
     * might be an improvement: here we could do it the other way around too, so we
     * start at the lowest connection and go up to highest
     * might be an improvement for bigger graphs that are only checked twice: also
     * we can only go though it once starting from the highest and then from the
     * lowest so we check our big graphs 4 times with different combinations
     */

    public static int getChromaticNumberEdges(int numOfVertices, int numOfEdges,
            ArrayList<ArrayList<Integer>> graph, Integer[] storeEdgeArray) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        int chromaticNum = numOfVertices;

        array = helperFunction(numOfVertices, storeEdgeArray);

        for (int tryThatShit = numOfVertices; tryThatShit > 0; tryThatShit--) {
            ArrayList<ArrayList<Integer>> colorArray = new ArrayList<>(numOfVertices); // those are
            // basically the
            // different
            // colors, every
            // number in the
            // same row is the
            // same color

            int removeItem = 0;
            int toAdd = array.get(0);
            array.remove(removeItem);
            array.add(toAdd);

            // here I'm implementing the Array with n lists, cause we need as many lists up
            // till the upper bound (maxDegree) in our Array
            for (int i = 0; i <= numOfVertices; i++) {
                colorArray.add(new ArrayList());
            }

            colorArray.get(0).add(array.get(0)); // here I add the first number to the first color ->
                                                 // so the highest vertice will be in the first row

            // here I start to decrease from the highest to 1
            for (int z = 1; z < numOfVertices; z++) {
                int i = array.get(z); // get highest number of array

                int counterMaxDegree = 0;
                int counterEdges = 0;
                ArrayList<Integer> connectedVertices = new ArrayList<Integer>(); // this stores the values that every
                                                                                 // vertice
                // is connected to
                while (counterEdges <= numOfEdges) { // while we still have edges to check if the vertice number is part
                                                     // of, this code will run
                    if (graph.get(counterEdges).contains(i)) { // if the vertice number is part of the vertices
                                                               // the edge connects
                        graph.get(counterEdges).remove(new Integer(i)); // remove the vertice number
                        int m = graph.get(counterEdges).get(0); // then store the other vertice that our current i
                                                                // is connected to in the array
                        graph.get(counterEdges).add(i); // and then add our current i back to it, because we need
                        // it for the next vertice number too
                        // if there is a possibility that the connected vertice number is already part
                        // of our color system, add it to our compare array. We do not need to compare a
                        // value we did not assign to the color scheme yet, cause it will never be found
                        // anyways
                        connectedVertices.add(m);
                    }
                    counterEdges += 1; // and then look for the next edge
                }

                if (connectedVertices.isEmpty()) { // if there are no connections found to numbers in our color scheme,
                                                   // just
                    // add it to the first one
                    colorArray.get(0).add(i);
                } else {
                    int checkVariable = 0; // if this variable stays 0 during the whole checking process it means that
                                           // there is no color in our system yet that we can add our i to and we have
                                           // to create a new color.
                    loop: while (counterMaxDegree <= numOfVertices) { // while we did not compare every color, check the
                        // next one
                        // System.out.println(totallyNonStatisticArrayList.get(counterMaxDegree));
                        if (!colorArray.get(counterMaxDegree).isEmpty()) { // if the color does not
                                                                           // have any numbers yet, we
                                                                           // do not need to check it
                            for (Integer s : connectedVertices) { // then iterate through every number that is connected
                                                                  // to our
                                // i (vertice)
                                // System.out.println(totallyNonStatisticArrayList.get(counterMaxDegree));
                                if (colorArray.get(counterMaxDegree).contains(s)) { // if the color
                                                                                    // contains the
                                                                                    // number we're
                                                                                    // currently at
                                    // System.out.println("contains");
                                    break; // break the loop, because then we can not add the number to this color, so
                                           // we should get the next color
                                } else {
                                    if (s.equals(connectedVertices.get(connectedVertices.size() - 1))) { // if we tried
                                                                                                         // every number
                                        // on that color and it did
                                        // not match with any value
                                        // in the array we can add i
                                        // to that color
                                        colorArray.get(counterMaxDegree).add(i);
                                        checkVariable += 1; // if we have no color yet for the new number, we have to
                                                            // add a new color.
                                        break loop; // because then we added an i and we need to break the whole loop
                                                    // to get the next i
                                    }
                                }
                            }
                        } else if (checkVariable == 0) { // as I said before, here we check if we need a new color
                            colorArray.get(counterMaxDegree).add(i); // we add a new dimension here
                            break; // and then break the whole circle because we can get the new i
                        }
                        counterMaxDegree += 1;
                    }
                }
            }

            int y = 0;
            for (int x = 0; x < colorArray.size(); x++) { // while there are still colors in our array
                if (!colorArray.get(x).isEmpty()) {// if the color contains numbers
                    y += 1; // we add one here
                }
            }

            if (y < chromaticNum) {
                chromaticNum = y;
            }
        }
        return chromaticNum; // this might be our chromatic number

    }

    // this function here orders the vertices from highest degree (most connected
    // edges) to lowest
    public static ArrayList<Integer> helperFunction(int v, Integer[] storeEdgeArray) {
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
        }
        Arrays.sort(array, (a, b) -> Integer.compare(a[0], b[0]));
        for (int x = v; x > 0; x--) {
            greedyAlgorithm.add(array[x - 1][1]);
        }
        return greedyAlgorithm;
    }

    public static int runUpperBound() {
        ReadGraph rg = new ReadGraph();
        int numOfVertices = rg.n;
        int numOfEdges = (rg.m) - 1; // Edges -1
        ArrayList<ArrayList<Integer>> graph = rg.passDynamicArray();

        int upperBound = getChromaticNumberEdges(numOfVertices, numOfEdges, graph, rg.storeEdgeArray);
        System.out.println("UPPER BOUND IS " + upperBound);
        return upperBound;
    }

}