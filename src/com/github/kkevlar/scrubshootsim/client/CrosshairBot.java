package com.github.kkevlar.scrubshootsim.client;

public abstract class CrosshairBot extends Crosshair 
{
	public CrosshairBot(ShootClient client) {
		super(client);
	}

	private NewClientScrub chosen;
	private long targetTime;
	
	private boolean didChoose;	
	
	public void childSpotlight()
	{
		setDidChoose(true);
		if(getChosen() != null)
		{
			if(getChosen().isVisible() == false)
			{
				interrupted();
				return;
			}
			if(getTargetTime() == 0 && this.distanceToCenters(getChosen())<(getChosen().getWidth()+0.0)/1)
			{
				setTargetTime(System.currentTimeMillis());
				return;
			}
			if(getTargetTime() != 0 && System.currentTimeMillis() - getTargetTime() > getClickDelay())
			{
				this.attack(getChosen());
				setTargetTime(0);
				return;
			}
			if(!attacking())
				move();
		}
		
	}

	protected abstract void interrupted();

	private int getClickDelay() 
	{
		return 200;
	}
	private void move() 
	{
		
		double mod = getSpeed();
		this.moveTowards(getChosen(), mod);
	}

	public abstract double getSpeed() ;
	public boolean attacking()
	{
		return this.getTargetTime() != 0;
	}
	public NewClientScrub getChosen() {
		return chosen;
	}
	public void setChosen(NewClientScrub chosen) 
	{
		this.chosen = chosen;
	}

	public boolean isDidChoose() {
		return didChoose;
	}

	public void setDidChoose(boolean didChoose) {
		this.didChoose = didChoose;
	}

	public long getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(long targetTime) {
		this.targetTime = targetTime;
	}
	
}
