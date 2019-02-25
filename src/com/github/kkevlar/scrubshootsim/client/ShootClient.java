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
					String[] splits = line.split("~");
					
					for(int i = 0; i < splits.length; i++)
					{
						crosshairPoses.set(i, new Position(splits[i]));
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
					writer.println(mousePos.toString());
					writer.flush();
					System.out.println(mousePos.toString());
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
public void setMousePos(Position mousePos)
{
	this.mousePos = mousePos;
}	
}
