package com.github.kkevlar.scrubshootsim.client;

public class CrosshairOpponent extends CrosshairPlayer 
{

	private long splitStartTime;
	
	public CrosshairOpponent(int id, ShootClient client) {
		super(id, client);
	}

	@Override
	protected void childForEachScrub(NewClientScrub scrub) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wantsToUseAOE() {
		return System.currentTimeMillis() - splitStartTime > 300;
	}

	@Override
	public boolean attacking() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean showLighterColor()
	{
		return this.getClient().crosshairDowns.get(this.getId());
	}

	public void setDoSplit()
	{
		
		splitStartTime = System.currentTimeMillis();
	}

}
