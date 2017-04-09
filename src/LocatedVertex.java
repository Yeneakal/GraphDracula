/**
 * Represents a vertex with cartesian coordinates as info.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class LocatedVertex extends Vertex{
	
	/** x coordinate. */
	private final double x;
	
	/** y coordinate. */
	private final double y;
	
	public LocatedVertex(final LocatedVertex vertex){
		super(vertex);
		this.x = vertex.getX();
		this.y = vertex.getY();
	}
	
	/**
	 * Creates a new Vertex with information about its position in cartesian plane.
	 * 
	 * @param x Coordinate x.
	 * @param y	Coordinate y.
	 */
	public LocatedVertex(final double x, final double y){
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	@Override public String toString(){
		String result = String.format("Vertex NO. %d (%.2f, %.2f) -> ", getID(), getX(), getY());
		if(!getNeighbours().isEmpty())
			for(final Vertex nextNeighbour : getNeighbours())
				result += String.format("%d ", nextNeighbour.getID());
		result += "\n";
		return result;
	}
}
