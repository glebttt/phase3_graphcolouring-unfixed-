import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

class ColEdge {
	int u;
	public int v;
}

public class ReadGraph {

	public final static boolean DEBUG = true;
	public final static String COMMENT = "//";
	public static int globalVertices;
	static int ourStaticTable[][];
	static int upperBound = 0; // Upper bound
	static ArrayList<Integer> storeEdge = new ArrayList<Integer>();
	static Integer[] storeEdgeArray;
	// ! n is the number of vertices in the graph
	static int n = -1;
	// ! m is the number of edges in the graph
	static int m = -1;

	public static int[][] passStaticArray() {
		int[][] ourStaticArray = new int[((storeEdgeArray).length) / 2][2];
		int b = 0; // increments storeEdgeArray separately
		for (int i = 0; i < (storeEdgeArray.length) / 2; i++) { // both for loops
			for (int j = 0; j < 2; j++) {
				ourStaticArray[i][j] = storeEdgeArray[b]; // Turning single dimensional storeEdgeArray[] into
															// ourStaticArray[][]
				b++;
			}
		}
		return ourStaticArray; // To do: Figure out how to pass actual array not has value
	}

	public static ArrayList passDynamicArray() {
		ArrayList<ArrayList<Integer>> ourDynamicArray = new ArrayList<ArrayList<Integer>>(storeEdgeArray.length);

		for (int i = 0; i < (storeEdgeArray.length) / 2; i++) {
			ourDynamicArray.add(new ArrayList());
		}
		int a = 0;
		for (int i = 0; i < (storeEdgeArray.length) / 2; i++) {
			for (int j = 0; j < 2; j++) {
				// System.out.println("a " + j + " i " + i);
				ourDynamicArray.get(i).add(storeEdgeArray[a]);
				a++;
			}

		}
		return ourDynamicArray;
	}

