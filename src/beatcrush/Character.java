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
 * Character.java
 * 
 * Abstract base class.
 * Player is-a Character.
 */
public abstract class Character
{
	protected String name;

	// Constructor sets name
	public Character(String name)
	{
		this.name = name;
	}

	// Returns the character's name
	public String getName()
	{
		return name;
	}

	// All subclasses must define how to reset their stats
	public abstract void resetStats();
}
