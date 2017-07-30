package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;

public class SText extends Shape {

	private String text;
	private Point loc;

	public SText(String text, Point loc) {
		this.text = text;
		this.loc = new Point(loc.x, loc.y);
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
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
		this.loc.translate(x, y);
	}

	@Override
	public Rectangle getBounds() {
		FontAttributes f = (FontAttributes) this.getAttributes(FontAttributes.ID);
		Rectangle r = (Rectangle) f.getBounds(text);
		return new Rectangle(this.loc.x, this.loc.y - r.height, r.width, r.height);

	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitText(this);
	}

	@Override
	public void grow(int x, int y) {
		((FontAttributes) this.getAttributes(FontAttributes.ID)).grow(x, y);
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
