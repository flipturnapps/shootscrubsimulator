package com.github.kkevlar.scrubshootsim.client;

import java.util.ArrayList;

public class CrosshairMouse extends CrosshairPlayer 
{
	
	private int targetId;
	private Position mousePos;
	private ShootClient client;
	
	public CrosshairMouse(int id, ShootClient client)
	{
		super(id);
		this.setClient(client);
	}
	
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
		setMousePos(new Position((int) getPanel().getMouseX(),(int) getPanel().getMouseY()));
		client.setMousePos(getMousePos());
		Position pos = client.getCrosshairPoses().get(0);
		this.setCenterX(pos.getX());
		this.setCenterY(pos.getY());
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

	public ShootClient getClient() {
		return client;
	}

	private void setClient(ShootClient client) {
		this.client = client;
	}

	
}
