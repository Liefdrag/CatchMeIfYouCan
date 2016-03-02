package networking.client.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * This class receives input from the server and processes it. Right now it uses
 * messages but will be altered to receive packets.
 */
public class ClientInput implements Runnable {

	private int serverPort;

	public ClientInput(int serverPort) {
		this.serverPort = serverPort;
	}

	@SuppressWarnings("resource")
	@Override
	public void run() {
		try {
			DatagramSocket socket = new DatagramSocket(serverPort);
			while (true) {
				byte[] buf = new byte[10];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				if(packet != null)
					processPacket(packet);
			}
		} catch (IOException e) {
			System.out.print(e.toString());
			return;
		}
	}

	/**
	 * Processes a received message from the server. All it does right now is
	 * print the message for testing, but will be changed to interpret packets
	 * from the server, probably passing the message to a state machine.
	 */
	private void processPacket(DatagramPacket packet) {
		// TODO Auto-generated method stub

	}

}
