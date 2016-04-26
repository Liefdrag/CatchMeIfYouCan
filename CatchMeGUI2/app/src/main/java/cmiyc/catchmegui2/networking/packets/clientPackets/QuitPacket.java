package cmiyc.catchmegui2.networking.packets.clientPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class QuitPacket extends Packet {
	
	public QuitPacket(){
		putByte(Packet.QUIT);
	}
	
}