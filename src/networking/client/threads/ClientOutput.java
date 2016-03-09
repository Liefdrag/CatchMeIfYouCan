package networking.client.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import networking.packets.ClientPacket;
import networking.packets.Packet;

/**
 * This class is just used for testing right now.
 * Will be altered to send packets rather than messages
 * sent from the command line.
 */
public class ClientOutput implements Runnable {

	private Socket clientSocket;
	private LinkedList<ClientPacket> packetQueue;

	public ClientOutput(Socket clientSocket) throws UnknownHostException{
		this.clientSocket = clientSocket;
		this.packetQueue = new LinkedList<ClientPacket>();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				int queueSize;
				if ((queueSize = packetQueue.size()) > 0) {
					for (int i = 0; i < queueSize; i++) {
						clientSocket.getOutputStream().write(packetQueue.pop().bytes);
					}
				}
				Thread.sleep(100);
			} catch (IOException | InterruptedException e) {
				break;
			}	
		}
	}

	/**
	 * Sends a message to the server. This is currently just
	 * for testing, will be changed to send packets.
	 */
	public void SendMessage(String userInput) {
	}
}
