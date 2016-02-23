package scrubshootsimulator;

import java.awt.Color;
import java.util.ArrayList;

public class CrosshairBot extends Crosshair 
{
	private Scrub chosen;
	private long targetTime;
	private long lastChoseTime;
	private boolean didChoose;
	private double tempDist;
	private double pow;
	private double div;
	private static ArrayList<Scrub> pickedScrubs;
	private Color[] colors = new Color[]{Color.BLUE, Color.PINK, Color.CYAN, Color.MAGENTA, Color.GREEN};
	public CrosshairBot() 
	{
		if(pickedScrubs == null)
			pickedScrubs = new ArrayList<Scrub>();
		while(pickedScrubs.size() < this.getId())
		{
			pickedScrubs.add(null);
		}
		this.setCrosshairColor(colors[(getId()-1)%colors.length]);
		this.setX((int) (Math.random() * 500));
		this.setY((int) (Math.random() *500));
	}
	public void forEachScrub(Scrub scrub)
	{
		if(getChosen() == null || !getChosen().isVisible() || System.currentTimeMillis() - lastChoseTime > 10000)
		{
			didChoose = false;
			setChosen(scrub);
			tempDist = this.distanceToCenters(getChosen());
			targetTime = 0;
			lastChoseTime = System.currentTimeMillis();
			regenRand();
		}
		if(didChoose == false)
		{
			
			boolean random = (int) (Math.random() * 5) == 0;
			boolean random2 = (int) (Math.random() * 10) >3;
			boolean closest = this.distanceToCenters(scrub) < tempDist;
			boolean chosenBefore = pickedScrubs.contains(scrub);
			if((closest && random2) && (!chosenBefore || random))
			{
				setChosen(scrub);
				if(closest)
					tempDist = this.distanceTo(scrub);
			}
		}
	}
	private void regenRand()
	{
		pow = 1.025 + Math.random()*0.1;
		div = 7 * (Math.random()*.5 + 1) * 1.2 * 1/((getScore()/40)+.5);
		
	}
	public void spotlight()
	{
		didChoose = true;
		if(getChosen() != null)
		{
			if(getChosen().isVisible() == false)
				return;
			if(targetTime == 0 && this.distanceToCenters(getChosen())<(getChosen().getWidth()+0.0)/1)
			{
				targetTime = System.currentTimeMillis();
				return;
			}
			if(targetTime != 0 && System.currentTimeMillis() - targetTime > 200)
			{
				this.attack(getChosen());
				targetTime = 0;
				return;
			}
			if(!attacking())
				move();
		}
		
	}
	private void move() 
	{
		
		double mod = Math.pow(this.distanceTo(getChosen()),pow)/div ;
		
		this.moveTowards(getChosen(), mod);
	}
	public boolean attacking()
	{
		return this.targetTime != 0;
	}
	public Scrub getChosen() {
		return chosen;
	}
	public void setChosen(Scrub chosen) 
	{
		this.chosen = chosen;
		pickedScrubs.set(this.getId()-1, chosen);
	}
	
}
