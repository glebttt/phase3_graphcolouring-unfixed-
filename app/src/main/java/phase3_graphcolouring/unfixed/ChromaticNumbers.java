package phase3_graphcolouring.unfixed;

import java.io.IOException;
import java.util.ArrayList;

class ChromaticNumbers
{

    /* can be done recursive... */

    /*
     * goal: backtrack until we find a solution. That means that we start at one
     * color less than our upper bound (greedy algorithm) gave us, since we know
     * that there is a combination for that color number.
     * then we assign the vertices one by one to a color that fits. If no color fits
     * we go back to the former vertix and try to assign that one to another color
     * and so on until we either get a combination that works (we stop there and try
     * it with less colors) or until no vertix can be assigned to another color
     * anymore and we know there is no possible solution for that number of colors
     */
    public static boolean callColorChecker(int numberOfColors, ArrayList<ArrayList<Integer>> graph, int numOfEdges,
            int numOfVertices) throws IOException {

        //System.out.println(numberOfColors);
        /*
         * initialize multidimensional Arraylist with number of colors we want to test
         */
        ArrayList<ArrayList<Integer>> colorArray = new ArrayList<>();
        for (int i = 0; i < numberOfColors; i++) {
            colorArray.add(new ArrayList());
        }

        int index = 0;
        /* loop through every number we have */
        for (int x = 0; x < numOfVertices;) {
            //System.out.println("current vertix: " + (x + 1)); // testing
            boolean checkVariable = checkColors(colorArray, index, numOfEdges, x + 1, graph); // check if we can assign
                                                                                              // one of the colors to
                                                                                              // the current vertix
            if (x >= 0) { // since we backtrack and decrease the number of vertices at some point we have
                          // to make sure that we don't get negative vertice numbers, because then we get
                          // into an infinity loop
                if (checkVariable == true) {
                   // System.out.println("this was true"); // testing
                    index = 0; // then we go back to the first color
                    //writeBacktracking(colorArray, (x + 1)); // log file
                    x++; // and take the next vertix
                } else { // if we can not assign it to a color
                    //writeBacktrackingFalse(colorArray, (x + 1)); // log file
                    //System.out.println("this was false"); // testing
                    int z = 0;
                    while (z < colorArray.size()) {
                        if (colorArray.get(z).contains(x)) { // we go though every color
                            int indexSearched = colorArray.get(z).indexOf(x); // and look for the colorindex of the last
                                                                              // vertix (not the current one)
                            colorArray.get(z).remove(indexSearched); // then we remove the former vertix from the color
                            index = z + 1; // and save the index as the next color for the former vertix
                            break;
                        }
                        z++;
                    }
                    x--; // and here we get the former vertix
                }
                //System.out.println(colorArray); // testing
            } else {
                //System.out.println("false"); // testing
                return false;
            }

        }
        /*
         * just for testing: this only checks if every number is included in the array
         * in the end
         */
        for (int y = 1; y <= numOfVertices; y++) {
            int counter = 0;
            for (int a = 0; a < colorArray.size(); a++) {
                if (colorArray.get(a).contains(y)) {
                    counter++;
                }
            }
            if (counter == 0) {
               // System.out.println("missing: " + y);
            } else if (counter > 1) {
               // System.out.println("this is more than once here: " + y);
            } else {
               // System.out.println(" number is included: " + y);
            }
        }
        /* log file
        try {
            write(colorArray);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

         */
        //System.out.println(colorArray);
        return true; // we found a combination the color
    }

    public static boolean checkColors(ArrayList<ArrayList<Integer>> colorArray, int index, int numOfEdges,
            int currentVertice, ArrayList<ArrayList<Integer>> graph) throws IOException {
        ArrayList<Integer> connectedVertices = getAllConnections(numOfEdges, graph, currentVertice);
        // System.out.println(connectedVertices);
        int counter = 0;
        while (index < colorArray.size()) { // we start at the index we give the method
            int compareIndex = index;
            for (int s : connectedVertices) { // we loop through the connected Vertices
                if (colorArray.get(index).contains(s)) { // and look if the colorArray contains one of those vertices.
                    index++; // if so we check the next color
                    break;
                }
            }
            if (index == compareIndex) { // if we did not need to check another color we can just assign the current
                                         // vertix to the current color
                colorArray.get(index).add(currentVertice);
                return true; // and return that we found a color where we could add our vertix to
            }
        }
        return false; // if we could not find a fitting color we return false and backtrack
    }

