import java.util.ArrayList;
import java.util.Hashtable;

public class Graph{

    Hashtable <String, Node> nodes;

    public Graph(){
        nodes = new Hashtable<>();
    }

    public void addNode(String id){
        nodes.put(id, new Node(id));
    }

    public void addArc (String source, String destination, int peso){
        nodes.get(source).addSuccessor(destination, peso);
    }

    public ArrayList<Pair> getSuccessor(String id){
        return nodes.get(id).getSuccessors();
    }

    public int getSize(){
        return nodes.size();
    }


}