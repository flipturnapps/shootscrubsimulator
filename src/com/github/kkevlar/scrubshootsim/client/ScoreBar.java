package com.github.kkevlar.scrubshootsim.client;

import java.awt.Color;
import java.awt.Graphics;

import com.flipturnapps.kevinLibrary.sprite.PositionSprite;
import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class ScoreBar extends PositionSprite
{
	private int pos;
	private Color color;
	private CrosshairPlayer parent;
	
	public ScoreBar(int pos, Color color, CrosshairPlayer parent)
	{
		this.setLayer(0);
		this.pos = pos;
		this.color = color;		
		this.parent = parent;
	}
	@Override
	protected void drawShape(Graphics g, SpritePanel s, int x, int y, int width, int height) {
		g.setColor(color);
		g.fillRect(0, pos*30, parent.getScore()*this.getPanelWidth()/500, 30);

	}

}