    /* here we get all the connected vertixes to our current vertix */
    public static ArrayList<Integer> getAllConnections(int numOfEdges, ArrayList<ArrayList<Integer>> graph,
            int currentVertice) throws IOException {
        ArrayList<Integer> connectedVertices = new ArrayList<Integer>();
        int counterEdges = 0;
        while (counterEdges <= numOfEdges) {
            if (graph.get(counterEdges).contains(currentVertice)) {
                graph.get(counterEdges).remove(Integer.valueOf(currentVertice));
                int m = graph.get(counterEdges).get(0);
                graph.get(counterEdges).add(currentVertice);
                connectedVertices.add(m);
            }
            counterEdges += 1;
        }

        //System.out.println(currentVertice + " connections: " + connectedVertices); // testing
        //writeCombis(connectedVertices, currentVertice); // log file
        return connectedVertices;
    }

    /*
     * if we found a combination we will decrease the number of colors, otherwise
     * we'll return one number of colors more than the current number
     */
    public static int addColorIfNeeded(int numberOfColors, ArrayList<ArrayList<Integer>> graph, int edges,
            int numOfVertices) throws IOException {
        boolean checkIfItWorks = callColorChecker(numberOfColors, graph, edges, numOfVertices);
        //System.out.println(checkIfItWorks);
        while (checkIfItWorks == true) {
            System.out.println("NEW UPPER BOUND IS " + (numberOfColors + 1));
            numberOfColors -= 1;
            checkIfItWorks = callColorChecker(numberOfColors, graph, edges,
                    numOfVertices);
        }
        System.out.println("CHROMATIC NUMBER IS " + (numberOfColors + 1));
        return numberOfColors + 1;
    }

    /*
     * the following three methods are only for the logfiles of the backtracking,
     * the combinations and the color combinations

    public static void write(ArrayList<ArrayList<Integer>> x) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("filenameColorArray.txt", true));
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < x.get(i).size(); j++) {
                // Maybe:
                outputWriter.write(x.get(i).get(j) + " ");

            }
            outputWriter.write(" || ");

        }
        outputWriter.newLine();
        outputWriter.flush();
        outputWriter.close();

    }

    public static void writeCombis(ArrayList<Integer> x, int z) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("filenameCombis.txt", true));
        outputWriter.write("num: " + z);
        outputWriter.newLine();
        for (int i = 0; i < x.size(); i++) {
            // Maybe:
            outputWriter.write(x.get(i) + " ");
        }
        outputWriter.newLine();

        outputWriter.flush();
        outputWriter.close();

    }

    public static void writeBacktracking(ArrayList<ArrayList<Integer>> x, int z) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("filenameBacktracking.txt", true));

        outputWriter.write("num: " + z);

        outputWriter.newLine();
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < x.get(i).size(); j++) {
                // Maybe:
                outputWriter.write(x.get(i).get(j) + " ");

            }
            outputWriter.write(" || ");

        }
        outputWriter.newLine();
        outputWriter.flush();
        outputWriter.close();

    }

    public static void writeBacktrackingFalse(ArrayList<ArrayList<Integer>> x, int z) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("filenameBacktracking.txt", true));
        outputWriter.write("num: " + z);
        outputWriter.newLine();
        outputWriter.write("false");
        outputWriter.newLine();
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < x.get(i).size(); j++) {
                // Maybe:
                outputWriter.write(x.get(i).get(j) + " ");

            }
            outputWriter.write(" || ");

        }
        outputWriter.newLine();
        outputWriter.flush();
        outputWriter.close();

    }

     */

    public static int runChromaticNumber() throws IOException {

        ReadGraph rg = new ReadGraph();
        int numOfVertices = rg.n;
        int numOfColors = rg.upperBound - 1; // Upper bound
        int numOfEdges = (rg.m) - 1; // Edges -1
        ArrayList<ArrayList<Integer>> graph = rg.passDynamicArray();

       // System.out.println("timer starts");
        int chromNum = addColorIfNeeded(numOfColors, graph, numOfEdges, numOfVertices);
        //System.out.println("timer ends");
        //System.out.println(numOfVertices);
        return chromNum;

    }



}
