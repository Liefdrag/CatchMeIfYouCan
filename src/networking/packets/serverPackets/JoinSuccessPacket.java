package networking.packets.serverPackets;

import networking.packets.Packet;

public class JoinSuccessPacket extends Packet {
	
	public JoinSuccessPacket(){
		putByte(Packet.JOIN_SUCCESS);
	}
	
}
