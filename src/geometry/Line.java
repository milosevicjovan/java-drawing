package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		setColor(color);
	}

	@Override
	public Line clone() {
		return new Line(this.startPoint, this.endPoint, this.isSelected(), this.getColor());
	}
	
	@Override
	public int compareTo(Object o) {		
		if (!(o instanceof Line)) {
			return 0;
		}
		return (int)(this.length() - ((Line) o).length());
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);		
	}
	
	public Point middleOfLine() {
		int middleByX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), 
					this.endPoint.getX(), this.endPoint.getY());	
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.startPoint.getX() - 3, this.startPoint.getY() - 3, 6, 6);
			g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
			g.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
		}
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public boolean equals(Object o) {	
		if (!(o instanceof Line)) {
			return false;
		}
		
		Line line = (Line)o;
		if (!this.startPoint.equals(line.getStartPoint()) ||
				!this.endPoint.equals(line.getEndPoint())) {
			return false;
		}
		
		return true;
	}
	
	public boolean contains(int x, int y) {
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contains(Point p) {
		if ((startPoint.distance(p.getX(), p.getY()) + endPoint.distance(p.getX(), p.getY()))
				- length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	public String toString() {
		return startPoint + "--->" + endPoint;
	}
	public String getFileData() {
		return "Line;" + startPoint.getX() + ";" + startPoint.getY() + ";" + endPoint.getX() + 
									";" + endPoint.getY() + ";" + getColor().getRGB() + ";false";
	}
}
