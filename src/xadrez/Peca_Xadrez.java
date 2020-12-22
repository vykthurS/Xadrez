package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabul;

public abstract class Peca_Xadrez extends Peca {
	private Color color;
	private int contMov;

	public Peca_Xadrez(Tabul tabu, Color color) {
		super(tabu);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getContMov() {
		return contMov;
	}

	public void aumentaContMov() {
		contMov++;
	}
	public void decrementaContMov() {
		contMov--;
	}
	public XadrezPos getPosXadrez() {
		return XadrezPos.PosicaoOrigem(posicao);
	}
	
	protected boolean hapecaoponente(Posicao posicao) {
		Peca_Xadrez p=(Peca_Xadrez)getTabu().peca(posicao);
		return p!=null && p.getColor()!=color;
	}
	

}
