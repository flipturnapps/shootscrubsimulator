package com.github.kkevlar.scrubshootsim.client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import com.flipturnapps.kevinLibrary.sprite.PositionSprite;
import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class NewClientScrub extends PositionSprite 
{

	private static final int SIZE = 60;
	private Image scrubImage;
	private int id;
	private boolean isSuper;
	private float count;
	private int xOffset;
	private int yOffset;
	private boolean purdy;

	public NewClientScrub(ScrubLibrary lib, int id) 
	{
		this.setAll(0, 0, NewClientScrub.SIZE, NewClientScrub.SIZE);
		this.scrubImage = lib.getScrub(id);
		this.setLayer(1);
		this.id = id;
		this.count = 0;
	}
	
	@Override
	protected void drawShape(Graphics g, SpritePanel s, int x, int y, int width, int height) 
	{
		g.drawImage(scrubImage, x+xOffset, yOffset+y, width-2*xOffset, height-2*yOffset, null);
		if(purdy)
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(Color.HSBtoRGB(count, 1, 1)));
			count += .02;
			if(count == 1)
				count = 0;
			g2.setStroke(new BasicStroke(7));
			g2.drawRect(x, y, width, height);
		}
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


	public boolean isSuper() {
		return isSuper;
	}


	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}

}
