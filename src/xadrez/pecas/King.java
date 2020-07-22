package xadrez.pecas;

import tabuleiro.Tabul;
import xadrez.Color;
import xadrez.Peca_Xadrez;

public class King extends Peca_Xadrez {

	public King(Tabul tabu, Color color) {
		super(tabu, color);
	}
	@Override
	public String toString() {
		return"K";
	}
	

}
