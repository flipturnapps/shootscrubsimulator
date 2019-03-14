package com.github.kkevlar.scrubshootsim.server;

import java.io.IOException;
import java.net.Socket;

public class Player extends GenericServerClient {

	private String pos;
	private int id;
	private ShootServer server;
	public int score = 0;
	public int chargeCount = 0;
	public boolean numsChanged = false;
	
	public Player(int id, ShootServer server, Socket s) throws IOException 
	{
		super(server, s);
		this.setId(id);
		this.server = server;
	}

	@Override
	public void clientInit() 
	{
		server.sentToAll("id:" + id);
	}

	@Override
	public void incomingMessage(String read) {
		if(read.startsWith("pos:"))
			pos = read.substring("pos:".length());
		else if(read.startsWith("rm:"))
		{
			server.sentToAll(read);
			score++;
			numsChanged = true;
		}
		else if (read.startsWith("split:"))
			server.sentToAll(read);
		else if (read.startsWith("nums:"))
		{
			setNums(read.substring("nums:".length()));
		}

	}

	private void setNums(String nums) 
	{
		try		
		{
			String[] splits = nums.split(",");
//			int tempScore = Integer.parseInt(splits[0]);
			int tempCharge = Integer.parseInt(splits[1]);
//			if(tempScore != score)
//			{
//				score = tempScore;
//				numsChanged = true;
//			}
			if(chargeCount != tempCharge)
			{
				chargeCount = tempCharge;
				numsChanged = true;
			}			
		}
		catch(RuntimeException ex)
		{
			
		}
	}

	@Override
	public void clientDisconnected() {
		// TODO Auto-generated method stub
		
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

}
