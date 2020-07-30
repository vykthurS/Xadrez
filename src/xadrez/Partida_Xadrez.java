package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabul;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

public class Partida_Xadrez {
	
	private int turn;
	private Color playerTurn;
	
	private Tabul tabu;
	private List<Peca>pecaNoTab=new ArrayList<>();
	private List<Peca>pecaCapt=new ArrayList<>();

	public Partida_Xadrez() {
		tabu = new Tabul(8, 8);
		turn=1;
		playerTurn=Color.WHITE;
		configIni();
	}
	public int getTurn() {
		return turn;
	}
	public Color getPlayerTurn() {
		return playerTurn;
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
	public boolean[][] moviposs(XadrezPos origemPosicao){
		Posicao posicao=origemPosicao.toPosicao();
		validaOrigemPos(posicao);
		return tabu.peca(posicao).movimentosPoss();
	} 
	public Peca_Xadrez performMovXad(XadrezPos posicaoOrigem, XadrezPos destPosicao) {
		Posicao origem= posicaoOrigem.toPosicao();
		Posicao dest= destPosicao.toPosicao();
		validaOrigemPos(origem);
		validaDestPos(origem,dest);
		Peca pecaCapt=fazMov(origem,dest);
		nextTurn();
		return (Peca_Xadrez)pecaCapt;
	}
	private Peca fazMov(Posicao origem,Posicao dest) {
		Peca p=tabu.removePeca(origem);
		Peca pecaCapt=tabu.removePeca(dest);
		tabu.posicionamentoPeca(p, dest);
		
		if(pecaCapt!=null) {
			pecaNoTab.remove(pecaCapt);
			this.pecaCapt.add(pecaCapt);
		}
		return pecaCapt;
	}
	private void validaOrigemPos(Posicao posicao) {
		if (!tabu.existePeca(posicao)) {
			throw new XadrezException ("Não existe peça na posição de origem");
		}
		if(playerTurn !=((Peca_Xadrez)tabu.peca(posicao)).getColor()) {
			throw new XadrezException("A peça escolhida não é sua");
		}
		if(!tabu.peca(posicao).haMoviPoss()) {
			throw new XadrezException("Não há movimentos possiveis para essa peça");
		}
	}
	
	private void validaDestPos(Posicao origem,Posicao dest) {
		if(!tabu.peca(origem).movimentosPossiveis(dest)){
			throw new XadrezException("A peça escolhida não pode ser movimentada para a posição de destino");
		}
	}
	
	
	private void nextTurn() {
		turn++;
		playerTurn=(playerTurn == Color.WHITE)? Color.BLACK : Color.WHITE;
	}

	private void posicaoNovaPeca(char coluna, int linha, Peca_Xadrez peca) {
		tabu.posicionamentoPeca(peca, new XadrezPos(coluna, linha).toPosicao());
		pecaNoTab.add(peca);
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
