package game;

import java.net.UnknownHostException;

import networking.packets.clientPackets.hostPackets.*;

public class HostPlayer extends Player{
	
	public HostPlayer() throws UnknownHostException{
		super();
	}
	
	//needs to be changed for names
	public Game create(String roomName, String hostName, double[] address){
		CreateRoomPacket packet = new CreateRoomPacket();
		packet.putRoomName(roomName);
		packet.putHostName(hostName);
		packet.putMACAddress(address);
		client.sendPacket(packet);
		//wait for reply and return game object
		return null;
	}
	
	public void close(){
		client.sendPacket(new CloseRoomPacket());
	}
	
	public void useAbility(byte[] ability) {
		// TODO Auto-generated method stub
		
	}

	public void captureTarget() {
		// TODO Auto-generated method stub
		
	}

	public void playerCaptured() {
		// TODO Auto-generated method stub
		
	}

	public void join(String roomKey) {
		// TODO Auto-generated method stub
		
	}

	public void ready() {
		// TODO Auto-generated method stub
		
	}

	public void quit() {
		// TODO Auto-generated method stub
		
	}

	public void vote(byte[] data) {
		// TODO Auto-generated method stub
		
	}

}
