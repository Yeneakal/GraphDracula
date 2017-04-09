import java.util.Optional;
import java.util.Set;


public interface Graph {
	
	public Set<LocatedVertex> getAdjList();
	
	public Set<Edge> getEdges();
	
	public Optional<LocatedVertex> getVertex(final LocatedVertex vertex);
	
	public Optional<LocatedVertex> getAnyVertex();
	
	public void addEdge(final Edge edge);
	
	public void addVertex(final LocatedVertex vertex);
	
	public int numberOfEdges();
	
	public int numberOfVertices();
	
	public double getWeight();

	/**
	 * Adds randomly weighted edges to a graph so that it will be complete.
	 * 
	 * @param graph Already existing graph.
	 */
	public default void makeComplete(){
		this.getAdjList().stream()
		.forEach(actualVertex -> {
			this.getAdjList().stream()
			.filter(nextVertex -> !nextVertex.equals(actualVertex))
			.filter(nextVertex -> !nextVertex.isAdjacentTo(actualVertex))
			.forEach(nextVertex -> {
				this.addEdge(new Edge(actualVertex, nextVertex, Math.random()));
			});
		});
	}
	
	public default void makeCompleteEuclid(){
		this.getAdjList().stream()
		.forEach(actualVertex -> {
			this.getAdjList().stream()
			.filter(nextVertex -> !nextVertex.equals(actualVertex))
			.filter(nextVertex -> !nextVertex.isAdjacentTo(actualVertex))
			.forEach(nextVertex -> {
				this.addEdge(new Edge(actualVertex, nextVertex, Math.sqrt(Math.pow(actualVertex.getX() - nextVertex.getX(), 2) + Math.pow(actualVertex.getY() - nextVertex.getY(), 2))));
			});
		});
	}
}
