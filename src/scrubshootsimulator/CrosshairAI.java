package scrubshootsimulator;

import java.awt.Color;
import java.util.ArrayList;

public class CrosshairAI extends CrosshairBot
{
	
	private long lastChoseTime;
	private boolean didChoose;
	private double tempDist;
	private double pow;
	private double div;
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
	public double getSpeed() {
		double mod = Math.pow(this.distanceTo(getChosen()),getPow())/getDiv() ;
		return mod;
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
		setDiv(7 * (Math.random()*.5 + 1) * 2 * 1/((getScore()/120)+.5));
	}
	
	public void setChosen(Scrub chosen) 
	{
		super.setChosen(chosen);
		pickedScrubs.set(this.getId()-1, chosen);
	}
	public double getPow() {
		return pow;
	}
	public void setPow(double pow) {
		this.pow = pow;
	}
	public double getDiv() {
		return div;
	}
	public void setDiv(double div) {
		this.div = div;
	}
	@Override
	protected void interrupted() 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean wantsToUseAOE() 
	{
		int rand = (int) (Math.random() * 500);
		return rand == 0;
	}
	
}
