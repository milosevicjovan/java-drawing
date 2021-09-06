package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle {

	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) { 
		this(center, radius, innerRadius, selected);
		setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) { 
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	
	@Override
	public Donut clone() {
		return new Donut(this.getCenter(), this.getRadius(), this.innerRadius, this.isSelected(), this.getColor(), this.getInnerColor());
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - getInnerRadius(), getCenter().getY() - getInnerRadius(), getInnerRadius() * 2, getInnerRadius() * 2);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}
	
	@Override
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return dFromCenter > innerRadius && super.contains(x, y);
	}
	
	@Override
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return dFromCenter > innerRadius && super.contains(p.getX(), p.getY());
	}
	
	@Override
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut donut = (Donut) obj;
			if (this.getCenter().equals(donut.getCenter()) &&
					this.getRadius() == donut.getRadius() &&
					this.innerRadius == donut.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public int getInnerRadius() {
		return this.innerRadius;
	}
	
	public void setInnerRadius(int innerRadius) {
		if (innerRadius > 0) {
			if (innerRadius < getRadius()) {
				this.innerRadius = innerRadius;
			} else {
				throw new NumberFormatException("Inner radius has to be a value less then outer radius (" + getRadius() + ")!");
			}
		} else {
			throw new NumberFormatException("Inner radius has to be a value greater then 0!");
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + ", inner radius=" + innerRadius;
	}

	@Override
    public String getFileData() {
		return "Donut;" + getCenter().getX() + ";" + getCenter().getY() + ";" + getRadius() + ";" + getInnerRadius() + ";" + getColor().getRGB() + ";"  + getInnerColor().getRGB() + ";false"; 
	}
	
}