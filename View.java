package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * We create a View for the MVC
 * @author Oofperiodjpeg
 */
public class View implements EventHandler<ActionEvent> {

	private PaintModel model;

	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private ColorChooserPanel colorChooserPanel;
	private LineThicknessChooserPanel linethicknesschooserpanel;
	/**
	 * Constructs a new View
	 * @param model The PaintModel of the MVC
	 * @param stage The stage of the canvas
	 */
	public View(PaintModel model, Stage stage) {

		this.model = model;
		initUI(stage);
	}
	/** Adds all the panels and css to the stage
	 * @param stage The stage of the canvas
	 */
	private void initUI(Stage stage) {

		this.paintPanel = new PaintPanel(this.model, this);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		this.colorChooserPanel = new ColorChooserPanel(this, this.paintPanel);
		this.linethicknesschooserpanel = new LineThicknessChooserPanel(this, this.paintPanel);
		
		BorderPane root = new BorderPane();
		HBox topbar = new HBox(10);
		topbar.getChildren().addAll(createMenuBar(),linethicknesschooserpanel);
		root.setTop(topbar);
		root.setCenter(this.paintPanel);
		root.setLeft(this.shapeChooserPanel);
		root.setBottom(this.colorChooserPanel); // trying to set the position!!! ***
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Paint");
		stage.show();
	}
	/**
	 * @return return the PaintPanel
	 */
	public PaintPanel getPaintPanel() {
		return paintPanel;
	}
	/**
	 * @return return the ShapeChooserPanel
	 */
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}
	/**
	 * @return return the ColorChooserPanel
	 */
	public ColorChooserPanel getColorChooserPanel() {return colorChooserPanel; }
	/**
	 * @return return the LineThicknessChooserPanel
	 */	
	public LineThicknessChooserPanel getLineThicknessChooserPanel() {
		return linethicknesschooserpanel;
	}
	/**
	 * @return return the MenuBar
	 */	
	private MenuBar createMenuBar() {

		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;

		// A menu for File

		menu = new Menu("File");

		menuItem = new MenuItem("New");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Open");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Save");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Exit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		// Another menu for Edit

		menu = new Menu("Edit");

		menuItem = new MenuItem("Cut");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Copy");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Paste");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Undo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Redo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		return menuBar;
	}
	/**Deals with the menu options
	 * @param event handles the users events 
	 */
	@Override
	public void handle(ActionEvent event) {
		String command = ((MenuItem) event.getSource()).getText();
		this.paintPanel.setMode(command);
		System.out.println(command);
		this.shapeChooserPanel.reset();
	}
}
