// TODO Fix that there cannot be multiple versions of a vertex in one graph (eg LocatedVertex and Vertex without location data).
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents an edge weighted graph.
 * Self-Loops are possible, parallel edges are possible thus you can create a multigraph.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class WeightedGraph implements Graph{

	/** Weighted edges of this graph. */
	private final TreeSet<Edge> edges = new TreeSet<>();
	
	/** The adjacency list. Each entry represents a vertex with its neighbours. */
	private final Set<LocatedVertex> adjList = new HashSet<>();
	
	/**
	 * Creates an empty graph.
	 */
	public WeightedGraph(){

	}
	
	/**
	 * Builds an instance of weighted graph without any edges.
	 * 
	 * @param vertices How many vertices this graph shall have.
	 */
	public WeightedGraph(final int vertices){
		for(int index = 0; index < vertices; index++)
			adjList.add(new LocatedVertex(0D, 0D));
	}
	
	/**
	 * Builds an euclidean edge-weighted graph.
	 * Vertices will have a position on cartesian plane.
	 * Edges weights will be computed equal to the vertices euclidean distances.
	 * 
	 * @param vertices Amount of vertices this graph will have.
	 * @param randomUpperBoundary Will be upper boundary for random generated locations of the vertices.
	 */
	public WeightedGraph(final int vertices, final double randomUpperBoundary){
		for(int index = 0; index < vertices; index++)
			adjList.add(new LocatedVertex(Math.random()*randomUpperBoundary, Math.random()*randomUpperBoundary));
	}
	
	/**
	 * Copy Ctor to make a full copy of a graph.
	 * 
	 * @param graph Existing edgeweighted graph.
	 */
	public WeightedGraph(final Graph graph){
		edges.addAll(new TreeSet<Edge>(graph.getEdges()));
		adjList.addAll(new HashSet<LocatedVertex>(graph.getAdjList()));
	}

	public int numberOfEdges() {
		return getEdges().size();
	}

	public int numberOfVertices() {
		return getAdjList().size();
	}

	public Set<LocatedVertex> getAdjList() {
		return adjList;
	}

	public TreeSet<Edge> getEdges() {
		return edges;
	}
	
	/**
	 * Look if a given vertex exists in this graph and returns it.
	 * Note that Vertices of other graphs may fulfill the equals method.
	 * 
	 * @param vertex Vertex to look for.
	 * @return The vertex of this graph if the given vertex is available.
	 */
	public Optional<LocatedVertex> getVertex(final LocatedVertex vertex){
		Optional<LocatedVertex> result = Optional.empty();
		for(final LocatedVertex nextVertex : getAdjList()){
			if(nextVertex.equals(vertex))
				result = Optional.of(nextVertex); 
		}
		return result;
	}
	
	// TODO ugly typecasting...
	/**
	 * Returns any vertex of the graph.
	 * 
	 * @return Some random vertex of the graph.
	 */
	public Optional<LocatedVertex> getAnyVertex(){
		return  Optional.of((LocatedVertex)getAdjList().toArray()[0]);
	}
	
	/**
	 * Adds a vertex to the graph.
	 * 
	 * @param vertex The vertex to add.
	 */
	@Override public void addVertex(final LocatedVertex vertex){
		getAdjList().add(vertex);
	}
	
	// TODO first check, if there are vertices in the graph where this edge could be added.
	/** Adds a new edge to the graph.
	 * Vertices must already exist.
	 * 
	 * @param edge Edge to add.
	 */
	@Override public void addEdge(final Edge edge){
		final Vertex from = edge.getOne();
		final Vertex to = edge.getAnother();
		from.addNeighbour(to);
		to.addNeighbour(from);
		edges.add(edge);
	}
	
	/**
	 * Removes an edge from the graph
	 * 
	 * @param edge Edge to remove.
	 */
	public void removeEdge(final Edge edge){
		final Vertex oneVertex = edge.getOne();
		final Vertex anotherVertex = edge.getAnother();
		oneVertex.removeNeighbour(anotherVertex);
		anotherVertex.removeNeighbour(oneVertex);
		getEdges().remove(edge);
	}
	
	/**
	 * Removes the edge with the lowest weight in the graph.
	 */
	public void removeFirstEdge(){
		removeEdge(getEdges().first());
	}
	
	/**
	 * Computes total weight of this graph.
	 * 
	 * @return Weight of this graph.
	 */
	public double getWeight(){
		double totalWeight = 0;
		for(final Edge nextEdge : getEdges())
			totalWeight += nextEdge.getWeight();
		return totalWeight;
	}
	
	@Override public String toString(){
		String result = "";
		for(final Edge nextEdge : getEdges())
			result += String.format("%s%n", nextEdge);
		return result;
	}
	
	public static void main(final String... args){
		final WeightedGraph weightedgraph = new WeightedGraph(3, 100);
		
		// Build an complete graph without self-loops and parallel edges.
		weightedgraph.getAdjList().stream()
		.forEach(actualVertex -> {
			weightedgraph.getAdjList().stream()
			.filter(nextVertex -> !nextVertex.equals(actualVertex))
			.filter(nextVertex -> !nextVertex.isAdjacentTo(actualVertex))
			.forEach(nextVertex -> {
				weightedgraph.addEdge(new Edge(actualVertex, nextVertex, Math.random()*100));
			});
		});
		
		
		
		System.out.println("complete graph created :)");
		System.out.println(weightedgraph.getAdjList());
		System.out.println(weightedgraph);
		System.out.println(weightedgraph.getWeight());
		
		System.out.println();
		
		System.out.println("remove edge with lowest weight:");
		weightedgraph.removeFirstEdge();
		System.out.println("removed...");
		System.out.println(weightedgraph.getAdjList());
		System.out.println(weightedgraph);
		System.out.println(weightedgraph.getWeight());
	}
}
