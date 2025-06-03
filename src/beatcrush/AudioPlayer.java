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
 * AudioPlayer.java
 *
 * Plays .wav audio files using javax.sound.sampled.
 *
 * This class "has-a" responsibility to handle audio playback only.
 * Game logic and timing are controlled elsewhere (in Track or
 * GameManager).
 *
 */

import javax.sound.sampled.*;
import java.io.*;

public class AudioPlayer
{

	// AudioPlayer "has-a" Clip to store the loaded audio file
	private Clip clip;

	/**
	 * Loads and plays a .wav file from the file system.
	 * 
	 * @param fileName the name of the audio file to play ("beat1.wav")
	 */
	public void play(String fileName)
	{
		try
		{
			// Load the sound file from the specified path
			File soundFile = new File(fileName);

			// Create an AudioInputStream from the file
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(soundFile);
			// Get a sound clip resource
			clip = AudioSystem.getClip();

			// Open the clip and load samples from the audio input stream
			clip.open(audioIn);

			// Start playing the clip
			clip.start();
		}
		catch (Exception e)
		{
			System.out.println("Error playing sound: " + e.getMessage());
		}
	}

	// Stops the currently playing sound, if one is active.
	public void stop()
	{
		if (clip != null && clip.isRunning())
		{
			clip.stop();
		}
	}
}
