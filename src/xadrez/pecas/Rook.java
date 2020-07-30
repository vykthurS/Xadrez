package xadrez.pecas;

import tabuleiro.Posicao;
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
		
		Posicao p=new Posicao(0,0);
		p.setValor(posicao.getLinha()-1,posicao.getColuna());
		while (getTabu().existenciaPosicao(p)&& !getTabu().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
			p.setLinha(p.getLinha()-1);
		}
		if(getTabu().existenciaPosicao(p)&& hapecaoponente(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha(),posicao.getColuna()-1);
		while (getTabu().existenciaPosicao(p)&& !getTabu().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
			p.setColuna((p.getColuna()-1));
		}
		if(getTabu().existenciaPosicao(p)&& hapecaoponente(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha(),posicao.getColuna()+1);
		while (getTabu().existenciaPosicao(p)&& !getTabu().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
			p.setColuna((p.getColuna()+1));
		}
		if(getTabu().existenciaPosicao(p)&& hapecaoponente(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha()+1,posicao.getColuna());
		while (getTabu().existenciaPosicao(p)&& !getTabu().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
			p.setLinha(p.getLinha()+1);
		}
		if(getTabu().existenciaPosicao(p)&& hapecaoponente(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		return mat;
	}

}
