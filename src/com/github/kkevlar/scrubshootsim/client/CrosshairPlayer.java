package com.github.kkevlar.scrubshootsim.client;

import java.awt.Color;

public abstract class CrosshairPlayer extends Crosshair 
{
	private int id;
	private Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN, Color.DARK_GRAY, Color.GREEN, Color.ORANGE};
	private ShootClient client;

	public CrosshairPlayer(int id, ShootClient client)
	{
		super();
		this.id = id;
		this.client = client;
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
	
	public void childSpotlight()
	{
		Position pos = client.getCrosshairPoses().get(this.getId());
		this.setCenterX(pos.getX());
		this.setCenterY(pos.getY());
	}
	
	public ShootClient getClient() {
		return client;
	}

	private void setClient(ShootClient client) {
		this.client = client;
	}
}