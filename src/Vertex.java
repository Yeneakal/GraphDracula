import java.util.HashSet;
import java.util.Set;

/**
 * Represents a vertex of a graph.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class Vertex {

	/** Potentional vertecies this Vertex is connected to. */
	private final Set<Vertex> neighbours = new HashSet<>();
	
	private final int ID;
	
	public Vertex(){
		ID = VertexID.createID();
	}
	
	/**
	 * Copy Ctor to make a real copy of a vertex.
	 * Beware that the copy will have the same ID as the original
	 * thus when invoking equals on a vertex and its clone the result will be true.
	 * 
	 * @param vertex The vertex to clone.
	 */
	public Vertex(final Vertex vertex){
		ID = vertex.getID();
	}

	public Set<Vertex> getNeighbours() {
		return neighbours;
	}

	public int getID() {
		return ID;
	}

	/**
	 * Adds a new neighbour to the adjacency list.
	 * 
	 * @param neighbour Neighbouring vertex to add.
	 */
	public void addNeighbour(final Vertex neighbour){
		neighbours.add(neighbour);
	}
	
	/**
	 * Removes a neighbour to this vertex.
	 * This means there is no more connection between this and the other vertex.
	 * 
	 * @param neighbour The neighbouring vertex to be removed.
	 */
	public void removeNeighbour(final Vertex neighbour){
		if(getNeighbours().contains(neighbour))
			getNeighbours().remove(neighbour);
	}
	
	/**
	 * Detach this vertex from all its neighbours.
	 */
	public void removeNeighbours(){
		getNeighbours().removeAll(getNeighbours());
	}
	
	/**
	 * Computes degree of this vertex.
	 * 
	 * @return degree of this vertex.
	 */
	public int getDegree(){
		return neighbours.size();
	}
	
	/**
	 * Checks if a vertex is adjacent to this vertex.
	 * 
	 * @param other The vertex to check.
	 * @return true, if they are connected, false otherwise.
	 */
	public boolean isAdjacentTo(final Vertex other){
		boolean result = false;
		if(getNeighbours().contains(other))
			result = true;
		return result;
	}
	
	@Override public int hashCode(){
		return getID();
	}
	
	@Override public boolean equals(final Object object){
		assert object != null : "cannot be null";
		if(getClass() != object.getClass())
			return false;
		final Vertex otherVertex = (Vertex)object;
		if(getID() == otherVertex.getID())
			return true;
		return false;
	}
	
	@Override public String toString(){
		String result = String.format("Vertex NO. %d -> ", getID());
		if(!getNeighbours().isEmpty())
			for(final Vertex nextNeighbour : getNeighbours())
				result += String.format("%d ", nextNeighbour.getID());
		result += String.format("%n");
		return result;
	}
}
