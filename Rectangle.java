package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
/**
 * We create a Rectangle
 * @author Oofperiodjpeg
 */
public class Rectangle implements ShapeCommand, ShapeManipulatorStrategy{
	private static final long serialVersionUID = 1L;
    private Point origin; // represents the point where the mouse is first pressed
    private Point start;
    private Point end; // represents the point where the mouse is released
    private int horizontal; // represents the length of the horizontal side of the rectangle
    private int vertical; // represents the length of the vertical side of the rectangle
    private Boolean fill;
    // color
    private int red, green, blue;
    private int linethickness;
    /**
	 * Construct a new Rectangle with a started and ending point
	 * @param start one edge of the rectangle
	 * @param end opposite side of the rectangle
	 * @param fill Checks if the rectangle is filled
	 */
    public Rectangle(Point start, Point end, Boolean fill){ //constructor for class Rectangle
        this.origin = start;
        this.start = start;
        this.end = end;
        this.horizontal = Math.abs(origin.getX() - end.getX());
        this.vertical = Math.abs(origin.getY() - end.getY());
        this.fill = fill;
    }

    /**Sets the color of the Rectangle
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
	 * @param sets the line thickness
	 */
    public void setLineThickness(int linethickness) {
		this.linethickness = linethickness;
	}
    /**
	 * @return returns the line thickness
	 */
	public int getLineThickness() {
		return linethickness;
	}
	/**
	 * @return returns the horizontal value
	 */
    public int getHorizontal(){return this.horizontal;}
    /**
	 * @return returns the vertical value
	 */
    public int getVertical(){return this.vertical;}
    /**
	 * @return returns the origin value
	 */
    public Point getOrigin(){return this.origin;}
    /**
	 * @return returns the start value
	 */
    public Point getStart(){return this.start;}
    /**
	 * @return returns the end value
	 */
    public Point getEnd(){return this.end;}
    /**Sets the start point
	 * 
	 */
    public void setStart() {
        // this code handles all events for rectangle
        int x1 = this.origin.getX(); //origin point is used to keep track
        int y1 = this.origin.getY(); //of where the rectangle was first pressed
        int x2 = this.end.getX();
        int y2 = this.end.getY();

        if (x1 >= x2 && y1 >= y2) {
            this.start = this.end;
        } else if (x1 >= x2 && y1 <= y2) {
            this.start = new Point(x2, y1);
        } else if (x1 <= x2 && y1 >= y2) {
            this.start = new Point(x1, y2);
        }
    }
    /**
	 * Sets the end point
	 */
    public void setEnd(Point end) { //set the end point from outside the Rectangle class
        this.end = end;
        this.horizontal = Math.abs(this.origin.getX() - end.getX());
        this.vertical = Math.abs(this.origin.getY() - end.getY());
    }
    /**
	 * @return returns if the rectangle is filled
	 */
    public Boolean getFill() {
    	return this.fill;
    }
    /**
	 * @return returns the type of shape
	 */
	@Override
	public String type() {
		return "Rectangle";
	}
	/**draws the rectangles
	 * @param g GraphicsContext canvas
	 */
	@Override
	public void execute(GraphicsContext g) {
		int x = this.start.getX();
		int y =this.start.getY();
		if (getFill()) {
			g.setFill(Color.rgb(this.red, this.blue, this.green));
			g.fillRect(x, y, this.horizontal, this.vertical);
		} else {
		g.setStroke(Color.rgb(this.red, this.blue, this.green)); // set the color of the object
		g.setLineWidth(this.linethickness);
		g.strokeRect(x, y, this.horizontal, this.vertical);
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
		Point start_point = new Point((int) e.getX(), (int) e.getY());
		Point end_point = new Point(start_point.getX(), start_point.getY());
		Rectangle rectangle;

		if (PaintPanel.GetFill()) {
			rectangle = new Rectangle(start_point, end_point,true);
		} else {
			rectangle = new Rectangle(start_point, end_point,false);
			}
		rectangle.setColor(panel.getRed(), panel.getBlue(), panel.getGreen());// set the color in the object
		rectangle.setLineThickness(panel.getLineThickness());
		model.add(rectangle);
		return rectangle;
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
		Point end_point = new Point((int) e.getX(), (int) e.getY());
		model.del(this);
		this.setEnd(end_point); //set end point to where the mouse was released
		this.setStart();
		//remove the previous rectangle for the array to avoid a massive array
        model.add(this); //add finished rectangle to the array
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
		Point end_point = new Point((int) e.getX(), (int) e.getY());
		model.del(this);
		this.setEnd(end_point); //set end point to where the mouse was released
		this.setStart();
		model.add(this); //add finished rectangle to the array
		return this;
	}
}
