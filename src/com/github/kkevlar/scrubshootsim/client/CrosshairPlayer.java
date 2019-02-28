package com.github.kkevlar.scrubshootsim.client;

import java.awt.Color;

public abstract class CrosshairPlayer extends Crosshair 
{
	private int id;
	private Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN, Color.DARK_GRAY, Color.GREEN, Color.ORANGE};
	

	public CrosshairPlayer(int id)
	{
		super();
		this.id = id;
		this.setCrosshairColor(colors[id%colors.length]);
	}
	
	public ScoreBar makeScoreBar()
	{
		ScoreBar bar = new ScoreBar(id,this.getCrosshairColor(),this);
		return bar;
	}	
	
	public int getId()
	{
		return id;
	}
}
