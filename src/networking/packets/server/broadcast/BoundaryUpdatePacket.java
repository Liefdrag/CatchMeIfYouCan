package networking.packets.server.broadcast;

import networking.packets.ServerPacket;

public class BoundaryUpdatePacket extends ServerPacket {

	public BoundaryUpdatePacket(){
		putByte(BROADCAST);
		putByte(BROADCAST_BOUNDARY_UPDATE);
	}
	
	public BoundaryUpdatePacket(byte[] data){
		packet = data;
	}
	
	public void putBoundaryUpdate(double longitude, double latitude, int radius){
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