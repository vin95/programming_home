package pr2.a05;
import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SmileyTest {

	public static void main(String[] args) {
		DrawFrame frame = new DrawFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

	private static class DrawFrame extends JFrame {
		public DrawFrame() {
			setTitle("Simple Drawing Frame");
			setLayout(new BorderLayout());
			add(new SmileyView(), BorderLayout.CENTER);
		}
	}
}