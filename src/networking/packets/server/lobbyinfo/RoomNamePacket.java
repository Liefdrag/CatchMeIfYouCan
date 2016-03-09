package networking.packets.server.lobbyinfo;

import networking.packets.ServerPacket;

public class RoomNamePacket extends ServerPacket {

	public RoomNamePacket() {
		putByte(LOBBYINFO);
		putByte(LOBBYINFO_ROOM_NAME);
	}

	public RoomNamePacket(byte[] data) {
		packet = data;
	}

	public void putRoomName(String name) {
		putString(name);
	}

	public String getRoomName() {
		return getString();
	}

}