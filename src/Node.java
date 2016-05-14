import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Daniel on 14/05/2016.
 */
public class Node {

    String id;


    ArrayList<Pair> successors = new ArrayList<>();

    public Node(String id){
        this.id = id;
    }

    public void addSuccessor(String id, int peso){
        successors.add(new Pair(id, peso));
    }

    public ArrayList<Pair> getSuccessors() {
        return successors;
    }
}
