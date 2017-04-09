/**
 * Client class to test the graph library.
 * 				
 *   ____                 _       ____                       _       
 *  / ___|_ __ __ _ _ __ | |__   |  _ \ _ __ __ _  ___ _   _| | __ _ 
 * | |  _| '__/ _` | '_ \| '_ \  | | | | '__/ _` |/ __| | | | |/ _` |
 * | |_| | | | (_| | |_) | | | | | |_| | | | (_| | (__| |_| | | (_| |
 *  \____|_|  \__,_| .__/|_| |_| |____/|_|  \__,_|\___|\__,_|_|\__,_|
 *                 |_| 
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class Main {

	public static void main(final String... args){

		final WeightedGraph graph = new WeightedGraph(1700, 500);
		graph.makeCompleteEuclid();
		final KruskalMST mst = new KruskalMST(graph);
		final Graph mstGraph = mst.computeMST();
		final DFS dfs = new DFS(mstGraph);
		dfs.computeDFS(mstGraph.getAnyVertex().get());
		System.out.println(dfs.toGraph());
		System.out.println(dfs.toGraph().getAdjList());
		System.out.println("DFS marked list: \n" + dfs.getMarkedList() + "\n");
		System.out.println("MST edges: \n" + mstGraph.getEdges() + "\n");
		System.out.println("original graph adj-list: \n" + graph.getAdjList() + "\n");
		System.out.println("MST adj-list: \n" + mstGraph.getAdjList() + "\n");
		
		System.out.println("original graph total weight: " + graph.getWeight());
		System.out.println("mst graph weight: " + mstGraph.getWeight());
		System.out.println("tsp-tour weight: " + dfs.toGraph().getWeight());
		final GUI gui = new GUI(graph);
		final GUI mstGUI = new GUI(mstGraph);
		final GUI dfsGUI = new GUI(dfs.toGraph());
		gui.start();
		mstGUI.start();
		dfsGUI.start();

	}
}
