package tabuleiro;

public class Tabul {
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	public Tabul(int linhas, int colunas) {
		super();
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}
	public int getLinhas() {
		return linhas;
	}
	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}
	public int getColunas() {
		return colunas;
	}
	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	
	
	
}
