package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabul;
import xadrez.Color;
import xadrez.Peca_Xadrez;

public class Cavalo extends Peca_Xadrez {

	public Cavalo(Tabul tabu, Color color) {
		super(tabu, color);
	}
	@Override
	public String toString() {
		return"C";
	}
	
	private boolean canMove(Posicao posicao) {
		Peca_Xadrez p=(Peca_Xadrez)getTabu().peca(posicao);
		return p ==null || p.getColor()!=getColor();
	}
	@Override
	public boolean[][] movimentosPoss() {
		boolean[][] mat=new boolean[getTabu().getLinhas()][getTabu().getColunas()];
		
		Posicao p= new Posicao(0,0);
		
		p.setValor(posicao.getLinha()-1, posicao.getColuna()-2);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha()-2, posicao.getColuna()-1);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha()-2, posicao.getColuna()+1);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha()-1, posicao.getColuna()+2);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		p.setValor(posicao.getLinha()+1, posicao.getColuna()+2);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha()+2, posicao.getColuna()+1);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha()+2, posicao.getColuna()-1);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		p.setValor(posicao.getLinha()+1, posicao.getColuna()-2);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		
		
		return mat;
	}
	

}
