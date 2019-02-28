package com.github.kkevlar.scrubshootsim.client;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class ScrubFrame extends JFrame 
{
	private Crosshair player;
	private Scrub firstScrub;
	private SpritePanel panel;
	private ScrubLibrary lib;
	private ShootClient client;
	private long lastTime = 0;
	public ScrubFrame( int id, ShootClient client)
	{
		panel = new SpritePanel();
		this.client = client;
		player = new CrosshairMouse(id, client);
		panel.add(player);
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lib = new UrlListScrubLib(new File("warrens.txt"));
		firstScrub = new Scrub(lib);
		firstScrub.setX(20);
		firstScrub.setY(20);
		panel.add(firstScrub);
		this.setSize(600, 600);
	}
	public void doChecks()
	{
		for(int y = 0; y < panel.getSprites().size(); y++)
		{
			Crosshair cx = null;
			if(panel.getSprites().get(y) instanceof Crosshair)
				cx = (Crosshair) panel.getSprites().get(y);
			else
				continue;
			cx.spotlight();

		}
		if(System.currentTimeMillis() - lastTime > 85)
		{
			Scrub newScrub = new Scrub(lib);
			panel.add(newScrub);
			newScrub.setX((int) (Math.random() * panel.getWidth()));
			newScrub.setY((int) (Math.random() * panel.getHeight()));
			lastTime = System.currentTimeMillis();
		
		}
		for(int i = 0; i < panel.getSprites().size(); i++)
		{
			Scrub scrub = null;
			if(panel.getSprites().get(i) instanceof Scrub)
				scrub = (Scrub) panel.getSprites().get(i);
			else
				continue;
			for(int y = 0; y < panel.getSprites().size(); y++)
			{
				Crosshair cx = null;
				if(panel.getSprites().get(y) instanceof Crosshair)
					cx = (Crosshair) panel.getSprites().get(y);
				else
					continue;
				cx.forEachScrub(scrub);
			}
			if(!scrub.isVisible())
			{
				panel.remove(scrub);
				i--;
			}
			
		}
		this.setTitle(panel.getSprites().size()+"");
	}
}
