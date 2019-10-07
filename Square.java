package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
/**
 * We create a Square by creating a start, origin, end point
 * @author Oofperiodjpeg
 */
public class Square implements ShapeCommand, ShapeManipulatorStrategy{
	private static final long serialVersionUID = 1L;
    private Point origin; // represents the point where the mouse is first pressed
    private Point start;
    private Point end; // represents the point where the mouse is released
    private int length; //represents the length of the squares sides
    private Boolean fill;
    // color
    private int red, green, blue;
    private int linethickness;
    /**
	 * Construct a new Square with a start and end point
	 * @param start Start point
	 * @param end End point
	 * @param Checks if square is fill
	 */
    public Square(Point start, Point end, Boolean fill){ //constructor for class Rectangle
        this.origin = start;
        this.start = start;
        this.end = end;
        this.fill = fill;
    }
    /**
	 * Resets the side length
	 * @param e New point user clicked
	 */
    public void setLength(Point e) {
        int Dx = Math.abs(this.origin.getX() - e.getX());
        int Dy = Math.abs(this.origin.getY() - e.getY());
        if (Dx > Dy) {
            this.length = Dx;
        } else { this.length = Dy; }
    }
    /**
	 * @return returns the length of the square
	 */
    public int getLength() {
        return this.length;
    }

    /**
	 * @return returns if the square is filled
	 */
    public Boolean getfill() {
        return this.fill;
    }

    /**Sets the color of the Square
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
	 * @param linethickness Sets the line thickness
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
	 * @return returns the origin
	 */
    public Point getOrigin(){return this.origin;}
    /**
	 * @return returns the start
	 */
    public Point getStart(){return this.start;}
    /**
	 * @return returns the end
	 */
    public Point getEnd(){return this.end;}
    /**Resets the start point
	 */
    public void setStart() {
        // this code handles all events for squares
        int x1 = this.origin.getX(); //origin point is used to keep track
        int y1 = this.origin.getY(); //of where the rectangle was first pressed
        int x2 = this.end.getX();
        int y2 = this.end.getY();
        int L = this.length;

        if (x1 >= x2 && y1 >= y2) {
            this.start = new Point(x1 - L, y1 - L);
        } else if (x1 >= x2 && y1 <= y2) {
            this.start = new Point(x1 - L, y1);
        } else if (x1 <= x2 && y1 >= y2) {
            this.start = new Point(x1, y1 - L);
        } else if (x1 <= x2 && y1 <= y2) {
            this.start = new Point(x1, y1);
        }
    }
    /**
	 * @param end Sets the end point
	 */
    public void setEnd(Point end) { //set the end point from outside the Square class
        this.end = end;
    }
    /**
	 * @return returns if the square is filled
	 */
    public Boolean getFill() {
        return this.fill;
    }
    /**
	 * @return returns the type of the Shape
	 */
    @Override
    public String type() {

        return "Square";
    }
    /** Draws the square
	 * @param g GraphicsContext canvas
	 */
	@Override
	public void execute(GraphicsContext g) {

		int x = this.start.getX();
		int y = this.start.getY();
		int length = this.length;
		if (getFill()) {
			g.setFill(Color.rgb(this.red, this.blue, this.green));
			g.fillRect(x, y, length, length);
		} else {
			g.setStroke(Color.rgb(this.red, this.blue, this.green)); // set the color of the object
			g.setLineWidth(this.linethickness);
			g.strokeRect(x, y, length, length);
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
		Square square;
		if (PaintPanel.GetFill()) {
			square = new Square(start_point, end_point,true);
		} else {
			square = new Square(start_point, end_point,false);
		}
		square.setColor(panel.getRed(), panel.getGreen(), panel.getBlue());// set the color in the object
		square.setLineThickness(panel.getLineThickness());
		model.add(square);
		return square;
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
		this.setLength(end_point);
		this.setStart();
		//remove the previous rectangle for the array to avoid a massive array
		model.add(this); //add finished rectangle to the array
		panel.getUndo().push(this);
		return null;
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
		this.setLength(end_point);
		this.setStart();
		//remove the previous rectangle for the array to avoid a massive array
		model.add(this); //add finished rectangle to the array
		return this;
	}
}

