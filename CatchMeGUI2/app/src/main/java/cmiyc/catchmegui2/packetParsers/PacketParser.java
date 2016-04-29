package cmiyc.catchmegui2.packetParsers;

import java.util.Arrays;

import cmiyc.catchmegui2.Home;
import cmiyc.catchmegui2.game.Game;
import cmiyc.catchmegui2.game.HostPlayer;
import cmiyc.catchmegui2.game.Player;
import cmiyc.catchmegui2.networking.packets.*;
import cmiyc.catchmegui2.networking.packets.serverPackets.*;

public class PacketParser {
	
	private String roomKey; //Used to determine which room to perform changes to
	private final BroadcastPacketParser broadcastParser;
	private final LobbyInfoPacketParser lobbyInfoParser;
	private Game game; // will be retrieved from Player/HostPlayer instance from the client
	//private Player player;

	public PacketParser(/*Player player*/) {
		roomKey = "";
		//this.player = player;
		this.game = null;
		broadcastParser = new BroadcastPacketParser(game);
		lobbyInfoParser = new LobbyInfoPacketParser(game);
	}
	
	/**
	 * Method for processing client packets
	 * @param packet - Byte data of the packet
	 */
	public void processPacket(byte[] packet) {
		byte dataID = packet[0]; //First byte is the host packet ID
		packet = Arrays.copyOfRange(packet, 1, packet.length); //Removes the ID from the packet so only data is left
		//Each Case is a Protocol
		switch (dataID) {
		
		//Location of the Client
		case Packet.LOCATION :
			LocationPacket locationPacket = new LocationPacket(packet);
            if(locationPacket.getID() == Home.player.getGame().getTargetID()){
                Home.player.getGame().setTargetLocation(locationPacket.getLocation());
            }
			//Gets the Location as Coordinates and the Client ID (If its single player then the ID would correspond to target ID
			//If its a team mode, then the client would be able to choose which ID is his target
			break;
		
		case Packet.PING :
			PingPacket pp = new PingPacket();
			Home.player.getClient().sendPacket(pp);
			//Sends the packet back to the Server
			break;
		
		case Packet.BROADCAST :
			broadcastParser.processBroadcast(packet);
			break;
		
		case Packet.TARGET :
			TargetPacket p = new TargetPacket(packet);
            Home.player.getGame().setTarget(p.getTargetID());
			//Sets what the ID of the target is (All players locations are broadcast to everyone and so this ID chooses the ID
			break;
				
		case Packet.ABILITY_ACTION :
			AbilityUsagePacket abilityUsage = new AbilityUsagePacket(packet);
			abilityUsage.getAbility();
			// do something with ability
			break;
				
		case Packet.GAME_START :
            Home.player.getGame().startGame();
            //GameLeaderboardActivity.current = false;

			break;
			
		case Packet.GAME_END :
            Home.player.getGame().endGame();
			break;
			
		case Packet.ROOM_CLOSE :
            Home.player.getGame().closeRoom();
			break;
		
		case Packet.ROOM_KEY :
			RoomKeyPacket rkp = new RoomKeyPacket(packet);
			String roomKey = rkp.getRoomKey();
			this.setRoomKey(roomKey);
            Home.player.setRoomKey(roomKey);
			String playerName = Home.player.getPlayerName();
			game = new Game(roomKey, playerName, true);
            Home.player.setGame(game);
            broadcastParser.setGame(game);
            lobbyInfoParser.setGame(game);
			break;
		
		case Packet.LOBBYINFO :
			lobbyInfoParser.processLobbyInfo(dataID, packet);
			break;
			
		case Packet.KICK :
			KickPacket kickPacket = new KickPacket(packet);
			kickPacket.getKickReason(); //Dialog box on Home screen (return to screen)
			Home.player = null;
			break;
				
		case Packet.NAK :
            NAKPacket nak = new NAKPacket(packet);
            byte nakType = nak.getNAKID();
            switch (nakType){
                case Packet.NAK_INVALID_ROOM_KEY:
                    Home.player.joinRoomFail("KEY");
                    break;
                case Packet.NAK_NOT_ENOUGH_PLAYERS:
                    //host??
                    break;
                case Packet.NAK_ROOM_FULL:
                    Home.player.joinRoomFail("FULL");
                    break;
                case Packet.NAK_NO_VALID_TARGETS:
                    //??
                    break;
                case Packet.NAK_ROOM_IN_GAME:
                    Home.player.joinRoomFail("INGAME");
                    break;
                default:
                    break;
            }
			//determine what the NAK is, i.e. wrong key, not enough players, game full/in progress.
			break;
			
		case Packet.HOST :
			Home.player.getGame().setHost();
            Home.player = new HostPlayer(Home.player.getPlayerName(), Home.player.getClient(), game);
			// will this work?
            // probably have to switch the gui to the host view (for the host options, not sure
            // what it's called) and pass a new HostPlayer object to it with the client and game
			break;
			
		case Packet.CAUGHT :
            Home.player.catchAttempt();
			break;
		
		case Packet.JOIN_SUCCESS :
            JoinSuccessPacket jsp = new JoinSuccessPacket(packet);
			String name = Home.player.getPlayerName();
            Home.player.setID(jsp.getPlayerID());
            if(!Home.player.isHost()) {
			    game = new Game(this.roomKey, name, false);
                Home.player.joinRoom();
            }
			break;

            case Packet.CATCH_SUCCESS :
               CatchSuccessPacket csp = new CatchSuccessPacket(packet);
                byte success = csp.getSuccess();
                if(success == (byte) 0x00){
                    Home.player.catchSuccess(false);
                } else {
                    Home.player.catchSuccess(true);
                }

                break;
			
		default : 
			String bytes = dataID + " | ";
			for (int i = 0; i < packet.length; i ++) {
				bytes += packet[i] + " | ";
			}
			System.err.println("Unrecognised packet: \"" + bytes +
					" in room: " + this.roomKey);
			break;
		}
	}
	
	/**
	 * Method for setting the room key for both the client packet parser
	 * and the host action packet parser
	 * @param key - The Room Key
	 */
	public void setRoomKey(String key) {
		roomKey = key;
	}

    public void setGame(Game game){
        this.game = game;
    }

}
