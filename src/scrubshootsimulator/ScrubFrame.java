package scrubshootsimulator;

import javax.swing.JFrame;

import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class ScrubFrame extends JFrame
{
	public ScrubFrame()
	{
		SpritePanel panel = new SpritePanel();
		Crosshair crosshair = new Crosshair();
		panel.add(crosshair);
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
	}
}
