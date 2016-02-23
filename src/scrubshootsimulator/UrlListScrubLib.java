package scrubshootsimulator;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.flipturnapps.kevinLibrary.helper.FileHelper;
import com.flipturnapps.kevinLibrary.helper.TextFileHelper;

public class UrlListScrubLib implements ScrubLibrary
{
	private static final String NOPE = "http://vignette2.wikia.nocookie.net/thefutureofeuropes/images/5/5d/Nope.png/revision/latest?cb=20140916154703";
	private static final boolean LOAD_INTO_RAM = false;
	private File outputDir;
	private Image[] scrubs;
	private int count;
	private boolean failure;
	public UrlListScrubLib(File inputFile)
	{
		this.readUrlsFromFile(inputFile);
		//count = 5;
		//outputDir = new File(FileHelper.fileInDir(FileHelper.getAppDataDir("flipturnapps", "shootscrubsimulator"), inputFile.getName().replace('.', '-')));
		
	}
	private void readUrlsFromFile(File resFile)
	{
		this.failure = true;
		String[] imageUrls = null;
		try {
			imageUrls = TextFileHelper.getTextByLine(resFile);
		} catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Failure reading image input file.");
			return;
		}
		outputDir = new File(FileHelper.fileInDir(FileHelper.getAppDataDir("flipturnapps", "shootscrubsimulator"), resFile.getName().replace('.', '-')));
		if(!outputDir.exists())
			outputDir.mkdirs();
		this.count = imageUrls.length;
		if(LOAD_INTO_RAM)
			scrubs = new Image[imageUrls.length];
		for(int i = 0; i < imageUrls.length; i++)
		{
			  BufferedImage bi = getImageFromUrl(imageUrls[i]);
			    File outputfile = getImageFile(i);
			    try 
			    {
					ImageIO.write(bi, "png", outputfile);
				} catch (IOException e) 
			    {
					e.printStackTrace();
					System.out.println("Failure writing the output files.");
					return;
				}
			    if(LOAD_INTO_RAM)
			    	scrubs[i] = bi;
		}
		this.failure = false;
	}
	private File getImageFile(int i)
	{
		File outputfile = new File(FileHelper.fileInDir(outputDir, i+".png.sss"));
		return outputfile;
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
	@Override
	public Image getScrub() 
	{
		if(failure)
			return getImageFromUrl(NOPE);
		int i = (int) (Math.random() * count);
		if(LOAD_INTO_RAM)
			return scrubs[i];
		else
		{
			BufferedImage img = null;
			try {
				img = ImageIO.read(this.getImageFile(i));
			} catch (IOException e) {
			}
			return img;
		}
	}

	

}
