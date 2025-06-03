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

/**
 * GamePanel.java
 *
 * Displays falling notes for gameplay. Notes fall down the screen based on
 * timestamp and elapsed time. This class "has-a" Track and Player.
 *
 */

package beatcrush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener
{
	private Track track;
	private Player player;
	private long startTime;
	private int missCount = 0;
	private int maxMisses = 10;
	private boolean gameOver = false;
	private JFrame window;
	private AudioPlayer audioPlayer;
	private JButton backToMenuButton;
	private boolean gameWon = false;
	private Main main;
	private FileManager fileManager = new FileManager();
	private boolean scoreSaved = false; // prevents duplicate saves

	public GamePanel(Track track, AudioPlayer audioPlayer, JFrame window,
			String playerName)
	{
		this.track = track;
		this.player = new Player(playerName);
		this.audioPlayer = audioPlayer;
		this.window = window;
		this.main = main;
		this.startTime = System.currentTimeMillis();

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 600));
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);

		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(Color.BLACK);

		backToMenuButton = new JButton("Back to Menu");
		backToMenuButton.setVisible(false);
		backToMenuButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				audioPlayer.stop();
				window.getContentPane().removeAll();
				window.setContentPane(new BeatCrushMainMenu((Main) window));
				window.revalidate();
				window.repaint();
			}
		});

		bottomPanel.add(backToMenuButton);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		double currentTime = (System.currentTimeMillis() - startTime) / 1000.0;
		ArrayList<Note> notes = track.getNotes();

		for (Note note : notes)
		{
			if (note.wasHit()) continue;
			double noteTime = note.getTimeStamp();
			int y = (int) ((noteTime - currentTime) * -300) + 400;

			if (y >= -40 && y <= getHeight())
			{
				int x = getLaneX(note.getDirection());
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.BOLD, 50));
				g.drawString(getArrowSymbol(note.getDirection()), x + 15,
						y + 20);
			}
			else if (!note.wasHit() && y > getHeight())
			{
				note.setMissed();
				missCount++;
			}
		}

		g.setColor(Color.RED);
		g.fillRect(0, 400, getWidth(), 5);

		// HUD
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString("Score: " + player.getScore(), 20, 30);
		g.drawString("Misses: " + missCount + "/" + maxMisses, 20, 60);

		if (missCount >= maxMisses && !gameOver)
		{
			gameOver = true;
			if (!scoreSaved)
			{
				fileManager.saveHighScore(player.getName(), player.getScore());
				scoreSaved = true;
			}
			backToMenuButton.setVisible(true);
		}

		if (gameOver)
		{
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("GAME OVER", 100, 300);

			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("Final Score: " + player.getScore(), 130, 340);
		}

		// Check if all notes are hit or missed AND the song is over
		if (!gameOver && !gameWon && allNotesProcessed()
				&& currentTime > track.getLastNoteTime() + 1)
		{
			gameWon = true;
			if (!scoreSaved)
			{
				fileManager.saveHighScore(player.getName(), player.getScore());
				scoreSaved = true;
			}
			backToMenuButton.setVisible(true);
		}

		if (gameWon)
		{
			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("YOU WIN!", 120, 300);

			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("Final Score: " + player.getScore(), 130, 340);
		}

		repaint();
	}

	private boolean allNotesProcessed()
	{
		for (Note note : track.getNotes())
		{
			if (!note.wasHit())
			{
				return false;
			}
		}
		return true;
	}

	private int getLaneX(String direction)
	{
		if (direction.equals("LEFT")) return 20;
		if (direction.equals("UP")) return 110;
		if (direction.equals("DOWN")) return 200;
		if (direction.equals("RIGHT")) return 290;
		return 150;
	}

	private String getArrowSymbol(String direction)
	{
		if (direction.equals("LEFT")) return "←";
		if (direction.equals("UP")) return "↑";
		if (direction.equals("DOWN")) return "↓";
		if (direction.equals("RIGHT")) return "→";
		return "?";
	}

	public void keyPressed(KeyEvent e)
	{
		if (gameOver) return;

		String dir = "";
		if (e.getKeyCode() == KeyEvent.VK_LEFT) dir = "LEFT";
		if (e.getKeyCode() == KeyEvent.VK_UP) dir = "UP";
		if (e.getKeyCode() == KeyEvent.VK_DOWN) dir = "DOWN";
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) dir = "RIGHT";

		double currentTime = (System.currentTimeMillis() - startTime) / 1000.0;
		double window = 0.25;

		for (Note note : track.getNotesInWindow(currentTime, window))
		{
			if (!note.wasHit() && note.getDirection().equals(dir))
			{
				note.setHit();
				player.increaseScore(100);
				return;
			}
		}
		missCount++;
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
	}
}