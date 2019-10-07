package ca.utoronto.utm.paint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.canvas.GraphicsContext;
/**
 * We create a PaintModel that helps communication between the panel and invoker
 * @author Oofperiodjpeg
 */
public class PaintModel extends Observable implements Serializable{
	private static final long serialVersionUID = 1L;
	private ShapeCommandInvoker invoker;
	/**
	 * Construct a new PaintModel with a new invoker
	 * 
	 */
	public PaintModel() {
		
		invoker = new ShapeCommandInvoker();
	}
	/**
	 * Construct a new PaintModel with a existing invoker
	 * @param model Adding a pre-exiting model
	 */
	public PaintModel(PaintModel model) {
		
		invoker = new ShapeCommandInvoker();
		invoker.addAll(model.getCommands());
	}
	/**
	 * Combines the invoker
	 * @param model Adding a pre-exiting model
	 */
	public void addAll(PaintModel model) {
		
		invoker.addAll(model.getCommands());
	}
	/**
	 * 
	 * @param c Class of a ShapeCommand
	 * @return return the class type of the ShapeCommand
	 */
	public ArrayList<ShapeCommand> getType(Class c) {
		return this.invoker.getType(c);
	}
	/**
	 * @param s adds Shape to invoker
	 */
	public void add(ShapeCommand s) {
		this.invoker.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * @param s deletes Shape to invoker
	 */
	public void del(ShapeCommand s) {
		this.invoker.del(s);
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * @return the list of invoker commands
	 */
	public ArrayList<ShapeCommand> getCommands(){
		return this.invoker.getCommands();
	}
	/**Deletes all commands in invoker
	 */
	public void delAll() {
		this.invoker.reset();
		this.setChanged();
		this.notifyObservers();
	}
	/**Executes all draws
	 * @param g Creates a GraphicsContext canvas
	 */
	public void executeAll(GraphicsContext g) {
		invoker.executeAll(g);
	}
}
