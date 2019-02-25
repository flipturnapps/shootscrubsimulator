package com.github.kkevlar.scrubshootsim.client;

import java.util.ArrayList;

public class CrosshairMouse extends Crosshair 
{
	
	private int targetId;
	
	
	
	@Override
	public boolean attacking() 
	{
		return this.getPanel().mouseDown();
	}

	public void setTarget(Scrub scrub) 
	{
		this.targetId = scrub.getId();
	}


	public boolean targetIs(Scrub scrub) 
	{
		if(scrub.getId() == this.targetId)
			return true;
		return false;
	}
	public void childForEachScrub(Scrub scrub)
	{
		if(!this.getPanel().mouseDown() && this.collidingWithCircles(scrub))
			this.setTarget(scrub);
		if(this.getPanel().mouseDown() && this.collidingWithCircles(scrub) && this.targetIs(scrub))
			this.attack(scrub);
	}

	@Override
	public void childSpotlight() 
	{
		
		this.setCenterX((int) getPanel().getMouseX());
		this.setCenterY((int) getPanel().getMouseY());
		
	}

	public boolean wantsToUseAOE()
	{
		return this.getPanel().rightMouseDown();
	}

	
}
