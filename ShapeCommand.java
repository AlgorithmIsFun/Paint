package ca.utoronto.utm.paint;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
/**
 * We create a ShapeCommand
 * @author Oofperiodjpeg
 */
public interface ShapeCommand extends Serializable{
	static final long serialVersionUID = 1L;
	/**
	 * returns the type of Shape
	 */
public String type();
/**
 * executes the draw of the Shape
 */
public abstract void execute(GraphicsContext g);
}
