package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;

public class SCircle extends Shape {

	private int radius;
	private Point loc;

	public SCircle(Point p, int radius) {
		this.radius=radius;
		this.loc=new Point(p); 		
	}

	public int getRadius() {
		return this.radius;
	}

	public void setRadius(int r) {
		this.radius = r;
	}

	@Override
	public Point getLoc() {
		return this.loc.getLocation();
	}

	@Override
	public void setLoc(Point point) {
		this.loc.setLocation(point);
	}

	@Override
	public void translate(int x, int y) {
		//this.loc.translate((int)(x-loc.getX()), (int)(y-loc.getY()));
		this.loc.translate(x, y);
	}

	@Override
	public Rectangle getBounds() {

		Rectangle rect = new Rectangle(this.loc.x,this.loc.y,2*this.radius,2*this.radius);
		return rect.getBounds();
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitCircle(this);
	}

	@Override
	public void grow(int x, int y) {
		this.radius+=Integer.max(x,y);
	}

	@Override
	public void newColor(String color) {
		((ColorAttributes) this.getAttributes(ColorAttributes.ID)).filled = true;
		switch (color) {
		case "Black":
			((ColorAttributes) this.getAttributes(ColorAttributes.ID)).filledColor = Color.BLACK;
			break;
		case "Blue":
			((ColorAttributes) this.getAttributes(ColorAttributes.ID)).filledColor = Color.BLUE;
			break;
		case "Gray":
			((ColorAttributes) this.getAttributes(ColorAttributes.ID)).filledColor = Color.GRAY;
			break;
		case "Green":
			((ColorAttributes) this.getAttributes(ColorAttributes.ID)).filledColor = Color.GREEN;
			break;
		case "Magenta":
			((ColorAttributes) this.getAttributes(ColorAttributes.ID)).filledColor = Color.MAGENTA;
			break;
		case "Orange":
			((ColorAttributes) this.getAttributes(ColorAttributes.ID)).filledColor = Color.ORANGE;
			break;
		case "Pink":
			((ColorAttributes) this.getAttributes(ColorAttributes.ID)).filledColor = Color.PINK;
			break;
		case "Red":
			((ColorAttributes) this.getAttributes(ColorAttributes.ID)).filledColor = Color.RED;
			break;
		default:
			break;
		}
		
	}

}
