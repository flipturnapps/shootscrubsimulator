package com.github.kkevlar.scrubshootsim.client;

import java.util.HashMap;

import com.flipturnapps.kevinLibrary.sprite.Sprite;
import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class ScrubPanel extends SpritePanel 
{
	private HashMap<Integer, NewClientScrub> scrubTable;
	
	public ScrubPanel()
	{
		scrubTable = new HashMap<>();
	}
	
	public void add(Sprite sprite)
	{
		if(sprite instanceof NewClientScrub)
		{
			NewClientScrub scrub = (NewClientScrub) sprite;
			scrubTable.put(scrub.getId(), scrub);
		}
		super.add(sprite);
	}
	
	public void remove(Sprite sprite)
	{
		if(sprite instanceof NewClientScrub)
		{
			NewClientScrub scrub = (NewClientScrub) sprite;
			scrubTable.remove(scrub.getId(), scrub);
		}
		super.remove(sprite);
	}
	
	public void remove(String line) 
	{
		try
		{
		int num = Integer.parseInt(line);
		NewClientScrub scrub = scrubTable.remove(num);
		this.remove(scrub);
		}
		catch(Exception ex) {}
		
	}
	
	
	
}
