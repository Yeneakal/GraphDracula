import java.util.HashSet;
import java.util.Set;


/**
 * Represents a simple undirected, unweighted graph.
 * 
 * @deprecated This class is obsolete for now. Use WeightedGraph instead.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class BasicGraph {

	/** Amount of vertices in this graph. */
	private final int vertices;
	
	/** Amount of edges in this graph. */
	private int edges;
	
	/** The adjacency list. Each entry represents a vertex with its neighbours. */
	private final Set<Vertex> adjList = new HashSet<>();

	/** Creates an empty graph. */
	public BasicGraph(){
		vertices = 0;
		edges = 0;
	}
	
	/**
	 * Creates a graph with isolated vertecies.
	 * 
	 * @param vertecies Amount of vertecies in this graph.
	 */
	public BasicGraph(final int vertecies){
		this.vertices = vertecies;
		for(int index = 0; index < vertecies; index++)
			adjList.add(new Vertex());
		edges = 0;
	}
	
	/**
	 * Creates a graph with isolated vertices that have a random position on cartesian plane.
	 * 
	 * @param vertecies Amount of vertices to create.
	 * @param randomUpperBoundary x, y positions max values.
	 */
	public BasicGraph(final int vertecies, final double randomUpperBoundary){
		this.vertices = vertecies;
		for(int index = 0; index < vertecies; index++)
			adjList.add(new LocatedVertex(Math.random()*100, Math.random()*100));
		edges = 0;
	}
	
	public int numberOfVertices() {
		return vertices;
	}

	public int numberOfEdges() {
		return edges;
	}
	
	/** Cranks up the number of edges by one. */
	public void incrementEdges(){
		edges += 1;
	}

	public Set<Vertex> getAdjList() {
		return adjList;
	}
	
	/**
	 * Adds an edge between two vertices. 
	 *
	 * @param fromVertex one end of the edge.
	 * @param toVertex other end of the edge.
	 */
	public void addEdge(final Vertex fromVertex, final Vertex toVertex){
		if(getAdjList().contains(fromVertex) && getAdjList().contains(toVertex)){
			fromVertex.addNeighbour(toVertex);
			toVertex.addNeighbour(fromVertex);
			edges++;
		}
	}
	
	/**
	 * Adds an edge between two vertices.
	 * 
	 * @param edge The edge object to add.
	 */
	public void addEdge(final Edge edge){
		final Vertex one = edge.getOne();
		final Vertex another = edge.getAnother();
		addEdge(one, another);
	}
	
	@Override public String toString(){
		String result = "";
		for(final Vertex vertex : getAdjList())
			result += vertex.toString();
		return result;
	}
	
	public static void main(final String... args){
		final BasicGraph graph = new BasicGraph(2000);
		System.out.println("empty graph created");
		
		// Creates a complete graph.
		graph.getAdjList().stream()
		.forEach(actualVertex -> {
			graph.getAdjList().stream()
			.filter(nextVertex -> !nextVertex.equals(actualVertex))
			.forEach(nextVertex -> {
				graph.addEdge(actualVertex, nextVertex);
			});
		});
		
		System.out.println("complete graph created!");
		//System.out.println(graph);
		
		
		final BasicGraph graph2 = new BasicGraph(10, 100);
		System.out.println(graph2);
	}
}
