package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	private int x;
	private int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		if (x>=0 && y>=0) {
			this.x = x;
			this.y = y;
		} else {
			throw new NumberFormatException("X and Y have to be a value greater or equal to 0!");
		}
	}
	
	public Point(int x, int y, boolean selected) {
		this(x, y);
		setSelected(selected);
	}
	
	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		setColor(color);
	}
	
	@Override
	public Point clone() {
		return new Point(this.x, this.y);
	}
	
	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Point)) {
			return 0;
		}
		
		Point p = new Point(0, 0);
		return (int) (this.distance(p.getX(), 
				((Point) o).getY()) - ((Point) o).distance(p.getX(),  p.getY()));
		
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.x += byX;
		this.y += byY;
	}
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx*dx+dy*dy);
		return d;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
		g.drawLine(this.x, this.y - 2, this.x, this.y + 2);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.x - 3, this.y - 3, 6, 6);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Point)) {
			return false;
		}
		
		Point point = (Point)o;
		
		if (this.x != point.getX() || this.y != point.getY()) {
			return false;
		}
		
		return true;		
	}
	
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if (x>=0) {
			this.x = x;
		} else {
			throw new NumberFormatException("X has to be a value greater or equal to 0!");
		}
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if (y>=0) {
			this.y = y;
		} else {
			throw new NumberFormatException("Y has to be a value greater or equal to 0!");
		}
	}		
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public String getFileData() {
		return "Point;" + x + ";" + y + ";" + getColor().getRGB() + ";false";
	}
}
