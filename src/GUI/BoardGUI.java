package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cards.Hand;
import Game.PokerGame;
import Players.HoldEmPlayer;
import Players.Player;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JToggleButton;

public class BoardGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfSetCardsPlayers;
	private JTextField tfSetCardsBoard;
	private PokerGame pokerGame;
	private boolean omaha;
	private BoardPanel boardPanel;
//	private final int numJugadores = 10;
//	private JTextField[] tfPlayer;
//	private PlayerPanel[] panelPlayer;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardGUI frame = new BoardGUI(new PokerGame());
					frame.setVisible(true);
					frame.pack();
					frame.setSize(1025, 550);
					frame.setResizable(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BoardGUI(PokerGame pokerGame) {
		this.pokerGame = pokerGame;
		this.omaha = false;
//		this.panelPlayer = new PlayerPanel[numJugadores];
//		this.tfPlayer = new JTextField[numJugadores];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
//		JPanel panel = new JPanel();
		boardPanel = new BoardPanel();
//		

//		JPanel[] panelPlayer = new JPanel[10];
//		panelPlayer[0] = new JPanel();
//		panelPlayer[1] = new JPanel();
//		panelPlayer[2] = new JPanel();	
//		panelPlayer[3] = new JPanel();
//		panelPlayer[4] = new JPanel();	
//		panelPlayer[5] = new JPanel();	
//		panelPlayer[6] = new JPanel();		
//		panelPlayer[7] = new JPanel();		
//		panelPlayer[8] = new JPanel();		
//		panelPlayer[9] = new JPanel();
		

//		for (int i = 0; i < numJugadores; i++){
//			panelPlayer[i] = new PlayerPanel(); 
//			tfPlayer[i] = new JTextField();
//			tfPlayer[i].setEditable(false);
//			tfPlayer[i].setColumns(10);
//			tfPlayer[i].setVisible(false);
//		}
		
//		GroupLayout gl_panel = new GroupLayout(panel);
//		gl_panel.setHorizontalGroup(
//			gl_panel.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_panel.createSequentialGroup()
//					.addGap(58)
//					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
//						.addGroup(gl_panel.createSequentialGroup()
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addComponent(tfPlayer[3], GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
//						.addGroup(gl_panel.createSequentialGroup()
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addComponent(tfPlayer[2], GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
//							.addPreferredGap(ComponentPlacement.RELATED))
//						.addComponent(panelPlayer[3], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
//						.addComponent(panelPlayer[2], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
//					.addGap(53)
//					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//						.addGroup(gl_panel.createSequentialGroup()
//							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
//								.addComponent(panelPlayer[4], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
//								.addGroup(gl_panel.createSequentialGroup()
//									.addPreferredGap(ComponentPlacement.RELATED)
//									.addComponent(tfPlayer[4], 0, 0, Short.MAX_VALUE)))
//							.addPreferredGap(ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
//							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//								.addGroup(gl_panel.createSequentialGroup()
//									.addPreferredGap(ComponentPlacement.RELATED)
//									.addPreferredGap(ComponentPlacement.RELATED)
//									.addComponent(tfPlayer[5], 0, 0, Short.MAX_VALUE))
//								.addComponent(panelPlayer[5], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
//						.addGroup(gl_panel.createSequentialGroup()
//							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//								.addComponent(panelPlayer[1], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
//								.addGroup(gl_panel.createSequentialGroup()
//									.addPreferredGap(ComponentPlacement.RELATED)
//									.addComponent(tfPlayer[1], GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
//							.addGap(49)
//							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
//								.addComponent(panelPlayer[0], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
//								.addGroup(gl_panel.createSequentialGroup()
//									.addPreferredGap(ComponentPlacement.RELATED)
//									.addComponent(tfPlayer[0], GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
//							.addGap(46)
//							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//								.addGroup(gl_panel.createSequentialGroup()
//									.addPreferredGap(ComponentPlacement.RELATED)
//									.addComponent(tfPlayer[9], GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
//								.addComponent(panelPlayer[9], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))
//					.addGap(42)
//					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//						.addComponent(panelPlayer[8], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
//						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
//							.addGap(26)
//							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//								.addComponent(panelPlayer[7], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
//								.addGroup(gl_panel.createSequentialGroup()
//									.addPreferredGap(ComponentPlacement.RELATED)
//									.addComponent(tfPlayer[7], GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))))
//						.addGroup(gl_panel.createSequentialGroup()
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addComponent(tfPlayer[8], GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
//							.addGap(26))
//						.addGroup(gl_panel.createSequentialGroup()
//							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
//								.addGroup(gl_panel.createSequentialGroup()
//									.addPreferredGap(ComponentPlacement.RELATED)
//									.addComponent(tfPlayer[6], GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
//								.addComponent(panelPlayer[6], Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
//							.addGap(26)))
//					.addGap(64))
//		);
//		gl_panel.setVerticalGroup(
//			gl_panel.createParallelGroup(Alignment.TRAILING)
//				.addGroup(gl_panel.createSequentialGroup()
//					.addGap(22)
//					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//						.addComponent(panelPlayer[4], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
//						.addComponent(panelPlayer[5], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
//					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//						.addGroup(gl_panel.createSequentialGroup()
//							.addGap(28)
//							.addComponent(panelPlayer[3], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
//								.addComponent(tfPlayer[3], GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
//							.addGap(11)
//							.addComponent(panelPlayer[2], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
//								.addComponent(tfPlayer[2], GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
//						.addGroup(gl_panel.createSequentialGroup()
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
//								.addComponent(tfPlayer[4], GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
//						.addGroup(gl_panel.createSequentialGroup()
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
//								.addComponent(tfPlayer[5], GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//						.addComponent(panelPlayer[0], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
//						.addComponent(panelPlayer[9], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
//						.addComponent(panelPlayer[1], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
//							.addComponent(tfPlayer[9], GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
//						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
//							.addComponent(tfPlayer[0], GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
//						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
//							.addComponent(tfPlayer[1], GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
//					.addGap(44))
//				.addGroup(gl_panel.createSequentialGroup()
//					.addGap(77)
//					.addComponent(panelPlayer[6], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//						.addComponent(tfPlayer[6], GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
//					.addGap(8)
//					.addComponent(panelPlayer[7], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
//						.addComponent(tfPlayer[7], GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
//					.addGap(8)
//					.addComponent(panelPlayer[8], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
//						.addComponent(tfPlayer[8], GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
//					.addContainerGap(93, Short.MAX_VALUE))
//		)));
//		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 756, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 208, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(boardPanel, 0, 0, Short.MAX_VALUE)
					.addGap(0))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblAllRandom = new JLabel("All random:");
		lblAllRandom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNumberOfCards = new JLabel("Number of cards on Board:");
		
		JComboBox cbCardsOnBoard = new JComboBox();
		cbCardsOnBoard.setModel(new DefaultComboBoxModel(new String[] {"0", "3", "4", "5"}));
		
		
		JLabel lblNumberOfPlayers = new JLabel("Number of players:");
		
		JComboBox cbNumPlayers = new JComboBox();
		cbNumPlayers.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		
		JLabel lblSetPlayers = new JLabel("Set Players:");
		lblSetPlayers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblPlayer = new JLabel("Player:");
		
		JLabel lblSetCards = new JLabel("Set Cards:");
		
		tfSetCardsPlayers = new JTextField();
		tfSetCardsPlayers.setColumns(10);
		
		JComboBox cbPlayer = new JComboBox();
		cbPlayer.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		
		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					boardPanel.getPanelPlayer(cbPlayer.getSelectedIndex()).addPlayer
					(pokerGame.addPlayer(cbPlayer.getSelectedIndex() + 1, new Hand(tfSetCardsPlayers.getText())));
					boardPanel.changeVisibily(true, cbPlayer.getSelectedIndex());
				}
				catch (Exception e1){
					JOptionPane.showMessageDialog(new JFrame(), "La mano introducida no es valida o el jugador ya existe", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JButton btnRandom = new JButton("Random");
		btnRandom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				if (!omaha){
					boardPanel.getPanelPlayer(cbPlayer.getSelectedIndex()).addPlayer(pokerGame.addPlayer(cbPlayer.getSelectedIndex() + 1));
				}
				else {
					boardPanel.getPanelPlayer(cbPlayer.getSelectedIndex()).addPlayer(pokerGame.addOmahaPlayer(cbPlayer.getSelectedIndex() + 1));
				}
				
				boardPanel.changeVisibily(true, cbPlayer.getSelectedIndex());
				}
				catch (Exception e1){
					JOptionPane.showMessageDialog(new JFrame(), "El juagador ya existe", "Error", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		JLabel lblSetBoard = new JLabel("Set Board:");
		lblSetBoard.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label = new JLabel("Set Cards:");
		
		tfSetCardsBoard = new JTextField();
		tfSetCardsBoard.setColumns(10);
		
		JButton button = new JButton("Set");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					Hand hand = new Hand(tfSetCardsBoard.getText());
					pokerGame.addCommonCards(hand);
					boardPanel.addCommonCards(hand);
				}
				catch (Exception e1){
					JOptionPane.showMessageDialog(new JFrame(), "Las cartas comunes introducidas no son validas", "Error", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		JButton button_1 = new JButton("Random");
		
		
		JLabel lblNumberOfCards_1 = new JLabel("Number of cards on Board:");
		
		JComboBox cbSetCardsOnBoard = new JComboBox();
		cbSetCardsOnBoard.setModel(new DefaultComboBoxModel(new String[] {"0", "3", "4", "5"}));
		
		JLabel lblExecute = new JLabel("Execute:");
		lblExecute.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnClaculateEquity = new JButton("Claculate Equity");
		btnClaculateEquity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] info;
				if (omaha){
					info = pokerGame.processGameOmaha();
				}
				else {
					info = pokerGame.processGame();
				}
				for (int i = 0; i < info.length; i++){
					String[] parte = info[i].split(" ");
					Integer num = new Integer(parte[0].split("P")[1].toString()) - 1;
					boardPanel.gettfPlayer(num).setText(parte[1]);
				}
			}
		});
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		JSeparator separator_2 = new JSeparator();
		
		JButton btnRandom_1 = new JButton("Random");
		btnRandom_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer num = new Integer(cbSetCardsOnBoard.getSelectedItem().toString());
				addCommonCards(num);
				/*
				boolean hecho = false;
				if (pokerGame.getNumCommon() == 0){
					pokerGame.flop();
					hecho = true;
				}
				else if (pokerGame.getNumCommon() < 5){
					while (pokerGame.getNumCommon() < 3){
						pokerGame.addCommonCard();
						hecho = true;
					}
					if (!hecho){
						pokerGame.addCommonCard();
						hecho = true;
					}
				}
				if (hecho){
					boardPanel.addCommonCards(new Hand(pokerGame.getCommonCards()));
				}
				*/
			}
		});
		
		JButton btnFold = new JButton("Fold");
		btnFold.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if (pokerGame.foldPlayer(cbPlayer.getSelectedIndex() + 1, omaha)){
					boardPanel.gettfPlayer(cbPlayer.getSelectedIndex()).setText("");
					boardPanel.changeVisibily(false, cbPlayer.getSelectedIndex());
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(), "El jugador seleccionado no esta jugando", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer numCartasComunes = new Integer(cbCardsOnBoard.getSelectedItem().toString());
				addCommonCards(numCartasComunes);

				Integer numJugadores = new Integer(cbNumPlayers.getSelectedItem().toString());
				for (int i = 0; i <  numJugadores; i++){
					if (!omaha){
						Player player = pokerGame.addPlayer(i + 1);
						if (player != null){
							
							boardPanel.getPanelPlayer(i).addPlayer(player);
						}
					}	
					else {
						Player player = pokerGame.addOmahaPlayer(i + 1);
						if (player != null){

							boardPanel.getPanelPlayer(i).addPlayer(player);
						}
					}
					
					boardPanel.changeVisibily(true, i);
				}
			}
		});
		
		JSeparator separator_3 = new JSeparator();
		
		JButton cambioJuego = new JButton("Texas Hold\u00B4Em");
		cambioJuego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i =0; i < 10; i++){
					boardPanel.gettfPlayer(i).setText("");
					boardPanel.changeVisibily(false, i);			
				}
				pokerGame.reset();				
				if (!omaha){
					cambioJuego.setText("Omaha");
					omaha = true;
				}
				else {
					cambioJuego.setText("Texas Hold\u00B4Em");
					omaha = false;
				}
			}
		});
		
		
		JSeparator separator_4 = new JSeparator();
		
		JLabel lblJuego = new JLabel("Game:");
		lblJuego.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAllRandom, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(lblNumberOfCards)
											.addGap(18)
											.addComponent(cbCardsOnBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(lblNumberOfPlayers, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(cbNumPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(8)))
									.addGap(192))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(6)
									.addComponent(lblExecute, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnClaculateEquity)
									.addGap(252)
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnGenerate))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSetPlayers, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
									.addComponent(lblPlayer)
									.addGap(27)
									.addComponent(cbPlayer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnFold))
								.addComponent(separator, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addComponent(btnSet)
							.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
							.addComponent(btnRandom))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblSetCards, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfSetCardsPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(355))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSetBoard, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(395, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(tfSetCardsBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(360, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNumberOfCards_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbSetCardsOnBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(351, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnRandom_1)
					.addContainerGap(444, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(327, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(333, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblJuego, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cambioJuego, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(332, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAllRandom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfCards)
						.addComponent(cbCardsOnBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfPlayers)
						.addComponent(cbNumPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGenerate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSetPlayers, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayer)
						.addComponent(cbPlayer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFold))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSetCards)
						.addComponent(tfSetCardsPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSet)
						.addComponent(btnRandom))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSetBoard, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfSetCardsBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(8)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbSetCardsOnBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumberOfCards_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRandom_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(14)
							.addComponent(button_1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblExecute, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnClaculateEquity))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJuego, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(cambioJuego))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void addCommonCards(Integer numCartasComunes){
		boolean cambio = false;
		for (int i = pokerGame.getNumCommon(); i < numCartasComunes; i++){
			pokerGame.addCommonCard();
			cambio = true;
		}
		if(cambio){
			boardPanel.addCommonCards(new Hand(pokerGame.getCommonCards()));
		}
	}
	
	
}
