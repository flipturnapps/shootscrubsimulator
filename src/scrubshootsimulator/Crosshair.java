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

public abstract class Crosshair extends PositionSprite 
{

	private static final int STROKE = 2;
	private Color crosshairColor;
	private int score;
	private int id;
	private static int idCount;
	private boolean madeScorebar = false;
	public Crosshair()
	{
		this.setOutsideAllowed(false);
		this.setAll(20, 20, 40, 40);
		this.setCrosshairColor(Color.RED);
		this.setLayer(2);
		setId(idCount++);
	}

	@Override
	protected void drawShape(Graphics g, SpritePanel s, int x, int y, int width, int height) 
	{
		if(!madeScorebar && ! (this instanceof CrosshairZombie))
		{
			this.getPanel().add(new ScoreBar());
			this.madeScorebar = true;
		}
		Color maiColor = this.getCrosshairColor();
		if(attacking())
			maiColor = KevinColor.mix(this.getCrosshairColor(), Color.WHITE, .3);
		g.setColor(maiColor);
	
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(maiColor);
		g2.setStroke(new BasicStroke(STROKE));
		
		g2.drawOval(this.getX(), this.getY(), width, height);
		//g.drawOval(this.getX()+1, this.getY()+1, width-2, height-2);
		///g.setColor(this.getPanel().getBackground());
		///g.fillOval(this.getX()+STROKE, this.getY()+STROKE, width-(STROKE*2), height-(STROKE*2));        
		
		
		{
		g.fillRect(this.getCenterX()-STROKE/2, this.getCenterY()+1- this.getHeight()/2, STROKE, this.getHeight()-2);
		g.fillRect(this.getCenterX()+1- this.getWidth()/2 , this.getCenterY()-STROKE/2, this.getWidth() -2, STROKE);
		/*
		else
		{
			CrosshairZombie cz = (CrosshairZombie) this;
			Scrub scrub = cz.getChosen();
			g.drawRect(scrub.getCenterX() - 10, scrub.getCenterY() - 10, 20, 20);
			g2.drawString(x+","+y, scrub.getX(), scrub.getY());
		}
		*/
		}
	}


	public  abstract boolean attacking();
	public abstract void spotlight();
	public abstract void forEachScrub(Scrub scrub);

	public Color getCrosshairColor() {
		return crosshairColor;
	}


	public void setCrosshairColor(Color crosshairColor) {
		this.crosshairColor = crosshairColor;
	}


	private class ScoreBar extends PositionSprite
	{
		public ScoreBar()
		{
			this.setLayer(0);
		}
		@Override
		protected void drawShape(Graphics g, SpritePanel s, int x, int y, int width, int height) {
			g.setColor(getCrosshairColor());
			g.fillRect(0, getId()*30, score*this.getPanelWidth()/100, 30);
			
		}
		
	}


	public void attack(Scrub scrub)
	{
		if(scrub.isVisible())
			score++;
		scrub.setVisible(false);
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
