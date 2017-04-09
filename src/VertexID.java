/**
 * A number generator to give new vertecies an ID.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public class VertexID {

	/** Holds the ID. */
	private static int number = 0;

	public static int createID(){
		return number++;
	}
}
