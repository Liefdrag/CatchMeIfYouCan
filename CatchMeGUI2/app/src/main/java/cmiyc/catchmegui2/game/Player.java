package cmiyc.catchmegui2.game;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import java.net.InetAddress;
import java.net.UnknownHostException;

import cmiyc.catchmegui2.CreateGameActivity;
import cmiyc.catchmegui2.networking.client.Client;
import cmiyc.catchmegui2.networking.packets.clientPackets.AbilityUsagePacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.CapturedPacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.CatchPerformedPacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.JoinPacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.PlayerReadyPacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.QuitPacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.ReportPacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.VotePacket;
import cmiyc.catchmegui2.packetParsers.PacketParser;


/**
 * Client -> Server methods
 */
public class Player {
	
	private Client client;
	protected Game game;
	protected String playerName;
	private String roomKey;
    private UpdateRoomKey urk;
    private JoinSuccessInterface jsi;
	
	public Player(String playerName) throws UnknownHostException {
		//change to address
		client = new Client(InetAddress.getLocalHost().toString(), 0, new PacketParser(/*this*/));
		game = null;
		this.playerName = playerName;
	}	
	
	public Player(String playerName, Client client, Game game){
		this.client = client;
		this.game = game;
		this.playerName = playerName;
	}
	
	public void create(String roomKey, String address){
		JoinPacket packet = new JoinPacket();
		packet.putRoomKey(roomKey);
		packet.putPlayerName(playerName);
		packet.putMACAddress(address);
		client.sendPacket(packet);
	}
	
	public void useAbility(byte ability) {
		AbilityUsagePacket packet = new AbilityUsagePacket();
		packet.putAbility(ability);
		client.sendPacket(packet);
	}
	
	public void captureTarget() {
		CatchPerformedPacket packet = new CatchPerformedPacket();
		client.sendPacket(packet);
	}
	
	public void playerCaptured() {
		CapturedPacket packet = new CapturedPacket();
		client.sendPacket(packet);
	}
	
	public void ready() {
		PlayerReadyPacket packet = new PlayerReadyPacket();
		client.sendPacket(packet);
	}
	
	public void quit() {
		QuitPacket packet = new QuitPacket();
		client.sendPacket(packet);
	}
	
	public void vote(byte vote) {
		VotePacket packet = new VotePacket();
		packet.putVote(vote);
		client.sendPacket(packet);
	}
	
	public void report(int player){
		ReportPacket packet = new ReportPacket();
		packet.report(player);
		client.sendPacket(packet);
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	public String getPlayerName(){
		return playerName;
	}

	public void setRoomKey(String key) {
        roomKey = key;
        for(int i = 0; i < 10; i++){
            if (urk != null){
                urk.updateRK(key);
                break;
            } else {
                try {
                    Thread.currentThread().sleep(70);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void joinRoom(){
        for(int i = 0; i < 10; i++){
            if (jsi != null){
               jsi.joinSuccess();
                break;
            } else {
                try {
                    Thread.currentThread().sleep(70);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void joinRoomFail(String reason){
        for(int i = 0; i < 10; i++){
            if (jsi != null){
                jsi.joinFailure(reason);
                break;
            } else {
                try {
                    Thread.currentThread().sleep(70);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


	public String getRoomKey() { return roomKey; }

    public void setURKInterface(UpdateRoomKey urk){
        this.urk = urk;
    }

	public Client getClient() {
		return client;
	}

    public void setJSInterface(JoinSuccessInterface jsi){
        this.jsi = jsi;
    }

}
