package networking.client.threads;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import networking.TestingInterface;
import networking.packets.GenericPacket;
import networking.packets.Packet;
import packetParsers.PacketParser;

/**
 * This class receives input from the server and processes it. Right now it uses
 * messages but will be altered to receive packets.
 */
public class ClientInput implements Runnable {

	private Socket clientSocket;
	private PacketParser packetParser;

	public ClientInput(Socket clientSocket, PacketParser packetParser) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestingInterface window = new TestingInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		this.clientSocket = clientSocket;
		this.packetParser = packetParser;
	}

	@Override
	public void run() {
		try {
			int read = -1;
			byte[] temp = new byte[512];
			while ((read = clientSocket.getInputStream().read(temp, 0, temp.length)) > -1) {
				byte[] bytes = Arrays.copyOfRange(temp, 0, read);
				Packet packet = new GenericPacket(bytes);
				TestingInterface.ta.append(packet.toString() + "\n------------------------\n");
				packetParser.processPacket(bytes);
				temp = new byte[512];
			}
		} catch (IOException e) {
			System.err.print(e.toString());
			return;
		}
	}

}
