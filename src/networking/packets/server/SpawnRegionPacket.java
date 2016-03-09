package networking.packets.server;

import networking.packets.ServerPacket;

public class SpawnRegionPacket extends ServerPacket {

	public SpawnRegionPacket(){
		putByte(SPAWN_REGION);
	}
	
	public SpawnRegionPacket(byte[] data){
		packet = data;
	}
	
	public void putSpawnPoint(double longitude, double latitude){
		putDouble(longitude);
		putDouble(latitude);
	}
	
	public double[] getSpawnPoint(){
		return new double[]{getDouble(), getDouble()};
	}
	
}