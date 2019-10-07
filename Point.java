package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
/**
 * We create a Point that implements ShapeCommand
 * @author Oofperiodjpeg
 */
public class Point implements ShapeCommand {
	private static final long serialVersionUID = 1L;
	int x, y;

	// color
	private int red, green, blue;
	private int linethickness;
	/**
	 * Construct a new Point with its x and y coordinates
	 * @param x x-coords
	 * @param y y-coords
	 */
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * @return return x-coords
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param Sets x-coords
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return return the y-coords
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param Sets y-coords
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return return the type of shape
	 */
	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "Point";
	}
	/**Sets the color of the point
	 * @param red the red value
	 * @param green the green value
	 * @param blue the blue value
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
	 * Does nothing
	 */
	@Override
	public void execute(GraphicsContext g) {
		
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
}
