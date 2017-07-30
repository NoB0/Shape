package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;

public class SRectangle extends Shape {

	private Rectangle rect;

	public SRectangle(Point p, int width, int height) {
		this.rect = new Rectangle(p.x, p.y, width, height);
	}

	public Rectangle getRect() {
		return this.rect;
	}

	@Override
	public Point getLoc() {
		return this.rect.getLocation();
	}

	@Override
	public void setLoc(Point point) {
		this.rect.setLocation(point);
	}

	@Override
	public void translate(int x, int y) {
		// this.rect.translate((int)(x-rect.x), (int)(y-rect.y));
		this.rect.translate(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return this.rect.getBounds();
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitRectangle(this);
	}

	@Override
	public void grow(int x, int y) {
		this.rect.grow(x, y);
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
