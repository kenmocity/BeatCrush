/**
 * Lead Author(s):
 * Kenmo Pinnguen; 5550114581
 *
 * References:
 * Morelli, R., & Walde, R. (2016).
 * Java, Java, Java: Object-Oriented Problem Solving
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Version: 2025-05-06
 */
package beatcrush;

import java.util.ArrayList;

/**
 * Temporary testing class to confirm that
 * Note, Track, and Player classes are working properly.
 * This class "has-a" Player, Track, and GameManager.
 */
public class TestGame
{
	public static void main(String[] args)
	{
		Player player = new Player("Kenmo");
		Track track = new Track("Funky Beats");

		// Simulate adding notes
		track.addNote(new Note("UP", 1.0));
		track.addNote(new Note("DOWN", 2.5));
		track.addNote(new Note("LEFT", 4.0));
		Track track2 = new Track("Funkier Beats");
		track2.addNote(new Note("LEFT", 1.5));
		track2.addNote(new Note("RIGHT", 3.0));

		// Set up GameManager
		GameManager gameManager = new GameManager(player);

		// Add the tracks to the GameManager's list
		gameManager.addTrack(track);
		gameManager.addTrack(track2);

		// Select track2 to play
		gameManager.selectTrack(track2);

		// Start the selected game
		gameManager.startGame(track2);

		// Simulate time passing from 0 to 5 seconds to test note syncing and
		// activation window
		double simulatedTime = 0;
		double endTime = 5.0;
		double timeStep = 0.5;
		double window = 0.5; // Half-second window to "catch" notes

		Track loadedTrack = new Track("be_nice_2_me");

		// Load notes from file
		loadedTrack.loadBeatmap("be_nice_2_me_beatmap.txt");

		System.out.println("Loaded beatmap notes:");
		for (Note note : loadedTrack.getNotes())
		{
			System.out.println(note.getTimeStamp() + " " + note.getDirection());
		}

		while (simulatedTime <= endTime)
		{
			System.out.println("\nTime: " + simulatedTime + "s");
			for (Note note : track.getNotesInWindow(simulatedTime, window))
			{
				System.out.println("Active Note: " + note.getDirection()
						+ " at " + note.getTimeStamp() + "s");
			}
			simulatedTime += timeStep;
		}

		// Saves the player's score to a file and then loads high scores to
		// verify the file I/O
		FileManager fileManager = new FileManager();
		fileManager.saveHighScore(player.getName(), player.getScore());

		ArrayList<String> scores = fileManager.loadHighScores();
		System.out.println("High Scores:");
		for (String score : scores)
		{
			System.out.println(score);
		}
	}
}
