package xadrez;


import tabuleiro.Posicao;
import tabuleiro.Tabul;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

public class Partida_Xadrez {
	private Tabul tabu;
	public Partida_Xadrez() {
		tabu=new Tabul(8,8);
		configIni();
	}
	public Peca_Xadrez[][] getPecas(){
		Peca_Xadrez[][] mat=new Peca_Xadrez[tabu.getLinhas()][tabu.getColunas()];
		for(int i=0;i<tabu.getLinhas();i++) {
			for(int j=0; j<tabu.getColunas();j++) {
				mat[i][j]=(Peca_Xadrez)tabu.peca(i, j);
			}
		}
		return mat;
		
	}
	private void configIni() {
		tabu.posicionamentoPeca(new Rook(tabu,Color.WHITE),new Posicao(2,1));
		tabu.posicionamentoPeca(new King(tabu,Color.BLACK),new Posicao(0,4));
		tabu.posicionamentoPeca(new King(tabu,Color.WHITE),new Posicao(7,4));
	}

}
