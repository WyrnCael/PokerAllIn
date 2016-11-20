package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Range.Percentage;
import Range.Range;
import Range.SklanskyChubukov;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;


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
	private JTextField inputArea;
	private JTextField outputArea;
	private String finalRange = "";
	private JSlider slider;
	private JTextField percentText;
	
	//private JButton calculateRange;

	/**
	 * Metodo constructor
	 */
	public TableGUI() {
		matrixButtons = new JButton[13][13];
		mapButtons = new TreeMap<String, JButton>();
		selectedList = new ArrayList<JButton>();
		initGUI();

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * El metodo que construye los botones
	 */
	private void initGUI() {
		JPanel panel = new JPanel(new GridLayout(13, 13, 3, 3));
		percentaje = new JLabel();
		percentText = new JTextField();
		slider = new JSlider();
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
		refreshPercentaje();
		
		
		
		JPanel panelPorcentaje = new JPanel();
		panelPorcentaje.setLayout(new BoxLayout(panelPorcentaje, BoxLayout.X_AXIS));
		
		panelPorcentaje.add(slider);
		slider.setValue(0);
		slider.addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent ce) {
	        	if(slider.isFocusOwner()){
		           drawCustomPercent();
	        	}
	        }
	    });
		slider.setPreferredSize(new Dimension(700, 20));
		
		panelPorcentaje.add(percentaje);
		
		panelPorcentaje.add(percentText);
		percentText.setHorizontalAlignment(SwingConstants.RIGHT);
		percentText.setMaximumSize(new Dimension(42, percentText.getMinimumSize().height));
		percentText.setPreferredSize(new Dimension(42, percentText.getMinimumSize().height));
		percentText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawCustomPercentFromText();
			}
		});
		percentText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				drawCustomPercentFromText();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				percentText.setText("");
			}
		});
		JLabel percSimbol = new JLabel("%");
		panelPorcentaje.add(percSimbol);
		
		JPanel panelControl = new JPanel();
		panelControl.setPreferredSize(new Dimension(200,50));
		panelControl.setMaximumSize(new Dimension(200,50));
		
		JLabel input = new JLabel("Enter range to print on the board:");
		input.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		inputArea = new JTextField();
		inputArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputArea.getMinimumSize().height));
		inputArea.setPreferredSize(new Dimension(150,  20));
		inputArea.setToolTipText("Introduce aqui el rango a calcular");
		
		JButton drawHandRange = new JButton("Show Range on the board");
		inputArea.addKeyListener(new KeyListener() {			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					// Para quitar el salto de linea
					drawHandRange.grabFocus();
					drawHandRange.doClick();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		drawHandRange.setAlignmentX(JButton.CENTER_ALIGNMENT);
		drawHandRange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Range(TableGUI.this, inputArea.getText());
				refreshPercentaje();
			}
		});
		
		outputArea = new JTextField();
		outputArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, outputArea.getMinimumSize().height));
		outputArea.setEditable(false);
		outputArea.setToolTipText("Salida del rango resaltado en el panel");
		JLabel selected = new JLabel("Range selected frome the board:");
		selected.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		JButton calculateRange = new JButton("Calculate Range from Board");
		calculateRange.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		calculateRange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Si no hay ningun boton seleccionado no hace nada
				if (!selectedList.isEmpty()){
					//Limpio el string para que no se concatene con lo anterior en cada llamada
					finalRange = "";
					ordenarLista();
					calcularRango();	
				}
			}
		});
		
		
		JButton clear = new JButton("Clear");
		clear.setAlignmentX(JButton.CENTER_ALIGNMENT);
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputArea.setText("");
				outputArea.setText("");
				slider.setValue(0);
				percentText.setText("0,0");
				clearTable();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelPorcentaje, GroupLayout.PREFERRED_SIZE, 924, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 689, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 607, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(151)
							.addComponent(panelControl, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelPorcentaje, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		GroupLayout gl_panelControl = new GroupLayout(panelControl);
		gl_panelControl.setHorizontalGroup(
			gl_panelControl.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelControl.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelControl.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelControl.createSequentialGroup()
							.addComponent(outputArea, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panelControl.createSequentialGroup()
							.addComponent(inputArea, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panelControl.createSequentialGroup()
							.addComponent(input)
							.addGap(38))
						.addGroup(gl_panelControl.createSequentialGroup()
							.addGroup(gl_panelControl.createParallelGroup(Alignment.LEADING)
								.addComponent(selected)
								.addComponent(drawHandRange))
							.addGap(42))
						.addGroup(gl_panelControl.createSequentialGroup()
							.addComponent(calculateRange)
							.addGap(39))))
				.addGroup(Alignment.LEADING, gl_panelControl.createSequentialGroup()
					.addGap(90)
					.addComponent(clear)
					.addContainerGap(104, Short.MAX_VALUE))
		);
		gl_panelControl.setVerticalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup()
					.addGap(28)
					.addComponent(input)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inputArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(drawHandRange)
					.addGap(72)
					.addComponent(selected)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(outputArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(calculateRange)
					.addGap(69)
					.addComponent(clear)
					.addGap(21))
		);
		panelControl.setLayout(gl_panelControl);
		getContentPane().setLayout(groupLayout);
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
		if (!selectedList.contains(button)){
			selectedList.add(button);
		}
		button.setBackground(Color.yellow);	
		boolean finish = tmp.equals(finalButton);
		while (!finish) {
			tmp = tmp.replace(tmp.substring(1, 2), parseNum(parseChar(tmp.charAt(1)) + -1));					
			button = this.mapButtons.get(tmp);
			if (button == null){
				throw new IOException(tmp);
			}
			if (!selectedList.contains(button)){
				selectedList.add(button);
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
			if (!selectedList.contains(button)){
				selectedList.add(button);
			}
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
		this.slider.setValue((int) Double.parseDouble(percentText.getText().replace(',', '.'))); 
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
	
	/**
	 * Separa entre longitud 2 y 3
	 */
	private void calcularRango(){
		int pos = -1;
		if(selectedList.get(0).getText().length() == 2){
			pos = rangePair(0);
			pos = rangeSuitedOffSuited(pos);
			rangeSuitedOffSuited(pos);
		} else if(selectedList.get(0).getText().length() == 3){
			pos = rangeSuitedOffSuited(0);
			rangeSuitedOffSuited(pos);
		}
		this.outputArea.setText(finalRange);
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
				finalRange += range + ",";
				range = current;
			}
			endPos++;
		}
		if(endPos != selectedList.size() && selectedList.get(endPos).getText().length() == 3){
			finalRange += range + ",";
			System.out.print(range + ",");
		} else {
			System.out.println(range);
			finalRange += range;
			
		}
		outputArea.setText(finalRange);
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
				finalRange += range + ",";
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
					finalRange += range + ",";
					range = next;
					initRange = range + "-";
				}
			}
			current = next;
			endPos++;
		}
		if(endPos != selectedList.size() && selectedList.get(endPos).getText().charAt(2) != suit){
			System.out.print(range + ",");
			finalRange += range + ",";
		} else {
			System.out.println(range);
			finalRange += range;
		}
		
		return endPos;
	}
	
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
		DecimalFormat df = new DecimalFormat("0.0");
		double per = Percentage.getPercent(nSuited, nPairs, nOffSuited);
		this.percentaje.setText("Porcentaje: ");
		this.percentText.setText(df.format(per));		
		
	}
	
	private void drawCustomPercent(){
		this.outputArea.setText("");
		clearTable();
		selectedList.clear();
		double percent = slider.getValue();
		
		List<String> ranking = SklanskyChubukov.getList(percent);
		
		for(String combo : ranking){
			JButton button = this.mapButtons.get(combo);
			//if (!selectedList.contains(button)){
				selectedList.add(button);
			//}
			//button.setBackground(Color.PINK);	
				button.setBackground(Color.yellow);	
		}
		this.inputArea.setText("");
		refreshPercentaje();
	}
	
	private void drawCustomPercentFromText(){
		this.outputArea.setText("");
		clearTable();
		selectedList.clear();
		double percent;
		try{
			String input = this.percentText.getText().replace("," , ".");
			percent = Double.valueOf(input);
		}
		catch (NumberFormatException e){
			System.out.println("Error, no es un valor numerico: " + e.getMessage());
			percent = 0;
		}
		List<String> ranking = SklanskyChubukov.getList(percent);
		
		for(String combo : ranking){
			JButton button = this.mapButtons.get(combo);
			//if (!selectedList.contains(button)){
				selectedList.add(button);
			//}
			//button.setBackground(Color.PINK);	
				button.setBackground(Color.yellow);	
		}
		this.inputArea.setText("");
		this.slider.setValue((int) percent); 
	}
}
