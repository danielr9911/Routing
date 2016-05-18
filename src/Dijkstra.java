/****************************************************
 ***Algoritmo: Dijkstra (One Source Shortest Path)
 ***Tipo: Grafos
 ***Autor: Jhosimar George Arias Figueroa
 ****************************************************/

/*
EJEMPLO DE INPUT
5 9
1 2 7
1 4 2
2 3 1
2 4 2
3 5 4
4 2 3
4 3 8
4 5 5
5 3 5
1
*/




import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Dijkstra {
	
	//similar a los defines de C++
	static final int MAX = 15000;  //maximo numero de v�rtices
	static final int INF = 1<<30;  //definimos un valor grande que represente la distancia infinita inicial, basta conque sea superior al maximo valor del peso en alguna de las aristas

	//En el caso de java usamos una clase que representara el pair de C++
	static class Node implements Comparable<Node>{
		int first, second;
		Node( int d , int p ){							//constructor
			this.first = d;
			this.second = p;
		}
		public int compareTo( Node other){				//es necesario definir un comparador para el correcto funcionamiento del PriorityQueue
			if( second > other.second ) return 1;
			if( second == other.second ) return 0;
			return -1;
		}
	};
	
	static Scanner sc = new Scanner( System.in );	   //para lectura de datos
	static List< List< Node > > ady = new ArrayList< List< Node > >(); //lista de adyacencia
	static int distancia[ ] = new int[ MAX ];          //distancia[ u ] distancia de v�rtice inicial a v�rtice con ID = u
	static boolean visitado[ ] = new boolean[ MAX ];   //para v�rtices visitados
	static PriorityQueue< Node > Q = new PriorityQueue<Node>(); //priority queue propia de Java, usamos el comparador definido para que el de menor valor este en el tope
	static int V;                                      //numero de vertices
	static int previo[] = new int[ MAX ];              //para la impresion de caminos
	static ArrayList<Integer> camino = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> caminos = new ArrayList<>();
	//funci�n de inicializaci�n
	static void init(){
	    for( int i = 0 ; i <= V ; ++i ){

			//System.out.println(i);

	        distancia[ i ] = INF;  //inicializamos todas las distancias con valor infinito
	        visitado[ i ] = false; //inicializamos todos los v�rtices como no visitados
	        previo[ i ] = -1;      //inicializamos el previo del vertice i con -1
	    }
	}

	//Paso de relajacion
	static void relajacion( int actual , int adyacente , int peso ){
	    //Si la distancia del origen al vertice actual + peso de su arista es menor a la distancia del origen al vertice adyacente
	    if( distancia[ actual ] + peso < distancia[ adyacente ] ){
	        distancia[ adyacente ] = distancia[ actual ] + peso;  //relajamos el vertice actualizando la distancia
	        previo[ adyacente ] = actual;                         //a su vez actualizamos el vertice previo
	        Q.add( new Node( adyacente , distancia[ adyacente ] ) ); //agregamos adyacente a la cola de prioridad
	    }
	}

	//Impresion del camino mas corto desde el vertice inicial y final ingresados
	static void print( int destino ){
	    if( previo[ destino ] != -1 )    //si aun poseo un vertice previo
	        print( previo[ destino ] );  //recursivamente sigo explorando
	    System.out.printf("%d " , destino );        //terminada la recursion imprimo los vertices recorridos
		camino.add(destino);
	}


	static void dijkstra( int inicial ,ArrayList<Integer> query ){
	    init(); //inicializamos nuestros arreglos
	    Q.add( new Node( inicial , 0 ) ); //Insertamos el v�rtice inicial en la Cola de Prioridad
	    distancia[ inicial ] = 0;      //Este paso es importante, inicializamos la distancia del inicial como 0
	    int actual , adyacente , peso;
	    while( !Q.isEmpty() ){                   //Mientras cola no este vacia
	        actual = Q.element().first;            //Obtengo de la cola el nodo con menor peso, en un comienzo ser� el inicial
	        Q.remove();                           //Sacamos el elemento de la cola
	        if( visitado[ actual ] ) continue; //Si el v�rtice actual ya fue visitado entonces sigo sacando elementos de la cola
	        visitado[ actual ] = true;         //Marco como visitado el v�rtice actual

	        for( int i = 0 ; i < ady.get( actual ).size() ; ++i ){ //reviso sus adyacentes del vertice actual
	            adyacente = ady.get( actual ).get( i ).first;   //id del vertice adyacente
	            peso = ady.get( actual ).get( i ).second;        //peso de la arista que une actual con adyacente ( actual , adyacente )
	            if( !visitado[ adyacente ] ){        //si el vertice adyacente no fue visitado
	                relajacion( actual , adyacente , peso ); //realizamos el paso de relajacion
	            }
	        }
	    }


	    System.out.printf( "Distancias mas cortas iniciando en vertice %d\n" , inicial );
	    for( int i = 1 ; i <= V ; ++i ){
	    	//System.out.printf("Vertice %d , distancia mas corta = %d\n" , i , distancia[ i ] );
	    }

	    System.out.println("\n**************Impresion de camino mas corto**************");
		System.out.println("Nodo inicial: "+ inicial);
		for(int i=0;i<query.size();i++){

			int destino = query.get(i);
			System.out.println("Vertice destino: "+ destino);
			print( destino );
			camino.add(distancia[destino]);
			System.out.printf("\n");
			caminos.add(new ArrayList<>(camino));
			camino.clear();

		}
	}
	
	
	public static void main(String[] args)  throws IOException {
			int E, destino, peso, inicial;

			V = 14499;
			E = 32442;

			Hashtable<String, Integer> toNumber = new Hashtable<String, Integer>();
			Hashtable<Integer,String> toId = new Hashtable<>();

			String cadena;
			FileReader f = new FileReader("./nodes.txt");
			BufferedReader b = new BufferedReader(f);

			int i = 0;
			while ((cadena = b.readLine()) != null) {
				toNumber.put(cadena, i);
				toId.put(i,cadena);
				ady.add(new ArrayList<Node>());
				i++;
			}

			V = i++;

			System.out.print(i);
			b.close();


			String arcos;
			FileReader fi = new FileReader("./arc.txt");
			BufferedReader bi = new BufferedReader(fi);
			while ((arcos = bi.readLine()) != null) {
				String[] words = arcos.split(" ");
				int source = toNumber.get(words[0]);
				int destination = toNumber.get(words[1]);
				int weight = Integer.parseInt(words[2]);

				ady.get(source).add(new Node(destination, weight));

			}
			bi.close();

			String query;
			FileReader fiq = new FileReader("./query.txt");
			BufferedReader biq = new BufferedReader(fiq);
			ArrayList<Integer> nodes = new ArrayList<>();
			while ((query = biq.readLine()) != null) {
				nodes.add(toNumber.get(query));
			}
			fiq.close();



//		for( int i = 0 ; i <= V ; ++i ) ady.add(new ArrayList<Node>()) ; //inicializamos lista de adyacencia
//		for( int i = 0 ; i < E ; ++i ){
//			origen = sc.nextInt(); destino = sc.nextInt(); peso = sc.nextInt();
//			ady.get( origen ).add( new Node( destino , peso ) );    //grafo diridigo
//			//ady.get( destino ).add( new Node( destino , peso ) ); //no dirigido
//		}
		Hashtable<Integer,Integer> mapGraph = new Hashtable<>();
		Hashtable<Integer,Integer> mapGraph2 = new Hashtable<>();
		for(int n=0;n<nodes.size();n++){
			inicial = nodes.get(n);
			mapGraph.put(n,inicial);
			mapGraph2.put(inicial,n);
			ArrayList<Integer> otherNodes = new ArrayList<>();
			for(int z=0;z<nodes.size();z++){
				if(nodes.get(z)!=inicial){
					otherNodes.add(nodes.get(z));
				}
			}

			dijkstra(inicial, otherNodes );
		}

		System.out.println(caminos);
		int cantidadCaminos = caminos.size();

		GraphAL grafo = new GraphAL(nodes.size());
		for(int z=0;z<cantidadCaminos;z++){
			int longitudCamino = caminos.get(z).size();
			int origen = mapGraph2.get(caminos.get(z).get(0));
			int destination = mapGraph2.get(caminos.get(z).get(longitudCamino-2));
			int weight = caminos.get(z).get(longitudCamino-1);
			grafo.addArc(origen,destination,weight);
		}

		ArrayList<Integer> solution = caminoNode.findPath(grafo,0);
		System.out.println(solution);
		int[] mappedSolution = new int[solution.size()-1];
		for(int j=0;j<solution.size()-1;j++){
			mappedSolution[j]=mapGraph.get(solution.get(j));
		}

		System.out.println(Arrays.toString(mappedSolution));

		for(int j=0;j<mappedSolution.length-1;j++){
			for(int k=0;k<caminos.size();k++){
				if(caminos.get(k).get(0).equals(new Integer(mappedSolution[j]))&&caminos.get(k).get(caminos.get(k).size()-2).equals(new Integer(mappedSolution[j+1]))){
					for(int l=0;l<caminos.get(k).size()-2;l++){
						System.out.print(caminos.get(k).get(l)+", ");
					}
				}
			}
		}

	}
}
