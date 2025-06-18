package pr2.a07;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Mockup2 {

	public static void main(String[] args) {
		EventSmileyFrame frame = new EventSmileyFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setJMenuBar(new SmileyMenubar(Color.BLUE));
		frame.setVisible(true);
	}
	
	private static class EventSmileyFrame extends JFrame {
		public EventSmileyFrame() {
			Point2D position = new Point2D.Double(100, 100);
			
			setTitle("SmileyEventHandling");
			SmileyModel model = new SmileyModel(position, 100, true);
			SmileyView view = new SmileyView(model);
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(new SmileyToolbar(Color.BLACK), BorderLayout.WEST);
			getContentPane().add(new NestedComponentsFrame(model, view), BorderLayout.CENTER);
		}
	}
	
	private static class NestedComponentsFrame extends JPanel {
		public NestedComponentsFrame(SmileyModel model, SmileyView view) {
			setLayout(new BorderLayout());
			add(new ControlPanel(Color.YELLOW, model, view), BorderLayout.EAST);
			add(new GPanel(Color.MAGENTA, model, view), BorderLayout.CENTER);
		}
	}
	
	private static class ControlPanel extends JPanel implements ActionListener{
		protected SmileyModel model;
		protected SmileyView view;
		
		public ControlPanel(Color color, SmileyModel model, SmileyView view) {
			this.model = model;
			this.view = view;
						
			JButton buttonIncSize = new JButton("increase size");
			buttonIncSize.setActionCommand(Command.SMILEY_SIZE_INC);
			buttonIncSize.addActionListener(this);
			
			JButton buttonDecSize = new JButton("decrease size");
			buttonDecSize.setActionCommand(Command.SMILEY_SIZE_DEC);
			buttonDecSize.addActionListener(this);
			
			JButton buttonIncEyes = new JButton("increase eyes");
			buttonIncEyes.setActionCommand(Command.SMILEY_EYES_INC);
			buttonIncEyes.addActionListener(this);
			
			JButton buttonDecEyes = new JButton("decrease eyes");
			buttonDecEyes.setActionCommand(Command.SMILEY_EYES_DEC);
			buttonDecEyes.addActionListener(this);
			
			setLayout(new GridLayout(5,1));
			add(buttonIncSize);
			add(buttonDecSize);
			add(buttonIncEyes);
			add(buttonDecEyes);
			
			setBackground(Color.BLUE);
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
		    switch (event.getActionCommand()) {
		        case Command.SMILEY_SIZE_INC:
		            model.changeSize(0.1);
				    model.update();
		            break;
		        case Command.SMILEY_SIZE_DEC:
		            model.changeSize(-0.1);
				    model.update();
		            break;
		        case Command.SMILEY_EYES_INC:
		        	model.changeEyes(0.1);
		        	break;
		        case Command.SMILEY_EYES_DEC:
		            model.changeEyes(-0.1);
		            break;
		    }
		    view.repaint();
		}
	}
	
	private static class GPanel extends JPanel {
		public GPanel(Color color, SmileyModel model, SmileyView view) {
			setPreferredSize(new Dimension (400, 600));
			setBackground(color);
			setLayout(new BorderLayout());
			add(view, BorderLayout.CENTER);
		}
	}
	
	private static class Command{
		public final static String SMILEY_SIZE_INC = "SMILEY_SIZE_INC";
		public final static String SMILEY_SIZE_DEC = "SMILEY_SIZE_DEC";
		public final static String SMILEY_EYES_INC = "SMILEY_EYES_INC";
		public final static String SMILEY_EYES_DEC = "SMILEY_EYES_DEC";
	}
	
	private static class SmileyToolbar extends JToolBar {
		public SmileyToolbar(Color color) {
			setPreferredSize(new Dimension (100, 400));
			setBackground(color);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			add(Box.createVerticalGlue()); // Flexibler Abstand oben
			for (int i = 0; i < 4; i++) {
				ButtonPanelDummy button = new ButtonPanelDummy(Color.GREEN);
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