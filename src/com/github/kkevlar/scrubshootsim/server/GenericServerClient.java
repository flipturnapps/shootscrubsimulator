package com.github.kkevlar.scrubshootsim.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class GenericServerClient implements Runnable
{
    private ShootServer server;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private boolean isConnected = true;

    public GenericServerClient(ShootServer server, Socket s) throws IOException
    {
        this.socket = s;
        this.server = server;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
        new Thread(this).start();
    }
    
    public abstract void clientInit();

    public void run()
    {
        String s = null;
        clientInit();
        while (isConnected)
        {
            try {
                s = reader.readLine();
            } catch (IOException e) {
              this.isConnected = false;
                this.disconnect();
                	System.out.println("aaa disconnect");
            }

            if (s != null)
            {
                incomingMessage(s);
            }
        }
    }

    public Socket getSocket()
    {
        return socket;
    }
    
    public abstract void incomingMessage(String read);

    public void disconnect()
    {
        try {
        	clientDisconnected();
            writer.close();
            reader.close();
            socket.close();
            isConnected = false;
        }
        catch(Exception ex)
        {

        }
    }

    public abstract void clientDisconnected();

	public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public PrintWriter getWriter() {
        return writer;
    }
}
