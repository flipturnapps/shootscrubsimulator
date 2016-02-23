package scrubshootsimulator;

import java.awt.Color;
import java.util.ArrayList;

public class CrosshairBot extends CrosshairAI
{
	
	private long lastChoseTime;
	private boolean didChoose;
	private double tempDist;
	
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
			setTargetTime(0);
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
	public void regenRand()
	{
		setPow(1.025 + Math.random()*0.1);
		setDiv(7 * (Math.random()*.5 + 1) * 1.2 * 1/((getScore()/40)+.5));
	}
	
	public void setChosen(Scrub chosen) 
	{
		super.setChosen(chosen);
		pickedScrubs.set(this.getId()-1, chosen);
	}
	
}
