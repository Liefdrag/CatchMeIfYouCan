package networking.client.threads;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import networking.packets.Packet;

/**
 * This class is just used for testing right now.
 * Will be altered to send packets rather than messages
 * sent from the command line.
 */
public class ClientOutput implements Runnable {

	private Socket clientSocket;
	private LinkedList<Packet> packetQueue;

	public ClientOutput(Socket clientSocket) throws UnknownHostException{
		this.clientSocket = clientSocket;
		this.packetQueue = new LinkedList<Packet>();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				int queueSize;
				if ((queueSize = packetQueue.size()) > 0) {
					for (int i = 0; i < queueSize; i++) {
						clientSocket.getOutputStream().write(packetQueue.pop().getPacket());
					}
				}
				Thread.sleep(10);
			} catch (IOException | InterruptedException e) {
				break;
			}	
		}
	}
	
	public void addPacketToQueue(Packet serverPacket){
		packetQueue.add(serverPacket);
	}
}
