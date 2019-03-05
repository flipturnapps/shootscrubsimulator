package com.github.kkevlar.scrubshootsim.client;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

public class ShootMain {

	public static void main(String[] args) 
	{
		ScrubLibrary lib = new UrlListScrubLib(new File("warrens.txt"));
		ShootClient client = null;
		try {
			client = new ShootClient(lib);
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		while(client.myId < 0)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		ScrubFrame frame = new ScrubFrame(lib,client.myId,client);
		frame.setVisible(true);
		while(true)
		{
			frame.repaint();
			frame.doChecks();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
