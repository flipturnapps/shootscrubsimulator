package com.github.kkevlar.scrubshootsim.client;

import java.util.ArrayList;

public class CrosshairMouse extends CrosshairPlayer 
{
	
	private int targetId;
	private Position mousePos;
	
	public CrosshairMouse(int id, ShootClient client)
	{
		super(id,client);
	}
	
	@Override
	public boolean attacking() 
	{
		return this.getPanel().mouseDown();
	}

	public void setTarget(NewClientScrub scrub) 
	{
		this.targetId = scrub.getId();
	}

	public boolean targetIs(NewClientScrub scrub) 
	{
		if(scrub.getId() == this.targetId)
			return true;
		return false;
	}
	public void childForEachScrub(NewClientScrub scrub)
	{
		if(!this.getPanel().mouseDown() && this.collidingWithCircles(scrub))
			this.setTarget(scrub);
		if(this.getPanel().mouseDown() && this.collidingWithCircles(scrub) && this.targetIs(scrub))
			this.attack(scrub);
	}

	@Override
	public void childSpotlight() 
	{
		this.getClient().mouseDown = this.getPanel().mouseDown();
		setMousePos(new Position((int) getPanel().getMouseX(),(int) getPanel().getMouseY()));
		this.getClient().setMousePos(getMousePos());
		super.childSpotlight();
	
	}

	public boolean wantsToUseAOE()
	{
		return this.getPanel().rightMouseDown();
	}

	public Position getMousePos() {
		return mousePos;
	}

	private void setMousePos(Position mousePos) {
		this.mousePos = mousePos;
	}



	
}
