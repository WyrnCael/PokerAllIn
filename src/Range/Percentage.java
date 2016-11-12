package Range;

public final class Percentage {
	private static int numCombinaciones = 1326;
	
	public Percentage(int Nsuited, int Npar, int Noffsuited){
		
	}
	
	public static double getPercent(int Nsuited, int Npar, int Noffsuited){
		double total = 0;
		total = Nsuited*4 + Npar*6+ Noffsuited*12;
		double percent = total / numCombinaciones;
		return percent;
	}
	
	public static int getNumberCards(double percent){
		int r = (int) (percent * numCombinaciones / 100);
		return r;
	}
}
