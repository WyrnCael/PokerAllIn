package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cards.Hand;
import Players.Player;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;

public class BoardGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardGUI frame = new BoardGUI();
					frame.setVisible(true);
					frame.pack();
					frame.setSize(1025, 550);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BoardGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//JPanel panel = new BackgroundPanel();
		JPanel panel = new JPanel();
		
		JPanel player1 = new JPanel();
		//PlayerPanel player1 = new PlayerPanel(new Player("P1", new Hand("AcAh")));
	
		JPanel player2 = new JPanel();
		//PlayerPanel player2 = new PlayerPanel(new Player("P2", new Hand("2s5h")));
		
		JPanel player3 = new JPanel();
		//PlayerPanel player3 = new PlayerPanel(new Player("P3", new Hand("JcTd")));
		
		JPanel player4 = new JPanel();
		//PlayerPanel player4 = new PlayerPanel(new Player("P4", new Hand("8s9s")));
		
		JPanel player5 = new JPanel();
		//PlayerPanel player5 = new PlayerPanel(new Player("P5", new Hand("ThTs")));
		
		JPanel player6 = new JPanel();
		//PlayerPanel player6 = new PlayerPanel(new Player("P6", new Hand("AsKs")));
		
		JPanel player7 = new JPanel();
		//PlayerPanel player7 = new PlayerPanel(new Player("P7", new Hand("9s9c")));
		
		JPanel player8 = new JPanel();
		//PlayerPanel player8 = new PlayerPanel(new Player("P8", new Hand("2c2d")));
		
		JPanel player9 = new JPanel();
		//PlayerPanel player9 = new PlayerPanel(new Player("P9", new Hand("Qh7c")));
		
		JPanel player10 = new JPanel();		
		//PlayerPanel player10 = new PlayerPanel(new Player("P10", new Hand("8c3h")));

		JButton btnFold = new JButton("Fold");
		
		JButton button_2 = new JButton("Fold");
		
		JButton button_3 = new JButton("Fold");
		
		JButton button_4 = new JButton("Fold");
		
		JButton button_5 = new JButton("Fold");
		
		JButton button_6 = new JButton("Fold");
		
		JButton button_7 = new JButton("Fold");
		
		JButton button_8 = new JButton("Fold");
		
		JButton button_9 = new JButton("Fold");
		
		JButton button_10 = new JButton("Fold");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(player4, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(player3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnFold, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(player2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
							.addGap(49)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(player1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_10, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addGap(46)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(player10, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(player5, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addGap(168)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(player6, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(player9, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(player7, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(player8, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
						.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(player5, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(player6, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(player4, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(button_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(player3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_2))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(button_5)
								.addComponent(button_4))))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(player1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(player10, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(player2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFold)
						.addComponent(button_9)
						.addComponent(button_10))
					.addGap(45))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(87)
					.addComponent(player7, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_6)
					.addGap(1)
					.addComponent(player8, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_7)
					.addGap(7)
					.addComponent(player9, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_8)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 768, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1434, Short.MAX_VALUE)
					.addGap(3))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblAllRandom = new JLabel("All random:");
		lblAllRandom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNumberOfCards = new JLabel("Number of cards on Boards:");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "3", "4", "5"}));
		
		JLabel lblNumberOfPlayers = new JLabel("Number of players:");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		
		JButton btnGenerate = new JButton("Generate");
		
		JLabel lblSetPlayers = new JLabel("Set Players:");
		lblSetPlayers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblPlayer = new JLabel("Player:");
		
		JLabel lblSetCards = new JLabel("Set Cards:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		
		JButton btnSet = new JButton("Set");
		
		JButton btnRandom = new JButton("Random");
		
		JLabel lblSetBoard = new JLabel("Set Board:");
		lblSetBoard.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label = new JLabel("Set Cards:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton button = new JButton("Set");
		
		JButton button_1 = new JButton("Random");
		
		JLabel label_1 = new JLabel("Number of cards on Boards:");
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"0", "3", "4", "5"}));
		
		JLabel lblExecute = new JLabel("Execute:");
		lblExecute.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnClaculateEquity = new JButton("Claculate Equity");
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		JSeparator separator_2 = new JSeparator();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAllRandom, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNumberOfCards)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblNumberOfPlayers, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblSetBoard, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(lblPlayer)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(btnSet)
											.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
											.addComponent(btnRandom))
										.addComponent(lblSetPlayers, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(lblSetCards)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(194))
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
							.addGap(188))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(63)
							.addComponent(btnGenerate))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblExecute, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClaculateEquity)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAllRandom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfCards)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNumberOfPlayers))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnGenerate)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSetPlayers, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayer)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSetCards)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSet)
						.addComponent(btnRandom))
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSetBoard, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExecute, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClaculateEquity))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
	}
	
	private class BackgroundPanel extends JPanel{
		private Image backgroundImage;
		
		/**
		 * Constructor
		 */
		BackgroundPanel(){		
			try {
				URL url = this.getClass().getClassLoader().getResource("img/fondo.png");
				BufferedImage image = ImageIO.read(url);
				backgroundImage = new ImageIcon(image).getImage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			if(backgroundImage != null){
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),this);
			}
		}
	}
}
