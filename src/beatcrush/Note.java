/**
 * Lead Author(s):
 * Kenmo Pinnguen; 5550114581
 *
 * References:
 * Morelli, R., & Walde, R. (2016).
 * Java, Java, Java: Object-Oriented Problem Solving
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/package-summary.html
 * 
 * https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#currentTimeMillis
 * 
 * https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html
 * 
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html
 * 
 * Version: 2025-06-02
 */


package beatcrush;

/**
 * The Note class represents a single beat in the game.
 * Each Note has a direction (UP, DOWN, LEFT, RIGHT) and a timeStamp indicating
 * when it should be hit.
 * 
 * Game "has-a" Note as part of a Track.
 */
public class Note
{
	private String direction; // "UP", "DOWN", "LEFT", "RIGHT"
	private double timeStamp; // When this note should be hit (in seconds)
	private boolean hit; // Whether or not note was successfully hit
	private boolean wasHit = false; // tracks if the note was hit

	// Constructor
	public Note(String direction, double timeStamp)
	{
		this.direction = direction;
		this.timeStamp = timeStamp;
		this.hit = false;
	}

	// Returns the direction of the note
	public String getDirection()
	{
		return direction;
	}

	// Returns the time when the note should be hit
	public double getTimeStamp()
	{
		return timeStamp;
	}

	public boolean wasHit()
	{
		return wasHit;
	}

	public void setHit()
	{
		wasHit = true;
	}

	public void setMissed()
	{
		wasHit = true; // treat missed as "hit" so it won’t trigger again
	}

}
