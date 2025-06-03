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

import java.io.*;
import java.util.ArrayList;

/**
 * FileManager handles all saving and loading of high score data.
 * FileManager "has-a" file path and "has-a" job to do file I/O.
 */
public class FileManager
{
	// File path for the high scores
	private String highScoreFile = "highscores.txt";

	/**
	 * Saves a player's name and score to a text file.
	 * Each entry is written on a new line in the format: name,score
	 */
	public void saveHighScore(String name, int score)
	{
		// FileManager "has-a" responsibility to write to text files
		PrintWriter output = null;

		try
		{
			output = new PrintWriter(new FileWriter(highScoreFile, true)); // append
																			// mode
			output.println(name + "," + score);
		}
		catch (IOException e)
		{
			System.out.println("Error saving high score: " + e.getMessage());
		}
		finally
		{
			if (output != null)
			{
				output.close(); // Close the file after writing
			}
		}
	}

	/**
	 * Loads all high scores from the text file.
	 * 
	 * @return an ArrayList of score entries in "name,score" format
	 */
	public ArrayList<String> loadHighScores()
	{
		// FileManager "has-a" responsibility to read from text files
		ArrayList<String> scores = new ArrayList<>();
		BufferedReader input = null;

		try
		{
			input = new BufferedReader(new FileReader(highScoreFile));
			String line = input.readLine();

			while (line != null)
			{
				scores.add(line);
				line = input.readLine();
			}
		}
		catch (IOException e)
		{
			System.out.println("Error loading high scores: " + e.getMessage());
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close(); // release the file resource
				}
				catch (IOException e)
				{
					System.out.println("Error closing file: " + e.getMessage());
				}
			}
		}

		return scores;
	}
}
