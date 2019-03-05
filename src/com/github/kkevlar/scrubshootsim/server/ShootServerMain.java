package com.github.kkevlar.scrubshootsim.server;
import java.io.IOException;

public class ShootServerMain
{
    public static void main (String[] args) throws IOException 
    {

        ShootServer s = new ShootServer(25567);
        PlayerUpdater u = new PlayerUpdater(s);
        
        try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        s.sentToAll("add:100,100~2~0");
        
        
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
