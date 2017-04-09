// TODO: MAKE REAL HASHCODE IN EDGE.JAVA INSTEAD!!!

/**
 * A number generator to give new edges an ID.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class EdgeID {

	/** Holds the ID. */
	private static int number = 0;

	public static int createID(){
		return number++;
	}
}
