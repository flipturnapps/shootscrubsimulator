package com.github.kkevlar.scrubshootsim.client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import com.flipturnapps.kevinLibrary.sprite.PositionSprite;
import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class Scrub extends PositionSprite 
{

	private static final int SIZE = 60;
	private Image scrubImage;
	private int id;
	private static int staticid;
	private boolean isSuper;
	private float count;
	private int xOffset;
	private int yOffset;
	private boolean purdy;

	public Scrub(ScrubLibrary lib) 
	{
		this.setAll(0, 0, Scrub.SIZE, Scrub.SIZE);
		this.scrubImage = lib.getScrub(id);
		this.setLayer(1);
		id=staticid;
		staticid++;
		
		int rand = (int) (Math.random() * 15);
		if(rand == 0)
		{
			this.setSuper(true);
			rand = (int) (Math.random() * 2);
			if(rand ==0)
			{
				purdy = true;
			xOffset = 3;
			yOffset = 3;
			this.setWidth(this.getWidth() + 6);
			this.setWidth(this.getHeight() + 6);
			}
		}
	}


	@Override
	protected void drawShape(Graphics g, SpritePanel s, int x, int y, int width, int height) 
	{
//		g.setColor(Color.BLACK);
//		g.fillRect(x, y, width, height);
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
