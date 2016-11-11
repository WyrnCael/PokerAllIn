package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import Range.Percentage;

public class TableGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// campos de la clase
	private JButton[][] matrixButtons;
	private Map<String, JButton> mapButtons;
	private List<JButton> selectedList;
	private JLabel percentaje;

	//private JButton calculateRange;

	/**
	 * Metodo constructor
	 */
	public TableGUI() {
		matrixButtons = new JButton[13][13];
		mapButtons = new TreeMap<String, JButton>();
		selectedList = new ArrayList<JButton>();

		//this.setLayout(new GridLayout(14, 13, 3, 3));
		this.setLayout(new BorderLayout(5,5));
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
		JPanel panel = new JPanel(new GridLayout(13, 13, 3, 3));
		for (int i = 12; i >= 0; i--) {
			for (int j = 12; j >= 0; j--) {
				String buttonName = parseButtonName(i, j);
				matrixButtons[i][j] = new JButton(buttonName);
				matrixButtons[i][j].setPreferredSize(new Dimension(50, 50));
				matrixButtons[i][j].setBorder(new BevelBorder(BevelBorder.RAISED));
				matrixButtons[i][j].setFont(new Font("Arial", Font.BOLD, 16));
				matrixButtons[i][j].addActionListener(this);

				this.mapButtons.put(buttonName, matrixButtons[i][j]);
				panel.add(matrixButtons[i][j]);
			}
		}
		JButton calculateRange = new JButton("Calculate Range");
		calculateRange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Si no hay ningun boton seleccionado no hace nada
				if (!selectedList.isEmpty()){
					ordenarLista();
					calcularRango1();					
				}
			}

		});
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.add(calculateRange);
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
			}
		});
		panel2.add(clear);
		percentaje = new JLabel();
		refreshPercentaje();
		panel2.add(percentaje);
		this.add(panel, BorderLayout.CENTER);
		this.add(panel2, BorderLayout.LINE_END);
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
		this.selectedList.clear();
		paintTable();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton boton = (JButton) e.getSource();
		String texto = (boton).getText();
		System.out.println(texto);
		//El boton ya esta pulsado, se vuelve al color original y se eliminar de la lista
		if (boton.getBackground().equals(Color.yellow)){
			selectedList.remove(boton);
			//Suited (Rojo)
			if (boton.getText().length() == 3 && boton.getText().charAt(2) == 's'){
				this.mapButtons.get(texto).setBackground(new Color(255, 71, 26));
			}
			//Off suited (Gris)
			else if (boton.getText().length() == 3){
				this.mapButtons.get(texto).setBackground(new Color(214, 214, 194));
			}
			//Pair (Verde)
			else {
				this.mapButtons.get(texto).setBackground(new Color(71, 209, 71));
			}
		}
		else {
			//Evitamos meter el mismo boton en la lista si ya esta en ella
			if (!selectedList.contains(boton)){
				selectedList.add(boton);
			}
			this.mapButtons.get(texto).setBackground(Color.yellow);			
		}
		refreshPercentaje();
	}
	
	/**
	 * Ordena la lista de carta de menor valor (32o) a mayor valor (AA)
	 */
	private void ordenarLista() {
		this.selectedList.sort(new Comparator<JButton>() {
			@Override
			public int compare(JButton o1, JButton o2) {
				//Como no hay duplicados solo hay que devolver si el boton 1 es mayor que el boton 2
				return getButtonValue(o1.getText()) < getButtonValue(o2.getText())? 1 : -1;
				
			}
		});
	}
	
	
	/**
	 * Recibe un boton y devuelve el valor asociado para poder ordenarlo en la lista, el valor va de menor a mayor (offsuited/suited/pair)
	 * @param name La informacion del boton, para poder extraer el valor de la mano pulsada
	 * @return El valor para hacer el comparador
	 */
	private int getButtonValue(String name) {
		int value = 0;
		switch(name.length()){                                             //170 x 2 
		case 2: value = parseChar(name.charAt(0)) + parseChar(name.charAt(1)) + 340; break;
		case 3: value = (12 * parseChar(name.charAt(0))) + parseChar(name.charAt(1));
		//como el total de combinaciones es 169, separo entre sweater y off sweater
		value += name.charAt(2) == 's' ? 170 : 0; break;
		default:
			System.out.println("ERROR");
		}
		
		return value;
	}
	
	private void calcularRango1(){
		int pos = -1;
		if(selectedList.get(0).getText().length() == 2){
			pos = rangePair(0);
			pos = rangeSuitedOffSuited(pos);
			rangeSuitedOffSuited(pos);
		} else if(selectedList.get(0).getText().length() == 3){
			pos = rangeSuitedOffSuited(0);
			rangeSuitedOffSuited(pos);
		}
	}
	
	private int rangePair(int initPos){
		int endPos = initPos + 1;
		String current = selectedList.get(initPos).getText();
		String next, range = current;
		boolean pair = current.charAt(0) == 'A';
		while(endPos != selectedList.size() && selectedList.get(endPos).getText().length() != 3){
			next = selectedList.get(endPos).getText();
			
			if(pair && (parseChar(current.charAt(0)) - parseChar(next.charAt(0))) == 1){
				current = next;
				range = current + "+";
			} else {
				pair = false;
				current = next;
				System.out.print(range + ",");
				range = current;
			}
			endPos++;
		}
		if(endPos != selectedList.size() && selectedList.get(endPos).getText().length() == 3){
			System.out.print(range + ",");
		} else {
			System.out.println(range);
		}
		return endPos;
	}
	
	private int rangeSuitedOffSuited(int initPos){
		if(initPos == selectedList.size()){
			return initPos;
		}
		int endPos = initPos + 1;
		String current = selectedList.get(initPos).getText();
		String next, range = current, initRange = current + "-";
		boolean bo = (parseChar(current.charAt(0)) - parseChar(current.charAt(1)) == 1);
		char suit = current.charAt(2);
		while(endPos != selectedList.size() && selectedList.get(endPos).getText().charAt(2) == suit){
			next = selectedList.get(endPos).getText();
			if(current.charAt(0) != next.charAt(0)){
				bo = (parseChar(next.charAt(0)) - parseChar(next.charAt(1)) == 1);
				System.out.print(range + ",");
				range = next;
				initRange = range + "-";
			} else {
				if(bo && (parseChar(current.charAt(1)) - parseChar(next.charAt(1))) == 1){
					range = next + "+";
				} else if((parseChar(current.charAt(1)) - parseChar(next.charAt(1))) == 1) {
					range = initRange + next;
				} else {
					bo = false;
					System.out.print(range + ",");
					range = next;
					initRange = range + "-";
				}
			}
			current = next;
			endPos++;
		}
		if(endPos != selectedList.size() && selectedList.get(endPos).getText().charAt(2) != suit){
			System.out.print(range + ",");
		} else {
			System.out.println(range);
		}
		
		return endPos;
	}
	/**
	 * Calcula y muestra el rango de todas las manos seleccionadas por consola
	 * Nunca se llama con la lista vacia, la funcion que llama a esta lo controla
	 */
	/*
	private void calcularRango() {
		Stack<String> stack = new Stack<String>();
		String nombreManoActual, nombreManoAnterior, nombreInicioRango = "";
		nombreManoAnterior = selectedList.get(0).getText();
		for (int i = 1; i < selectedList.size(); i++){
			
			nombreManoActual = selectedList.get(i).getText();
			
			if (nombreManoActual.length() == nombreManoAnterior.length()){
				//No parejas, sean suited o offsuited
				if (nombreManoAnterior.length() == 3){
					//Tienen el mismo caracter de empieze y hay solo 1 de diferencia entre el el segundo caracter, son un rango agrupado
					if (nombreManoActual.charAt(0) == nombreManoAnterior.charAt(0) && 
							parseChar(nombreManoActual.charAt(1)) - parseChar(nombreManoAnterior.charAt(1)) == 1){
						//Solo lo guardo la primera vez, cuando esta vacio
						if (nombreInicioRango.equals("")){
							nombreInicioRango = nombreManoAnterior;						
						}
					}
					else {
						if (nombreInicioRango.equals("")){
							System.out.print(nombreManoAnterior);
							stack.push(nombreManoAnterior);
						}
						//Rango entero (+) Si el segundo caracter mas 1 es el mismo que el del primero, es que formarian pareja
						else if (parseChar(nombreManoAnterior.charAt(1)) + 1  == parseChar(nombreManoAnterior.charAt(0))){
							System.out.print(nombreInicioRango + "+");	
							stack.push(nombreInicioRango + "+");
						}
						//Rango Acotado (-)
						else {
							System.out.print(nombreManoAnterior + "-" + nombreInicioRango);
							stack.push(nombreManoAnterior + "-" + nombreInicioRango);
						}
						nombreInicioRango = "";
						System.out.print(",");
					}
					
				}
				//Parejas
				else if (parseChar(nombreManoActual.charAt(0)) - parseChar(nombreManoAnterior.charAt(0)) == 1 && 
						parseChar(nombreManoActual.charAt(1)) - parseChar(nombreManoAnterior.charAt(1)) == 1){
					//Solo lo guardo la primera vez, cuando esta vacio
					if (nombreInicioRango == ""){
						nombreInicioRango = nombreManoAnterior;						
					}
					
				}
				else {
					if (nombreInicioRango.equals("")){
						System.out.print(nombreManoAnterior);	
						stack.push(nombreManoAnterior);
					}
					//Rango entero (+) Si el segundo caracter mas 1 es el mismo que el del primero, es que formarian pareja
					else if (parseChar(nombreManoAnterior.charAt(1)) + 1  == parseChar(nombreManoAnterior.charAt(0))){
						System.out.print(nombreInicioRango + "+");	
						stack.push(nombreInicioRango + "+");
					}
					//Rango Acotado (-)
					else {
						System.out.print(nombreManoAnterior + "-" + nombreInicioRango);
						stack.push(nombreManoAnterior + "-" + nombreInicioRango);
					}
					nombreInicioRango = "";
					System.out.print(",");
				}
			}
			
			else {
				if (nombreInicioRango.equals("")){
					System.out.print(nombreManoAnterior);	
					stack.push(nombreManoAnterior);
				}
				//Rango entero (+) Si el segundo caracter mas 1 es el mismo que el del primero, es que formarian pareja
				else if (parseChar(nombreManoAnterior.charAt(1)) + 1  == parseChar(nombreManoAnterior.charAt(0))){
					System.out.print(nombreInicioRango + "+");
					stack.push(nombreInicioRango + "+");
				}
				//Rango Acotado (-)
				else {
					System.out.print(nombreManoAnterior + "-" + nombreInicioRango);
					stack.push(nombreManoAnterior + "-" + nombreInicioRango);
				}
				nombreInicioRango = "";
				System.out.print(",");
			}
			nombreManoAnterior = nombreManoActual;
		}
		
		
		//Por si acaso solo hay una carta en la lista
		nombreManoActual = nombreManoAnterior;
		if (nombreManoActual.length() == 3){
			if (nombreInicioRango == ""){
				System.out.println(nombreManoActual);	
				stack.push(nombreManoActual);
			}
			//Rango entero (+) Si el segundo caracter mas 1 es el mismo que el del primero, es que formarian pareja
			else if (parseChar(nombreManoActual.charAt(1)) + 1  == parseChar(nombreManoActual.charAt(0))){
				System.out.println(nombreInicioRango + "+");
				stack.push(nombreInicioRango + "+");
			}
			//Rango Acotado (-)
			else {
				System.out.println(nombreManoActual + "-" + nombreInicioRango);
				stack.push(nombreManoActual + "-" + nombreInicioRango);
			}
		}
		else {
			if (nombreInicioRango.equals("")){
				System.out.println(nombreManoActual);	
				stack.push(nombreManoActual);
			}
			////Rango entero (+) Caso Particular, en las parejas solo es entero si el final es AA
			else if (nombreManoActual.equals("AA")){
				System.out.println(nombreInicioRango + "+");
				stack.push(nombreInicioRango + "+");
			}
			//Rango Acotado (-)
			else {
				System.out.println(nombreManoActual + "-" + nombreInicioRango);
				stack.push(nombreManoActual + "-" + nombreInicioRango);
			}
		}
		while(!stack.isEmpty()){
			String str = stack.pop();
			System.out.print(str);
			if(!stack.isEmpty()){
				System.out.print(",");
			} else {
				System.out.println();
			}
		}
	}
	*/
	
	private void refreshPercentaje(){
		int nPairs = 0, nSuited = 0, nOffSuited = 0;
		for (int i = 0; i < selectedList.size(); i++){
			String nombreManoActual = selectedList.get(i).getText();
			if(nombreManoActual.length() == 2){
				nPairs++;
			}
			else if(nombreManoActual.charAt(2) == 's'){
				nSuited++;
			}
			else if(nombreManoActual.charAt(2) == 'o'){
				nOffSuited++;
			}
		}
		DecimalFormat df = new DecimalFormat("0.00");
		double per = Percentage.getPercent(nSuited, nPairs, nOffSuited);
		this.percentaje.setText("Porcentaje: " + df.format(per) + "%");
		
	}
}
