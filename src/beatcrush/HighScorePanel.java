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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * HighScorePanel displays a list of scores from the highscores.txt file.
 * It "has-a" FileManager to load them and a "has-a" reference to Main.
 */
public class HighScorePanel extends JPanel
{
	private Main main;

	public HighScorePanel(Main main)
	{
		this.main = main;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		JLabel title = new JLabel("High Scores");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 24));
		title.setForeground(Color.WHITE);
		add(title, BorderLayout.NORTH);

		FileManager fileManager = new FileManager();
		ArrayList<String> scores = fileManager.loadHighScores();

		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
		scorePanel.setBackground(Color.BLACK);

		if (scores.size() == 0)
		{
			JLabel noScores = new JLabel("No scores yet.");
			noScores.setForeground(Color.WHITE);
			scorePanel.add(noScores);
		}
		else
		{
			for (String line : scores)
			{
				JLabel scoreLabel = new JLabel(line);
				scoreLabel.setForeground(Color.WHITE);
				scorePanel.add(scoreLabel);
			}
		}

		JScrollPane scrollPane = new JScrollPane(scorePanel);
		scrollPane.setBorder(null);
		add(scrollPane, BorderLayout.CENTER);

		JButton backButton = new JButton("Return to Menu");
		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				main.showMainMenu();
			}
		});
		add(backButton, BorderLayout.SOUTH);
	}
}
