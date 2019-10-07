package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
/**
 * We draw a Circle that has a point on the centre and side 
 * The circle has a radius value and a fill Bolean
 * Implements ShapeCommand
 * @author Oofperiodjpeg
 */
public class Circle implements ShapeCommand, ShapeManipulatorStrategy{
	private static final long serialVersionUID = 1L;
	private Point centre;
	private Point side;
	private int radius;
	private Boolean fill = false;
	private int red, green, blue;
	private int linethickness;

	/**
	 * Construct a new Circle with the specified centre, side, radius fill
	 * @param centre the Point of the centre of the circle
	 * @param side the point at the circumference of the circle
	 * @param radius the radius of the circle
	 * @param fill if the circle is filled or just the outline
	 */
	public Circle(Point centre,Point side, int radius, Boolean fill) {
		this.centre = centre;
		this.radius = radius;
		this.side = side;
		this.fill = fill;
	}

	/**
	 * Sets the color of the circle through rgb values 
	 * @param red The red value
	 * @param green The green value
	 * @param blue The blue value
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
	/**Sets the line thickness value
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
	 * @return returns the centre point
	 */
	public Point getCentre() {
		return centre;
	}
	/**
	 * @return returns side point
	 */
	public Point getSide() {
		return side;
	}
	/**
	 * @param Sets the side point
	 */
	public void setSide(Point side) {
		this.side = side;
	}
	/**
	 * @param Sets the centre point
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	/**
	 * @return returns the radius value
	 */
	public int getRadius() {
		return radius;
	}
	/** Calculates the radius and returns it
	 * @return returns the radius value
	 */
	public int calRadius(int cenx, int ceny, int x2, int y2){
		int r = (int)Math.sqrt(((Math.pow(x2-cenx,2)) + (Math.pow((y2-ceny),2))));;
		return r;
	}
	/**
	 * @param radius Sets the radius value
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	/**
	 * @return returns the fill boolean value
	 */
	public Boolean getfill() {
		return this.fill;
	}
	/**
	 * @return returns the fill boolean value
	 */
	public void setfill(Boolean fill) {
		this.fill = fill;
	}
	/**
	 * @return returns the type of Shape
	 */
	@Override
	public String type() {
		return "Circle";
	}
	/** Draws the circle on the canvas
	 * @return g GraphicContext canvas
	 */
	@Override
	public void execute(GraphicsContext g) {
		int x = this.centre.getX();
		int y = this.centre.getY();
		int radius = this.radius;
		if (getfill()) {
			g.setFill(Color.rgb(this.red, this.blue, this.green));
			g.fillOval(x-(radius/2), y-(radius/2), radius, radius);
		} else {
			g.setStroke(Color.rgb(this.red, this.blue, this.green));
			g.setLineWidth(linethickness);
			g.strokeOval(x-(radius/2), y-(radius/2), radius, radius);
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
		Point centre = new Point((int) e.getX(), (int) e.getY());
		int radius = 0;
		Point side = new Point((int) e.getX(), (int) e.getY());
		if (PaintPanel.GetFill()) {
			this.setCentre(centre);
			this.setRadius(radius);
			this.setSide(side);
			this.setfill(true);
		} else {
			this.setCentre(centre);
			this.setRadius(radius);
			this.setSide(side);
			this.setfill(false);
			}
		this.setColor(panel.getRed(), panel.getGreen(), panel.getBlue());// set the color in the object
		this.setLineThickness(panel.getLineThickness());
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
		Point side = new Point((int) e.getX(), (int) e.getY());
        this.setSide(side);
        int r = this.calRadius(this.getCentre().getX(),this.getCentre().getY(),this.getSide().getX(),this.getSide().getY());
        this.setRadius(r*2);
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
		Point side = new Point((int) e.getX(), (int) e.getY());
		this.setSide(side);
		int r = this.calRadius(this.getCentre().getX(), this.getCentre().getY(), this.getSide().getX(), this.getSide().getY());
		this.setRadius(r * 2);
		model.add(this);
		//System.out.println(model.getCommands().size());
		return this;
	}
}
