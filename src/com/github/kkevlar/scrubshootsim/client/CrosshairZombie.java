package com.github.kkevlar.scrubshootsim.client;

public class CrosshairZombie extends CrosshairBot
{

	private Crosshair parent;
	private long spawnTime;

	
	public CrosshairZombie(Crosshair parent, NewClientScrub chosen, ShootClient client)
	{
		super(client);
		this.parent = parent;
		this.setChosen(chosen);
		this.setCrosshairColor(parent.getCrosshairColor());
		this.setX(parent.getX());
		this.setY(parent.getY());
		this.setAoeCharges(0);
		this.spawnTime = System.currentTimeMillis();
	}

	

	@Override
	public void childForEachScrub(NewClientScrub scrub) 
	{
		//do nothing
	}
	
	public boolean attack(NewClientScrub scrub)
	{
		boolean b = super.attack(scrub);
		interrupted();
		return b;
	}



	public void interrupted() 
	{
		this.setVisible(false);
		this.parent.setScore(this.getScore() + parent.getScore());
		this.getPanel().remove(this);
	}



	@Override
	public double getSpeed()
	{
		double pow = 1.1;
		double dist = this.distanceToCenters(getChosen());
		double div = 20;
		return Math.pow(dist, pow)/div;
	}

	public void spotlight()
	{
		if(System.currentTimeMillis() - this.spawnTime > 2000)
			this.interrupted();
		else
			super.spotlight();
		
	}

	@Override
	public boolean wantsToUseAOE() 
	{
		return false;
	}

}
