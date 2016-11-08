package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

public class TableGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// campos de la clase
	private JButton[][] matrixButtons;
	private Map<String, JButton> mapButtons;
	private List<JButton> selectedList;
	//private JButton calculateRange;

	/**
	 * Metodo constructor
	 */
	public TableGUI() {
		matrixButtons = new JButton[13][13];
		mapButtons = new TreeMap<String, JButton>();
		selectedList = new ArrayList<JButton>();
		this.setLayout(new GridLayout(13, 13, 3, 3));

		initGUI();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * El metodo que construye los botones
	 */
	private void initGUI() {
		for (int i = 12; i >= 0; i--) {
			for (int j = 12; j >= 0; j--) {
				String buttonName = parseButtonName(i, j);
				matrixButtons[i][j] = new JButton(buttonName);
				matrixButtons[i][j].setPreferredSize(new Dimension(50, 50));
				matrixButtons[i][j].setBorder(new BevelBorder(BevelBorder.RAISED));
				matrixButtons[i][j].setFont(new Font("Arial", Font.BOLD, 16));
				matrixButtons[i][j].addActionListener(this);

				this.mapButtons.put(buttonName, matrixButtons[i][j]);
				this.add(matrixButtons[i][j]);
			}
		}
		JButton calculateRange = new JButton("Calculate Range");
		calculateRange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ordenarLista();
			}

			private void ordenarLista() {
				// TODO Auto-generated method stub
				
			}
		});
		this.add(calculateRange);
		paintTable();
	}

	/**
	 * El metodo que colorea a la tabla
	 */
	private void paintTable() {
		for (int i = 12; i >= 0; i--) {
			for (int j = 12; j >= 0; j--) {
				if (i == j) { // mismo color(verde)
					matrixButtons[i][j].setBackground(new Color(71, 209, 71));
				} else if (i < j) { // offsuited(gris)
					matrixButtons[i][j].setBackground(new Color(214, 214, 194));
				} else { // i > j suited(rojo)
					matrixButtons[i][j].setBackground(new Color(255, 71, 26));
				}
			}
		}
	}

	/**
	 * El metodo que parsea los butones
	 * 
	 * @param i
	 *            El parametro i es numero de fila que esta este buton
	 * @param j
	 *            El parametro j es numero de columna que esta este buton
	 * @return Devuelve el nombre del boton
	 */
	private String parseButtonName(int i, int j) {
		String buttonName = "";
		if (i <= j) {
			buttonName += parseNum(j) + parseNum(i);
		} else {
			buttonName += parseNum(i) + parseNum(j);
		}

		if (i < j) {
			buttonName += "o";
		} else if (i > j) {
			buttonName += "s";
		}

		return buttonName;
	}

	/**
	 * El metodo que parsea del numero al letra
	 * 
	 * @param n
	 *            El parametro n es el numero de la carta
	 * @return Devuelve la letra de la carta
	 */
	private String parseNum(int n) {
		String letter = "";
		switch (n) {
		case 12:
			letter = "A";
			break;
		case 11:
			letter = "K";
			break;
		case 10:
			letter = "Q";
			break;
		case 9:
			letter = "J";
			break;
		case 8:
			letter = "T";
			break;
		default:
			letter = String.valueOf(n + 2);
			break;
		}
		return letter;
	}

	/**
	 * El metodo que parsea de letra al numero de la carta
	 * 
	 * @param c
	 *            El parametro c es la letra de la carta
	 * @return Devuelve la letra de la carta
	 */
	private int parseChar(char c) {
		int num = -1;
		switch (c) {
		case 'A':
			num = 12;
			break;
		case 'K':
			num = 11;
			break;
		case 'Q':
			num = 10;
			break;
		case 'J':
			num = 9;
			break;
		case 'T':
			num = 8;
			break;
		case '9':
			num = 7;
			break;
		case '8':
			num = 6;
			break;
		case '7':
			num = 5;
			break;
		case '6':
			num = 4;
			break;
		case '5':
			num = 3;
			break;
		case '4':
			num = 2;
			break;
		case '3':
			num = 1;
			break;
		case '2':
			num = 0;
			break;
		default:
			break;
		}
		return num;
	}

	/**
	 * El metodo que actualiza el color del boton
	 * 
	 * @param buttonName
	 *            El parametro buttonName es el boton de inicio
	 * @param finalButton
	 *            El parametro finalButton es el boton de final
	 * @throws IOException 
	 */
	public void updateButton(String buttonName, String finalButton) throws IOException {
		String tmp = buttonName;
		
			JButton button = this.mapButtons.get(tmp);
			//La mano introducida no es correcta
			if (button == null){
				throw new IOException(tmp);
			}
			button.setBackground(Color.yellow);	
			boolean finish = tmp.equals(finalButton);
			while (!finish) {
				tmp = tmp.replace(tmp.substring(1, 2), parseNum(parseChar(tmp.charAt(1)) + -1));					
				button = this.mapButtons.get(tmp);
				if (button == null){
					throw new IOException(tmp);
				}
				button.setBackground(Color.yellow);
				if (tmp.equals(finalButton)) {
					finish = true;
				}
			}
		
	}

	/**
	 * El metodo que actualiza el color del boton
	 * 
	 * @param buttonName
	 *            El parametro buttonName es el boton inicio
	 * @param mode
	 *            El parametro mode es la forma del recorrer, puede ser diagonal
	 *            o horizontal/vertical
	 * @param increDecre
	 *            El parametro increDecre decide el recorrido va incrementar(1)
	 *            o decrementar(-1)
	 * @throws IOException 
	 */
	public void updateButton(String buttonName, String mode, int increDecre) throws IOException {
		String tmp = buttonName.substring(0, buttonName.length() - 1);
		JButton button = this.mapButtons.get(tmp);
		if (button == null){
			throw new IOException(tmp);
		}
		boolean finish = false;
		
		// mientra que el boton no sea null
		while (!finish) {
			button.setBackground(Color.yellow);
			switch (mode) {
			case "Diagonal":
				// si es diagonal, actualizar las dos letras del boton, AA -> KK o KK -> AA
				tmp = parseNum(parseChar(tmp.charAt(0)) + increDecre) + parseNum(parseChar(tmp.charAt(1)) + increDecre);
				break;
			case "VertHor":
				// si es horizontal o vertical, solo actualiza la segunda, AK -> AQ o Q5 -> Q6
				tmp = tmp.replace(tmp.substring(1, 2), parseNum(parseChar(tmp.charAt(1)) + increDecre));
				
				// si llega al diagonal, para
				if (tmp.charAt(0) == tmp.charAt(1)) {
					finish = true;
				}
				break;
			default:
				break;
			}

			if ((button = this.mapButtons.get(tmp)) == null) {
				finish = true;
			}
		}
	}

	/**
	 * El metodo que pintar tabla como en principio
	 */
	public void clearTable() {
		paintTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(((JButton) e.getSource()).getText());
		selectedList.add((JButton) e.getSource());
		this.mapButtons.get(((JButton) e.getSource()).getText()).setBackground(Color.yellow);
		
	}
}
