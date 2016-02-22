package scrubshootsimulator;

import java.awt.Color;

public class CrosshairBot extends Crosshair 
{
	private Scrub chosen;
	private long targetTime;
	private long lastChoseTime;
	private boolean didChoose;
	private double tempDist;
	private double pow;
	private double div;
	private Color[] colors = new Color[]{Color.BLUE, Color.PINK, Color.CYAN, Color.MAGENTA, Color.GREEN};
	public CrosshairBot() 
	{
		this.setCrosshairColor(colors[(getId()-1)%colors.length]);
		this.setX((int) (Math.random() * 500));
		this.setY((int) (Math.random() *500));
	}
	public void forEachScrub(Scrub scrub)
	{
		if(chosen == null || !chosen.isVisible() || System.currentTimeMillis() - lastChoseTime > 2000)
		{
			didChoose = false;
			chosen = scrub;
			tempDist = this.distanceToCenters(chosen);
			targetTime = 0;
			lastChoseTime = System.currentTimeMillis();
			regenRand();
		}
		if(didChoose == false)
		{
			if((int) (Math.random() * 5) == 0 || (this.distanceToCenters(scrub) < tempDist && (int) (Math.random() * 10) >3 ))
			{
				chosen = scrub;
				tempDist = this.distanceTo(scrub);
			}
		}
	}
	private void regenRand()
	{
		pow = 1.05 + Math.random()*0.1;
		div = 7 * (Math.random()*.5 + 1);
		
	}
	public void spotlight()
	{
		didChoose = true;
		if(chosen != null)
		{
			if(chosen.isVisible() == false)
				return;
			if(targetTime == 0 && this.distanceToCenters(chosen)<(chosen.getWidth()+0.0)/1)
			{
				targetTime = System.currentTimeMillis();
				return;
			}
			if(targetTime != 0 && System.currentTimeMillis() - targetTime > 200)
			{
				this.attack(chosen);
				targetTime = 0;
				return;
			}
			if(!attacking())
				move();
		}
		
	}
	private void move() 
	{
		
		double mod = Math.pow(this.distanceTo(chosen),pow)/div ;
		
		this.moveTowards(chosen, mod);
	}
	public boolean attacking()
	{
		return this.targetTime != 0;
	}
	
}
