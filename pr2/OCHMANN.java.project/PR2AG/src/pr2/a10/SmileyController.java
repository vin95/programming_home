package pr2.a10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SmileyController implements ActionListener, ChangeListener{
	protected SmileyModel model;
	protected SmileyView view;
	
	public SmileyController(SmileyModel model, SmileyView view) {
		this.model = model;
		this.view = view;
	}
	
	public void stateChanged(ChangeEvent e) {
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
		case Command.SLIDER_CHANGE_EYES:
			view.changeEyes(value);
			break;
		}
//		System.out.println("Slider bewegt: " + slidername + " → Wert: " + value);
		
		view.prepareDrawing();
		view.repaint();
	}
	
	public void actionPerformed(ActionEvent event) {
	    switch (event.getActionCommand()) {
	        case Command.SMILEY_SIZE_INC:
	            view.changeSize(0.1);
			    view.prepareDrawing();
	            break;
	        case Command.SMILEY_SIZE_DEC:
	            view.changeSize(-0.1);
	            view.prepareDrawing();
	            break;
	        case Command.SMILEY_EYE_INC:
	            view.changeEyes(0.1);
	            break;
	        case Command.SMILEY_EYE_DEC:
	            view.changeEyes(-0.1);
	            break;
	    }
	    view.repaint();
	}
}
