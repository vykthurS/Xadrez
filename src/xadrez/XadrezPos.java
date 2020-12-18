package xadrez;

import tabuleiro.Posicao;

public class XadrezPos {
	private char coluna;
	private int linha;
	public XadrezPos(char coluna, int linha) {
		if(coluna<'a'|| coluna>'h'||linha<1||linha>8) {
			throw new XadrezException("Erro ao instanciar a posi��o de xadrez, valores possiveis s�o de a1 at� h8 ");
		}
		this.coluna = coluna;
		this.linha = linha;
	}
	public char getColuna() {
		return coluna;
	
	}
	public int getLinha() {
		return linha;
	}
	protected Posicao toPosicao() {
		return new Posicao(8-linha,coluna-'a');
		
	}
	protected static XadrezPos PosicaoOrigem(Posicao posicao) {
		return new XadrezPos((char)('a'+posicao.getColuna()),8-posicao.getLinha());
	}
	@Override
	public String toString() {
		return ""+ coluna + linha;
	}
	
	
	

}
