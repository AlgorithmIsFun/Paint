package ca.utoronto.utm.paint;

import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
/**
 * We create a ShapeChooserPanel that handles the buttons for Shape Chooser
 * @author Oofperiodjpeg
 */
public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private static Label label1;
	private View view; // So we can talk to our parent or other components of the view
	private Boolean fill = false;
	private Button[] buttons;
	private Image[] image;
	private ImageView[] icon;
	private FileInputStream input;
	/**
	 * Construct a ShapeChooserPanel with buttons and images on the buttons
	 * @param view view + controller
	 */
	public ShapeChooserPanel(View view) {
		this.view = view;
		String[] Images = {"circle.png","rectangle.png","square.png","squiggle.png","pline.png","fill.png"};
		
		image = new Image[6]; 
		icon = new ImageView[6]; 
		int num = 0;
		for (String iname : Images) {
			try {
				input = new FileInputStream("image/"+iname);
				image[num] = new Image(input); //creates the images using the files in images
				icon[num] = new ImageView(image[num]); //makes the images viewable in Button
				num++;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline","Fill" };
		int row = 0;
		buttons = new Button[6];
		for (String label : buttonLabels) {
			buttons[row] = new Button(label, icon[row]);
			buttons[row].setMinWidth(100);
			this.add(buttons[row], 0, row);
			buttons[row].setOnAction(this);
			row++;
		}
		
		label1 = new Label("Outline"); 
		label1.setMinWidth(100);
		label1.setAlignment(Pos.CENTER);
		this.add(label1, 0, 7);
		
	}
	
	
	
	/**
	 * Reset the id of all buttons
	 */
	public void reset() {
		for (int i = 0; i < 5; i++) {
			buttons[i].setId("");
		}
	}
	/** handles the button pressed
	 * @param event handles the user choice
	 */
	@Override
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getText();
		
		if ("Fill" != command) {
			
		int j = 0;
		for (int i = 0; i < 5; i++) {
			if (buttons[i].getText() == command) {
				j = i;
			}
			buttons[i].setId("");
		}
		buttons[j].setId("pressed");
		this.view.getPaintPanel().setMode(command);
		System.out.println(command);
		}else {
			fill = !fill;
			}if (fill) {
				label1.setText("Solid");
				System.out.println("Solid");
			}else {
				label1.setText("Outline");
				System.out.println("Outline");
			}
		}
	/**
	 * @return return if the buttons are on fill
	 */
	public Boolean getfill() {
		return fill;
	}

}
