package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller implements MouseWheelListener {

	private Point click;
	private boolean notDragged;
	public static final int GROW_SIZE = 5;

	public ShapesController(Object newModel) {
		super(newModel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Shape s = this.getTarget(e);
		if (!e.isShiftDown())
			this.unselectAll();
		if (s != null)
			((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).toggleSelection();

		this.getView().repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.click = (Point) e.getPoint().clone();
		Shape s = this.getTarget(e);

		notDragged = (s == null || !((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).isSelected());
		this.getView().repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		notDragged = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!this.notDragged) {
			int x = e.getX();
			int y = e.getY();

			int dx = (int) (x - click.getX());
			int dy = (int) (y - click.getY());

			this.translateSelected(dx, dy);
		}
		this.click = e.getPoint();
		this.getView().repaint();

	}

	public void translateSelected(int x, int y) {
		SCollection sc = (SCollection) this.getModel();
		for (Iterator<Shape> i = sc.iterator(); i.hasNext();) {
			Shape s = i.next();
			if (((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).isSelected()) {
				s.translate(x, y);
			}
		}
	}

	public Shape getTarget(MouseEvent e) {
		Shape s;
		SCollection sc = (SCollection) this.getModel();
		for (Iterator<Shape> i = sc.iterator(); i.hasNext();) {
			s = (Shape) i.next();
			if (s.getBounds().contains(e.getPoint()))
				return s;
		}
		return null;
	}

	public void unselectAll() {
		SCollection sc = (SCollection) this.getModel();
		for (Iterator<Shape> i = sc.iterator(); i.hasNext();) {
			Shape s = (Shape) i.next();
			((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).unselect();
		}
	}

	public void growSelected(int x, int y) {
		SCollection sc = (SCollection) this.getModel();
		for (Iterator<Shape> i = sc.iterator(); i.hasNext();) {
			Shape s = i.next();
			if (((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).isSelected()) {
				s.grow(x, y);
			}
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int wheelRotation = e.getWheelRotation();

		if (wheelRotation > 0)
			this.growSelected(this.GROW_SIZE, this.GROW_SIZE);
		if (wheelRotation < 0)
			this.growSelected(-this.GROW_SIZE, -this.GROW_SIZE);
		this.getView().repaint();
	}

}
