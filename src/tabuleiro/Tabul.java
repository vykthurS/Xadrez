package tabuleiro;

public class Tabul {
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	public Tabul(int linhas, int colunas) {
		if(linhas < 1 || colunas<1) {
			throw new TabulException("Erro ao criar tabuleiro: é nescessário que haja ao menos uma linha e uma coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}
	public int getLinhas() {
		return linhas;
	}
	
	public int getColunas() {
		return colunas;
	
	}
	public Peca peca(int linha,int coluna) {
		if(!existenciaPosicao(linha,coluna)) {
			throw new TabulException("Não existe esta posição no tabuleiro");
			
		}
		return pecas[linha][coluna];
	}
	public Peca peca(Posicao posicao) {
		if(!existenciaPosicao(posicao)) {
			throw new TabulException("Não existe esta posição");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	public void posicionamentoPeca(Peca peca,Posicao posicao) {
		if(existePeca(posicao)) {
			throw new TabulException("Há uma peça na posição "+posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()]=peca;
		peca.posicao=posicao;
		
	}
	public Peca removePeca(Posicao posicao) {
		if(!existenciaPosicao(posicao)) {
			throw new TabulException("Não existe esta posição");
		}
		if (peca(posicao)==null) {
			return null;
		}
		Peca aux=peca(posicao);
		aux.posicao=null;
		pecas[posicao.getLinha()][posicao.getColuna()]=null;
		return aux;
	}
	private boolean existenciaPosicao(int linha, int coluna) {
		return linha>=0 && linha< linhas && coluna>=0 && coluna<colunas;
	}
	
	public boolean existenciaPosicao(Posicao posicao) {
		return existenciaPosicao(posicao.getLinha(),posicao.getColuna());
	}
	public boolean existePeca(Posicao posicao) {
		if(!existenciaPosicao(posicao)) {
			throw new TabulException("Não existe esta posição");
		}
		return peca(posicao)!=null;
		
	}
		
	
	
	
	
}
