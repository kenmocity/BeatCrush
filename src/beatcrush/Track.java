/**
 * Lead Author(s):
 * Kenmo Pinnguen; 5550114581
 *
 * References:
 * Morelli, R., & Walde, R. (2016).
 * Java, Java, Java: Object-Oriented Problem Solving
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Version: 2025-06-02
 */
package beatcrush;

import java.io.*;
import java.util.ArrayList;

/**
 * Track.java
 * 
 * Stores the notes and title of a song/level.
 * Track "has-a" list of Notes.
 */

// Constructor creates empty note list
public class Track
{
	private String title; // Title of the track
	private ArrayList<Note> notes; // List of notes for the track

	// Constructor
	public Track(String title)
	{
		this.title = title;
		this.notes = new ArrayList<>();
	}

	// Adds a note to the track
	public void addNote(Note note)
	{
		notes.add(note);
	}

	// Returns the list of notes for the track
	public ArrayList<Note> getNotes()
	{
		return notes;
	}

	// Returns the title of the track
	public String getTitle()
	{
		return title;
	}

	// Return only notes close to the current time
	public ArrayList<Note> getNotesInWindow(double currentTime, double window)
	{
		ArrayList<Note> activeNotes = new ArrayList<>();
		for (Note note : notes)
		{
			if (!note.wasHit()
					&& Math.abs(note.getTimeStamp() - currentTime) <= window)
			{
				activeNotes.add(note);
			}
		}
		return activeNotes;
	}

	// Used to figure out when the last note occurs
	public double getLastNoteTime()
	{
		double max = 0;
		for (Note note : notes)
		{
			if (note.getTimeStamp() > max)
			{
				max = note.getTimeStamp();
			}
		}
		return max;
	}

	// Load notes from a text beatmap file (format: timestamp direction)
	public void loadBeatmap(String fileName)
	{
		try
		{
			BufferedReader reader = new BufferedReader(
					new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null)
			{
				String[] parts = line.split(" ");
				double timestamp = Double.parseDouble(parts[0]);
				String direction = parts[1];
				notes.add(new Note(direction, timestamp));
			}
			reader.close();
		}
		catch (IOException e)
		{
			System.out.println("Error loading beatmap: " + e.getMessage());
		}
	}

}
