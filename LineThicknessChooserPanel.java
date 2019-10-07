package ca.utoronto.utm.paint; 
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
/**
 * We create a LineThicknessChooserPanel that handles the thickness of the lines
 * @author Oofperiodjpeg
 */
public class LineThicknessChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view
	private PaintPanel paintPanel;
	private javafx.scene.control.ChoiceBox choicebox = new javafx.scene.control.ChoiceBox();
	private String choice;
	private int linethickness;
	/**
	 * Construct a new LineThicknessChooserPanel with the specified view and panel
	 * @param view The view of the MVC model
	 * @param panel handles repaint, mouse events and mode changes
	 */
	public LineThicknessChooserPanel(View view, PaintPanel paintPanel){
		this.view = view;
		this.paintPanel= paintPanel;
		
		ChoiceBox choicebox = new ChoiceBox(FXCollections.observableArrayList(
				"Line Thickness 1","Line Thickness 2","Line Thickness 3","Line Thickness 4","Line Thickness 5","Line Thickness 6","Line Thickness 7","Line Thickness 8","Line Thickness 9"));
		choicebox.getSelectionModel().selectFirst();
		choicebox.setMinWidth(100);
		this.add(choicebox, 0, 0);
		choicebox.setOnAction(this);
		      }
	/**Tells the panel the line thickness
	 * @param choice Sets the line thickness choice
	 */
	public void setChoice(String choice) {
		switch(choice) {
		case "Line Thickness 1": linethickness = 1;
			break;
		case "Line Thickness 2": linethickness = 2;
		break;
		case "Line Thickness 3": linethickness = 3;
		break;
		case "Line Thickness 4": linethickness = 4;
		break;
		case "Line Thickness 5": linethickness = 5;
		break;
		case "Line Thickness 6": linethickness = 6;
		break;
		case "Line Thickness 7": linethickness = 7;
		break;
		case "Line Thickness 8": linethickness = 8;
		break;
		case "Line Thickness 9": linethickness = 9;
		break;
		}
		paintPanel.setLineThickness(linethickness);
	}
		   	
	/**
	 * @return returns the linethickness
	 */
	public int getChoice() {
		return linethickness;
	}
	
	/**Sets the choice and tells the panel
	 * @param event handles the users event
	 */
    public void handle(ActionEvent event) {
    	String s = (String) ((ChoiceBox) event.getSource()).getValue();
    	setChoice(s);
        System.out.print(s);
    }
	}

	

