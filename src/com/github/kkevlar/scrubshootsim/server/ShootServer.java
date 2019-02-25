package com.github.kkevlar.scrubshootsim.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;


public class ShootServer extends ServerSocket implements Runnable
{
	private boolean up;
	private LinkedList<Player> users;

	public ShootServer(int port) throws IOException
	{
		super(port);
		up = true;
		users = new LinkedList<Player>();
		new Thread(this).start();
		
	}

	public void run()
	{
		int id = 0;
		while (up)
		{
			Socket socket = null;
			try {
				socket = accept();
				Player nuser = new Player(id++,this,socket);
				users.add(nuser);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Player> getPlayers() {
		return users;
	}
}