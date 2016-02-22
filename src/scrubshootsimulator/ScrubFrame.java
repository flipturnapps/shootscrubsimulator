package scrubshootsimulator;

import java.io.File;

import javax.swing.JFrame;

import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class ScrubFrame extends JFrame 
{
	private Crosshair crosshair;
	private Scrub firstScrub;
	private SpritePanel panel;
	private ScrubLibrary lib;
	private long lastTime = 0;
	public ScrubFrame()
	{
		panel = new SpritePanel();
		crosshair = new Crosshair();
		panel.add(crosshair);
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lib = new UrlListScrubLib(new File("res/warrens.txt"));
		firstScrub = new Scrub(lib);
		firstScrub.setX(20);
		firstScrub.setY(20);
		panel.add(firstScrub);
		this.setSize(600, 600);
	}
	public void doChecks()
	{
		if(System.currentTimeMillis() - lastTime > 1000)
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
			if(!panel.mouseDown() && crosshair.collidingWithCircles(scrub))
				crosshair.setTarget(scrub);
			if(panel.mouseDown() && crosshair.collidingWithCircles(scrub) && crosshair.targetIs(scrub))
				crosshair.attack(scrub);
			if(!scrub.isVisible())
			{
				panel.remove(scrub);
				i--;
			}

		}
	}
}
