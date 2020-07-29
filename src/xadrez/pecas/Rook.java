package xadrez.pecas;

import tabuleiro.Tabul;
import xadrez.Color;
import xadrez.Peca_Xadrez;

public class Rook extends Peca_Xadrez {

	public Rook(Tabul tabu, Color color) {
		super(tabu, color);
	}
	@Override
	public String toString() {
		return "R";
	}
	@Override
	public boolean[][] movimentosPoss() {
		boolean[][] mat=new boolean[getTabu().getLinhas()][getTabu().getColunas()];
		return mat;
	}

}
