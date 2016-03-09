package networking.packets.server.lobbyinfo;

import networking.packets.ServerPacket;

public class GametypePacket extends ServerPacket {

	public GametypePacket() {
		putByte(LOBBYINFO);
		putByte(LOBBYINFO_GAMETYPE);
	}

	public GametypePacket(byte[] data) {
		packet = data;
	}

	public void putGametype(byte gametype) {
		putByte(gametype);
	}

	public byte getGametype() {
		return getByte();
	}

}