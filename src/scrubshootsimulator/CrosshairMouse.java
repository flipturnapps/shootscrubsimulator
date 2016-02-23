package scrubshootsimulator;

import java.util.ArrayList;

public class CrosshairMouse extends Crosshair 
{
	private static final double SPLIT_RANGE = 600;
	private static final int SPLIT_COOLDOWN = 2000;
	private boolean isSplitting;
	private int targetId;
	private ArrayList<CrosshairZombie> zombies;
	private long lastSplit;
	
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
	public void forEachScrub(Scrub scrub)
	{
		if(this.isSplitting && this.distanceToCenters(scrub) < SPLIT_RANGE)
		{
			CrosshairZombie zzz = new CrosshairZombie(this,scrub);
			this.getPanel().add(zzz);
			zombies.add(zzz);
		}
		if(!this.getPanel().mouseDown() && this.collidingWithCircles(scrub))
			this.setTarget(scrub);
		if(this.getPanel().mouseDown() && this.collidingWithCircles(scrub) && this.targetIs(scrub))
			this.attack(scrub);
	}

	@Override
	public void spotlight() 
	{
		boolean alreadySplit = false;
		if(this.isSplitting)
		{
			this.isSplitting = false;
		}
		this.setCenterX((int) getPanel().getMouseX());
		this.setCenterY((int) getPanel().getMouseY());
		if(!alreadySplit && !this.isSplitting && this.getPanel().rightMouseDown() && System.currentTimeMillis() - lastSplit > SPLIT_COOLDOWN)
			splitAttack();
	}

	private void splitAttack() 
	{
		this.isSplitting = true;	
		this.initZombies();
		lastSplit = System.currentTimeMillis();
	}

	private void initZombies() 
	{
		if(zombies == null)
			zombies = new ArrayList<CrosshairZombie>();
	}
}
