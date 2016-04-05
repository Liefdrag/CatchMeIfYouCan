package networking.packets.serverPackets;

import networking.packets.Packet;

public class CaughtPacket extends Packet {

	public CaughtPacket() {
		putByte(Packet.CAUGHT);
	}
}