	public static void main(String args[]) throws IOException {

		chromaticNumbers cn = new chromaticNumbers();
		upperBound ub = new upperBound();
		lowerBound lb = new lowerBound();
		//bruteForce bf = new bruteForce();

		ArrayList<ArrayList<Integer>> ourTable = new ArrayList<ArrayList<Integer>>(); // 2D ArrayList storing the table
		if (args.length < 1) {
			System.out.println("Error! No filename specified.");
			System.exit(0);
		}

		String inputfile = args[0];

		boolean seen[] = null;

		// ! e will contain the edges of the graph
		ColEdge e[] = null;

		try {
			FileReader fr = new FileReader(inputfile);
			BufferedReader br = new BufferedReader(fr);

			String record = new String();

			// ! THe first few lines of the file are allowed to be comments, staring with a
			// // symbol.
			// ! These comments are only allowed at the top of the file.

			// ! -----------------------------------------
			while ((record = br.readLine()) != null) {
				if (record.startsWith("//"))
					continue;
				break; // Saw a line that did not start with a comment -- time to start reading the
						// data in!
			}

			if (record.startsWith("VERTICES = ")) {
				n = Integer.parseInt(record.substring(11));
				if (DEBUG)
					System.out.println(COMMENT + " Number of vertices = " + n);
			}

			seen = new boolean[n + 1];

			record = br.readLine();

			if (record.startsWith("EDGES = ")) {
				m = Integer.parseInt(record.substring(8));
				if (DEBUG)
					System.out.println(COMMENT + " Expected number of edges = " + m);
			}

			e = new ColEdge[m];

			for (int d = 0; d < m; d++) {
				if (DEBUG)
					System.out.println(COMMENT + " Reading edge " + (d + 1));
				record = br.readLine();
				String data[] = record.split(" ");
				if (data.length != 2) {
					System.out.println("Error! Malformed edge line: " + record);
					System.exit(0);
				}
				e[d] = new ColEdge();

				e[d].u = Integer.parseInt(data[0]);
				storeEdge.add(e[d].u); // elements are being stored into the arraylist
				e[d].v = Integer.parseInt(data[1]);
				storeEdge.add(e[d].v); // elements are being stored into the arraylist

				seen[e[d].u] = true;
				seen[e[d].v] = true;

				if (DEBUG)
					System.out.println(COMMENT + " Edge: " + e[d].u + " " + e[d].v);

			}

			String surplus = br.readLine();
			if (surplus != null) {
				if (surplus.length() >= 2)
					if (DEBUG)
						System.out.println(
								COMMENT + " Warning: there appeared to be data in your file after the last edge: '"
										+ surplus + "'");
			}

			globalVertices = n;

		} catch (IOException ex) {
			// catch possible io errors from readLine()
			System.out.println("Error! Problem reading file " + inputfile);
			System.exit(0);
		}

		for (int x = 1; x <= n; x++) {
			if (seen[x] == false) {
				if (DEBUG)
					System.out.println(COMMENT + " Warning: vertex " + x
							+ " didn't appear in any edge : it will be considered a disconnected vertex on its own.");
			}
		}

		// ! At this point e[0] will be the first edge, with e[0].u referring to one
		// endpoint and e[0].v to the other
		// ! e[1] will be the second edge...
		// ! (and so on)
		// ! e[m-1] will be the last edge
		// !
		// ! there will be n vertices in the graph, numbered 1 to n

		// ! INSERT YOUR CODE HERE!

		int mostFrequentEdge = 0;

		storeEdgeArray = storeEdge.toArray(new Integer[0]); // we created a new array containing every element of the
															// arraylist

		checkGraphComplete cGC = new checkGraphComplete();


		long firstTime = System.currentTimeMillis();

		upperBound = ub.runUpperBound();

		int lowerBound = lb.runLowerBound(upperBound);

		bruteForce bF = new bruteForce();


		long midTime = System.currentTimeMillis() - firstTime;
		System.out.println("Time needed for upper and lower bound: " + (midTime/1000) + " seconds.");


		if(lowerBound == upperBound){
			System.out.println("CHROMATIC NUMBER IS " + lowerBound);
		}
		else{
		Heawood heawood = new Heawood(passStaticArray());
		if (heawood.isHeawood){System.out.println("CHROMATIC NUMBER IS 2. (heawood graph)");}

		//System.out.println("part 1");
		PetersenGraph petersen = new PetersenGraph(passStaticArray());
		if (petersen.isPete){ System.out.println("CHROMATIC NUMBER IS 3 (petersen graph)");}


		UtilityGraph uG = new UtilityGraph(passStaticArray());
		if(uG.utility){
			System.out.println("CHROMATIC NUMBER IS 2");
		}

		FindGrid fG = new FindGrid();
		fG.runGridGraph();



		//System.out.println("part 2");
		TriangleFreeChecker checkTriangles = new TriangleFreeChecker(passStaticArray());

		boolean completeGraph = cGC.checkGraphComplete(n);

		boolean treeGraph = false;
		if(completeGraph == true){
			//System.out.println("Part x");
			System.out.println("CHROMATIC NUMBER IS " + n);
		}
		else if (checkTriangles.triangleFree) {
			//System.out.println("part 3");
			Tree tree = new Tree(e, storeEdgeArray);
			if(tree.isTree){

				//System.out.println("part 4");
				System.out.println("CHROMATIC NUMBER IS 2");
			}
			else {
				boolean isBipartite = FindBipartite.runBipartite(n);
				//boolean isBipartite = false;
				if(isBipartite){

					//System.out.println("part 5");
					System.out.println("CHROMATIC NUMBER IS 2");
				}
				else{
					//System.out.println("part 6");

					System.out.println("RUNNING BACKTRACKING... ");
					cn.runChromaticNumber();

					System.out.println("RUNNING BRUTE FORCE... ");

					bF.runBruteForce();
				}

			}
		}
		else{
			//System.out.println("part 6");

			System.out.println("RUNNING BACKTRACKING... ");

			cn.runChromaticNumber();

			long backtracking = System.currentTimeMillis() - firstTime;
			System.out.println("Time needed for backtracking: " + (backtracking/1000) + " seconds.");

			System.out.println("RUNNING BRUTE FORCE... ");

			bF.runBruteForce();

			long bruteforce = System.currentTimeMillis() - backtracking;
			System.out.println("Time needed for brute force: " + (bruteforce/1000) + " seconds.");
		}
		}

		long secondTime = System.currentTimeMillis() - firstTime;
		System.out.println("Time needed: " + (secondTime/1000) + " seconds.");









		/*

		if (!isBi && !treeGraph && !heawood.isHeawood && !petersen.isPete){

			//cn.runChromaticNumber();
		}
*/
		//bf.runBruteForce();

	}
}
