package phase3_graphcolouring.unfixed;

import java.util.ArrayList;

public class TriangleFreeChecker {
    public boolean triangleFree = false;
    public TriangleFreeChecker (int[][] edges){
        isTriangleFree(edges);
    }
    public boolean isTriangleFree(int[][] edges) {
        for (int i=0; i<edges.length; i++){
            //System.out.println(i);
            int vertice = edges[i][0];
            ArrayList<Integer> secondConnections = new ArrayList<>();
            for (int k=0; k<edges.length; k++) {
                if (vertice == edges[k][0]){
                    secondConnections.add(edges[k][1]);
                }
                if (vertice == edges[k][1]) {
                    secondConnections.add(edges[k][0]);
                }
            }
            for (int k=0; k<secondConnections.size(); k++){
                ArrayList<Integer> thirdConnections = new ArrayList<>();
                int secondConnection = secondConnections.get(k);
                for (int p=0; p<edges.length; p++) {
                    if (secondConnection == edges[p][0]){
                        thirdConnections.add(edges[p][1]);
                    }
                    if (secondConnection == edges[p][1]) {
                        thirdConnections.add(edges[p][0]);
                    }
                }
                for (int q=0; q<thirdConnections.size(); q++){
                    ArrayList<Integer> lastConnections = new ArrayList<>();
                    int thirdConnection = thirdConnections.get(q);
                    for (int p=0; p<edges.length; p++) {
                        if (thirdConnection == edges[p][0]){
                            lastConnections.add(edges[p][1]);
                        }
                        if (thirdConnection == edges[p][1]) {
                            lastConnections.add(edges[p][0]);
                        }
                    }
                    if (lastConnections.contains(vertice)){
                        return false;
                    }
                }
            }
        }
        triangleFree=true;
        return true;
    }
}
