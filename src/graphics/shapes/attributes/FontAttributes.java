package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class FontAttributes extends Attributes {
	public static final String ID = "font";
	public static final Color FONT_COLOR = Color.BLACK;
	public static final Font FONT = new Font("arial", Font.PLAIN, 11);
	public Font font;
	public Color fontColor;
	public FontRenderContext frc;

	public FontAttributes() {
		this(FONT, FONT_COLOR);
		this.frc = new FontRenderContext(new AffineTransform(), true, true);
	}

	public FontAttributes(Font font, Color fontColor) {
		this.font = font;
		this.fontColor = fontColor;
		this.frc = new FontRenderContext(new AffineTransform(), true, true);
	}

	@Override
	public String getId() {
		return this.ID;
	}

	public Rectangle2D getBounds(String text) {
		return font.getStringBounds(text, this.frc).getBounds();
	}

	public void grow(int x, int y) {
		this.font = this.font.deriveFont((float) (this.font.getSize() + Integer.max(x, y)));
	}
}
