package ca.utoronto.utm.paint;
import java.util.ArrayList;
/**
 * We create a Stack
 * @author Oofperiodjpeg
 */
public class Stack {
	private ArrayList<ShapeCommand> items;
	/**
	 * Construct a new Stack
	 */
	public Stack() {
		this.items = new ArrayList<>();	
	}
	/**
	 * Pushes a command to the stack
	 * @param element A shapeCommand
	 */
	public void push(ShapeCommand element) {
		this.items.add(element);
	}
	/**
	 * Pops a command to the stack
	 * @return returns the top element
	 */
	public ShapeCommand pop() {
		ShapeCommand temp = this.items.get(this.items.size()-1);
		this.items.remove(this.items.size()-1);
		return temp;
	}
	/**
	 * Returns the items
	 * @return returns the ArrayList
	 */
	public ArrayList<ShapeCommand> lst() {
		return this.items;
	}
	/**
	 * Checks the top element
	 * @return returns the top element
	 */
	public ShapeCommand peek() {
		return this.items.get(this.items.size()-1);
	}
	/**
	 * @return returns the size of Stack
	 */
	public int size() {
		return this.items.size();
	}
	/**
	 * @return returns if the Stack is empty
	 */
	public boolean isEmpty() {
		return this.items.size() == 0;
	}
}
