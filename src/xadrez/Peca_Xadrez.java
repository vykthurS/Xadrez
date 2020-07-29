package xadrez;

import tabuleiro.Peca;
import tabuleiro.Tabul;

public abstract class Peca_Xadrez extends Peca {
	private Color color;

	public Peca_Xadrez(Tabul tabu, Color color) {
		super(tabu);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	

}
