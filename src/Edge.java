/**
 * Represents an weighted edge of a graph.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class Edge implements Comparable<Edge>{

	/** One end of the edge. */
	private final LocatedVertex one;
	
	/** The other end of the edge. */
	private final LocatedVertex another;
	
	/** Weight of this edge. */
	private double weight;
	
	/** This edges unique ID. */
	private final int edgeID;
	
	/**
	 * Creates a new edge between two given vertices.
	 * 
	 * @param one One end of this edge.
	 * @param another Other end of this edge.
	 * @param weight This edges weight.
	 */
	public Edge(final LocatedVertex one, final LocatedVertex another, final double weight){
		this.one = one;
		this.another = another;
		this.weight = weight;
		edgeID = EdgeID.createID();
	}
	
	/**
	 * This Ctor makes a copy of an edge and puts it between two different vertices.
	 * 
	 * @param edge The edges ID and weight to copy.
	 * @param one The edges new one end.
	 * @param another The edges new other end.
	 */
	public Edge(final Edge edge, final LocatedVertex one, final LocatedVertex another){
		edgeID = edge.getID();
		weight = edge.getWeight();
		this.one = one;
		this.another = another;
		
	}

	public int getID() {
		return edgeID;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(final double weight) {
		this.weight = weight;
	}

	public LocatedVertex getOne() {
		return one;
	}

	public LocatedVertex getAnother() {
		return another;
	}

	@Override
	public int compareTo(Edge other) {
		if(this.equals(other))
			return 0;
		int result = 0;
		if(getWeight() > other.getWeight() )
			result = 1;
		if(getWeight() <= other.getWeight())
			result = -1;
		return result;
	}
	
	@Override public int hashCode(){
		return getID();
	}
	
	@Override public boolean equals(final Object object){
		assert object != null : "cannot happen";
		if(getClass() != object.getClass())
			return false;
		final Edge otherEdge = (Edge)object;
		if(getOne().equals(otherEdge.getOne()) && getAnother().equals(otherEdge.getAnother()) && 
		   getWeight() == otherEdge.getWeight())
			return true;
		return false;
	}
	
	@Override public String toString(){
		return String.format("edge NO. %d between (%d, %d), weight: %.2f", getID(), getOne().getID(), getAnother().getID(), getWeight());
	}
}
