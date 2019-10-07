package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
/**
 * We create a Squiggle that is a list of points
 * @author Oofperiodjpeg
 */
public class Squiggle implements ShapeCommand, ShapeManipulatorStrategy {
	private static final long serialVersionUID = 1L;
    private int red, green, blue;
    private int linethickness;
    private ArrayList<Point> squiggle;

    /**
	 * Construct a new Squiggle by setting an arraylist of points 
	 */
    public Squiggle(){
        this.squiggle = new ArrayList<Point>();
    }

    /**
	 * @param point add point to ArrayList
	 */
    public void addPoint(Point point){
        this.squiggle.add(point);
    }
    /**
	 * @return returns squiggle Arraylist
	 */
    public ArrayList<Point> getSquiggle(){
        return this.squiggle;
    }
    
    /**Sets the color of the Squiggle
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
     * @return the red value
	 */
    public int getRed() {
        return red;
    }
    /**
     * @return the green value
	 */
    public int getGreen() {
        return green;
    }
    /**
     * @return the blue value
	 */
    public int getBlue() {
        return blue;
    }
    /**
     * @param l sets the line thickness
	 */
    public void setLineThickness(int l) {
    	this.linethickness = l;
    }
    /**
     * @return the line thickness value
	 */
    public int getLineThickness() {
    	return linethickness;
    }
    /**
     * @return the type of Shape
	 */
    @Override
    public String type() {
        return "Squiggle";
    }
    /**Draws the Squiggle
     * @param g GraphicsContext canvas
	 */
    @Override
    public void execute(GraphicsContext g) {
        g.setStroke(Color.rgb(this.red, this.blue, this.green)); // set the color of the object
        g.setLineWidth(this.linethickness);
        for (int i = 0; i < this.squiggle.size() - 1; i++){
            Point p1 = this.squiggle.get(i);
            Point p2 = this.squiggle.get(i + 1);
            g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }
    /** mousePressed Strategy
	 * @param model model for MVC
	 * @param panel handles all mouse events on canvas, mode changes and repaint
	 * @param e handles mouse events
	 * @return ShapeCommand changed
	 * 
	 */
	@Override
	public ShapeCommand mousePressed(PaintModel model, PaintPanel panel, MouseEvent e) {
		model.add(this);
		panel.getUndo().push(this);
		this.setColor(panel.getRed(), panel.getBlue(), panel.getGreen());// set the color in the object
		this.setLineThickness(panel.getLineThickness());
		Point new_p = new Point((int) e.getX(), (int) e.getY());

		this.addPoint(new_p);

		return this;
	}
	/** mouseReleased Strategy
	 * @param model model for MVC
	 * @param panel handles all mouse events on canvas, mode changes and repaint
	 * @param e handles mouse events
	 * @return ShapeCommand changed
	 * 
	 */
	@Override
	public ShapeCommand mouseReleased(PaintModel model, PaintPanel panel, MouseEvent e) {
		model.del(this);
		Point new_p = new Point((int) e.getX(), (int) e.getY()); //setting color for each point
		this.addPoint(new_p);
		model.add(this);
		panel.getUndo().push(this);
		return this;
	}
	/** mouseClicked Strategy
	 * @param model model for MVC
	 * @param panel handles all mouse events on canvas, mode changes and repaint
	 * @param e handles mouse events
	 * @return ShapeCommand changed
	 * 
	 */
	@Override
	public ShapeCommand mouseClicked(PaintModel model, PaintPanel panel, MouseEvent e) {
		// TODO Auto-generated method stub
		return null;
	}
	/** mouseDragged Strategy
	 * @param model model for MVC
	 * @param panel handles all mouse events on canvas, mode changes and repaint
	 * @param e handles mouse events
	 * @return ShapeCommand changed
	 * 
	 */
	@Override
	public ShapeCommand mouseDragged(PaintModel model, PaintPanel panel, MouseEvent e) {
		model.del(this);
		Point new_p = new Point((int) e.getX(), (int) e.getY()); //setting color for each point
		this.addPoint(new_p);
		model.add(this);
		return this;
	}
}
