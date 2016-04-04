package networking.client.threads;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.Arrays;

import networking.packets.Packet;

/**
 * This class receives input from the server and processes it. Right now it uses
 * messages but will be altered to receive packets.
 */
public class ClientInput implements Runnable {

	private Socket clientSocket;

	public ClientInput(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try {
			int read = -1;
			byte[] temp = new byte[512];
			while ((read = clientSocket.getInputStream().read(temp, 0, temp.length)) > -1) {
				byte[] bytes = Arrays.copyOfRange(temp, 0, read);
				// Process packet
			}
		} catch (IOException e) {
			System.err.print(e.toString());
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
