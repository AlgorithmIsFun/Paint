package ca.utoronto.utm.paint;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.scene.*;
/**
 * We create a Save object
 * @author Oofperiodjpeg
 */
public class Save {
	/**Saves the model
	 * @param model the PaintModel of the MVC
	 */
public void save_Object(PaintModel model) {
	try {
		FileOutputStream fileOut = new FileOutputStream("Data");
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(model);
		objectOut.close();
		System.out.println("The Object was succesfully written to a file");
		
	} catch (Exception ex) {
		            ex.printStackTrace();
		        }	
}
/**Loads the model
 * @return return the saved model
 */
public PaintModel read_Object() throws ClassNotFoundException, IOException {
	FileInputStream fileOut = new FileInputStream("Data");
	ObjectInputStream objectOut = new ObjectInputStream(fileOut);
	PaintModel model = (PaintModel) objectOut.readObject();
	objectOut.close();
	return model;	
}
}
