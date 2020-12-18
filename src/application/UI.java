package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Color;
import xadrez.Partida_Xadrez;
import xadrez.Peca_Xadrez;
import xadrez.XadrezPos;

public class UI {
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void cls (){
		try{
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}
		catch(Exception  e) {
			System.out.println(e);
		}
	}
	public static XadrezPos LerEntradaPos(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new XadrezPos(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException(
					"Erro ao instanciar a posi��o de xadrez, valores possiveis s�o de a1 at� h8");
		}
	}
	public static void printPartida(Partida_Xadrez partidaXadrez, List<Peca_Xadrez> capturadas) {
		printTabu(partidaXadrez.getPecas());
		System.out.println();
		printCaptP(capturadas);
		System.out.println("Turno: "+ partidaXadrez.getTurn());
		System.out.println("Esperando jogador: "+partidaXadrez.getPlayerTurn());
		if (partidaXadrez.getCheck()) {
			System.out.println("CHECK!!!");
		}
	}

	public static void printTabu(Peca_Xadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	public static void printTabu(Peca_Xadrez[][] pecas, boolean[][] moviposs) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], moviposs[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPeca(Peca_Xadrez pecas, boolean background){
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (pecas == null) {
			System.out.print("-"+ANSI_RESET);
		} else {
			if (pecas.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + pecas + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + pecas + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	private static void printCaptP(List<Peca_Xadrez>capturadas) {
		List<Peca_Xadrez>white=capturadas.stream().filter(x->x.getColor()==Color.WHITE).collect(Collectors.toList());
		List<Peca_Xadrez>black=capturadas.stream().filter(x->x.getColor()==Color.BLACK).collect(Collectors.toList());
		System.out.println("Pe�as capturadas: ");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.deepToString(white.toArray()));
		System.out.print(ANSI_RESET);
		System.out.println("Pe�as capturadas: ");
		System.out.print("Pretas: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.deepToString(black.toArray()));
		System.out.print(ANSI_RESET);
	}
}
