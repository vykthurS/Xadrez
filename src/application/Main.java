package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Partida_Xadrez;
import xadrez.Peca_Xadrez;
import xadrez.XadrezException;
import xadrez.XadrezPos;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Partida_Xadrez partida=new Partida_Xadrez();
		
		while (true) {
			try {
				UI.cls();
				UI.printPartida(partida);
				System.out.println();
				System.out.print("Origem: ");
				XadrezPos origem=UI.LerEntradaPos(sc);
				
				boolean[][] moviposs = partida.moviposs(origem);
				UI.cls();
				UI.printTabu(partida.getPecas(),moviposs);
				
				System.out.println();
				System.out.print("Destino: ");
				XadrezPos dest=UI.LerEntradaPos(sc);
				
				Peca_Xadrez pecaCapt= partida.performMovXad(origem, dest);
			}
			catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}
