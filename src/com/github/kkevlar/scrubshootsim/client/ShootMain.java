package com.github.kkevlar.scrubshootsim.client;

public class ShootMain {

	public static void main(String[] args) 
	{
		ScrubFrame frame = new ScrubFrame();
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
