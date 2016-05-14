/**
 * Created by cristyansepulveda on 14/05/16.
 */
public class Pair {

    String id;
    int peso;

    public Pair(String id, int peso){
        this.peso = peso;
        this.id = id;
    }

    public int getPeso(){
        return peso;
    }

    public String getId() {
        return id;
    }
}
