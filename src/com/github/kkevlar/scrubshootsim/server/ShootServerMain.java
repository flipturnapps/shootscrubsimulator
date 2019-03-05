package com.github.kkevlar.scrubshootsim.server;
import java.io.IOException;

public class ShootServerMain
{
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
        			(int) (Math.random() * 600),
        			(int) (Math.random() * 600),
        			Math.random() > .25 ? 0 : 2,
        			i++);
        	s.sentToAll(scrubString);
        	try {
				Thread.sleep(500);
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
