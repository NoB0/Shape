package graphics.shapes.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View implements ActionListener {

	private JButton buttonRect;
	private JButton buttonText;
	private JButton buttonCircle;
	private JComboBox<String> changeColor;
	private JLabel labelShape;
	private JLabel labelColor;
	private static final String[] COLOR = { "","Black", "Blue", "Gray", "Green", "Magenta", "Orange", "Pink", "Red" };

	public ShapesView(Object model) {
		super(model);

		this.addMouseWheelListener((MouseWheelListener) super.getController());

		buttonRect = new JButton("Rectangle");
		buttonText = new JButton("Text");
		buttonCircle = new JButton("Circle");
		labelShape = new JLabel("Add a new shape");
		labelColor = new JLabel("Change shape's color");
		changeColor = new JComboBox<String>(this.COLOR);
		changeColor.setPreferredSize(new Dimension(100, 20));

		this.add(labelShape);
		this.add(buttonRect);
		this.add(buttonText);
		this.add(buttonCircle);
		this.add(labelColor);
		this.add(changeColor);

		
//		  this.setLayout(new BorderLayout()); 
//		  this.topPanel();
//		  this.bottomPanel();
		 

		buttonRect.addActionListener(this);
		buttonText.addActionListener(this);
		buttonCircle.addActionListener(this);
		changeColor.addActionListener(this);

	}

	public ShapesView(SCollection model, Dimension dim) {
		super(model);
	}

	
//	 public void topPanel(){ JPanel top=new JPanel(); top.add(buttonRect);
//	 top.add(buttonText); top.add(buttonCircle);
//	 this.add(top,BorderLayout.NORTH); }
//	 
//	 public void bottomPanel(){ JPanel bottom=new JPanel();
//	 bottom.add(labelColor); bottom.add(changeColor);
//	 this.add(bottom,BorderLayout.SOUTH); }
	 

	public void setThePreferredSize(Dimension dim) {
		this.setPreferredSize(dim);
	}

	public Controller defaultController(Object model) {
		return new ShapesController(model);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ShapeDraftman draftman = new ShapeDraftman(g);
		SCollection model = (SCollection) this.getModel();
		if (model != null)
			model.accept(draftman);
	}

	public void newColor() {
		SCollection model = (SCollection) this.getModel();
		for (Shape s : model.shapes) {
			if (((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).isSelected())
				s.newColor((String) changeColor.getSelectedItem());
		}
		changeColor.setSelectedItem("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SCollection model = (SCollection) this.getModel();
		Shape s = null;

		if (e.getSource() == buttonRect)
			s = new SRectangle(new Point(50, 50), 20, 10);

		if (e.getSource() == buttonText) {
			JOptionPane jop = new JOptionPane();
			String nom = jop.showInputDialog(null, "What do you want to write ?", "Put a text",
					JOptionPane.QUESTION_MESSAGE);

			if (nom != null) {
				s = new SText(nom, new Point(50, 50));
				s.addAttributes(new FontAttributes());
			}
		}

		if (e.getSource() == buttonCircle)
			s = new SCircle(new Point(50, 50), 15);

		if (s != null) {
			s.addAttributes(new SelectionAttributes());
			s.addAttributes(new ColorAttributes());
			model.add(s);
		}

		newColor();
		this.repaint();
	}

}
