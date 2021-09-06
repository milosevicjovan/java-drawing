package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape {

	private Point upperLeftPoint;
	private int height;
	private int width;
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) {
		this(upperLeftPoint, height, width);
		setSelected(selected);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color) {
		this(upperLeftPoint, height, width, selected);
		setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, height, width, selected, color);
		setInnerColor(innerColor);
	}
	
	@Override
	public Rectangle clone() {
		return new Rectangle(this.upperLeftPoint, this.height, this.width, this.isSelected(), this.getColor(), this.getInnerColor());
	}
	
	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Rectangle)) {
			return 0;
		}
		return (int)(this.area() - ((Rectangle)o).area());
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		upperLeftPoint.moveBy(byX, byY);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.height);
		
		this.fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, 
							getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		}
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX()+1, this.getUpperLeftPoint().getY()+1, width-1, height-1);
	}
	
	public double area() {
		return height * width;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Rectangle)) {
			return false;
		}
		
		Rectangle rectangle = (Rectangle)o;
		if (!this.upperLeftPoint.equals(rectangle.getUpperLeftPoint())
				|| this.height != rectangle.getHeight() || this.width != rectangle.getWidth()) {
			return false;
		}
		
		return true;
	}
	
	public boolean contains(int x, int y) {
		if (this.getUpperLeftPoint().getX() <= x &&
				upperLeftPoint.getY() <= y &&
				x <= upperLeftPoint.getX() + width &&
				y <= this.getUpperLeftPoint().getY() + height) {
			return true;
		}
			
		return false;
	}
	
	public boolean contains(Point p) {
		if (this.getUpperLeftPoint().getX() <= p.getX() &&
				upperLeftPoint.getY() <= p.getY() &&
				p.getX() <= upperLeftPoint.getX() + width &&
				p.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		}
		
		return false;
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		if (height > 0) {
			this.height = height;
		} else {
			throw new NumberFormatException("Height has to be a value greater then 0!");
		}
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		if (width > 0) {
			this.width = width;
		} else {
			throw new NumberFormatException("Width has to be a value greater then 0!");
		}
	}
	public String toString() {
		return "Upper left point: " + upperLeftPoint + ", height: " + height + ", width: " + width;
	}
	public String getFileData() {
		return "Rectangle;" + upperLeftPoint.getX() + ";" + upperLeftPoint.getY() + ";"
					+ width + ";" + height +  ";" + getColor().getRGB() + ";"  + getInnerColor().getRGB() +  ";false";
	}
}
