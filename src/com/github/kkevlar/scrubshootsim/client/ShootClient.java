package com.github.kkevlar.scrubshootsim.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.flipturnapps.kevinLibrary.sprite.PositionSprite;


public class ShootClient extends Socket implements Runnable
{
	private ArrayList<Position> crosshairPoses;
	private boolean initReader = false;
	private boolean initWriter = false;
	private Position mousePos;
	public int myId = -1;
	private int maxPlayerId = -1;
	private int oldMaxId = -1;
	private ScrubPanel panel = null;
	private LinkedList<PositionSprite> spritesToAdd;
	private HashMap<Integer,CrosshairPlayer> players;
	private ScrubLibrary lib;
	private PrintWriter writer;
	

	public ShootClient(ScrubLibrary lib) throws UnknownHostException, IOException
	{
		super("kevinkellar.com",25567);
		players = new HashMap<>();
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
//					System.out.println(line);
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
						NewClientScrub scrub = NewClientScrub.constructScrubFromString(line, lib);
						this.safeAddSprite(scrub);					
					}
					else if(line.startsWith("rm:"))
					{
						line = line.substring("rm:".length());
						this.getPanel().remove(line);
					}
					else if(line.startsWith("split:"))
					{
						line = line.substring("split:".length());
						int id = Integer.parseInt(line);
						CrosshairPlayer p = players.get(id);
						if(p instanceof CrosshairOpponent)
						{
							((CrosshairOpponent) p).setDoSplit();
						}
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
				setWriter(new PrintWriter(this.getOutputStream()));
				while(true)
				{
					if(System.currentTimeMillis() - last > 20)
					{
						last = System.currentTimeMillis();
						getWriter().println("pos:"+mousePos.toString());
						getWriter().flush();
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
		if(spr instanceof CrosshairPlayer)
		{
			CrosshairPlayer player = (CrosshairPlayer) spr;
			players.put(player.getId(),player);
		}
		
		if(this.getPanel() != null)
			this.getPanel().add(spr);
		else
			this.spritesToAdd.push(spr);
	}
	public void setMousePos(Position mousePos)
	{
		this.mousePos = mousePos;
	}
	public ScrubPanel getPanel() 
	{
		return panel;
	}
	public void setPanel(ScrubPanel panel) 
	{
		this.panel = panel;
		while(!this.spritesToAdd.isEmpty())
			panel.add(this.spritesToAdd.pop());
	}
	public void iKilledAScrub(NewClientScrub scrub)
	{
		getWriter().printf("rm:%d\n",scrub.getId());
		getWriter().flush();
	}
	public PrintWriter getWriter() {
		return writer;
	}
	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}
	
}
