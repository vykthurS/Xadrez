package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabul;
import xadrez.Color;
import xadrez.Peca_Xadrez;

public class Peao extends Peca_Xadrez{

	public Peao(Tabul tabu, Color color) {
		super(tabu, color);
	}

	@Override
	public boolean[][] movimentosPoss() {
		boolean[][] mat=new boolean[getTabu().getLinhas()][getTabu().getColunas()];
		
		Posicao p=new Posicao(0,0);
		
		if(getColor()==Color.WHITE) {
			p.setValor(posicao.getLinha()-1,posicao.getColuna());
			if(getTabu().existenciaPosicao(p)&& !getTabu().existePeca(p)) {
				mat[p.getLinha()][p.getColuna()]=true;
			}
			
			p.setValor(posicao.getLinha()-2,posicao.getColuna());
			Posicao p2=new Posicao(posicao.getLinha()-1,posicao.getColuna());
			if(getTabu().existenciaPosicao(p)&& !getTabu().existePeca(p)&&getTabu().existenciaPosicao(p2)&& !getTabu().existePeca(p2) && getContMov()==0) {
				mat[p.getLinha()][p.getColuna()]=true;
			}
			p.setValor(posicao.getLinha()-1,posicao.getColuna()-1);
			if(getTabu().existenciaPosicao(p)&& hapecaoponente(p)) {
				mat[p.getLinha()][p.getColuna()]=true;
			}
			p.setValor(posicao.getLinha()-1,posicao.getColuna()+1);
			if(getTabu().existenciaPosicao(p)&& hapecaoponente(p)) {
				mat[p.getLinha()][p.getColuna()]=true;
			}
		
		}
		else {
			p.setValor(posicao.getLinha()+1,posicao.getColuna());
			if(getTabu().existenciaPosicao(p)&& !getTabu().existePeca(p)) {
				mat[p.getLinha()][p.getColuna()]=true;
			}
			
			p.setValor(posicao.getLinha()+2,posicao.getColuna());
			Posicao p2=new Posicao(posicao.getLinha()+1,posicao.getColuna());
			if(getTabu().existenciaPosicao(p)&& !getTabu().existePeca(p)&&getTabu().existenciaPosicao(p2)&& !getTabu().existePeca(p2) && getContMov()==0) {
				mat[p.getLinha()][p.getColuna()]=true;
			}
			p.setValor(posicao.getLinha()+1,posicao.getColuna()-1);
			if(getTabu().existenciaPosicao(p)&& hapecaoponente(p)) {
				mat[p.getLinha()][p.getColuna()]=true;
			}
			p.setValor(posicao.getLinha()+1,posicao.getColuna()+1);
			if(getTabu().existenciaPosicao(p)&& hapecaoponente(p)) {
				mat[p.getLinha()][p.getColuna()]=true;
			}
		}
		return mat;
	}
	@Override
	public String toString() {
		return "P";
	}
	

	}
