package scrubshootsimulator;

import java.awt.Graphics;
import java.awt.Image;

import com.flipturnapps.kevinLibrary.sprite.PositionSprite;
import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class Scrub extends PositionSprite 
{

	private static final int SIZE = 60;
	private Image scrubImage;
	private int id;
	private static int staticid;

	public Scrub(ScrubLibrary lib) 
	{
		this.setAll(0, 0, Scrub.SIZE, Scrub.SIZE);
		this.scrubImage = lib.getScrub();
		this.setLayer(1);
		id=staticid;
		staticid++;
		
			
		
	}


	@Override
	protected void drawShape(Graphics g, SpritePanel s, int x, int y, int width, int height) 
	{
		g.drawImage(scrubImage, x, y, width, height, null);
	}

	public void setVisible(boolean b)
	{
		super.setVisible(b);
		if(!b)
		scrubImage = null;
	}

	public int getId() {
		return id;
	}

}
