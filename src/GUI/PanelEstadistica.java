package GUI;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class PanelEstadistica extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelEstadistica(){
		CategoryDataset dataset = createDataset();
		
		JFreeChart chart=ChartFactory.createBarChart("Combos", "", 
                "", dataset, PlotOrientation.HORIZONTAL, false, false, false);
		this.add(new ChartPanel(chart));
        this.setSize(new Dimension(700,460));
        this.setVisible(true);
	}
	
	public PanelEstadistica(List<Integer> listValue){
		CategoryDataset dataset = createDataset(listValue);
		
		JFreeChart chart=ChartFactory.createBarChart("Combos", "", 
                "", dataset, PlotOrientation.HORIZONTAL, false, false, false);
		this.add(new ChartPanel(chart));
	}
	
    private CategoryDataset createDataset()
    {
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        dataset.setValue(0,"","STRAIGHT_FLUSH");
        dataset.setValue(0,"","FOUR_OF_A_KIND");
        dataset.setValue(0,"","FULL_HOUSE");
        dataset.setValue(0,"","FLUSH");
        dataset.setValue(0,"","STRAIGHT");
        dataset.setValue(0,"","THREE_OF_A_KIND");
        dataset.setValue(0,"","TWO_PAIR");
        dataset.setValue(0,"","OVER_PAIR");
        dataset.setValue(0,"","TOP_PAIR");
        dataset.setValue(0,"","PP_BELOW_TP");
        dataset.setValue(0,"","MIDDLE_PAIR");
        dataset.setValue(0,"","WEAK_PAIR");
        dataset.setValue(0,"","PAIR");
        dataset.setValue(0,"","ace high");
        dataset.setValue(0,"","no made hand");
        return dataset;
    }
    
    private CategoryDataset createDataset(List<Integer> listValue)
    {
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        String[] str = { "STRAIGHT_FLUSH", 
				"FOUR_OF_A_KIND", 
				"FULL_HOUSE", 
				"FLUSH", 
				"STRAIGHT", 
				"THREE_OF_A_KIND",
				"TWO_PAIR", 
				"OVER_PAIR", 
				"TOP_PAIR", 
				"PP_BELOW_TP", 
				"MIDDLE_PAIR", 
				"WEAK_PAIR", 
				"PAIR", 
				"ace high",
				"no made hand" };
        
        for(int i=0; i < listValue.size(); i++){
        	dataset.setValue(listValue.get(i),"",str[i]);
        }
        
        return dataset;
    }
    
    public static void main(String[] args)
    {
        JFrame chart=new JFrame();
        List<Integer> lista = new ArrayList<Integer>();
        int[] valores = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for(int i=0; i< valores.length;i++){
        	lista.add(valores[i]);
        }
        chart.setContentPane(new PanelEstadistica(lista));
        chart.pack();
        chart.setVisible(true);
        
    }
    
}
