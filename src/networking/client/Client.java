package networking.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import networking.client.threads.ClientInput;
import networking.client.threads.ClientOutput;

/**
 * Client class. On creation of a Client() object, the program
 * will attempt to connect to the given address:port and initialise
 * input & output threads.
 */
public class Client {

	private ClientInput clientInput;
	private ClientOutput clientOutput;
	
	public Client(String serverAddress, int serverPort) {
		try {
			connectToServer(serverAddress, serverPort);
		} catch (IOException e) {
			System.out.println("Could not connect to server " + serverAddress + " on port " + serverPort + "."); // Would send a message to the user indicating that there's a problem with the server
		}
	}
	
	public void connectToServer(String serverAddress, int serverPort) throws UnknownHostException, IOException{

		Socket socket = new Socket(serverAddress, serverPort);
		// Creating the client input thread and starting it
		clientInput = new ClientInput(socket);
		new Thread(clientInput).start();
		
		// Creating the client output thread and starting it
		clientOutput = new ClientOutput(serverAddress, serverPort);
		new Thread(clientOutput).start();
	}
	
}
