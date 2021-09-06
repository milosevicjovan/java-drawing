package drawing;


import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import geometry.Shape;

public class PnlDrawing extends JPanel{

	//	List of shapes is main part of this class.
	//	Every drawn object has to be in this list.
	private ArrayList<Shape> shapes = new ArrayList<Shape>();


	private static final long serialVersionUID = 1L;

	public PnlDrawing() {
		
	}
	
	// This method draws shapes on the panel as it iterates through the list of shapes.
	public void paint(Graphics g) {
		super.paint(g);
		for (Shape shape: shapes) {
			shape.draw(g);
		}
	}
	
	// Getter for shapes list.
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	// Setter for shapes list.
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
}
