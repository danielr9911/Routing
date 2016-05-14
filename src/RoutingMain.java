import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Daniel on 12/05/2016.
 */
public class RoutingMain {
    public static void main(String[] args) throws IOException {
        System.out.println("El Viernes a las 12m");
        Graph map = createGraph("./nodes.txt");
        map = createArcs("./arc.txt", map);
    }

    private static Graph createGraph(String file) throws IOException {
        Graph map = new Graph();
        String cadena;
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            map.addNode(cadena);
        }
        b.close();
        return map;
    }

    private static Graph createArcs(String file, Graph map) throws IOException {
        String cadena;
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            String[] words = cadena.split(" ");
            String source = words[0];
            String destination = words[1];
            String weight = words[2];
            map.addArc(source, destination, weight);
        }
        b.close();
        return map;
    }
}
