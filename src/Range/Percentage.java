package Range;

public class Percentage {
	private final int numCombinaciones = 1326;
	private double percent;
	
	public Percentage(int Nsuited, int Npar, int Noffsuited){
		int total = 0;
		total = Nsuited*4 + Npar*6+ Noffsuited*12;
		percent = total / numCombinaciones;
	}
	public double getPercent(){
		return this.percent;
	}
}
