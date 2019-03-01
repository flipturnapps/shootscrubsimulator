package com.github.kkevlar.scrubshootsim.client;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class ScrubFrame extends JFrame 
{
	private CrosshairMouse player;
	private ScrubPanel panel;
	private ScrubLibrary lib;
	private ShootClient client;
	private long lastTime = 0;
	public ScrubFrame( int id, ShootClient client)
	{
		panel = new ScrubPanel();
		this.client = client;
		player = new CrosshairMouse(id, client);
		panel.add(player);
		panel.add(player.makeScoreBar());
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lib = new UrlListScrubLib(new File("warrens.txt"));
		this.setSize(600, 600);
		this.client.setPanel(panel);
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
