import java.util.ArrayList;

public class Graph {
    private ArrayList<ArrayList<Integer>> vertices;
    private ArrayList<ArrayList<Integer>> weights;
    private int size;

    public Graph() {
        vertices = new ArrayList<ArrayList<Integer>>();
        weights = new ArrayList<ArrayList<Integer>>();
    }

    public void addNode(String identifier) {
        //int id = Integer.parseInt(identifier);
        ArrayList<Integer> arcs = new ArrayList<Integer>();
        ArrayList<Integer> ws = new ArrayList<Integer>();
        vertices.add(arcs);
        weights.add(ws);
    }

    public int size() {
        return vertices.size();
    }

    public void addArc(String source, String destination, String weight) {
        int s = Integer.parseInt(source);
        int d = Integer.parseInt(destination);
        int w = Integer.parseInt(weight);
        vertices.get(s).add(d);
        weights.get(s).add(w);
    }

    public int getWeight(int source, int destination) {
        for (int j = 0; j < size; j++) {
            if (vertices.get(source).get(j) == destination)
                return weights.get(source).get(j);
        }
        return -1;
    }

    public ArrayList<Integer> getSuccessors(int vertice) {
        return vertices.get(vertice);
    }
}