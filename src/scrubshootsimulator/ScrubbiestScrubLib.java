package scrubshootsimulator;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.flipturnapps.kevinLibrary.helper.TextFileHelper;

public class ScrubbiestScrubLib implements ScrubLibrary
{

	private String[] imageUrls;
	public ScrubbiestScrubLib()
	{
		this.readUrlsFromFile();
	}
	private void readUrlsFromFile()
	{
		File resFile = new File("res/warrens.txt");
		try {
			imageUrls = TextFileHelper.getTextByLine(resFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < imageUrls.length; i++)
		{
			  BufferedImage bi = (BufferedImage) getImageFromUrl(imageUrls[i]);
			    File outputfile = new File("saved.png");
			    try {
					ImageIO.write(bi, "png", outputfile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public Image getImageFromUrl(String urlS) 
	{
		
		Image image = null;
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
	@Override
	public Image getScrub() 
	{
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("saved.png"));
		} catch (IOException e) {
		}
		return img;
	}

	

}
