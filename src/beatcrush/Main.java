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
 * Purpose: The reponsibility of main is ...
 *
 * main is-a ...
 * main is ...
 */
import javax.swing.*;

public class Main extends JFrame
{
	public static void main(String[] args)
	{
		Main app = new Main();
		app.start();
	}

	public void start()
	{
		setTitle("BeatCrush");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		setContentPane(new BeatCrushMainMenu(this));
		setVisible(true);
	}

	public void showMainMenu()
	{
		getContentPane().removeAll();
		setContentPane(new BeatCrushMainMenu(this));
		revalidate();
		repaint();
	}
}