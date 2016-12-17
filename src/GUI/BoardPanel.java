package GUI;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Cards.Hand;

public class BoardPanel extends JPanel {
	/**
	 * 
	 */
	private final int numJugadores = 10;
	private JTextField[] tfPlayer;
	private PlayerPanel[] panelPlayer;
	private Image backgroundImage;
	CommonCardsPanel panelComun;
	
	private static final long serialVersionUID = 1L;

	public BoardPanel() {
		setLayout(null);
		this.panelPlayer = new PlayerPanel[numJugadores];
		this.tfPlayer = new JTextField[numJugadores];
		
		try {
			URL url = this.getClass().getClassLoader().getResource("img/fondo.png");
			BufferedImage image = ImageIO.read(url);
			backgroundImage = new ImageIcon(image).getImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < numJugadores; i++){
			panelPlayer[i] = new PlayerPanel(); 
			tfPlayer[i] = new JTextField();
			tfPlayer[i].setEditable(false);
			tfPlayer[i].setVisible(false);
		}
		
		panelComun = new CommonCardsPanel();
		panelComun.setBounds(219, 200, 312, 100);
		add(panelComun);
		
		colocarPanelesJugadores();
		
		colocarTextoJugadores();
		
		
	}
	
	private void colocarPanelesJugadores(){

		panelPlayer[0].setBounds(431, 27, 100, 100);
		add(panelPlayer[0]);
		
		panelPlayer[1].setBounds(572, 50, 100, 100);
		add(panelPlayer[1]);
		
		panelPlayer[2].setBounds(595, 200, 100, 100);
		add(panelPlayer[2]);
		
		panelPlayer[3].setBounds(542, 362, 100, 100);
		add(panelPlayer[3]);
		
		panelPlayer[4].setBounds(401, 362, 100, 100);
		add(panelPlayer[4]);
		
		panelPlayer[5].setBounds(257, 362, 100, 100);
		add(panelPlayer[5]);
		
		panelPlayer[6].setBounds(110, 362, 100, 100);
		add(panelPlayer[6]);
		
		panelPlayer[7].setBounds(53, 200, 100, 100);
		add(panelPlayer[7]);

		panelPlayer[8].setBounds(83, 50, 100, 100);
		add(panelPlayer[8]);
	
		panelPlayer[9].setBounds(219, 27, 100, 100);
		add(panelPlayer[9]);
	}
	
	private void colocarTextoJugadores(){
		

		tfPlayer[0].setBounds(431, 138, 100, 20);
		add(tfPlayer[0]);

		tfPlayer[1].setBounds(572, 161, 100, 20);
		add(tfPlayer[1]);
		
		tfPlayer[2].setBounds(595, 311, 100, 20);
		add(tfPlayer[2]);

		tfPlayer[3].setBounds(542, 473, 100, 20);
		add(tfPlayer[3]);

		tfPlayer[4].setBounds(401, 473, 100, 20);
		add(tfPlayer[4]);
		
		tfPlayer[5].setBounds(257, 473, 100, 20);
		add(tfPlayer[5]);	

		tfPlayer[6].setBounds(110, 473, 100, 20);
		add(tfPlayer[6]);

		tfPlayer[7].setBounds(53, 311, 100, 20);
		add(tfPlayer[7]);

		tfPlayer[8].setBounds(83, 161, 100, 20);
		add(tfPlayer[8]);

		tfPlayer[9].setBounds(219, 138, 100, 20);
		add(tfPlayer[9]);
	}
	
	public PlayerPanel getPanelPlayer(int index){
		return panelPlayer[index];
	}
	
	public JTextField gettfPlayer(int index){
		return tfPlayer[index];
	}
	

	public void addCommonCards(Hand mano){
		panelComun.agregarCartas(mano);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(backgroundImage != null){
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),this);
		}
	}
	
	public void hideAllPlayers(){
		for (int i = 0; i < numJugadores; i++){
			changeVisibily(false, i);
		}
	}
	
	public void changeVisibily(boolean show, int index){
		panelPlayer[index].setVisible(show);
		tfPlayer[index].setVisible(show);
		tfPlayer[index].setText("");
	}
	
	/**
	 * Elimina las cartas comunitarias y oculta el panel
	 */
	public void cleanCommonCards(){
		panelComun.agregarCartas(new Hand());
	}
}
