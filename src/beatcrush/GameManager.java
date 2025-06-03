/**
 * Lead Author(s):
 * Kenmo Pinnguen; 5550114581
 *
 * References:
 * Morelli, R., & Walde, R. (2016).
 * Java, Java, Java: Object-Oriented Problem Solving
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Version: 2025-05-26
 */
package beatcrush;

import java.util.ArrayList;

/**
 * GameManager.java
 * 
 * Controls the main flow of the game, including track selection and game state.
 * 
 * GameManager "has-a" Player
 * GameManager "has-a" Track
 * 
 */
public class GameManager
{
	private Track currentTrack; // The track being played
	private Player player; // The player playing the game
	private boolean isPlaying; // True if the game is currently in progress
	private String gameState; // "MENU", "PLAYING", "RESULTS"
	private ArrayList<Track> availableTracks = new ArrayList<>();

	// Constructor
	public GameManager(Player player)
	{
		this.player = player;
		this.isPlaying = false;
		this.gameState = "MENU";
	}

	/**
	 * Starts the game with given track.
	 * Sets game state to "PLAYING".
	 */
	public void startGame(Track track)
	{
		this.currentTrack = track;
		this.isPlaying = true;
		this.gameState = "PLAYING";
		player.resetStats(); // Reset player score and combo at start
		System.out.println("Starting game with track: " + track.getTitle());
	}

	/**
	 * Ends the game and displays the results.
	 * Sets game state to "RESULTS".
	 */
	public void endGame()
	{
		this.isPlaying = false;
		this.gameState = "RESULTS";
		System.out.println("Game Over! Final score: " + player.getScore());
	}

	// Getter for current game state
	public String getGameState()
	{
		return gameState;
	}

	// Getter for current track
	public Track getCurrentTrack()
	{
		return currentTrack;
	}

	// Getter for the player
	public Player getPlayer()
	{
		return player;
	}

	// Checks if game is currently playing
	public boolean isPlaying()
	{
		return isPlaying;
	}

	// Adds a new Track to the list of available tracks
	public void addTrack(Track track)
	{
		availableTracks.add(track);
	}

	// Selects a track to play by setting the currentTrack
	public void selectTrack(Track track)
	{
		this.currentTrack = track;
		System.out.println("Selected track: " + track.getTitle());
	}
}
