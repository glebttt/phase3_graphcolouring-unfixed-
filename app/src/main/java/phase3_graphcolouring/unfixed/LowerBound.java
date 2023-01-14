package phase3_graphcolouring.unfixed;

import java.util.ArrayList;
import java.util.Collections;

public class LowerBound
{

    public static int loveIt = 0;
    static int checkycheck;

    public static int checkForCompleteGraph(int maxDegree, int numOfVertices, int numOfEdges,
                                                ArrayList<ArrayList<Integer>> checkValues, Integer[] storeEdgeArray, int upperBound) {

        ArrayList<Integer> posNumsComplete = getFrecquencyOfNumEdges(numOfVertices, storeEdgeArray);

        Collections.sort(posNumsComplete, Collections.reverseOrder());

        //System.out.println(posNumsComplete + " pos Num");
        for (int x = 0; x < posNumsComplete.size(); x++) {
            if (posNumsComplete.get(x) >= 1 && posNumsComplete.get(x) < upperBound) { // we do not need to check for one edge
                checkycheck = posNumsComplete.get(x) + 1; // number of vertices we need
                int dimension = 0;
                ArrayList<ArrayList<Integer>> hereWeGoAgain = new ArrayList<ArrayList<Integer>>();

                for (int i = 0; i <= numOfVertices; i++) { // can be improves oops
                    hereWeGoAgain.add(new ArrayList());
                }

                // here I start to decrease from the highest to 1
                for (int i = numOfVertices; i > 0; i--) {
                    ArrayList<Integer> forTesting = new ArrayList<Integer>();
                    int counterMaxDegree = 0;
                    int counterEdges = 0;
                    forTesting.add(i);
                    while (counterEdges <= numOfEdges) { // while we still have edges to check if the vertice number is
                        // part of, this code will run
                        if (checkValues.get(counterEdges).contains(i)) { // if the vertice number is part of the
                            // vertices the edge connects
                            checkValues.get(counterEdges).remove(Integer.valueOf(i)); // remove the vertice number
                            int m = checkValues.get(counterEdges).get(0); // then store the other vertice that our
                            // current i is connected to in the array
                            checkValues.get(counterEdges).add(i); // and then add our current i back to it, because we
                            // need it for the next vertice number too
                            forTesting.add(m);
                        }
                        counterEdges += 1; // and then look for the next edge
                    }
                   // System.out.println(forTesting + "test");

                    // only add the combinations that are bigger or equal to the number of vertices
                    // we need
                    if (forTesting.size() >= checkycheck) {
                        for (int s : forTesting) {
                            hereWeGoAgain.get(dimension).add(s);
                        }
                        dimension += 1;
                    }
                }

                // order the array from smallest to biggest
                for (int i = 0; i < (hereWeGoAgain.size() - 1); i++) {
                    if ((hereWeGoAgain.get(i).size()) > (hereWeGoAgain.get(i + 1).size())) {
                        Collections.swap(hereWeGoAgain, i, i + 1);
                        i = -1;
                    }
                }

                //System.out.println("arrays: " + hereWeGoAgain);

                // remove empty dimensions
                ArrayList<ArrayList<Integer>> whySoComplicated = new ArrayList<ArrayList<Integer>>();
                int counter = 0;
                for (int i = 0; i < hereWeGoAgain.size(); i++) { // can be improves oops
                    if (!hereWeGoAgain.get(i).isEmpty()) {
                        whySoComplicated.add(new ArrayList());
                        for (int theValue : hereWeGoAgain.get(i)) {
                            whySoComplicated.get(counter).add(theValue);
                        }
                        counter++;
                    }
                }
                //System.out.println(whySoComplicated + " new list");

                // go through every combination
                for (int h = 0; h < whySoComplicated.size(); h++) {
                    int countycount = 0;

                    // if the dimension is the size of vertices we're looking for we can enter this
                    if (whySoComplicated.get(h).size() == checkycheck) {
                        // we need the next combination
                        for (int g = h + 1; g < whySoComplicated.size(); g++) {
                            // then we sort both
                            //Collections.sort(whySoComplicated.get(g));
                           // Collections.sort(whySoComplicated.get(h));
                            // and then we look if the second one, cause that might be bigger than the first
                            // one, contains all elements from the first one, which has to have all vertices
                            // we need cause it has the size we are looking for
                            if (whySoComplicated.get(g).containsAll(whySoComplicated.get(h))) {
                                // if we find enough combinations with the same vertices we can just return that
                                // as lower bound
                                countycount += 1;
                                //System.out.println("here");
                                if (countycount == checkycheck) {
                                    System.out.println("LOWER BOUND IS " + checkycheck);
                                    return checkycheck;

                                }
                            }

                        }
                    }

                    // otherwise it's getting complicated and this part of the code is not finished
                    // yet
                    else {
                        ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
                        for (int i = 0; i <= whySoComplicated.size(); i++) { // can be improves oops
                            newList.add(new ArrayList());
                        }

                        // here we find all vertices of other combinations that contain the same number
                        // as the first one
                        /*
                        int counterIdkWhichOne = 1;
                        for (int g = h + 1; g < whySoComplicated.size(); g++) {
                            for (int testVariable : whySoComplicated.get(h)) {
                                if (whySoComplicated.get(g).contains(testVariable)) {
                                    newList.get(counterIdkWhichOne).add(testVariable);
                                    newList.get(0).add(testVariable);

                                }
                            }
                            counterIdkWhichOne += 1;
                        }
                        System.out.println(whySoComplicated + "complicated ");
                        System.out.println(newList + " new list");
                         */


                        // here I'm looking how many combinations we have left that are bigger or equal
                        // to the number of vertices we need
                        int anotherCounter = 0;
                        int andAnotherOne = 0;
                        ArrayList<ArrayList<Integer>> thisShouldBeTheLast = new ArrayList<ArrayList<Integer>>();
                        for (int iNeedNewVarNames = 0; iNeedNewVarNames < whySoComplicated.size(); iNeedNewVarNames++) {
                            if (whySoComplicated.get(iNeedNewVarNames).size() >= checkycheck) {
                                thisShouldBeTheLast.add(new ArrayList());
                                for (int addingThis : whySoComplicated.get(iNeedNewVarNames)) {
                                    if (!thisShouldBeTheLast.get(anotherCounter).contains(addingThis)) {
                                        thisShouldBeTheLast.get(anotherCounter).add(addingThis);
                                    }
                                }
                                //Collections.sort(thisShouldBeTheLast.get(anotherCounter));
                                anotherCounter += 1;
                                if (anotherCounter >= checkycheck) {
                                    andAnotherOne += 1;
                                }
                            }
                        }

                        // here we want the biggest one in front because otherwise I'll have a lot of
                        // empty ones
                        for (int i = 0; i < (thisShouldBeTheLast.size() - 1); i++) {
                            if ((thisShouldBeTheLast.get(i).size()) > (thisShouldBeTheLast.get(i + 1).size())) {
                                Collections.swap(thisShouldBeTheLast, i, i + 1);
                                i = -1;
                            }
                        }
                        //System.out.println(thisShouldBeTheLast + " last ");
                        //System.out.println(checkycheck);
                        // and here I'm not done now. What I need to do is actually to get all the
                        // combinations of n over k to check it as I did with the "if x is the same as
                        // before"
                        // this crashed my program before multiple times because the combinations will
                        // be too many so I either need another solution
                        // or figure out how my program does not crash
                        // so this here will not really work for all graphs:
                        if (andAnotherOne != 0) {
                            for (int f = 0; f < thisShouldBeTheLast.size(); f++) {
                               //System.out.println("check" + checkycheck);
                                //System.out.println("f" + thisShouldBeTheLast.get(f));
                                ArrayList<ArrayList<Integer>> combinations = sort(thisShouldBeTheLast.get(f), checkycheck);
                                for(int t = 0; t < combinations.size(); t++){
                                    countycount = 0;
                                   // System.out.println("raus hier");
                                    //System.out.println("somehow I am here");
                                    for (int g = h + 1; g < thisShouldBeTheLast.size(); g++) {
                                        //System.out.println(g + "g");


                                        if(thisShouldBeTheLast.get(g).containsAll(combinations.get(t))){
                                            //System.out.println(countycount + "count");
                                           //System.out.println(combinations.get(t) + "t");
                                           //System.out.println(thisShouldBeTheLast.get(g));
                                            countycount += 1;
                                            //System.out.println(countycount);
                                            if (countycount == checkycheck) {
                                                System.out.println("LOWER BOUND IS " + checkycheck);
                                                return checkycheck;

                                            }
                                        }
                                        // and then we look if the second one, cause that might be bigger than the first
                                        // one, contains all elements from the first one, which has to have all vertices
                                        // we need cause it has the size we are looking for



                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        System.out.println("LOWER BOUND IS 2");
        return 2;
    }
    public static ArrayList<ArrayList<Integer>> sort(ArrayList<Integer> dimensions, int edgeNum) {

        int toAdd = dimensions.get(0);
        ArrayList<Integer> indexes = new ArrayList<>();
        ArrayList<Integer> combo = new ArrayList<>();
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();

        ArrayList<ArrayList<Integer>> combinationsReturn = new ArrayList<>();
        for (int x = 1; x <= edgeNum; x++) {
            indexes.add(x);
        }
        int addIndex = 0;



        combinations.add(new ArrayList<>());
        for (int whatever = 0; whatever < indexes.size(); whatever++) {
            combinations.get(addIndex).add(indexes.get(whatever));
        }
        addIndex += 1;
        int change = edgeNum - 2;

        while (indexes.get(0) != (dimensions.size() - (edgeNum - 1)) || indexes.get(edgeNum - 1) != dimensions.size()) {
            if (indexes.get(edgeNum - 1) >= dimensions.size()) {
                if ((indexes.get(change) + 1) == indexes.get(change + 1)) {
                    if (change == 0) {
                        change += 1;
                    } else {
                        change -= 1;
                    }
                }

                int changeNum = indexes.get(change);
                indexes.remove(change);
                indexes.add(change, changeNum + 1);
                int x = changeNum + 1;
                for (int y = change; y < indexes.size() - 1; y++) {
                    indexes.remove(y + 1);
                    indexes.add(y + 1, x + 1);
                    x += 1;
                }

                combinations.add(new ArrayList<>());
                for (int whatever = 0; whatever < indexes.size(); whatever++) {
                    combinations.get(addIndex).add(indexes.get(whatever));
                }
                addIndex += 1;
                //System.out.println(combinations);

            } else {
                int newIndex = indexes.get(edgeNum - 1);
                indexes.remove(edgeNum - 1);
                newIndex += 1;
                indexes.add(edgeNum - 1, newIndex);
                combinations.add(new ArrayList<>());
                for (int whatever = 0; whatever < indexes.size(); whatever++) {
                    combinations.get(addIndex).add(indexes.get(whatever));
                }
                addIndex += 1;
                //System.out.println(combinations);
            }
        }
        //System.out.println(combinations + " combis");

        for (int rewrite = 0; rewrite < combinations.size(); rewrite++) {
            if (combinations.get(rewrite).get(0) == 1) {
                combinationsReturn.add(new ArrayList<>());
                for (int changeNum = 0; changeNum < combinations.get(rewrite).size(); changeNum++) {
                    int index = combinations.get(rewrite).get(changeNum) - 1;
                    combinationsReturn.get(rewrite).add(dimensions.get(index));
                }
            }
        }
        //System.out.println(combinationsReturn);
        return combinationsReturn;
    }



    public static ArrayList<Integer> getFrecquencyOfNumEdges(int v, Integer[] storeEdgeArray) {
        int[][] array = new int[v][2];
        int[] helperArray = new int[v];
        ArrayList<Integer> numbersThatMightBeComplete = new ArrayList<Integer>();

        // here we get all the frequencies of every edge and then store the numbers
        for (int x = v; x > 0; x--) {
            int count = 0;
            for (int y = 0; y < storeEdgeArray.length; y++) {
                if (x == storeEdgeArray[y]) {
                    count++;
                }
            }
            helperArray[x - 1] = count;
            array[x - 1][0] = count;
            array[x - 1][1] = x;
        }

        //System.out.println(Arrays.deepToString(array));
        //System.out.println(Arrays.toString(helperArray));


        // find nums of vertices where the edge number is bigger or equal to the egdenum
        // we're looking at
        int testVar = 0;
        for (int x = 0; x < helperArray.length; x++) {
            if (testVar < helperArray[x]) {
                testVar = helperArray[x];

                //System.out.println(testVar);
            }
        }


        int[][] arrayWithHäufigkeit = new int[testVar][2];

        // get an array with frequencies of edges added up
        while (testVar > 1) {
            int count2 = 0;
            for (int y = 0; y < helperArray.length; y++) {
                if (testVar <= array[y][0]) {
                    count2++;
                }
            }

            arrayWithHäufigkeit[testVar - 1][0] = testVar;
            arrayWithHäufigkeit[testVar - 1][1] = count2;
            testVar -= 1;
        }

        // add edge numbers that might be complete to an array
        for (int x = arrayWithHäufigkeit.length; x > 0; x--) {
            int checker = (arrayWithHäufigkeit[x - 1][0] + 1);
            if (checker <= arrayWithHäufigkeit[x - 1][1]) {
                numbersThatMightBeComplete.add(arrayWithHäufigkeit[x - 1][0]);
            }
        }
        //System.out.println(numbersThatMightBeComplete);
        return numbersThatMightBeComplete;
    }

    public static int  runLowerBound(int upperBound) {
        ReadGraph rg = new ReadGraph();
        int numOfVertices = rg.n;
        int maxDegree = rg.upperBound; // Upper bound
        int numOfEdges = (rg.m) - 1; // Edges -1
        ArrayList<ArrayList<Integer>> checkvalues = rg.passDynamicArray();
        int lowerBound = checkForCompleteGraph(maxDegree, numOfVertices, numOfEdges, checkvalues, rg.storeEdgeArray, upperBound);
        return lowerBound;
    }
}
