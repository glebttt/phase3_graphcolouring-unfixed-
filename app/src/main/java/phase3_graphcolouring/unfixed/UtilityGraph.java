package phase3_graphcolouring.unfixed;

import java.util.ArrayList;

public class UtilityGraph {
    public boolean utility = false;
    public UtilityGraph (int[][] edges){
        isUtilityGraph(edges);
    }
    public boolean isUtilityGraph(int[][] edges) {
        if (edges.length != 9) {
            return false;
        }
        ArrayList<Integer> vertices = new ArrayList<>();
        for (int i=0; i<edges.length;i++){
            if (!vertices.contains(edges[i][0])) {
                vertices.add(edges[i][0]);
            }
            if (!vertices.contains(edges[i][1])) {
                vertices.add(edges[i][1]);
            }
        }

        if (vertices.size() != 6) {
            return false;
        }

        for (int i=0; i<vertices.size(); i++) {
            int amount=0;
            for (int k=0; k<edges.length; k++) {
                if (edges[k][0]==vertices.get(i) || edges[k][1]==vertices.get(i)){
                    amount++;
                }
            }
            if (amount!=3){
                return false;
            }
        }
        ArrayList<Integer> red = new ArrayList<>();
        ArrayList<Integer> blue = new ArrayList<>();

        red.add(vertices.get(0));

        
        int vertice = vertices.get(0);

        ArrayList<Integer> secondConnections = new ArrayList<>();

        for (int k=0; k<edges.length; k++) {
            if (vertice == edges[k][0]){
                secondConnections.add(edges[k][1]);
            }
            if (vertice == edges[k][1]) {
                secondConnections.add(edges[k][0]);
            }
        }
        for (int i=0; i<secondConnections.size(); i++){
            blue.add(secondConnections.get(i));
        }

        ArrayList<Integer> thirdConnections = new ArrayList<>();
        for (int i=0; i<secondConnections.size(); i++){
            for (int k=0; k<edges.length; k++) {
                if (secondConnections.get(i) == edges[k][0]){
                    thirdConnections.add(edges[k][1]);
                }
                if (secondConnections.get(i) == edges[k][1]) {
                    thirdConnections.add(edges[k][0]);
                }
            }

        }
        for (int i=0; i<thirdConnections.size(); i++){
            if (!red.contains((thirdConnections).get(i))){
                red.add(thirdConnections.get(i));
            }
           
        }
        
        if (red.size()!=3 || blue.size()!=3){
            return false;
        }
        utility=true;
        return true;
    }
    
}
