package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
/**
 * We create a PaintPanel that handles mouse events, mode changes and repaints
 * @author Oofperiodjpeg
 */
class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private PaintModel model, copy; // slight departure from MVC, because of the way painting works
	private static View view; // So we can talk to our parent or other components of the view

	private String mode; // modifies how we interpret input (could be better?)
	private Circle circle; // the circle we are building
	private Rectangle rectangle; // the rectangle we are building
	private PolyLine p;
	private Square square;
	private Squiggle squiggle;
	private Stack undo, redo;
	//the color panel we are using
	private ColorChooserPanel ColorPanel;
	private LineThicknessChooserPanel linethicknesspanel;
	private ShapeManipulatorStrategy strategy;

	private int red = 255;
	private int green = 255;
	private int blue = 255;
	private int linethickness = 1;
	
	/**Sets the color
	 * @param red Sets the red value
	 * @param green Sets the green value
	 * @param blue Sets the blue value
	 */
	public void setColor(int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	/**
	 * @return returns the red value
	 */
	public int getRed() {
		return red;
	}
	/**
	 * @return returns the green value
	 */
	public int getGreen() {
		return green;
	}
	/**
	 * @return returns the blue value
	 */
	public int getBlue() {
		return blue;
	}
	/**
	 * @param l Sets the line thickness
	 */
	public void setLineThickness(int l) {
		this.linethickness = l;
	}
	/**
	 * @param strategy Sets Strategy
	 */
	public void setStrategy(ShapeManipulatorStrategy strategy) {
		this.strategy = strategy;
	}
	private Canvas canvas;
	private Save save;
	/**
	 * Construct a new PantPanel
	 * @param model model in MVC
	 * @param view view + controller in MVC
	 */
	public PaintPanel(PaintModel model, View view) {
		this.save = new Save();
		this.canvas = new Canvas(300, 300);
		this.getChildren().add(this.canvas);

		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: blue");
		this.addEventHandler(MouseEvent.ANY, this);

		this.mode = "";
		this.squiggle = new Squiggle();
		this.p = new PolyLine(new Point(0,0));
		this.undo = new Stack();
		this.redo = new Stack();

		this.model = model;
		this.model.addObserver(this);
		this.copy = this.model;

		this.view = view;
		this.ColorPanel = view.getColorChooserPanel();
	}
	/**
	 * @return returns if the setting is on fill
	 */
	public static Boolean GetFill() {
		Boolean fill = view.getShapeChooserPanel().getfill();
		return fill;
	}
	
	/**
	 * repaints the canvas
	 */
	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		this.model.executeAll(g);
	}
	/** updates the model
	 * @param o Observerable update
	 * @param arg Object argument
	 */
	@Override
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}
	/**
	 * @return returns the line thickness
	 */
	public int getLineThickness() {
		return linethickness;
	}
	/**
	 * @return returns the undo Stack
	 */
	public Stack getUndo() {
		return undo;
	}
	/**
	 * @param mode Sets the mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
		this.ModeMenu(mode);
		
	}
	/**
	 * @param mode Executes Buttons
	 */
	public void ModeMenu(String mode) {
		if (this.mode != "Polyline" && this.model.getType(PolyLine.class).size() != 0) {
			ArrayList<ShapeCommand> mod = this.model.getType(PolyLine.class);
			PolyLine p2 = (PolyLine) mod.get(mod.size()-1);
			if (!p2.ready()) {
				this.model.del(p2);
		}}
		
		if (this.mode == "Undo" && !this.undo.isEmpty()) {
			ShapeCommand del_shape = this.undo.pop();
			this.redo.push(del_shape);
			if (del_shape.type() == "Rectangle") {
				this.model.del(del_shape);
			}
			else if (del_shape.type() == "Circle") {
				this.model.del(del_shape);
			}
			else if (del_shape.type() == "Point") {
				this.model.del(del_shape);
			}
			else if (del_shape.type() == "Polyline") {
					this.model.del(del_shape);
			}
			else if (del_shape.type() == "Squiggle") {
				this.model.del(del_shape);
			}
			else if (del_shape.type() == "Square") {
				this.model.del(del_shape);
			}
		}
		else if (this.mode == "Redo" && !this.redo.isEmpty()){
			ShapeCommand del_shape = this.redo.pop();
			this.undo.push(del_shape);
			if (del_shape.type() == "Rectangle") {
				this.model.add(del_shape);
			}
			else if (del_shape.type() == "Circle") {
				this.model.add(del_shape);
			}
			else if (del_shape.type() == "Point") {
				this.model.add(del_shape);
			}
			else if (del_shape.type() == "Polyline") {
					this.model.add(del_shape);
			}
			else if (del_shape.type() == "Squiggle") {
				this.model.add(del_shape);
			}
			else if (del_shape.type() == "Square") {
				this.model.add(del_shape);
			}
		}
		else if (this.mode == "Exit") {
			System.exit(0);
		}
		else if (this.mode == "New") {
			circle = null; // the circle we are building
			rectangle = null; // the rectangle we are building
			p = null;
			undo = new Stack();
			redo = new Stack();
			this.model.delAll();
		}
		else if (this.mode == "Save") {
			this.save.save_Object(this.model);
		}
		else if (this.mode == "Open") {
			try {
				this.model = this.save.read_Object();
				this.model.addObserver(this);
				repaint();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (this.mode == "Copy") {
			this.copy = new PaintModel(this.model);
		}
		else if (this.mode == "Cut") {
			this.copy = new PaintModel(this.model);
			setMode("New");
		}
		else if (this.mode == "Paste") {
			
			if (this.copy != null) {
				this.model.addAll(this.copy);
				repaint();
			}
		}
		}
	
	/**
	 * @param event handles mouse event on canvas
	 */
	@Override
	public void handle(MouseEvent event) {

		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			mouseDragged(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			mousePressed(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			mouseMoved(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			mouseClicked(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			mouseReleased(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			mouseEntered(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			mouseExited(event);
		}

	}
	/**
	 * @param e handles mouse moved
	 */
	private void mouseMoved(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
	}
	/**
	 * @param e handles mouse dragged
	 */
	private void mouseDragged(MouseEvent e) {
		if (this.mode == "Squiggle") {
			this.squiggle = (Squiggle) this.squiggle.mouseDragged(model, this, e);
		}
		else if (this.mode == "Polyline"){
			Point new_p = new Point((int) e.getX(), (int) e.getY());
			if (this.model.getType(PolyLine.class).size() != 0) {
				ArrayList<ShapeCommand> mod = this.model.getType(PolyLine.class);
				PolyLine p2 = (PolyLine) mod.get(mod.size()-1);
				if (!p2.ready()) {
					p2.setP2(new_p);
					this.undo.push(p2);
				}
			}
			p = new PolyLine(new_p);
			p.setColor(this.red, this.green, this.blue);
			p.setLineThickness(this.linethickness);
			this.model.add(p);
		} else if (this.mode == "Circle") {
			this.circle = (Circle) this.circle.mouseDragged(model, this, e);
		} else if (this.mode == "Rectangle") {
			this.rectangle = (Rectangle) this.rectangle.mouseDragged(model, this, e);
		} else if (this.mode == "Square") {
			this.square = (Square) this.square.mouseDragged(model, this, e);
		}
	}
	/**
	 * @param e handles mouse clicked
	 */
	private void mouseClicked(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {
			
		}
		else if (this.mode == "Polyline") {
			
			this.p = (PolyLine) this.p.mouseClicked(model, this, e);
		}
	}
	/**
	 * @param e handles mouse pressed
	 */
	private void mousePressed(MouseEvent e) {
		if (this.mode == "Squiggle") {
			this.squiggle = new Squiggle();
			this.squiggle = (Squiggle) this.squiggle.mousePressed(model, this, e);
		} else if (this.mode == "Circle") {
			this.circle = new Circle(null, null, 0, null);
			this.circle = (Circle) this.circle.mousePressed(model, this, e);
			
		} else if (this.mode == "Rectangle"){
			//create rectangle object when mouse is pressed under mode = "Rectangle"
			this.rectangle = new Rectangle(new Point(0,0), new Point(0,0), false);
			this.rectangle = (Rectangle) this.rectangle.mousePressed(model, this, e);
		} else if (this.mode == "Square") {
			this.square = new Square(new Point(0,0), new Point(0,0), false);
			this.square = (Square) this.square.mousePressed(model, this, e);
		}
	}
	/**
	 * @param e handles mouse released
	 */
	private void mouseReleased(MouseEvent e) {
		if (this.mode == "Squiggle") {
			this.squiggle = (Squiggle) this.squiggle.mouseReleased(model, this, e);
		} else if (this.mode == "Circle") {
			this.circle = (Circle) this.circle.mouseReleased(model, this, e);
		} else if (this.mode == "Rectangle"){
			this.rectangle = (Rectangle) this.rectangle.mouseReleased(model, this, e);
		} else if (this.mode == "Square") {
			this.square = (Square) this.square.mouseReleased(model, this, e);
		}
	}
	/**
	 * @param e handles mouse entered
	 */
	private void mouseEntered(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
	}
	/**
	 * @param e handles mouse exited
	 */
	private void mouseExited(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
	}
}
