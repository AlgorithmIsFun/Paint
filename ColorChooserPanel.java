package ca.utoronto.utm.paint;

//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ColorPicker.html

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
/**
 * We create a ColorChooserPanel that handles the color of the shapes
 * @author Oofperiodjpeg
 */
public class ColorChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;
    private PaintPanel paintPanel; // So we can talk to our parent or other components of the view
    private javafx.scene.control.ColorPicker ColorPicker = new javafx.scene.control.ColorPicker();
    private Color color;
    private String convert = "0123456789abcdef";
    private int red, green, blue;
    /**
	 * Construct a new ColorChooserPanel with the specified view and panel
	 * @param view The view of the MVC model
	 * @param panel handles repaint, mouse events and mode changes
	 */
    public ColorChooserPanel(View view, PaintPanel paintPanel){
        this.view = view;
        this.paintPanel = paintPanel;
        ColorPicker.setPrefWidth(100);
        this.add(ColorPicker, 0, 0);
        ColorPicker.setOnAction(this::handle);
    }
    /**
	 * @return returns the color
	 */
    public Color getColor(){
        return this.color;
    }
    /**Sets the color of the panel
	 * 
	 */
    public void convertHex(){
            this.red = convert.indexOf(this.color.toString().charAt(2)) * 16 + convert.indexOf(this.color.toString().charAt(3));
            this.green = convert.indexOf(this.color.toString().charAt(4)) * 16 + convert.indexOf(this.color.toString().charAt(5));
            this.blue = convert.indexOf(this.color.toString().charAt(6)) * 16 + convert.indexOf(this.color.toString().charAt(7));
        paintPanel.setColor(this.red, this.green, this.blue);
    }
    /**
	 * @return returns the ColorPicker
	 */
    public javafx.scene.control.ColorPicker getColorPicker(){
        return this.ColorPicker;
    }
    /** User picks the color and calls convertHex()
	 * @param event handles the users choice
	 */
    public void handle(ActionEvent event) {
        Color c = this.ColorPicker.getValue();
        this.color = c;
        this.convertHex();
    }
}
