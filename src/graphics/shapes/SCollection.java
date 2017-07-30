package graphics.shapes;

import java.util.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import graphics.shapes.attributes.ColorAttributes;

public class SCollection extends Shape {
	public ArrayList<Shape> shapes;

	public SCollection() {
		this.shapes = new ArrayList<Shape>();
	}

	public Iterator<Shape> iterator() {
		return this.shapes.iterator();
	}

	public void add(Shape shape) {
		this.shapes.add(shape);
	}

	@Override
	public Point getLoc() {
		return this.getBounds().getLocation();
	}

	@Override
	public void setLoc(Point point) {
		for (Shape s : shapes) {
			s.translate(point.x, point.y);
		}
	}

	@Override
	public void translate(int x, int y) {
		for (Iterator<Shape> it = shapes.iterator(); it.hasNext();) {
			((Shape) it.next()).translate(x, y);
		}
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rect = new Rectangle(shapes.get(0).getLoc().x, shapes.get(0).getLoc().y,
				shapes.get(0).getBounds().width, shapes.get(0).getBounds().height);
		for (Iterator<Shape> it = shapes.iterator(); it.hasNext();) {
			rect = rect.union(((Shape) it.next()).getBounds());
		}
		return rect;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitCollection(this);
	}

	@Override
	public void grow(int x, int y) {
		for (Iterator<Shape> it = shapes.iterator(); it.hasNext();) {
			((Shape) it.next()).grow(x, y);
		}
	}

	@Override
	public void newColor(String color) {
		for (Shape s : this.shapes) {
			((ColorAttributes) s.getAttributes(ColorAttributes.ID)).filled = true;
			switch (color) {
			case "Black":
				((ColorAttributes) s.getAttributes(ColorAttributes.ID)).filledColor = Color.BLACK;
				break;
			case "Blue":
				((ColorAttributes) s.getAttributes(ColorAttributes.ID)).filledColor = Color.BLUE;
				break;
			case "Gray":
				((ColorAttributes) s.getAttributes(ColorAttributes.ID)).filledColor = Color.GRAY;
				break;
			case "Green":
				((ColorAttributes) s.getAttributes(ColorAttributes.ID)).filledColor = Color.GREEN;
				break;
			case "Magenta":
				((ColorAttributes) s.getAttributes(ColorAttributes.ID)).filledColor = Color.MAGENTA;
				break;
			case "Orange":
				((ColorAttributes) s.getAttributes(ColorAttributes.ID)).filledColor = Color.ORANGE;
				break;
			case "Pink":
				((ColorAttributes) s.getAttributes(ColorAttributes.ID)).filledColor = Color.PINK;
				break;
			case "Red":
				((ColorAttributes) s.getAttributes(ColorAttributes.ID)).filledColor = Color.RED;
				break;
			default:
				break;
			}

		}
	}

}
