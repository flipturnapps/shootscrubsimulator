package com.github.kkevlar.scrubshootsim.client;

public class Position {
	
	private final int x;
	private final int y;
	public Position(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	public Position(String string) 
	{
		int tempX;
		int tempY;
		try
		{
			String[] splits = string.split(",");
		
		tempX = Integer.parseInt(splits[0]);
		tempY = Integer.parseInt(splits[1]);
		}
		catch (Exception ex)
		{
			tempX = -1;
			tempY = -1;
		}
		x = tempX;
		y = tempY;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String toString()
	{
		return x + "," + y;
	}
}
