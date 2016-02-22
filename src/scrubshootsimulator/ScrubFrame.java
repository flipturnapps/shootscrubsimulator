package scrubshootsimulator;

import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;

import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

import serverside.ScrubLibrary;
import serverside.UrlListScrubLib;

public class ScrubFrame extends JFrame 
{
	private Crosshair player;
	private Scrub firstScrub;
	private SpritePanel panel;
	private ScrubLibrary lib;
	private long lastTime = 0;
	private Crosshair[] crosshairs = new Crosshair[6];
	public ScrubFrame()
	{
		panel = new SpritePanel();
		player = new CrosshairMouse();
		crosshairs[0] = player;
		panel.add(player);
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lib = new UrlListScrubLib(new File("res/warrens.txt"));
		firstScrub = new Scrub(lib);
		firstScrub.setX(20);
		firstScrub.setY(20);
		panel.add(firstScrub);
		for(int i = 1; i < crosshairs.length; i++)
		{
			crosshairs[i] = new CrosshairBot();
			panel.add(crosshairs[i]);
		}
		this.setSize(600, 600);
	}
	public void doChecks()
	{
		for (int i = 0; i < crosshairs.length; i++) {
			crosshairs[i].spotlight();
			
		}
		if(System.currentTimeMillis() - lastTime > 150)
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
			for (int y = 0; y < crosshairs.length; y++) {
				crosshairs[y].forEachScrub(scrub);
				
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
