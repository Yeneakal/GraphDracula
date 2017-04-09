import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of a simple, recursive depth-first-search algorithm.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class DFS {

	/** The input graph, to compute DFS on. */
	private final Graph inputGraph;
	
	/** When a vertex has been visited by the DFS algorithm it will be in this list. */
	private final List<Vertex> markedList = new ArrayList<>();
	
	/**
	 * Initializes instance of a DFS algorithm. 
	 * @param inputGraph Graph to compute a DFS for.
	 */
	public DFS(final Graph inputGraph){
		this.inputGraph = inputGraph;
	}
	
	public Graph getInputGraph() {
		return inputGraph;
	}

	public List<Vertex> getMarkedList() {
		return markedList;
	}

	/**
	 * Computes a DFS recursively
	 * 
	 * @param startVertex The vertex which will be searched for neighbours.
	 */
	public void computeDFS(final Vertex startVertex){
		getMarkedList().add(startVertex);
		for(final Vertex nextVertex : startVertex.getNeighbours())
			if(!getMarkedList().contains(nextVertex))
				computeDFS(nextVertex);
	}
	
	public Graph toGraph(){
		final Graph graph = new WeightedGraph();
		for(int index = 0; index < getMarkedList().size() - 1; index++){
			final LocatedVertex one = new LocatedVertex((LocatedVertex)getMarkedList().get(index));
			final LocatedVertex another = new LocatedVertex((LocatedVertex)getMarkedList().get(index + 1));
			graph.addEdge(new Edge(one, another, Math.sqrt(Math.pow(one.getX() - another.getX(), 2) + Math.pow(one.getY() - another.getY(), 2))));
			graph.addVertex(one);
			graph.addVertex(another);
		}
		return graph;
	}
	
	public static void main(final String... args){
		final WeightedGraph completeGraph = new WeightedGraph(4, 20);
		completeGraph.makeComplete();
		final KruskalMST mst = new KruskalMST(completeGraph);
		final Graph mstGraph = mst.computeMST();
		final DFS dfs = new DFS(mstGraph);
		dfs.computeDFS(mstGraph.getAnyVertex().get());
		
		System.out.println(dfs.getMarkedList());
		
	}
}
