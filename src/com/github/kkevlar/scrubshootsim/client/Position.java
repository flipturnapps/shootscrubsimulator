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
		String[] splits = string.split(",");
		x = Integer.parseInt(splits[0]);
		y = Integer.parseInt(splits[1]);
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
