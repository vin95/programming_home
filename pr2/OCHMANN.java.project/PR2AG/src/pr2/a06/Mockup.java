package pr2.a06;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Mockup {

	public static void main(String[] args) {
		NestedComponentsFrame frame = new NestedComponentsFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
	
	private static class NestedComponentsFrame extends JFrame {
		public NestedComponentsFrame() {
			setTitle("nested Components");
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(new ControlPanel(Color.YELLOW), BorderLayout.EAST);
			getContentPane().add(new GPanel(Color.MAGENTA), BorderLayout.CENTER);
			getContentPane().add(new SmileyToolbar(Color.CYAN), BorderLayout.WEST);
			getContentPane().add(new SmileyMenubar(Color.BLACK), BorderLayout.NORTH);
		}
	}
	
	private static class ControlPanel extends JPanel {
		public ControlPanel(Color color) {
			setPreferredSize(new Dimension (100, 400));
			setBackground(color);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(Box.createVerticalGlue()); // Flexibler Abstand oben
			for (int i = 0; i < 4; i++) {
				ButtonPanel button = new ButtonPanel(Color.GREEN);
				add(button);
				button.setAlignmentX(CENTER_ALIGNMENT); // optional: zentriert in X
				if (i < 3) {
					add(Box.createVerticalStrut(10)); // 10px Abstand zwischen Buttons
				}
			}
			add(Box.createVerticalGlue()); // Flexibler Abstand unten
		}
	}
	
	private static class GPanel extends JPanel {
		public GPanel(Color color) {
			setPreferredSize(new Dimension (400, 600));
			setBackground(color);
			setLayout(new BorderLayout());
			add(new pr2.a06.SmileyView(), BorderLayout.CENTER);
		}
	}
	
	private static class SmileyToolbar extends JToolBar {
		public SmileyToolbar(Color color) {
			setPreferredSize(new Dimension (100, 400));
			setBackground(color);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			add(Box.createVerticalGlue()); // Flexibler Abstand oben
			for (int i = 0; i < 4; i++) {
				ButtonPanel button = new ButtonPanel(Color.GREEN);
				add(button);
				button.setAlignmentX(CENTER_ALIGNMENT); // optional: zentriert in X
				if (i < 3) {
					add(Box.createVerticalStrut(10)); // 10px Abstand zwischen Buttons
				}
			}
			add(Box.createVerticalGlue()); // Flexibler Abstand unten
		}
	}
	
	private static class SmileyMenubar extends JMenuBar {
		public SmileyMenubar(Color color) {
			setPreferredSize(new Dimension (400, 100));
			setBackground(color);
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			add(Box.createHorizontalGlue()); // Flexibler Abstand oben
			for (int i = 0; i < 4; i++) {
				ButtonPanel button = new ButtonPanel(Color.GREEN);
				add(button);
				button.setAlignmentY(CENTER_ALIGNMENT); // optional: zentriert in Y
				if (i < 3) {
					add(Box.createHorizontalStrut(10)); // 10px Abstand zwischen Buttons
				}
			}
			add(Box.createHorizontalGlue()); // Flexibler Abstand unten
		}
	}
	
	private static class ButtonPanel extends JPanel {
		public ButtonPanel(Color color) {
			Dimension size = new Dimension(80, 80);
	        setPreferredSize(size);
	        setMaximumSize(size); // Begrenze maximale Größe
	        setBackground(color);
	        setLayout(new BorderLayout());

	        JButton button = new JButton("Button");
	        button.setHorizontalAlignment(JLabel.CENTER);
	        button.setVerticalAlignment(JLabel.CENTER);
	        add(button, BorderLayout.CENTER);
		}
	}
}