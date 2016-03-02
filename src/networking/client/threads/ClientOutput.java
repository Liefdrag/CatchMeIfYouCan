package networking.client.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

import networking.packets.ClientPacket;

/**
 * This class is just used for testing right now.
 * Will be altered to send packets rather than messages
 * sent from the command line.
 */
public class ClientOutput implements Runnable {
	
	private LinkedList<ClientPacket> packetQueue;
	private InetAddress serverAddress;
	private int serverPort;

	public ClientOutput(String serverAddress, int serverPort) throws UnknownHostException{
		this.serverAddress = InetAddress.getByName(serverAddress);
		this.serverPort = serverPort;
		this.packetQueue = new LinkedList<ClientPacket>();
	}
	
	@Override
	public void run() {
		if (packetQueue.size() > 0)
		{
			for (ClientPacket clientPacket : packetQueue){
				try {
					@SuppressWarnings("resource")
					DatagramSocket socket = new DatagramSocket();
					DatagramPacket packet = new DatagramPacket(clientPacket.bytes, clientPacket.bytes.length, 
					                                serverAddress, serverPort);
					socket.send(packet);
				} catch (IOException e) {
				}
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Sends a message to the server. This is currently just
	 * for testing, will be changed to send packets.
	 */
	public void SendMessage(String userInput) {
	}
}
