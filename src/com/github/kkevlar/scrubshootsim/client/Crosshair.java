package com.github.kkevlar.scrubshootsim.client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.flipturnapps.kevinLibrary.helper.KevinColor;
import com.flipturnapps.kevinLibrary.sprite.PositionSprite;
import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public abstract class Crosshair extends PositionSprite 
{
	public static final double SPLIT_RANGE = 300;
	public static final int SPLIT_COOLDOWN = 400;
	private static final int STROKE = 3;
	private Color crosshairColor;
	private int score;
	private boolean isSplitting;
	private ArrayList<CrosshairZombie> zombies;
	private long lastSplit;
	private int aoeCharges = 1;
	private ShootClient client;
	public Crosshair(ShootClient client)
	{
		this.setClient(client);
		this.setOutsideAllowed(false);
		this.setAll(20, 20, 40, 40);
		this.setLayer(2);
	}
	public  void splitAttack() 
	{
		this.isSplitting = true;
		this.initZombies();
		setAoeCharges(getAoeCharges() - 1);
		lastSplit = System.currentTimeMillis();
	}

	private void initZombies() 
	{
		if(zombies == null)
			zombies = new ArrayList<CrosshairZombie>();
	}
	public void forEachScrub(NewClientScrub scrub)
	{
		if(this.isSplitting && !scrub.isSuper() && this.distanceToCenters(scrub) < SPLIT_RANGE)
		{
			CrosshairZombie zzz = new CrosshairZombie(this,scrub,client);
			this.getPanel().add(zzz);
			zombies.add(zzz);
		}
		this.childForEachScrub(scrub);
	}

	protected abstract void childForEachScrub(NewClientScrub scrub);
	public void spotlight() 
	{
		boolean alreadySplit = false;
		if(this.isSplitting)
		{
			this.isSplitting = false;
		}
		this.childSpotlight();
		if(!alreadySplit && !this.isSplitting && wantsToUseAOE() && System.currentTimeMillis() - lastSplit > SPLIT_COOLDOWN && getAoeCharges() > 0)
			splitAttack();
		if(this.getScore() > 50)
{
			this.getPanel().setBackground(KevinColor.mix(this.crosshairColor, this.getPanel().getBackground()));
			this.setScore(0);
}
	}

	protected abstract void childSpotlight();
	public abstract boolean wantsToUseAOE();

	@Override
	protected void drawShape(Graphics g, SpritePanel s, int x, int y, int width, int height) 
	{
		
		Color maiColor = this.getCrosshairColor();
		if(showLighterColor())
			maiColor = KevinColor.mix(this.getCrosshairColor(), Color.WHITE, .3);
		g.setColor(maiColor);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(maiColor);
		g2.setStroke(new BasicStroke(STROKE));

		g2.drawOval(this.getX(), this.getY(), width, height);
		//g.drawOval(this.getX()+1, this.getY()+1, width-2, height-2);
		///g.setColor(this.getPanel().getBackground());
		///g.fillOval(this.getX()+STROKE, this.getY()+STROKE, width-(STROKE*2), height-(STROKE*2));        


		{
			g.fillRect(this.getCenterX()-STROKE/2, this.getCenterY()+1- this.getHeight()/2, STROKE, this.getHeight()-2);
			g.fillRect(this.getCenterX()+1- this.getWidth()/2 , this.getCenterY()-STROKE/2, this.getWidth() -2, STROKE);
			if(this.aoeCharges > 0 && !(this instanceof CrosshairZombie))
			{
				g2.setFont(new Font("Monospaced",Font.BOLD, 16));
				g2.drawString(this.getAoeCharges()+"", x, y);
			}
		}

	}


	protected boolean showLighterColor() 
	{
		return attacking();
	}
	public  abstract boolean attacking();
	//public abstract void spotlight();
	//public abstract void forEachScrub(Scrub scrub);

	public Color getCrosshairColor() {
		return crosshairColor;
	}


	public void setCrosshairColor(Color crosshairColor) {
		this.crosshairColor = crosshairColor;
	}


	public boolean attack(NewClientScrub scrub)
	{
		boolean visible = scrub.isVisible();
		scrub.setVisible(false);
		if(visible)
		{
			score++;
			if(scrub.isSuper())
				this.aoeCharges++;
			getClient().iKilledAScrub(scrub);
			return true;
		}
		return false;		
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	public int getAoeCharges() {
		return aoeCharges;
	}
	public void setAoeCharges(int aoeCharges) {
		this.aoeCharges = aoeCharges;
	}
	public ShootClient getClient() {
		return client;
	}
	public void setClient(ShootClient client) {
		this.client = client;
	}
}
