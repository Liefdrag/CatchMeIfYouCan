package main;
import networking.client.Client;
import networking.packets.Packet;

public class CatchMeIfYouCanMain {

	public static Client test;
	
	public static void main(String[] args) {
		test = new Client("localhost", 10401);
	}
	
	public static void sendPacket(Packet packet) {
		test.sendPacket(packet);
	}

}
