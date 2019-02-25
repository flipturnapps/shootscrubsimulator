package com.github.kkevlar.scrubshootsim.server;

public class PlayerUpdater implements Runnable {

	private ShootServer server;
	
	public PlayerUpdater(ShootServer server)
	{
		this.server = server;
		new Thread(this).start();
	}
	
	@Override
	public void run() 
	{
		long last = System.currentTimeMillis();
		while(true)
		{
			if(System.currentTimeMillis() - last > 100)
			{
				last = System.currentTimeMillis();
				String comb = "";
				for(Player player : server.getPlayers())
				{
					comb += player.getPos() + "~";
				}
				if(comb.length() <= 0)
					continue;
				comb = comb.substring(0, comb.length()-1);
				for(Player player : server.getPlayers())
				{
					player.getWriter().println(comb);
					player.getWriter().flush();
				}
				System.out.println(comb);
			}
		}
	}

}
