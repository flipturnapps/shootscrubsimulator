package scrubshootsimulator;

public class CrosshairZombie extends CrosshairBot
{

	private Crosshair parent;

	public CrosshairZombie(Crosshair parent, Scrub chosen)
	{
		this.parent = parent;
		this.setChosen(chosen);
		this.setCrosshairColor(parent.getCrosshairColor());
		this.setX(parent.getX());
		this.setY(parent.getY());
		this.setAoeCharges(0);
	}

	

	@Override
	public void childForEachScrub(Scrub scrub) 
	{
		//do nothing
	}
	
	public boolean attack(Scrub scrub)
	{
		boolean b = super.attack(scrub);
		interrupted();
		return b;
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



	@Override
	public boolean wantsToUseAOE() 
	{
		return false;
	}

}
