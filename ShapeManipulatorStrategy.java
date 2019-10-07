package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
/**
 * We create a ShapeManipulatorStrategy
 * @author Oofperiodjpeg
 */
public interface ShapeManipulatorStrategy {
	/** mousePressed Strategy
	 * @param model model for MVC
	 * @param panel handles all mouse events on canvas, mode changes and repaint
	 * @param e handles mouse events
	 * @return ShapeCommand changed
	 * 
	 */
public ShapeCommand mousePressed(PaintModel model, PaintPanel panel, MouseEvent e);
/** mouseReleased Strategy
 * @param model model for MVC
 * @param panel handles all mouse events on canvas, mode changes and repaint
 * @param e handles mouse events
 * @return ShapeCommand changed
 * 
 */
public ShapeCommand mouseReleased(PaintModel model, PaintPanel panel, MouseEvent e);
/** mouseClicked Strategy
 * @param model model for MVC
 * @param panel handles all mouse events on canvas, mode changes and repaint
 * @param e handles mouse events
 * @return ShapeCommand changed
 * 
 */
public ShapeCommand mouseClicked(PaintModel model, PaintPanel panel, MouseEvent e);
/** mouseDragged Strategy
 * @param model model for MVC
 * @param panel handles all mouse events on canvas, mode changes and repaint
 * @param e handles mouse events
 * @return ShapeCommand changed
 * 
 */
public ShapeCommand mouseDragged(PaintModel model, PaintPanel panel, MouseEvent e);
}
