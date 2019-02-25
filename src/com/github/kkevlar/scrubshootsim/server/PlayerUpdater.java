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
			if(System.currentTimeMillis() - last > 1000)
			{
				last = System.currentTimeMillis();
				String comb = "";
				for(Player player : server.getPlayers())
				{
					comb += player.getPos() + "~";
				}
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
