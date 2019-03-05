package com.github.kkevlar.scrubshootsim.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.flipturnapps.kevinLibrary.sprite.PositionSprite;
import com.flipturnapps.kevinLibrary.sprite.SpritePanel;


public class ShootClient extends Socket implements Runnable
{
	private ArrayList<Position> crosshairPoses;
	private boolean initReader = false;
	private boolean initWriter = false;
	private Position mousePos;
	public int myId = -1;
	private int maxPlayerId = -1;
	private int oldMaxId = -1;
	private SpritePanel panel = null;
	private LinkedList<PositionSprite> spritesToAdd;
	private ScrubLibrary lib;
	

	public ShootClient(ScrubLibrary lib) throws UnknownHostException, IOException
	{
		super("kevinkellar.com",25567);
		spritesToAdd = new LinkedList<>();
		this.lib = lib;
		setCrosshairPoses(new ArrayList<>());
		while(crosshairPoses.size() < 20)
			crosshairPoses.add(new Position(-1,-1));
		new Thread(this).start();
		mousePos = new Position(0,0);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(this).start();
		getCrosshairPoses().add(new Position(0,0));

	}
	public ArrayList<Position> getCrosshairPoses() {
		return crosshairPoses;
	}
	private void setCrosshairPoses(ArrayList<Position> crosshairPoses) {
		this.crosshairPoses = crosshairPoses;
	}
	@Override
	public void run()
	{
		if(!initReader)
		{
			initReader = true;
			BufferedReader reader = null;
			try
			{
				reader = new BufferedReader(new InputStreamReader(this.getInputStream()));
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			while(reader != null)
			{
				String line = null;
				try
				{
					line = reader.readLine();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				if(line != null)
				{
					if(line.startsWith("pos:"))
					{
						line = line.substring("pos:".length());
						String[] splits = line.split("~");

						for(int i = 0; i < splits.length; i++)
						{
							crosshairPoses.set(i, new Position(splits[i]));
						}
					}
					else if(line.startsWith("id:"))
					{
						line = line.substring("id:".length());
						this.maxPlayerId = (Integer.parseInt(line));
						if(this.myId < 0)
							this.myId = (this.maxPlayerId);
						while(this.maxPlayerId > this.oldMaxId)
						{
							this.oldMaxId++;
							if(this.oldMaxId != this.myId)
							{
								CrosshairOpponent op = new CrosshairOpponent(this.oldMaxId, this);
								this.safeAddSprite(op);
								this.safeAddSprite(op.makeScoreBar());
							}
						}
						
					}
					else if(line.startsWith("add:"))
					{
						line = line.substring("add:".length());
						this.maxPlayerId = (Integer.parseInt(line));
						NewClientScrub scrub = NewClientScrub.constructScrubFromString(line, lib);
						this.safeAddSprite(scrub);					
					}
				}
			}


		}
		if(!initWriter)
		{
			initWriter = true;
			try
			{
				long last = System.currentTimeMillis();
				PrintWriter writer = new PrintWriter(this.getOutputStream());
				while(true)
				{
					if(System.currentTimeMillis() - last > 60)
					{
						last = System.currentTimeMillis();
						writer.println("pos:"+mousePos.toString());
						writer.flush();
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}

	}

	private void safeAddSprite(PositionSprite spr)
	{
		if(this.getPanel() != null)
			this.getPanel().add(spr);
		else
			this.spritesToAdd.push(spr);
	}
	public void setMousePos(Position mousePos)
	{
		this.mousePos = mousePos;
	}
	public SpritePanel getPanel() 
	{
		return panel;
	}
	public void setPanel(SpritePanel panel) 
	{
		this.panel = panel;
		while(!this.spritesToAdd.isEmpty())
			panel.add(this.spritesToAdd.pop());
	}
	
}
