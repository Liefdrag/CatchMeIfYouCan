package cmiyc.catchmegui2.networking.packets.serverPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class CaughtPacket extends Packet {

	public CaughtPacket() {
		putByte(Packet.CAUGHT);
	}
}
