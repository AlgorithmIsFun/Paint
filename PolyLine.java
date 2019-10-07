package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
/**
 * We create a PolyLine by creating a line between 2 points
 * @author Oofperiodjpeg
 */
public class PolyLine implements ShapeCommand, ShapeManipulatorStrategy{
	private static final long serialVersionUID = 1L;
	private Point p1, p2;
	private int red, green, blue;
	private int linethickness;
	/**
	 * Construct a new Polyline with one point
	 * @param p1 The first point
	 */
	public PolyLine(Point p1) {
		this.p1 = p1;
	}
	/**
	 * Construct a new Polyline with 2 point
	 * @param p1 The first point
	 * @param p2 The second point
	 */
	public PolyLine(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	/**
	 * @return returns if there exists a second point
	 */
	public boolean ready() {
		return p2 != null;
	}
	/**
	 * @param p1 Sets the first point
	 */
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	/**
	 * @param p2 Sets the second point
	 */
	public void setP2(Point p2) {
		this.p2 = p2;
	}
	/**
	 * @return returns the first point
	 */
	public Point getP1() {
		return this.p1;
	}
	/**
	 * @return return the second point
	 */
	public Point getP2() {
		return this.p2;
	}

	/**Sets the color of the PolyLine
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
	 * @return return the type of Shape
	 */
	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "Polyline";
	}
	/** Draws the PolyLine
	 * @param g GraphicsContext canvas
	 */
	@Override
	public void execute(GraphicsContext g) {
		// TODO Auto-generated method stub
		if (ready()) {
			g.setStroke(Color.rgb(this.red, this.blue, this.green));
			g.setLineWidth(this.linethickness);
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
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
		Point new_p = new Point((int) e.getX(), (int) e.getY()); //setting color for each point
		new_p.setColor(this.red, this.green, this.blue);
		new_p.setLineThickness(this.linethickness);
		if (model.getType(PolyLine.class).size() != 0) {
			ArrayList<ShapeCommand> mod = model.getType(PolyLine.class);
			PolyLine p2 = (PolyLine) mod.get(mod.size()-1);
			if (!p2.ready()) {
				p2.setP2(new_p);
				panel.getUndo().push(p2);
			}
		}
		PolyLine p = new PolyLine(new_p);
		p.setColor(panel.getRed(), panel.getGreen(), panel.getBlue());
		p.setLineThickness(panel.getLineThickness());
		model.add(p);
		return p;
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
		// TODO Auto-generated method stub
		return null;
	}
}
