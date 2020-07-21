package tabuleiro;

public class Peca {
	protected Posicao posicao;
	private Tabul tabu;
	public Peca(Tabul tabu) {
		this.tabu = tabu;
		posicao=null;
	}
	protected Tabul getTabu() {
		return tabu;
	}
	
	

}
