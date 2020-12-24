package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabul;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.King;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rook;

public class Partida_Xadrez {
	
	private int turn;
	private Color playerTurn;
	
	private Tabul tabu;
	private boolean check;
	private boolean checkMate;
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
	public boolean getCheck() {
		return check;
	}
	public boolean getCheckMate() {
		return checkMate;
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
		if (testeCheck(playerTurn)) {
			DesfazMov(origem,dest,pecaCapt);
			throw new XadrezException("Você não pode se colocar em Check");
			
		}
		check=(testeCheck(oponente(playerTurn)))? true:false;
		
		if (testeCheckMate(oponente(playerTurn))) {
			checkMate=true;
		}
		else {
			nextTurn();
		}
		return (Peca_Xadrez)pecaCapt;
	}
	private Peca fazMov(Posicao origem,Posicao dest) {
		Peca_Xadrez p=(Peca_Xadrez)tabu.removePeca(origem);
		p.aumentaContMov();
		Peca pecaCapt=tabu.removePeca(dest);
		tabu.posicionamentoPeca(p, dest);
		
		if(pecaCapt!=null) {
			pecaNoTab.remove(pecaCapt);
			this.pecaCapt.add(pecaCapt);
		}
		return pecaCapt;
	}
	
	private void DesfazMov(Posicao origem, Posicao dest, Peca pecaCapt) {
		Peca_Xadrez p=(Peca_Xadrez)tabu.removePeca(dest);
		p.decrementaContMov();
		tabu.posicionamentoPeca(p, origem);
		
		if (pecaCapt != null) {
			tabu.posicionamentoPeca(pecaCapt, dest);
			this.pecaCapt.remove(pecaCapt);
			pecaNoTab.add(pecaCapt);
		}
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
	
	private Color oponente (Color color) {
		return(color==Color.WHITE)? Color.BLACK : Color.WHITE;
	}
	
	private Peca_Xadrez king (Color color) {
		List<Peca>list=pecaNoTab.stream().filter(x->((Peca_Xadrez)x).getColor()==color).collect(Collectors.toList());
		for (Peca p:list) {
			if (p instanceof King) {
				return (Peca_Xadrez)p;
			}
		}
		throw new IllegalStateException("Não existe rei da cor " + color + " no tabuleiro");
	}
	
	private boolean testeCheck(Color color) {
		Posicao kingPosicao=king(color).getPosXadrez().toPosicao();
		List<Peca> oponentePecas=pecaNoTab.stream().filter(x->((Peca_Xadrez)x).getColor()==oponente(color)).collect(Collectors.toList());
		for (Peca p: oponentePecas) {
			boolean[][] mat = p.movimentosPoss();
			if(mat[kingPosicao.getLinha()][kingPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	private boolean testeCheckMate(Color color) {
		if(!testeCheck(color)){
			return false;
		}
		List <Peca> list = pecaNoTab.stream().filter(x->((Peca_Xadrez)x).getColor()==color).collect(Collectors.toList());
		for(Peca p:list) {
			boolean[][] mat=p.movimentosPoss();
			for (int i=0; i<tabu.getLinhas();i++) {
				for(int j=0;j<tabu.getColunas();j++) {
					if(mat[i][j]) {
						Posicao origem=((Peca_Xadrez)p).getPosXadrez().toPosicao();
						Posicao dest=new Posicao(i,j);
						Peca pecaCapt = fazMov(origem,dest);
						boolean testeCheck=testeCheck(color);
						DesfazMov(origem,dest,pecaCapt);
						if(!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void posicaoNovaPeca(char coluna, int linha, Peca_Xadrez peca) {
		tabu.posicionamentoPeca(peca, new XadrezPos(coluna, linha).toPosicao());
		pecaNoTab.add(peca);
	}

	private void configIni() {
		posicaoNovaPeca('a', 1, new Rook(tabu, Color.WHITE));
		posicaoNovaPeca('b', 1, new Cavalo(tabu, Color.WHITE));
		posicaoNovaPeca('c', 1, new Bispo(tabu, Color.WHITE));
		posicaoNovaPeca('d', 1, new Rainha(tabu, Color.WHITE));
		posicaoNovaPeca('e', 1, new King(tabu, Color.WHITE));
		posicaoNovaPeca('f', 1, new Bispo(tabu, Color.WHITE));
		posicaoNovaPeca('g', 1, new Cavalo(tabu, Color.WHITE));
		posicaoNovaPeca('h', 1, new Rook(tabu, Color.WHITE));
		posicaoNovaPeca('a', 2, new Peao(tabu, Color.WHITE));
		posicaoNovaPeca('b', 2, new Peao(tabu, Color.WHITE));
		posicaoNovaPeca('c', 2, new Peao(tabu, Color.WHITE));
		posicaoNovaPeca('d', 2, new Peao(tabu, Color.WHITE));
		posicaoNovaPeca('e', 2, new Peao(tabu, Color.WHITE));
		posicaoNovaPeca('f', 2, new Peao(tabu, Color.WHITE));
		posicaoNovaPeca('g', 2, new Peao(tabu, Color.WHITE));
		posicaoNovaPeca('h', 2, new Peao(tabu, Color.WHITE));
		
		posicaoNovaPeca('a', 8, new Rook(tabu, Color.BLACK));
		posicaoNovaPeca('b', 8, new Cavalo(tabu, Color.BLACK));
		posicaoNovaPeca('c', 8, new Bispo(tabu, Color.BLACK));
		posicaoNovaPeca('d', 8, new Rainha(tabu, Color.BLACK));
		posicaoNovaPeca('e', 8, new King(tabu, Color.BLACK));
		posicaoNovaPeca('f', 8, new Bispo(tabu, Color.BLACK));
		posicaoNovaPeca('g', 8, new Cavalo(tabu, Color.BLACK));
		posicaoNovaPeca('h', 8, new Rook(tabu, Color.BLACK));
		posicaoNovaPeca('a', 7, new Peao(tabu, Color.BLACK));
		posicaoNovaPeca('b', 7, new Peao(tabu, Color.BLACK));
		posicaoNovaPeca('c', 7, new Peao(tabu, Color.BLACK));
		posicaoNovaPeca('d', 7, new Peao(tabu, Color.BLACK));
		posicaoNovaPeca('e', 7, new Peao(tabu, Color.BLACK));
		posicaoNovaPeca('f', 7, new Peao(tabu, Color.BLACK));
		posicaoNovaPeca('g', 7, new Peao(tabu, Color.BLACK));
		posicaoNovaPeca('h', 7, new Peao(tabu, Color.BLACK));
	}

}
