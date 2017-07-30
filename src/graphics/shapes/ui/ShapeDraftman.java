package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor {

	public static final ColorAttributes DEFAULT_COLOR_ATTRIBUTES = new ColorAttributes();
	public static final FontAttributes DEFAULT_FONT_ATTRIBUTES = new FontAttributes();
	public static final int HANDLER_SIZE = 4;
	private Graphics graph;

	public ShapeDraftman(Graphics graph) {
		this.graph = graph;
	}

	@Override
	public void visitRectangle(SRectangle rect) {
		ColorAttributes color = (ColorAttributes) rect.getAttributes(ColorAttributes.ID);
		if (rect.getAttributes("color") == null)
			color = DEFAULT_COLOR_ATTRIBUTES;

		if (color.filled) {
			graph.setColor(color.filledColor);
			graph.fillRect(rect.getLoc().x, rect.getLoc().y, rect.getBounds().width, rect.getBounds().height);
		}
		if (color.strocked) {
			graph.setColor(color.strockedColor);
			graph.drawRect(rect.getLoc().x, rect.getLoc().y, rect.getBounds().width, rect.getBounds().height);
		}

		this.drawHandler(rect);

	}

	@Override
	public void visitCircle(SCircle circle) {
		ColorAttributes color = (ColorAttributes) circle.getAttributes(ColorAttributes.ID);
		if (circle.getAttributes("color") == null)
			color = DEFAULT_COLOR_ATTRIBUTES;

		if (color.filled) {
			graph.setColor(color.filledColor);
			graph.fillOval(circle.getLoc().x, circle.getLoc().y, circle.getBounds().width, circle.getBounds().height);
		}
		if (color.strocked) {
			graph.setColor(color.strockedColor);
			graph.drawOval(circle.getLoc().x, circle.getLoc().y, circle.getBounds().width, circle.getBounds().height);
		}

		this.drawHandler(circle);
	}

	@Override
	public void visitText(SText text) {
		ColorAttributes color = (ColorAttributes) text.getAttributes(ColorAttributes.ID);
		FontAttributes font = (FontAttributes) text.getAttributes(FontAttributes.ID);

		if (text.getAttributes(ColorAttributes.ID) == null)
			color = DEFAULT_COLOR_ATTRIBUTES;

		if (text.getAttributes("font") == null)
			text.addAttributes(DEFAULT_FONT_ATTRIBUTES);

		Rectangle r = text.getBounds();

		if (color.filled) {
			graph.setColor(color.filledColor);
			graph.fillRect(r.x, r.y, r.width, r.height);
		}
		if (color.strocked) {
			graph.setColor(color.strockedColor);
			graph.drawRect(r.x, r.y, r.width, r.height);
		}

		graph.setFont(font.font);
		graph.setColor(font.fontColor);
		graph.drawString(text.getText(), text.getLoc().x, text.getLoc().y);

		SRectangle r2 = new SRectangle(r.getLocation(), r.width, r.height);
		r2.addAttributes(text.getAttributes(SelectionAttributes.ID));
		this.drawHandler(r2);
	}

	@Override
	public void visitCollection(SCollection collection) {

		for (Iterator<Shape> it = collection.iterator(); it.hasNext();) {
			it.next().accept(this);
		}
		Rectangle r = collection.getBounds();
		SRectangle r2 = new SRectangle(r.getLocation(), r.width, r.height);
		r2.addAttributes(collection.getAttributes("selection"));
		this.drawHandler(r2);

	}

	public void drawHandler(Shape s) {
		SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
		if (sa.isSelected()) {
			graph.setColor(Color.white);
			graph.fillRect(s.getLoc().x - HANDLER_SIZE, s.getLoc().y - HANDLER_SIZE, HANDLER_SIZE, HANDLER_SIZE);
			graph.fillRect(s.getLoc().x + s.getBounds().width, s.getLoc().y + s.getBounds().height, HANDLER_SIZE,
					HANDLER_SIZE);
			graph.setColor(Color.black);
			graph.drawRect(s.getLoc().x - HANDLER_SIZE, s.getLoc().y - HANDLER_SIZE, HANDLER_SIZE, HANDLER_SIZE);
			graph.drawRect(s.getLoc().x + s.getBounds().width, s.getLoc().y + s.getBounds().height, HANDLER_SIZE,
					HANDLER_SIZE);
		}
	}
}
