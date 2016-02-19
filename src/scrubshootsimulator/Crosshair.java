package scrubshootsimulator;

import java.awt.Color;
import java.awt.Graphics;

import com.flipturnapps.kevinLibrary.sprite.PositionSprite;
import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class Crosshair extends PositionSprite 
{

	private static final int STROKE = 4;


	public Crosshair()
	{
		this.setOutsideAllowed(false);
		this.setAll(20, 20, 60, 60);
	}


	@Override
	protected void drawShape(Graphics g, SpritePanel s, int x, int y, int width, int height) 
	{
		this.setCenterX((int) this.getPanel().getMouseX());
		this.setCenterY((int) this.getPanel().getMouseY());
		Color maiColor = Color.BLACK;
		if(this.getPanel().mouseDown())
			maiColor = Color.RED;
		g.setColor(maiColor);
		g.fillOval(this.getX(), this.getY(), width, height);
		g.setColor(this.getPanel().getBackground());
		g.fillOval(this.getX()+STROKE, this.getY()+STROKE, width-(STROKE*2), height-(STROKE*2));        
		g.setColor(maiColor);
		g.fillRect(this.getCenterX()-STROKE/2, this.getCenterY()+1- this.getHeight()/2, STROKE, this.getHeight()-2);
		g.fillRect(this.getCenterX()+1- this.getWidth()/2 , this.getCenterY()-STROKE/2, this.getWidth() -2, STROKE);


	}
}
