import javax.swing.JFrame;

/**
 * This class represents the full GUI.
 * It will contain all the components like a GraphCanvas which displays the graph,
 * an information panel, modification panel, etc.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class GUI extends JFrame{
	
	public GUI(final Graph graph){
		super("Graph Dracula");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		GraphCanvas graphCanvas = new GraphCanvas(graph);
		add(graphCanvas);
	}
	
	public void start(){
		setVisible(true);
	}
}
