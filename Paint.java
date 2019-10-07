package ca.utoronto.utm.paint;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * We create a Paint that controls the MVC
 * @author Oofperiodjpeg
 */
public class Paint extends Application {

	PaintModel model; // Model
	View view; // View + Controller
	/**Launches the program
	 * @param args the users input
	 */
	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * @param stage Creates a stage for the MVC
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
		this.model = new PaintModel();

		// View + Controller
		this.view = new View(model, stage);
	}
}