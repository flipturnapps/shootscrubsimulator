package scrubshootsimulator;

public class ShootMain {

	public static void main(String[] args) 
	{
		ScrubFrame frame = new ScrubFrame();
		frame.setVisible(true);
		while(true)
		{
			frame.repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
