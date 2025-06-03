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
 * Player.java
 * 
 * Tracks the score and combo for the player.
 * Player is-a Character.
 * GameManager "has-a" Player to manage game state.
 */
public class Player extends Character
{
	private String name; // The player's name
	private int score; // Player's current score
	private int combo; // Current combo streak

	// Constructor sets name and resets score/combo
	public Player(String name)
	{
		super(name);
		this.score = 0;
		this.combo = 0;
	}

	// Increases the player's score by specified number of points
	public void increaseScore(int points)
	{
		score += points;
	}

	// Resets player's combo count
	public void resetScore()
	{
		combo = 0;
	}

	// Increases player's combo count by 1
	public void increaseCombo()
	{
		combo++;
	}

	// Returns current score
	public int getScore()
	{
		return score;
	}

	// Returns current combo count
	public int getCombo()
	{
		return combo;
	}

	// Returns player's name
	public String getName()
	{
		return name;
	}

	// Resets the player's score and combo (used at start of new game)
	public void resetStats()
	{
		score = 0;
		combo = 0;
	}
}
