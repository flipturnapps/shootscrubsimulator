package com.github.kkevlar.scrubshootsim.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartFrame extends JFrame 
{
	JPanel panel;
	Image title;
	JButton button;
	
	public StartFrame()
	{
		panel = new JPanel()
				{
			public void paintComponent(java.awt.Graphics g)
			{
				if(title == null)
				{
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, this.getWidth(), this.getHeight());
				}
				else
				{
					g.drawImage(title, 0, 0, this.getWidth(), this.getHeight(), null);
				}
					
			}};
		title = getImageFromUrl("https://i.imgur.com/8R7sma9.png");
		button = new JButton("Play Game");
		button.addActionListener(e -> this.setVisible(false));
		
		this.getContentPane().add(panel);
		this.getContentPane().add(button,BorderLayout.SOUTH);
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public BufferedImage getImageFromUrl(String urlS) 
	{
		
		BufferedImage image = null;
		URL url = null;
		try {
			url = new URL(urlS);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
}
