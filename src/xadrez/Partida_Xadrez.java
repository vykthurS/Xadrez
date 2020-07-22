package xadrez;


import tabuleiro.Tabul;

public class Partida_Xadrez {
	private Tabul tabu;
	public Partida_Xadrez() {
		tabu=new Tabul(8,8);
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

}
