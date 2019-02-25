package com.github.kkevlar.scrubshootsim.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class ShootClient extends Socket implements Runnable
{
	private ArrayList<Position> crosshairPoses;
	private boolean initReader = false;
	private boolean initWriter = false;
	private Position mousePos;

	public ShootClient() throws UnknownHostException, IOException
	{
		super("kevinkellar.com",25567);
		setCrosshairPoses(new ArrayList<>());
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
			try
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(this.getInputStream()));
				while(true)
				{
					String line = null;
					line = reader.readLine();
					if(line != null)
					{
						String[] splits = line.split("~");
						for(int i = 0; i < splits.length; i++)
						{
							crosshairPoses.set(i, new Position(splits[i]));
						}
					}
				}
			}
			catch(Exception ex)
			{

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
					if(System.currentTimeMillis() - last > 100)
					{
						last = System.currentTimeMillis();
						writer.println(mousePos.toString());
						writer.flush();
					}
				}
			}
			catch(Exception ex)
			{

			}
		}

	}
	public void setMousePos(Position mousePos)
	{
		this.mousePos = mousePos;
	}	
}
