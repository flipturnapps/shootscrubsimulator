package scrubshootsimulator;

public class CrosshairZombie extends CrosshairBot
{

	private CrosshairMouse parent;

	public CrosshairZombie(CrosshairMouse parent, Scrub chosen)
	{
		this.parent = parent;
		this.setChosen(chosen);
		this.setCrosshairColor(parent.getCrosshairColor());
		this.setX(parent.getX());
		this.setY(parent.getY());
	}

	

	@Override
	public void forEachScrub(Scrub scrub) 
	{
		//do nothing
	}
	
	public void attack(Scrub scrub)
	{
		super.attack(scrub);
		interrupted();
	}



	public void interrupted() 
	{
		this.setVisible(false);
		this.parent.setScore(this.getScore() + parent.getScore());
		this.getPanel().remove(this);
	}



	@Override
	public double getSpeed()
	{
		double pow = 1.1;
		double dist = this.distanceToCenters(getChosen());
		double div = 20;
		return Math.pow(dist, pow)/div;
	}

}
