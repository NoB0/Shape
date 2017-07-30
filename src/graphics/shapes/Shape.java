package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;

public abstract class Shape {

	private Map<String, Attributes> attributes;

	public Shape() {
		this.attributes = new TreeMap<String, Attributes>();
	}

	public void addAttributes(Attributes attribute) {
		this.attributes.put(attribute.getId(), attribute);
	}

	public Attributes getAttributes(String name) {
		Attributes attribute = (Attributes) this.attributes.get(name);
		return attribute;
	}

	public abstract Point getLoc();

	public abstract void setLoc(Point point);

	public abstract void translate(int x, int y);

	public abstract void grow(int x, int y);

	public abstract Rectangle getBounds();

	public abstract void accept(ShapeVisitor visitor);

	public abstract void newColor(String color);

}
