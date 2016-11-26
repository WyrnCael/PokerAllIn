package Range;

import java.util.ArrayList;
import java.util.List;

public class SklanskyChubukov {
	private List<String> ranking;
	private final int numCombinaciones = 1326;

	/**
	 * Constructor, anadiendo todos los rangos segun rank de la tabla
	 * SklanskyChubukov
	 */
	public SklanskyChubukov() {
		ranking = new ArrayList<String>();
		ranking.add("AA");
		ranking.add("KK");
		ranking.add("AKs");
		ranking.add("QQ");
		ranking.add("AKo");
		ranking.add("JJ");
		ranking.add("AQs");
		ranking.add("TT");
		ranking.add("AQo");
		ranking.add("99");
		ranking.add("AJs");
		ranking.add("88");
		ranking.add("ATs");
		ranking.add("AJo");
		ranking.add("77");
		ranking.add("66");
		ranking.add("ATo");
		ranking.add("A9s");
		ranking.add("55");
		ranking.add("A8s");
		ranking.add("KQs");
		ranking.add("44");
		ranking.add("A9o");
		ranking.add("A7s");
		ranking.add("KJs");
		ranking.add("A5s");
		ranking.add("A8o");
		ranking.add("A6s");
		ranking.add("A4s");
		ranking.add("33");
		ranking.add("KTs");
		ranking.add("A7o");
		ranking.add("A3s");
		ranking.add("KQo");
		ranking.add("A2s");
		ranking.add("A5o");
		ranking.add("A6o");
		ranking.add("A4o");
		ranking.add("KJo");
		ranking.add("QJs");
		ranking.add("A3o");
		ranking.add("22");
		ranking.add("K9s");
		ranking.add("A2o");
		ranking.add("KTo");
		ranking.add("QTs");
		ranking.add("K8s");
		ranking.add("K7s");
		ranking.add("JTs");
		ranking.add("K9o");
		ranking.add("K6s");
		ranking.add("QJo");
		ranking.add("Q9s");
		ranking.add("K5s");
		ranking.add("K8o");
		ranking.add("K4s");
		ranking.add("QTo");
		ranking.add("K7o");
		ranking.add("K3s");
		ranking.add("K2s");
		ranking.add("Q8s");
		ranking.add("K6o");
		ranking.add("J9s");
		ranking.add("K5o");
		ranking.add("Q9o");
		ranking.add("JTo");
		ranking.add("K4o");
		ranking.add("Q7s");
		ranking.add("T9s");
		ranking.add("Q6s");
		ranking.add("K3o");
		ranking.add("J8s");
		ranking.add("Q5s");
		ranking.add("K2o");
		ranking.add("Q8o");
		ranking.add("Q4s");
		ranking.add("J9o");
		ranking.add("Q3s");
		ranking.add("T8s");
		ranking.add("J7s");
		ranking.add("Q7o");
		ranking.add("Q2s");
		ranking.add("Q6o");
		ranking.add("98s");
		ranking.add("Q5o");
		ranking.add("J8o");
		ranking.add("T9o");
		ranking.add("J6s");
		ranking.add("T7s");
		ranking.add("J5s");
		ranking.add("Q4o");
		ranking.add("J4s");
		ranking.add("J7o");
		ranking.add("Q3o");
		ranking.add("97s");
		ranking.add("T8o");
		ranking.add("J3s");
		ranking.add("T6s");
		ranking.add("Q2o");
		ranking.add("J2s");
		ranking.add("87s");
		ranking.add("J6o");
		ranking.add("98o");
		ranking.add("T7o");
		ranking.add("96s");
		ranking.add("J5o");
		ranking.add("T5s");
		ranking.add("T4s");
		ranking.add("86s");
		ranking.add("J4o");
		ranking.add("T6o");
		ranking.add("97o");
		ranking.add("T3s");
		ranking.add("76s");
		ranking.add("95s");
		ranking.add("J3o");
		ranking.add("T2s");
		ranking.add("87o");
		ranking.add("85s");
		ranking.add("96o");
		ranking.add("T5o");
		ranking.add("J2o");
		ranking.add("75s");
		ranking.add("94s");
		ranking.add("T4o");
		ranking.add("65s");
		ranking.add("86o");
		ranking.add("93s");
		ranking.add("84s");
		ranking.add("95o");
		ranking.add("T3o");
		ranking.add("76o");
		ranking.add("92s");
		ranking.add("74s");
		ranking.add("54s");
		ranking.add("T2o");
		ranking.add("85o");
		ranking.add("64s");
		ranking.add("83s");
		ranking.add("94o");
		ranking.add("75o");
		ranking.add("82s");
		ranking.add("73s");
		ranking.add("93o");
		ranking.add("65o");
		ranking.add("53s");
		ranking.add("63s");
		ranking.add("84o");
		ranking.add("92o");
		ranking.add("43s");
		ranking.add("72s");
		ranking.add("74o");
		ranking.add("54o");
		ranking.add("62s");
		ranking.add("52s");
		ranking.add("64o");
		ranking.add("83o");
		ranking.add("42s");
		ranking.add("82o");
		ranking.add("73o");
		ranking.add("63o");
		ranking.add("53o");
		ranking.add("32s");
		ranking.add("43o");
		ranking.add("72o");
		ranking.add("62o");
		ranking.add("52o");
		ranking.add("42o");
		ranking.add("32o");
	}

	/**
	 * El metodo que devuelve el rango segun el porcentaje, cogiendo segun la
	 * lista del ranking
	 * 
	 * @param percentaje
	 *            el porcentaje que consulta
	 * @return la lista de rangos que incluye el porcentaje
	 */
	public List<String> getList(double percentaje) {
		List<String> aux = new ArrayList<String>();
		int nPair = 0, nOff = 0, nSuit = 0, i = 0;
		boolean reached = false;
		while (!reached && i < 169) {
			String act = ranking.get(i);
			if (act.length() == 2)
				nPair++;
			else if (act.charAt(2) == 's')
				nSuit++;
			else if (act.charAt(2) == 'o')
				nOff++;
			if (getPercent(nSuit, nPair, nOff) <= percentaje)
				aux.add(ranking.get(i));
			else
				reached = true;
			i++;
		}

		return aux;
	}

	/**
	 * El metodo para calcular el porcentaje del rango
	 * 
	 * @param Nsuited
	 *            el numero de suited
	 * @param Npar
	 *            el numero de par
	 * @param Noffsuited
	 *            el numero de offsuited
	 * @return el porcentaje del rango
	 */
	public double getPercent(int Nsuited, int Npar, int Noffsuited) {
		double total = 0;
		total = Nsuited * 4 + Npar * 6 + Noffsuited * 12;
		double percent = total / numCombinaciones;
		return percent * 100;
	}
}
