import java.util.ArrayList;

public class Heawood { //CN=2
    public boolean isHeawood = false;
    public Heawood(int[][] edges){

        isHeawoodGraph(edges);
    }
    public boolean isHeawoodGraph(int[][] edges) {
        if (edges.length != 21) {
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

        if (vertices.size() != 14) {
            return false;
        }
        int s=0;
        for (int i=0; i<edges.length; i++){
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
                        if (edges[p][1]!=vertice){
                            thirdConnections.add(edges[p][1]);
                        }
                    }
                    if (secondConnection == edges[p][1]) {
                        if (edges[p][0]!=vertice){
                            thirdConnections.add(edges[p][0]);
                        }
                    }
                }
                for (int q=0; q<thirdConnections.size(); q++){
                    ArrayList<Integer> fourthConnections = new ArrayList<>();
                    int thirdConnection = thirdConnections.get(q);
                    for (int p=0; p<edges.length; p++) {
                        if (thirdConnection == edges[p][0]){
                            if (edges[p][1]!=secondConnection){
                                fourthConnections.add(edges[p][1]);
                            }
                        }
                        if (thirdConnection == edges[p][1]) {
                            if (edges[p][0]!=secondConnection){
                                fourthConnections.add(edges[p][0]);
                            }
                        }
                    }
                    if (fourthConnections.contains(vertice)){
                        return false;
                    }
                    for (int o=0; o<fourthConnections.size(); o++){
                        ArrayList<Integer> fifthConnections = new ArrayList<>();
                        int fourthConnection = fourthConnections.get(o);
                        for (int p=0; p<edges.length; p++) {
                            if (fourthConnection == edges[p][0]){
                                if (edges[p][1]!=thirdConnection){
                                    fifthConnections.add(edges[p][1]);
                                }
                            }
                            if (fourthConnection == edges[p][1]) {
                                if (edges[p][0]!=thirdConnection){
                                    fifthConnections.add(edges[p][0]);
                                }
                            }
                        }
                        if (fifthConnections.contains(vertice)){
                            return false;
                        }
                        for (int w=0; w<fifthConnections.size(); w++) {
                            ArrayList<Integer> sixConnections = new ArrayList<>();
                            int fifthConnection = fifthConnections.get(w);
                            for (int p=0; p<edges.length; p++) {
                                if (fifthConnection == edges[p][0]){
                                    if (edges[p][1]!=fourthConnection){
                                        sixConnections.add(edges[p][1]);
                                    }
                                }
                                if (fifthConnection == edges[p][1]) {
                                    if (edges[p][0]!=fourthConnection){
                                        sixConnections.add(edges[p][0]);
                                    }
                                }
                            }
                            if (sixConnections.contains(vertice)){
                                return false;
                            }
                            for (int y=0; y<sixConnections.size(); y++) {
                                ArrayList<Integer> lastConnections = new ArrayList<>();
                                int sixConnection = sixConnections.get(y);
                                for (int p=0; p<edges.length; p++) {
                                    if (sixConnection == edges[p][0]){
                                        if (edges[p][1]!=fifthConnection){
                                            lastConnections.add(edges[p][1]);
                                        }
                                    }
                                    if (sixConnection == edges[p][1]) {
                                        if (edges[p][0]!=fifthConnection){
                                            lastConnections.add(edges[p][0]);
                                        }
                                    }
                                }
                                if (lastConnections.contains(vertice)){
                                    s++;
                                }
                            }
                        }

                    }
                }
            }
        }
        if (s==0) {
            return false;
        }

        isHeawood = true;
        return true;
    }


}




