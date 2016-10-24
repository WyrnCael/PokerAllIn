package GUI;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Game.*;

@SuppressWarnings("serial")
public class OptionPanel extends JPanel {
	private JButton bFile;
	private JTextField pathField;
	private FileDialog openFile;

	private JComboBox<String> combo;
	private JButton play;
	private JButton next;
	private JButton stop;
	
	private String line;

	private BoardPanel bPanel;
	private JTextArea textArea;
	
	private Game game;
	
	private GUIController controller;

	public OptionPanel(BoardPanel bPanel) {
		bFile = new JButton("Choose File");
		pathField = new JTextField(10);
		openFile = new FileDialog(new JFrame(), "open", FileDialog.LOAD);

		String options[] = { "Game mode", "Best card of 5", "Best card of 2", "Poker game", "Omaha game" };
		combo = new JComboBox<String>(options);
		play = new JButton("Play");
		next = new JButton("Next");
		stop = new JButton("Stop");
		controller = null;
		
		this.bPanel = bPanel;
		textArea = new JTextArea();
		
		setAction();
		initGUI();
	}

	private void initGUI() {
		this.setPreferredSize(new Dimension(300, 800));
		
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea);
		sp.setPreferredSize(new Dimension(300,700));
		

		this.add(pathField);
		this.add(bFile);
		this.add(combo);
		this.add(play);
		this.add(next);
		this.add(stop);
		this.add(sp);
		pathField.setEditable(false);
		next.setVisible(false);
		stop.setVisible(false);
	}

	private void setAction() {
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(pathField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Elegir un fichero", "Error", JOptionPane.ERROR_MESSAGE);
				} else if(game == null) {
					JOptionPane.showMessageDialog(null, "Elegir un modo de juego", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try{
						controller = new GUIController(pathField.getText());
						line = controller.readLine();
						game.parseGame(line);
						game.processGame();
						
						textArea.append(game.toString() + System.getProperty("line.separator"));
						bPanel.updateBoardPanel(game);
						
						line = controller.readLine();
						if(line != null){
							combo.setEnabled(false);
							bFile.setEnabled(false);
							play.setVisible(false);
							next.setVisible(true);
							stop.setVisible(true);
						} else {
							controller.close();
						}
					} catch (Exception exception) {
						System.out.println(exception.getMessage());
						JOptionPane.showMessageDialog(null, "Este archivo de entrada no es para este modo de juego", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				game.clear();
				game.parseGame(line);
				game.processGame();
				
				textArea.append(game.toString() + System.getProperty("line.separator"));
				bPanel.updateBoardPanel(game);
				
				line = controller.readLine();
				if(line == null){
					controller.close();
					game.clear();
					
					bFile.setEnabled(true);
					combo.setEnabled(true);
					play.setVisible(true);
					next.setVisible(false);
					stop.setVisible(false);
				}
			}
		});
		
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				controller.close();
				game.clear();
				
				bFile.setEnabled(true);
				combo.setEnabled(true);
				play.setVisible(true);
				next.setVisible(false);
				stop.setVisible(false);
			}
		});
		
		bFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openFile.setVisible(true);
				if(openFile.getDirectory() != null && openFile.getFile() != null){
					pathField.setText(openFile.getDirectory() + openFile.getFile());
				}
			}

		});

		combo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == 1) {
					switch(e.getItem().toString()){
					case "Game mode":
						game = null;
						break;
					case "Best card of 5":
						game = new BestHandWith5Card();
						break;
					case "Best card of 2":
						game = new BestHandWith2Card();
						break;
					case "Poker game":
						game = new PokerGame();
						break;
					case "Omaha game":
						game = new OmahaGame();
						break;
					}
				}
			}
		});

	}
}
