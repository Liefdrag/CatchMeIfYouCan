package cmiyc.catchmegui2.networking.packets.serverPackets.broadcastPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class BoundaryUpdatePacket extends Packet {

	public BoundaryUpdatePacket(){
		putByte(Packet.BROADCAST);
		putByte(Packet.BROADCAST_BOUNDARY_UPDATE);
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