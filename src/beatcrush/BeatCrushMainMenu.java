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
 *
 * Displays a non-functional main menu GUI for BeatCrush using textbook methods.
 * 
 * This class "has-a" JFrame and multiple JButtons for user interface.
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * BeatCrushMainMenu builds the home screen.
 * This "has-a" JFrame and multiple buttons to let you choose tracks or view
 * scores.
 */
public class BeatCrushMainMenu extends JPanel implements ActionListener
{
	private Main main;
	private String selectedTrack = "be_nice_2_me";
	private JButton playButton;

	public BeatCrushMainMenu(Main main)
	{
		this.main = main;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		JLabel title = new JLabel("BeatCrush", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 36));
		title.setForeground(Color.MAGENTA);
		add(title, BorderLayout.NORTH);

		JPanel trackPanel = new JPanel(new GridLayout(5, 1, 10, 10));
		trackPanel.setBackground(Color.BLACK);

		String[] tracks = { "be_nice_2_me", "al_mosiqar", "cartman",
				"true_purple" };
		for (String trackName : tracks)
		{
			JButton trackButton = new JButton(trackName);
			trackButton.addActionListener(this);
			trackPanel.add(trackButton);
		}

		// High Score button
		JButton scoreButton = new JButton("High Scores");
		scoreButton.addActionListener(e -> {
			main.setContentPane(new HighScorePanel(main));
			main.revalidate();
		});
		trackPanel.add(scoreButton);

		add(trackPanel, BorderLayout.CENTER);

		playButton = new JButton("PLAY");
		playButton.setFont(new Font("Arial", Font.BOLD, 24));
		playButton.addActionListener(e -> launchGame());
		add(playButton, BorderLayout.SOUTH);
	}

	private void launchGame()
	{
		String playerName = JOptionPane.showInputDialog(main,
				"Enter your name:");
		if (playerName == null || playerName.trim().equals(""))
		{
			playerName = "Player"; // default if empty
		}

		Track track = new Track(selectedTrack);
		track.loadBeatmap(selectedTrack + "_beatmap.txt");

		AudioPlayer audioPlayer = new AudioPlayer();
		audioPlayer.play(selectedTrack + ".wav");

		GamePanel panel = new GamePanel(track, audioPlayer, main, playerName);
		main.setContentPane(panel);
		main.revalidate();
		panel.requestFocusInWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton source = (JButton) e.getSource();
		selectedTrack = source.getText();
	}
}
