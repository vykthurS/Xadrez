package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabul;
import xadrez.Color;
import xadrez.Partida_Xadrez;
import xadrez.Peca_Xadrez;

public class King extends Peca_Xadrez {
	
	private Partida_Xadrez partidaXadrez;

	public King(Tabul tabu, Color color, Partida_Xadrez partidaXadrez) {
		super(tabu, color);
		this.partidaXadrez=partidaXadrez;
	}
	@Override
	public String toString() {
		return"K";
	}
	
	private boolean canMove(Posicao posicao) {
		Peca_Xadrez p=(Peca_Xadrez)getTabu().peca(posicao);
		return p ==null || p.getColor()!=getColor();
	}
	
	private boolean TestaTorreRook(Posicao posicao) {
		Peca_Xadrez p=(Peca_Xadrez)getTabu().peca(posicao);
		return p!=null && p.getColor()==getColor()&&p.getContMov()==0;
	}
	
	@Override
	public boolean[][] movimentosPoss() {
		boolean[][] mat=new boolean[getTabu().getLinhas()][getTabu().getColunas()];
		
		Posicao p= new Posicao(0,0);
		
		p.setValor(posicao.getLinha()-1, posicao.getColuna());
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha()+1, posicao.getColuna());
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha(), posicao.getColuna()-1);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha(), posicao.getColuna()+1);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		p.setValor(posicao.getLinha()-1, posicao.getColuna()-1);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha()-1, posicao.getColuna()+1);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		p.setValor(posicao.getLinha()+1, posicao.getColuna()-1);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		p.setValor(posicao.getLinha()+1, posicao.getColuna()+1);
		if(getTabu().existenciaPosicao(p)&& canMove(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		
		if(getContMov()==0 && !partidaXadrez.getCheck()) {
			Posicao post1=new Posicao(posicao.getLinha(),posicao.getColuna()+3);
			if(TestaTorreRook(post1)) {
				Posicao p1=new Posicao(posicao.getLinha(),posicao.getColuna()+1);
				Posicao p2=new Posicao(posicao.getLinha(),posicao.getColuna()+2);
				if(getTabu().peca(p1)==null && getTabu().peca(p2)==null) {
					mat[posicao.getLinha()][posicao.getColuna()+2]=true;
				}
			}
			Posicao post2=new Posicao(posicao.getLinha(),posicao.getColuna()-4);
			if(TestaTorreRook(post2)) {
				Posicao p1=new Posicao(posicao.getLinha(),posicao.getColuna()-1);
				Posicao p2=new Posicao(posicao.getLinha(),posicao.getColuna()-2);
				Posicao p3=new Posicao(posicao.getLinha(),posicao.getColuna()-3);
				if(getTabu().peca(p1)==null && getTabu().peca(p2)==null && getTabu().peca(p3)==null) {
					mat[posicao.getLinha()][posicao.getColuna()+2]=true;
				}
			}
			
		}
		
		
		
		return mat;
	}
	

}
