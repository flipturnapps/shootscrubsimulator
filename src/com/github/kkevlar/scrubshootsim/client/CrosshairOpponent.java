package com.github.kkevlar.scrubshootsim.client;

public class CrosshairOpponent extends CrosshairPlayer 
{

	private long splitStartTime;
	private long lastSplitTime;
	
	public CrosshairOpponent(int id, ShootClient client) {
		super(id, client);
	}

	@Override
	protected void childForEachScrub(NewClientScrub scrub) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wantsToUseAOE() {
		boolean want = System.currentTimeMillis() - lastSplitTime > 300 && System.currentTimeMillis() - splitStartTime < 300;
		if (want)
			lastSplitTime = splitStartTime;
		return want;
	}

	@Override
	public boolean attacking() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setDoSplit()
	{	
		splitStartTime = System.currentTimeMillis();
	}

}
