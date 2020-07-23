package xadrez;


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
	private void posicaoNovaPeca(char coluna, int linha, Peca_Xadrez peca) {
		tabu.posicionamentoPeca(peca, new XadrezPos(coluna,linha).toPosicao());
	}
	private void configIni() {
		posicaoNovaPeca('b',6, new Rook(tabu,Color.WHITE));
		posicaoNovaPeca('e',8, new King(tabu,Color.BLACK));
		posicaoNovaPeca('e',1,new King(tabu,Color.WHITE));
	}

}
