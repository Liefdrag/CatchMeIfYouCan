package cmiyc.catchmegui2.networking.packets.serverPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class HostPacket extends Packet {

	public HostPacket(){
		putByte(Packet.HOST);
	}
	
}