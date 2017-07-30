package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes {
	public static final String ID = "color";
	public boolean filled = false;
	public boolean strocked = true;
	public Color filledColor = Color.BLACK;
	public Color strockedColor = Color.BLACK;

	public ColorAttributes() {

	}

	public ColorAttributes(boolean filled, boolean strocked, Color filledColor, Color strockedColor) {
		this.filled = filled;
		this.strocked = strocked;
		this.filledColor = filledColor;
		this.strockedColor = strockedColor;
	}

	@Override
	public String getId() {
		return ID;
	}

}
