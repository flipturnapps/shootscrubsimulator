package com.github.kkevlar.scrubshootsim.server;

import java.io.IOException;
import java.net.Socket;

public class Player extends GenericServerClient {

	public Player(ShootServer server, Socket s) throws IOException 
	{
		super(server, s);
	}

	@Override
	public void clientInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void incomingMessage(String read) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientDisconnected() {
		// TODO Auto-generated method stub
		
	}

}
