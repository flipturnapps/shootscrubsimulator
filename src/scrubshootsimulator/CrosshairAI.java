package scrubshootsimulator;

import java.awt.Color;
import java.util.ArrayList;

import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class CrosshairAI extends CrosshairBot
{
	
	private long lastChoseTime;
	private boolean didChoose;
	private double tempDist;
	private static ArrayList<Scrub> pickedScrubs;
	private Color[] colors = new Color[]{Color.BLUE, Color.YELLOW, Color.CYAN, Color.DARK_GRAY, Color.GREEN, Color.ORANGE};
	public CrosshairAI() 
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
	public double getSpeed() 
	{
		double pow = 1.05;
		double mult = .047;
			return Math.pow(this.distanceToCenters(getChosen()), pow)*mult;
	}
	public void childForEachScrub(Scrub scrub)
	{
		if(getChosen() == null || !getChosen().isVisible() || System.currentTimeMillis() - lastChoseTime > 10000)
		{
			didChoose = false;
			setChosen(scrub);
			tempDist = this.distanceToCenters(getChosen());
			setTargetTime(0);
			lastChoseTime = System.currentTimeMillis();

		}
		if(didChoose == false)
		{
			
			boolean random = (int) (Math.random() * 5) == 0;
			boolean random2 = (int) (Math.random() * 10) >3;
			boolean closest = this.distanceToCenters(scrub) < tempDist;
			boolean chosenBefore = pickedScrubs.contains(scrub);
			boolean isSuper = scrub.isSuper();
			if((isSuper && random && false ) || ((closest && random2) && (!chosenBefore || random)))
			{
				setChosen(scrub);
				if(isSuper)
					this.setDidChoose(true);
				if(closest)
					tempDist = this.distanceTo(scrub);
			}
		}
	}
	
	
	public void setChosen(Scrub chosen) 
	{
		super.setChosen(chosen);
		pickedScrubs.set(this.getId()-1, chosen);
	}

	@Override
	protected void interrupted() 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean wantsToUseAOE() 
	{
		int r = (int) (Math.random() * 100);
		if(r==0)
		{
			int count = 0;
			SpritePanel panel = this.getPanel();
			for(int i = 0; i < panel .getSprites().size(); i++)
			{
				Scrub scrub = null;
				if(panel.getSprites().get(i) instanceof Scrub)
					scrub = (Scrub) panel.getSprites().get(i);
				else
					continue;
				if( this.distanceToCenters(scrub) < SPLIT_RANGE)
					count++;
				if(count >4 )
					return true;
			}
		}
		int rand = (int) (Math.random() * 1500);
		return rand == 0;
	}
	
}
