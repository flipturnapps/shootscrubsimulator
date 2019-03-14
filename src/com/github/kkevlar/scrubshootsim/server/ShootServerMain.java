package com.github.kkevlar.scrubshootsim.server;
import java.io.IOException;

public class ShootServerMain
{
	
	public static int getSpecialInt()
	{
		double d = Math.random();
		if(d < .98)
		{
			return 0;
		}
		else if(d < .99)
		{
			return 1;
		}
		else
		{
			return 2;
		}
	}
	
    public static void main (String[] args) throws IOException 
    {

        ShootServer s = new ShootServer(25567);
        PlayerUpdater u = new PlayerUpdater(s);
        
        try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int i = 0;
        while (true)
        {
        	String scrubString = String.format("add:%d,%d~%d~%d", 
        			(int) (Math.random() * 550),
        			(int) (Math.random() * 550),
        			getSpecialInt(),
        			i++);
        	s.sentToAll(scrubString);
        	try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
		/*
		 * while (true) { for (String key : s.getRooms().keySet()) { Room room =
		 * s.getRooms().get(key); if(!room.isHasPrintedNominations() &&
		 * System.currentTimeMillis() - room.getStartTime() > 45000) {
		 * 
		 * if (room.checkSuggestion()) room.printNominations(); else {
		 * room.noSuggestions(); room.resetSuggestionPhase(); } }
		 * 
		 * if(room.isHasPrintedNominations() && !room.isHasPrintedFinal() &&
		 * System.currentTimeMillis() - room.getStartTime() > 45000) {
		 * room.printVoteResult(); } if (room.isFinished()) { room.closeRoom();
		 * s.getRooms().remove(room.getKey()); } } }
		 */

    }


}
