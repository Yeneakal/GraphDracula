import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * This class paints a given graph to a canvas.
 * For the moment it is only possible to draw graphs which vertices have cartesian
 * coordinate data.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class GraphCanvas extends JComponent{

	/** The graph to paint. */
	private final Graph graph;
	
	public GraphCanvas(final Graph graph){
		this.graph = graph;
		//setSize(400, 400);
	}
	
	public Graph getGraph(){
		return graph;
	}
	
	@Override public void paintComponent(final Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		getGraph().getAdjList().stream()
		.forEach(actualVertex -> {
//			g2d.fillOval((int)actualVertex.getX(), (int)actualVertex.getY(), 10, 10);
//			g2d.drawString(String.format("%d", actualVertex.getID()), (int)actualVertex.getX(), (int)actualVertex.getY());
		});
		
		getGraph().getEdges().stream()
		.forEach(actualEdge -> {
			g2d.drawLine((int)actualEdge.getOne().getX(), (int)actualEdge.getOne().getY(),(int)actualEdge.getAnother().getX(), (int)actualEdge.getAnother().getY());
			//g2d.drawString(String.format("%.2f", actualEdge.getWeight()), (int)(actualEdge.getOne().getX() + actualEdge.getAnother().getX()) / 2, 
																		  //(int)(actualEdge.getOne().getY() + actualEdge.getAnother().getY()) / 2);
		});
											
	}
}
