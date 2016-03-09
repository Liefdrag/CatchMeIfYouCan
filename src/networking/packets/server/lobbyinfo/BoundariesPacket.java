package networking.packets.server.lobbyinfo;

import networking.packets.ServerPacket;

public class BoundariesPacket extends ServerPacket {

	public BoundariesPacket(){
		putByte(LOBBYINFO);
		putByte(LOBBYINFO_BOUNDARIES);
	}
	
	public BoundariesPacket(byte[] data){
		packet = data;
	}
	
	public void putBoundaries(double longitude, double latitude, int radius){
		putDouble(longitude);
		putDouble(latitude);
		putInt(radius);
	}
	
	public double[] getBoundariesCentre(){
		return new double[]{getDouble(), getDouble()};
	}
	
	public int getBoundariesRadius(){
		return getInt();
	}
	
}