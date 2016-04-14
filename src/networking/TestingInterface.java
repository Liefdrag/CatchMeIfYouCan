package networking;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.CatchMeIfYouCanMain;

import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import networking.packets.*;
import networking.packets.clientPackets.*;
import networking.packets.clientPackets.hostPackets.*;

/**
 * A graphical interface to send packets to the server (to debug and test the server) and to
 * print responses
 * 
 * @author James
 *
 */

public class TestingInterface implements ActionListener {

	private JButton[] clientPackets = new JButton[9];
	private JButton[] hostPackets = new JButton[12];
	public static JTextArea ta= new JTextArea();
	private static JScrollPane jsp = new JScrollPane(ta);
	private JPanel contentPane;
	public JFrame frame;

	/**
	 * Create the frame.
	 */
	public TestingInterface() {
		frame = new JFrame("Testing Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		initialize();
	}
	
	private void initialize(){
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{144, 144, 144, 0};
		gbl_contentPane.rowHeights = new int[]{290, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel serv = new JLabel("Client packets");
		GridBagConstraints gbc_serv = new GridBagConstraints();
		gbc_serv.anchor = GridBagConstraints.NORTH;
		gbc_serv.fill = GridBagConstraints.HORIZONTAL;
		gbc_serv.insets = new Insets(0, 0, 0, 5);
		gbc_serv.gridx = 0;
		gbc_serv.gridy = 0;
		contentPane.add(serv, gbc_serv);
		for(int i = 0; i < 9; i++){
			clientPackets[i] = new JButton(""+i);
			clientPackets[i].setPreferredSize(new Dimension(70, 70));
			clientPackets[i].addActionListener(this);
			gbc_serv.gridy = i + 1;
			contentPane.add(clientPackets[i], gbc_serv);
		}
		
		JLabel lobinf = new JLabel("Host packets");
		GridBagConstraints gbc_lobinf = new GridBagConstraints();
		gbc_lobinf.anchor = GridBagConstraints.NORTH;
		gbc_lobinf.fill = GridBagConstraints.HORIZONTAL;
		gbc_lobinf.insets = new Insets(0, 0, 0, 5);
		gbc_lobinf.gridx = 1;
		gbc_lobinf.gridy = 0;
		contentPane.add(lobinf, gbc_lobinf);
		for(int i = 0; i < 12; i++){
			hostPackets[i] = new JButton(""+i);
			hostPackets[i].setPreferredSize(new Dimension(70, 70));
			hostPackets[i].addActionListener(this);
			gbc_lobinf.gridy = i + 1;
			contentPane.add(hostPackets[i], gbc_lobinf);
		}
		
		ta.setEditable(false);
		ta.setWrapStyleWord(true);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		contentPane.add(jsp, gbc_scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == clientPackets[0]){
			LocationPacket lp = new LocationPacket();
			lp.putLocation(456, 13045);
			CatchMeIfYouCanMain.sendPacket(lp);
		} else if(e.getSource() == clientPackets[1]){
			CatchPerformedPacket cpp = new CatchPerformedPacket();
			CatchMeIfYouCanMain.sendPacket(cpp);
		} else if(e.getSource() == clientPackets[2]){
			CapturedPacket cp = new CapturedPacket();
			CatchMeIfYouCanMain.sendPacket(cp);
		} else if(e.getSource() == clientPackets[3]){
			AbilityUsagePacket aup = new AbilityUsagePacket();
			aup.putAbility((byte)0x01);
			CatchMeIfYouCanMain.sendPacket(aup);
		} else if(e.getSource() == clientPackets[4]){
			VotePacket vp = new VotePacket();
			vp.putVote((byte)0x01);
			CatchMeIfYouCanMain.sendPacket(vp);
		} else if(e.getSource() == clientPackets[5]){
			ReportPacket rp = new ReportPacket();
			rp.report(1);
			CatchMeIfYouCanMain.sendPacket(rp);
		} else if(e.getSource() == clientPackets[6]){
			QuitPacket qp = new QuitPacket();
			CatchMeIfYouCanMain.sendPacket(qp);
		} else if(e.getSource() == clientPackets[7]){
			JoinPacket jp = new JoinPacket();
			jp.putRoomKey("TEST");
			jp.putMACAddress(new double[]{12,12,12,12,12,12});
			jp.putPlayerName("Alan");
			CatchMeIfYouCanMain.sendPacket(jp);
		} else if(e.getSource() == clientPackets[8]){
			ACKPacket ackp = new ACKPacket();
			CatchMeIfYouCanMain.sendPacket(ackp);
		}
		//host
		else if(e.getSource() == hostPackets[0]){
			StartRoundPacket srp = new StartRoundPacket();
			CatchMeIfYouCanMain.sendPacket(srp);
		} else if(e.getSource() == hostPackets[1]){
			EndRoundPacket erp = new EndRoundPacket();
			CatchMeIfYouCanMain.sendPacket(erp);
		} else if(e.getSource() == hostPackets[2]){
			KickPlayerPacket kpp = new KickPlayerPacket();
			kpp.putPlayerID(1);
			CatchMeIfYouCanMain.sendPacket(kpp);
		} else if(e.getSource() == hostPackets[3]){
			CreateRoomPacket crp = new CreateRoomPacket();
			crp.putRoomName("Debug");
			crp.putHostName("Steve");
			crp.putMACAddress(new double[]{12,12,12,12,12,12});
			CatchMeIfYouCanMain.sendPacket(crp);
		} else if(e.getSource() == hostPackets[4]){
			CloseRoomPacket crp = new CloseRoomPacket();
			CatchMeIfYouCanMain.sendPacket(crp);
		} else if(e.getSource() == hostPackets[5]){
			TimeLimitPacket tlp = new TimeLimitPacket();
			tlp.putTimeLimit(4500);
			CatchMeIfYouCanMain.sendPacket(tlp);
		} else if(e.getSource() == hostPackets[6]){
			ScoreLimitPacket slp = new ScoreLimitPacket();
			slp.putScoreLimit(8);
			CatchMeIfYouCanMain.sendPacket(slp);
		} else if(e.getSource() == hostPackets[7]){
			GametypePacket gtp = new GametypePacket();
			gtp.putGameType((byte)0x01);
			CatchMeIfYouCanMain.sendPacket(gtp);
		} else if(e.getSource() == hostPackets[8]){
			SetBoundariesPacket sbp = new SetBoundariesPacket();
			sbp.putBoundaries(10401, 40104, 500);
			CatchMeIfYouCanMain.sendPacket(sbp);
		} else if(e.getSource() == hostPackets[9]){
			BoundaryUpdatesPacket bup = new BoundaryUpdatesPacket();
			bup.putBoundaryUpdates(300, 15);
			CatchMeIfYouCanMain.sendPacket(bup);
		} else if(e.getSource() == hostPackets[10]){
			ChangeHostPacket chp = new ChangeHostPacket();
			chp.putNewHostID(2);
			CatchMeIfYouCanMain.sendPacket(chp);
		} else if(e.getSource() == hostPackets[11]){
			AllowVotingPacket avp = new AllowVotingPacket();
			CatchMeIfYouCanMain.sendPacket(avp);
		}
	}
	
}
