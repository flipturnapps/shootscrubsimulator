package com.github.kkevlar.scrubshootsim.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;

public class ShootMain {

	public static void main(String[] args) 
	{
		StartFrame start = new StartFrame();
		
		File dongFile = new File("dong.txt");
		dongFile.delete();
		
		
		
		try (BufferedInputStream in = new BufferedInputStream(new URL("https://raw.githubusercontent.com/kkevlar/shootscrubsimulator/master/warrens.txt").openStream());
					  FileOutputStream fileOutputStream = new FileOutputStream("dong.txt")) {
					    byte dataBuffer[] = new byte[1024];
					    int bytesRead;
					    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					        fileOutputStream.write(dataBuffer, 0, bytesRead);
					    }
					} catch (IOException e) {
					    // handle exception
					}
			
		while(start.isVisible())
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("hehee");
		
		ScrubLibrary lib = new UrlListScrubLib(dongFile);
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
