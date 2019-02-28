package com.github.kkevlar.scrubshootsim.client;

public class CrosshairOpponent extends CrosshairPlayer 
{

	public CrosshairOpponent(int id, ShootClient client) {
		super(id, client);
	}

	@Override
	protected void childForEachScrub(Scrub scrub) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wantsToUseAOE() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean attacking() {
		// TODO Auto-generated method stub
		return false;
	}

}
