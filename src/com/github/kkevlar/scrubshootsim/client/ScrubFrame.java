package com.github.kkevlar.scrubshootsim.client;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.flipturnapps.kevinLibrary.sprite.Sprite;

public class ScrubFrame extends JFrame 
{
	private CrosshairMouse player;
	private ScrubPanel panel;
	private ScrubLibrary lib;
	private ShootClient client;
	private long lastTime = 0;
	public ScrubFrame(ScrubLibrary lib, int id, ShootClient client)
	{
		panel = new ScrubPanel();
		this.client = client;
		player = new CrosshairMouse(id, client);
		panel.add(player);
		panel.add(player.makeScoreBar());
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.lib = lib;
		this.setSize(600, 600);
		this.client.setPanel(panel);
	}
	public void doChecks()
	{
		ArrayList<Sprite> sprs = null;
		try
		{
			sprs = new ArrayList<Sprite>(panel.getSprites());
		}
		catch (RuntimeException ex)
		{
			return;
		}
		LinkedList<Sprite> toRemove = new LinkedList<Sprite>();
		for(int y = 0; y < sprs.size(); y++)
		{
			Crosshair cx = null;
			if(sprs.get(y) instanceof Crosshair)
				cx = (Crosshair) sprs.get(y);
			else
				continue;
			cx.spotlight();

		}
		for(int i = 0; i < sprs.size(); i++)
		{
			NewClientScrub scrub = null;
			if(sprs.get(i) instanceof NewClientScrub)
				scrub = (NewClientScrub) sprs.get(i);
			else
				continue;
			for(int y = 0; y < sprs.size(); y++)
			{
				Crosshair cx = null;
				if(sprs.get(y) instanceof Crosshair)
					cx = (Crosshair) sprs.get(y);
				else
					continue;
				cx.forEachScrub(scrub);
			}
			if(!scrub.isVisible())
			{
				toRemove.add(scrub);
			}
			
		}
		
		while(!toRemove.isEmpty())
		{
			this.panel.remove(toRemove.pop());
		}
	
		this.panel.safeAddRemove();
		
		this.setTitle(sprs.size()+"");
	}
}
