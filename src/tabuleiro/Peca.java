package tabuleiro;

public abstract class Peca {
	protected Posicao posicao;
	private Tabul tabu;
	public Peca(Tabul tabu) {
		this.tabu = tabu;
		posicao=null;
	}
	protected Tabul getTabu() {
		return tabu;
	}
	
	public abstract boolean[][] movimentosPoss();
	public boolean movimentosPossiveis(Posicao posicao) {
		return movimentosPoss()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean haMoviPoss() {
		boolean[][]mat=movimentosPoss();
		for(int i=0; i<mat.length;i++) {
			for(int j=0;j<mat.length;j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
