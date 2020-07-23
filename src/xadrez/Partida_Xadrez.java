package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabul;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

public class Partida_Xadrez {
	private Tabul tabu;

	public Partida_Xadrez() {
		tabu = new Tabul(8, 8);
		configIni();
	}

	public Peca_Xadrez[][] getPecas() {
		Peca_Xadrez[][] mat = new Peca_Xadrez[tabu.getLinhas()][tabu.getColunas()];
		for (int i = 0; i < tabu.getLinhas(); i++) {
			for (int j = 0; j < tabu.getColunas(); j++) {
				mat[i][j] = (Peca_Xadrez) tabu.peca(i, j);
			}
		}
		return mat;

	}
	public Peca_Xadrez performMovXad(XadrezPos posicaoOrigem, XadrezPos destPosicao) {
		Posicao origem= posicaoOrigem.toPosicao();
		Posicao dest= destPosicao.toPosicao();
		validaOrigemPos(origem);
		Peca pecaCapt=fazMov(origem,dest);
		return (Peca_Xadrez)pecaCapt;
	}
	private Peca fazMov(Posicao origem,Posicao dest) {
		Peca p=tabu.ramovePeca(origem);
		Peca pecaCapt=tabu.ramovePeca(dest);
		tabu.posicionamentoPeca(p, dest);
		return pecaCapt;
	}
	private void validaOrigemPos(Posicao posicao) {
		if (!tabu.existePeca(posicao)) {
			throw new XadrezException ("Não existe peça na posição de origem");
		}
	}
	

	private void posicaoNovaPeca(char coluna, int linha, Peca_Xadrez peca) {
		tabu.posicionamentoPeca(peca, new XadrezPos(coluna, linha).toPosicao());
	}

	private void configIni() {
		posicaoNovaPeca('c', 1, new Rook(tabu, Color.WHITE));
		posicaoNovaPeca('c', 2, new Rook(tabu, Color.WHITE));
		posicaoNovaPeca('d', 2, new Rook(tabu, Color.WHITE));
		posicaoNovaPeca('e', 2, new Rook(tabu, Color.WHITE));
		posicaoNovaPeca('e', 1, new Rook(tabu, Color.WHITE));
		posicaoNovaPeca('d', 1, new King(tabu, Color.WHITE));

		posicaoNovaPeca('c', 7, new Rook(tabu, Color.BLACK));
		posicaoNovaPeca('c', 8, new Rook(tabu, Color.BLACK));
		posicaoNovaPeca('d', 7, new Rook(tabu, Color.BLACK));
		posicaoNovaPeca('e', 7, new Rook(tabu, Color.BLACK));
		posicaoNovaPeca('e', 8, new Rook(tabu, Color.BLACK));
		posicaoNovaPeca('d', 8, new King(tabu, Color.BLACK));
	}

}
