package scrubshootsimulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import com.flipturnapps.kevinLibrary.helper.KevinColor;
import com.flipturnapps.kevinLibrary.sprite.PositionSprite;
import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

import javafx.scene.shape.Ellipse;

public class Crosshair extends PositionSprite 
{

	private static final int STROKE = 2;
	private Color crosshairColor;
	private int targetId;

	public Crosshair()
	{
		this.setOutsideAllowed(false);
		this.setAll(20, 20, 40, 40);
		this.setCrosshairColor(Color.RED);
		this.setLayer(2);
	}


	@Override
	protected void drawShape(Graphics g, SpritePanel s, int x, int y, int width, int height) 
	{
		this.setCenterX((int) this.getPanel().getMouseX());
		this.setCenterY((int) this.getPanel().getMouseY());
		Color maiColor = this.getCrosshairColor();
		if(this.getPanel().mouseDown())
			maiColor = KevinColor.mix(this.getCrosshairColor(), Color.WHITE, .3);
		g.setColor(maiColor);
	
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(maiColor);
		g2.setStroke(new BasicStroke(STROKE));
		
		g2.drawOval(this.getX(), this.getY(), width, height);
		//g.drawOval(this.getX()+1, this.getY()+1, width-2, height-2);
		///g.setColor(this.getPanel().getBackground());
		///g.fillOval(this.getX()+STROKE, this.getY()+STROKE, width-(STROKE*2), height-(STROKE*2));        
		
		g.fillRect(this.getCenterX()-STROKE/2, this.getCenterY()+1- this.getHeight()/2, STROKE, this.getHeight()-2);
		g.fillRect(this.getCenterX()+1- this.getWidth()/2 , this.getCenterY()-STROKE/2, this.getWidth() -2, STROKE);
		

	}


	public Color getCrosshairColor() {
		return crosshairColor;
	}


	public void setCrosshairColor(Color crosshairColor) {
		this.crosshairColor = crosshairColor;
	}


	public void setTarget(Scrub scrub) 
	{
		this.targetId = scrub.getId();
	}


	public boolean targetIs(Scrub scrub) 
	{
		if(scrub.getId() == this.targetId)
			return true;
		return false;
	}


	public void attack(Scrub scrub)
	{
		scrub.setVisible(false);
	}
}
