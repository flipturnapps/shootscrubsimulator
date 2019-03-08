package com.github.kkevlar.scrubshootsim.client;

import java.util.HashMap;
import java.util.LinkedList;

import com.flipturnapps.kevinLibrary.sprite.Sprite;
import com.flipturnapps.kevinLibrary.sprite.SpritePanel;

public class ScrubPanel extends SpritePanel 
{
	private HashMap<Integer, NewClientScrub> scrubTable;
	private LinkedList<Sprite> toAdd;
	private LinkedList<Sprite> toRemove;
	
	public ScrubPanel()
	{
		scrubTable = new HashMap<>();
		toAdd = new LinkedList<>();
		toRemove = new LinkedList<>();
	}
	
	public void safeAddRemove()
	{
		while(!toAdd.isEmpty())
		{ 
			super.add(toAdd.pop());
		}
		while(!toRemove.isEmpty())
		{ 
			super.remove(toRemove.pop());
		}
	}
	
	public void add(Sprite sprite)
	{
		if(sprite instanceof NewClientScrub)
		{
			NewClientScrub scrub = (NewClientScrub) sprite;
			scrubTable.put(scrub.getId(), scrub);
		}
//		toAdd.add(sprite);
		super.add(sprite);
	}
	
	public void addLater(Sprite sprite)
	{
		toAdd.add(sprite);
	}
	
	public void remove(Sprite sprite)
	{
		if(sprite instanceof NewClientScrub)
		{
			NewClientScrub scrub = (NewClientScrub) sprite;
			scrubTable.remove(scrub.getId(), scrub);
		}
//		toRemove.add(sprite);
		super.remove(sprite);
	}
	
	public void removeLater(Sprite sprite)
	{
		toRemove.add(sprite);
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
