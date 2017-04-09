import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
// TODO The computation of MST will lead to have the resulting graph inconsistent states. It will have references not
// TODO belonging to the resulting graph anymore.

/**
 * Computes a minimum spanning tree (MST) via the Kruskal algorithm.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class KruskalMST {

	/** The input graph the MST shall be created for. */
	private final Graph inputGraph;
	
	/** Test value. How many iterations are needed to compute the mst? */
	static int kruskalCompleteIterations = 0;
	
	/** Test value. How many iterations are needed to reconfigure the connecting-components? */
	static int connectingComponentIterations = 0;
	
	/**
	 * Sets up an instance of KruskalMST to compute the minimum spanning tree.
	 * 
	 * @param completeGraph The graph the MST shall be created for.
	 */
	public KruskalMST(final WeightedGraph completeGraph){
		this.inputGraph = completeGraph;
	}

	public Graph getInputGraph() {
		return inputGraph;
	}
	
	/**
	 * Computes the MST of a graph using Kruskal's algorithm.
	 * 
	 * @return A MST version of the graph this object was initialized with.
	 */
	public Graph computeMST(){
		if(getInputGraph().numberOfEdges() == 0)
			throw new IllegalArgumentException();
		
		// Contains connecting-components.
		final Map<Integer, Set<LocatedVertex>> components = new HashMap<>();
		
		// This object will contain the final MST.
		final Graph mstGraph = new WeightedGraph();
		
		// Get a copy of all edges in the complete graph.
		final TreeSet<Edge> edges = new TreeSet<>(getInputGraph().getEdges());
		
		// Copy the input graphs vertices to the graph that will represent the MST.
		for(final Edge nextEdge : edges){
			final LocatedVertex oneCopy = new LocatedVertex(nextEdge.getOne());
			final LocatedVertex anotherCopy =  new LocatedVertex(nextEdge.getAnother());
			oneCopy.removeNeighbours();
			anotherCopy.removeNeighbours();
			if(!mstGraph.getAdjList().contains(oneCopy))
				mstGraph.addVertex(oneCopy);
			if(!mstGraph.getAdjList().contains(anotherCopy))
				mstGraph.addVertex(anotherCopy);
		}
		
		// In the beginning every vertex has its own component (vertices are isolated).
		int index = 0;
		for(final LocatedVertex nextVertex : getInputGraph().getAdjList()){
			final Set<LocatedVertex> vertexContainer = new HashSet<>();
			vertexContainer.add(nextVertex);
			components.put(index++, vertexContainer);
		}
		
		// Start Kruskal's algorithm.
		while(!edges.isEmpty()){
			kruskalCompleteIterations++;
			final Edge edgeCandidate = edges.first();
			edges.remove(edgeCandidate);
			final LocatedVertex one = edgeCandidate.getOne();
			final LocatedVertex another = edgeCandidate.getAnother();
			boolean isLoop = false;
			
			// Check if there would be loop if we add this edge to the MST. TODO Make stream version!
			for(final Map.Entry<Integer, Set<LocatedVertex>> nextEntry : components.entrySet()){
				if(nextEntry.getValue().contains(one) && nextEntry.getValue().contains(another))
					isLoop = true;
			}
			
			// If there is no problem with this edge merge the connecting-components of those two.
			if(!isLoop){
				// Indices remember to what component-class the vertices of this edge belong to.
				int component1 = 0;
				int component2 = 0;
				
				// Reconfigure the connecting components of the mst.
				// Search for the corresponding indices.
				for(final Map.Entry<Integer, Set<LocatedVertex>> nextEntry : components.entrySet()){
					if(nextEntry.getValue().contains(one))
						component1 = nextEntry.getKey();
					if(nextEntry.getValue().contains(another))
						component2 = nextEntry.getKey();
				}
				// Merge connecting components.
				final Set<LocatedVertex> tmpVertexContainer = components.get(component1); 
				components.get(component2).addAll(tmpVertexContainer);
				components.remove(component1);
				
				// make a copy of the edge and add it to the MST.
				final Edge edgeCandidateCopy = new Edge(mstGraph.getVertex(one).get(), mstGraph.getVertex(another).get(), edgeCandidate.getWeight());
				mstGraph.addEdge(edgeCandidateCopy);
			}
		}
		return mstGraph;
	}

	public static void main(final String... args){
		
		for(int cities = 100; cities <= 1500; cities += 100){
			KruskalMST.kruskalCompleteIterations = 0;
			final WeightedGraph completeGraph = new WeightedGraph(cities, 500);
			completeGraph.makeComplete();
			final KruskalMST mst = new KruskalMST(completeGraph);
			final Graph mstGraph = mst.computeMST();
			System.out.println("{" + cities + "," + KruskalMST.kruskalCompleteIterations + "},");
		}


		
		
//		final Vertex vertex0 = new  Vertex();
//		final Vertex vertex1 = new Vertex();
//		final Vertex vertex2 = new Vertex();
//		final Vertex vertex3 = new Vertex();
//
//		final Edge _0to1 = new Edge(vertex0, vertex1, 1);
//		final Edge _0to2 = new Edge(vertex0, vertex2, 1);
//		final Edge _0to3 = new Edge(vertex0, vertex3, 1);
//		final Edge _1to3 = new Edge(vertex1, vertex3, 2);
//		final Edge _1to2 = new Edge(vertex1, vertex2, 3);
//		final Edge _2to3 = new Edge(vertex2,vertex3, 2);
//		completeGraph.addVertex(vertex0);
//		completeGraph.addVertex(vertex1);
//		completeGraph.addVertex(vertex2);
//		completeGraph.addVertex(vertex3);
//		completeGraph.addEdge(_0to1);
//		completeGraph.addEdge(_0to2);
//		completeGraph.addEdge(_1to3);
//		completeGraph.addEdge(_1to2);
//		completeGraph.addEdge(_2to3);
//		completeGraph.addEdge(_0to3);
//
//		System.out.println(completeGraph.getAdjList());
//		System.out.println("no of edges: " + completeGraph.numberOfEdges());
//		System.out.println("no of vertices: " + completeGraph.numberOfVertices());
//		System.out.println("original weight: " + completeGraph.getWeight());
//		System.out.printf("%n%n");
//		
//		final KruskalMST mst = new KruskalMST(completeGraph);
//		final Graph mstGraph = mst.computeMST();
//		System.out.println("MST computed...");
//		System.out.println("MST weight: " + ((WeightedGraph)mstGraph).getWeight());
//		//System.out.println(mstGraph);
//		System.out.println("MSTs number of edges: " + mstGraph.numberOfEdges());
//		System.out.println(mstGraph);
//		System.out.println(mstGraph.getAdjList());

	}
}
