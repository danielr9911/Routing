import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by cristyansepulveda on 20/04/16.
 */
public class ParejaII {
    int a;
    int b;


    public ParejaII(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int getA(){
        return this.a;
    }

    public int getB(){
        return this.b;
    }

    @Override
    public boolean equals(Object pareja){

        if(pareja instanceof ParejaII) {

            ParejaII acomparar = (ParejaII) pareja;

            return this.a == acomparar.getA() && this.b == acomparar.getB();
        }

        return false;
    }
}
