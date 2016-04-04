package networking.packets.clientPackets;

import networking.packets.Packet;

public class QuitPacket extends Packet {
	
	public QuitPacket(){
		putByte(Packet.QUIT);
	}
	
}