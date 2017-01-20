package application;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import bean.Connessione;
import bean.Fermata;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private UndirectedGraph<Fermata, DefaultWeightedEdge> grafo ;

	public List<Fermata> getAllFermate(){
		List<Fermata> allf= new LinkedList<>();
		allf= dao.getAllf();
		return allf;
	}

	public List<Connessione> getAllConn(){
		List<Connessione> allc = dao.getAllC();
		return allc;
	}
	
	public double getDistanzaTraFermate(Fermata f1, Fermata f2){
		if(f1!=null && f2 != null){
			if(!f1.equals(f2)){
				LatLng c1 = new LatLng(f1.getCoodX(), f2.getCoordY());
				LatLng c2 = new LatLng (f2.getCoodX(), f2.getCoordY());
				double distanza = LatLngTool.distance(c1,  c2,  LengthUnit.KILOMETER);
				return distanza;
			}
		}
		return -1;
	}
	
	
	public void buildGraph(){
		grafo = new SimpleWeightedGraph<Fermata, DefaultWeightedEdge> (DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo,  getAllFermate());
		for(Connessione c : getAllConn()){
			for(Fermata v1 : grafo.vertexSet()){
				for(Fermata v2 : grafo.vertexSet()){
					if(!v1.equals(v2)){
						if(c.getIdStazA()==v1.getIdFermata() && c.getIdStazP()==v2.getIdFermata()){
							if(!grafo.containsEdge(v1, v2)){
								double distanza = getDistanzaTraFermate(v1, v2);
								//double velocita = c.getLinea().getVelocita();            //errore
								//double tempo = distanza/velocita;
							    Graphs.addEdge(grafo,  v1,  v2,  distanza);
							}
						}
					}
				}
			}
		}
	}
	
	public List<Fermata> getCamminoMinimo(Fermata start, Fermata end){  //vedo solo le fermate del cammino  //ok
		if(start!=null && end !=null){
			if(!start.equals(end)){
				DijkstraShortestPath<Fermata, DefaultWeightedEdge> di = new DijkstraShortestPath<Fermata, DefaultWeightedEdge> (grafo, start, end);
				GraphPath<Fermata, DefaultWeightedEdge> path= di.getPath();
				if(path==null){
					System.out.println("null");
					return null;
				}
				//System.out.println(Graphs.getPathVertexList(path));
				return Graphs.getPathVertexList(path);
			}
		}
		System.out.println("null");
		return null;
	}
	
	
	public List<DefaultWeightedEdge> getCamminoMinimoArchi(Fermata start, Fermata end){  //archi come sono ordinati??  //x vedere il peso e cioè la distanza?
		if(start!=null && end !=null){
			if(!start.equals(end)){
				DijkstraShortestPath<Fermata, DefaultWeightedEdge> di = new DijkstraShortestPath<Fermata, DefaultWeightedEdge> (grafo, start, end);
				List<DefaultWeightedEdge> path= di.getPathEdgeList();
				if(path == null){
					return null;
				}
				return path;
			}
	     }
		return null;
	
	}
	
	//cammino + lunghezza totale da start a end
	public double getCamminoMinimoLunghezza(Fermata start, Fermata end){  //archi come sono ordinati??  //x vedere il peso e cioè la distanza?
		if(start!=null && end !=null){
			if(!start.equals(end)){
				DijkstraShortestPath<Fermata, DefaultWeightedEdge> di = new DijkstraShortestPath<Fermata, DefaultWeightedEdge> (grafo, start, end);
				double lunghezza = di.getPathLength(); 
				return lunghezza;
			}
		}
		return -1;
	}
	
	
	
	public static void main (String [] args){
		Model m = new Model();
		m.buildGraph();
	}
}
