package com.github.kkevlar.scrubshootsim.server;
import java.io.IOException;

public class ShootServerMain
{
    public static void main (String[] args) throws IOException 
    {

        ShootServer s = new ShootServer(20000);
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
