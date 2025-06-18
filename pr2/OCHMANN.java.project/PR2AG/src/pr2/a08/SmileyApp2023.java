package pr2.a08;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

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
import javax.swing.event.ChangeEvent;

// Quelle der Icons: https://sourceforge.net/projects/openiconlibrary/

@SuppressWarnings("serial")
public class SmileyApp2023 {

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
			buttonIncSize.addActionListener(this::actionPerformed);
			
			JButton buttonDecSize = new JButton("decrease size");
			buttonDecSize.setActionCommand(Command.SMILEY_SIZE_DEC);
			buttonDecSize.addActionListener(this::actionPerformed);
			
			JSlider sliderSize = new JSlider(10, 500, 200);
			sliderSize.setMajorTickSpacing(100);
			sliderSize.setMinorTickSpacing(25);
			sliderSize.setPaintTicks(true);
			sliderSize.setName(Command.SLIDER_CHANGE_SIZE);
			sliderSize.addChangeListener(this::sliderStateChanged);
			
			setLayout(new GridLayout(5,1));
			add(buttonIncSize);
			add(buttonDecSize);
			add(sliderSize);
			
			setBackground(Color.BLUE);
		}
		
		private void sliderStateChanged(ChangeEvent e) {
			Object source = e.getSource();
			if (! (source instanceof JSlider)) {
				System.out.println("something went wrong");
				return;
			}
			JSlider slider = (JSlider) source;
			double value = slider.getValue();
			String slidername = slider.getName();
			switch (slidername) {
			case Command.SLIDER_CHANGE_SIZE:
				model.setRadiusHead(value);
				break;
			}
			model.update(); //warum sollte man in view eine preparedrawingmethode schreiben, wenn diese nur die Modelmethoden für das update aufruft?
			view.repaint();
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
		public final static String SLIDER_CHANGE_SIZE = "SLIDER_CHANGE_SIZE";
	}
	
	private static class SmileyToolbar extends JToolBar {
		public SmileyToolbar(Color color) {
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
			add(iconButton1);
			add(Box.createVerticalStrut(10));
			iconButton2.setAlignmentX(CENTER_ALIGNMENT);
			add(iconButton2);
			add(Box.createVerticalStrut(10));
			iconButton3.setAlignmentX(CENTER_ALIGNMENT);
			add(iconButton3);
			add(Box.createVerticalStrut(10));
			iconButton4.setAlignmentX(CENTER_ALIGNMENT);
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