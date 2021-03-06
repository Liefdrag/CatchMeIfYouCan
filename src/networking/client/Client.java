package networking.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import networking.client.threads.ClientInput;
import networking.client.threads.ClientOutput;
import networking.packets.Packet;
import packetParsers.PacketParser;

/**
 * Client class. On creation of a Client() object, the program
 * will attempt to connect to the given address:port and initialise
 * input & output threads.
 */
public class Client {

	private ClientInput clientInput;
	private ClientOutput clientOutput;
	private PacketParser packetParser;
	
	public Client(String serverAddress, int serverPort, PacketParser packetParser) {
		this.packetParser = packetParser;
		try {
			connectToServer(serverAddress, serverPort);
		} catch (IOException e) {
			System.out.println("Could not connect to server " + serverAddress + " on port " + serverPort + "."); // Would send a message to the user indicating that there's a problem with the server
		}
	}
	
	public synchronized void sendPacket(Packet clientPacket) {
		clientOutput.addPacketToQueue(clientPacket);
	}
	
	public void connectToServer(String serverAddress, int serverPort) throws UnknownHostException, IOException{

		Socket socket = new Socket(InetAddress.getByAddress(new byte[]{(byte)138,(byte)38,(byte)241,(byte)195}), serverPort);
		// Creating the client input thread and starting it
		clientInput = new ClientInput(socket, packetParser);
		new Thread(clientInput).start();
		
		// Creating the client output thread and starting it
		clientOutput = new ClientOutput(socket);
		new Thread(clientOutput).start();
	}
	
}
