import java.util.ArrayList;

/**
 * Created by cristyansepulveda on 20/04/16.
 */
public class ParejaAI {
    ArrayList<Integer> camino;
    int peso;

    public ParejaAI(ArrayList<Integer> camino, int peso){
        this.camino = camino;
        this.peso = peso;


    }

    public ArrayList<Integer> getCamino(){
        return this.camino;
    }

    public int getPeso(){
        return this.peso;
    }

}
