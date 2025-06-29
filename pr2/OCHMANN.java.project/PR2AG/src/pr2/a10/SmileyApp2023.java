package pr2.a10;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;

// Quelle der Icons: https://sourceforge.net/projects/openiconlibrary/

@SuppressWarnings("serial")
public class SmileyApp2023 {

	public static void main(String[] args) {
		SmileyModel model = new SmileyModel();
		SmileyView view = new SmileyView(model);
		SmileyController controller = new SmileyController(model, view);
		EventSmileyFrame frame = new EventSmileyFrame(model, view, controller);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setJMenuBar(new SmileyMenubar(Color.BLUE));
		frame.setVisible(true);
	}
	
	private static class EventSmileyFrame extends JFrame {
		public EventSmileyFrame(SmileyModel model, SmileyView view, SmileyController controller) {			
			setTitle("SmileyEventHandling");
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(new SmileyToolbar(Color.BLACK, controller), BorderLayout.WEST);
			getContentPane().add(new NestedComponentsFrame(view, controller), BorderLayout.CENTER);
		}
	}
	
	private static class NestedComponentsFrame extends JPanel {
		public NestedComponentsFrame(SmileyView view, SmileyController controller) {
			setLayout(new BorderLayout());
			add(new ControlPanel(Color.YELLOW, controller), BorderLayout.EAST);
			add(new GPanel(Color.MAGENTA, view), BorderLayout.CENTER);
		}
	}
	
	private static class ControlPanel extends JPanel{
		
		public ControlPanel(Color color, SmileyController controller) {
						
			JButton buttonIncSize = new JButton("increase size");
			buttonIncSize.setActionCommand(Command.SMILEY_SIZE_INC);
			buttonIncSize.addActionListener(controller);
			
			JButton buttonDecSize = new JButton("decrease size");
			buttonDecSize.setActionCommand(Command.SMILEY_SIZE_DEC);
			buttonDecSize.addActionListener(controller);
			
			JSlider sliderSize = new JSlider(10, 500, 200);
			sliderSize.setMajorTickSpacing(100);
			sliderSize.setMinorTickSpacing(25);
			sliderSize.setPaintTicks(true);
			sliderSize.setName(Command.SLIDER_CHANGE_SIZE);
			sliderSize.addChangeListener(controller);
			
			JSlider sliderEyes = new JSlider(10, 500, 200);
			sliderEyes.setMajorTickSpacing(100);
			sliderEyes.setMinorTickSpacing(25);
			sliderEyes.setPaintTicks(true);
			sliderEyes.setName(Command.SLIDER_CHANGE_EYES);
			sliderEyes.addChangeListener(controller);
			
//			System.out.println("Slider-Name: " + sliderEyes.getName());
			
			setLayout(new GridLayout(5,1));
			add(buttonIncSize);
			add(buttonDecSize);
			add(sliderSize);
			add(sliderEyes);
			
			setBackground(color);
		}
	}
	
	private static class GPanel extends JPanel {
		public GPanel(Color color, SmileyView view) {
			setPreferredSize(new Dimension (400, 600));
			setBackground(color);
			setLayout(new BorderLayout());
			add(view, BorderLayout.CENTER);
		}
	}
	
	
	
	private static class SmileyToolbar extends JToolBar {
		public SmileyToolbar(Color color, SmileyController controller) {
			setPreferredSize(new Dimension (200, 400));
			setBackground(color);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			
			ImageIcon icon1 = new ImageIcon("img/icon1.png"); // relativer oder absoluter Pfad
			ImageIcon icon2 = new ImageIcon("img/icon2.png"); // relativer oder absoluter Pfad
			ImageIcon icon3 = new ImageIcon("img/icon3.png"); // relativer oder absoluter Pfad
			ImageIcon icon4 = new ImageIcon("img/icon4.png"); // relativer oder absoluter Pfad
			JButton iconButton1 = new JButton(icon1);
			JButton iconButton2 = new JButton(icon2);
			JButton iconButton3 = new JButton(icon3);
			JButton iconButton4 = new JButton(icon4);
			
			add(Box.createVerticalGlue()); // Flexibler Abstand oben
			iconButton1.setAlignmentX(CENTER_ALIGNMENT);
			iconButton1.setActionCommand(Command.SMILEY_SIZE_INC);
			iconButton1.addActionListener(controller);
			add(iconButton1);
			add(Box.createVerticalStrut(10));
			iconButton2.setAlignmentX(CENTER_ALIGNMENT);
			iconButton2.setActionCommand(Command.SMILEY_SIZE_DEC);
			iconButton2.addActionListener(controller);
			add(iconButton2);
			add(Box.createVerticalStrut(10));
			iconButton3.setAlignmentX(CENTER_ALIGNMENT);
			iconButton3.setActionCommand(Command.SMILEY_EYE_INC);
			iconButton3.addActionListener(controller);
			add(iconButton3);
			add(Box.createVerticalStrut(10));
			iconButton4.setAlignmentX(CENTER_ALIGNMENT);
			iconButton4.setActionCommand(Command.SMILEY_EYE_DEC);
			iconButton4.addActionListener(controller);
			add(iconButton4);
			add(Box.createVerticalGlue()); // Flexibler Abstand unten
			
		}
	}
	
	private static class SmileyMenubar extends JMenuBar {
		public SmileyMenubar(Color color) {
			setPreferredSize(new Dimension (400, 100));
			setBackground(color);
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			add(Box.createHorizontalGlue()); // Flexibler Abstand oben

			ButtonPanelDummy button = new ButtonPanelDummy(Color.GREEN);
			add(button);
			button.setAlignmentY(CENTER_ALIGNMENT); // optional: zentriert in Y
				
			add(Box.createHorizontalStrut(10)); // 10px Abstand zwischen Buttons
	
			add(Box.createHorizontalGlue()); // Flexibler Abstand unten
		}
	}
	
	private static class ButtonPanelDummy extends JPanel {
		public ButtonPanelDummy(Color color) {
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