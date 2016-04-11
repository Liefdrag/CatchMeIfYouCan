package networking;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A graphical interface to send packets to the server (to debug and test the server) and to
 * print responses
 * 
 * @author James
 *
 */

public class TestingInterface implements ActionListener {

	private JButton[] clientPackets = new JButton[12];
	private JButton[] hostPackets = new JButton[12];
	public static JTextArea ta= new JTextArea();
	private JPanel contentPane;
	public JFrame frame;

	/**
	 * Create the frame.
	 */
	public TestingInterface() {
		frame = new JFrame("Testing Interface");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		initialize();
	}
	
	private void initialize(){
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{144, 144, 144, 144, 0};
		gbl_contentPane.rowHeights = new int[]{290, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0};
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
		for(int i = 0; i < 12; i++){
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
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 0;
		contentPane.add(ta, gbc_scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == clientPackets[0]){
			
		} else if(e.getSource() == clientPackets[1]){
			
		} else if(e.getSource() == clientPackets[2]){
			
		} else if(e.getSource() == clientPackets[3]){
			
		} else if(e.getSource() == clientPackets[4]){
			
		} else if(e.getSource() == clientPackets[5]){
			
		} else if(e.getSource() == clientPackets[6]){
			
		} else if(e.getSource() == clientPackets[7]){
			
		} else if(e.getSource() == clientPackets[8]){
			
		} else if(e.getSource() == clientPackets[9]){
			
		} else if(e.getSource() == clientPackets[10]){
			
		} else if(e.getSource() == clientPackets[11]){
			
		} else if(e.getSource() == clientPackets[12]){
			
		} else if(e.getSource() == hostPackets[0]){
			
		} else if(e.getSource() == hostPackets[1]){
			
		} else if(e.getSource() == hostPackets[2]){
			
		} else if(e.getSource() == hostPackets[3]){
			
		} else if(e.getSource() == hostPackets[4]){
			
		} else if(e.getSource() == hostPackets[5]){
			
		} else if(e.getSource() == hostPackets[6]){
			
		}
	}
	
}
