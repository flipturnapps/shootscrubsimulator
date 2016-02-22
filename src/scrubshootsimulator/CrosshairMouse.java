package scrubshootsimulator;

public class CrosshairMouse extends Crosshair 
{

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
		
	}
}
