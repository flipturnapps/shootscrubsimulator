package scrubshootsimulator;

public class CrosshairMouse extends Crosshair 
{
	private static final double SPLIT_RANGE = 300;
	private boolean isSplitting;
	public CrosshairMouse() 
	{
		
	}

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
	public void forEachScrub(Scrub scrub)
	{
		if(this.isSplitting && this.distanceToCenters(scrub) < SPLIT_RANGE)
		{
			
		}
		if(!this.getPanel().mouseDown() && this.collidingWithCircles(scrub))
			this.setTarget(scrub);
		if(this.getPanel().mouseDown() && this.collidingWithCircles(scrub) && this.targetIs(scrub))
			this.attack(scrub);
	}

	@Override
	public void spotlight() 
	{
		this.setCenterX((int) getPanel().getMouseX());
		this.setCenterY((int) getPanel().getMouseY());
		if(!this.isSplitting && this.getPanel().rightMouseDown())
			splitAttack();
	}

	private void splitAttack() 
	{
		this.isSplitting = true;		
	}
}
