package ca.utoronto.utm.paint;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
/**
 * We create a ShapeCommandInvoker that stores all the ShapeCommands
 * @author Oofperiodjpeg
 */
public class ShapeCommandInvoker implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<ShapeCommand> commands = new ArrayList<ShapeCommand>();
	/**
	 * Executes all commands
	 */
	public void executeAll(GraphicsContext g) {
		for(ShapeCommand bc:commands) {
			bc.execute(g);
		}
	}
	/**
	 * @param command adds the command to the ArrayList
	 */
	public void add(ShapeCommand command) {
		this.commands.add(command);
	}
	/**
	 * @param command deletes the command in the ArrayList
	 */
	public void del(ShapeCommand command) {
		this.commands.remove(command);
	}
	/**
	 * @param com adds all the commands to the ArrayList
	 */
	public void addAll(ArrayList<ShapeCommand> com) {
		this.commands.addAll(com);
	}
	/**
	 * @param com deletes all the commands in the ArrayList
	 */
	public void delAll() {
		this.commands = new ArrayList<ShapeCommand>();
	}
	/**
	 * @return return the commands
	 */
	public ArrayList<ShapeCommand> getCommands() {
		return commands;
	}
	/**resets the ArrayList commands
	 */
	public void reset() {
		this.commands = new ArrayList<ShapeCommand>();
	}
	/**
	 * @param c Class type
	 * @return returns the the sub-ArrayList of the class type c
	 */
	public ArrayList<ShapeCommand> getType(Class c){
		ArrayList<ShapeCommand> myArray = new ArrayList<ShapeCommand>();
		
		for(ShapeCommand p : this.commands) {
		    if(p.getClass() == c) {
		        myArray.add(p);
		}
		
	}
		return myArray;
}
}
