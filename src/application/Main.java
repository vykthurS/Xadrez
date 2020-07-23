package application;

import java.util.Scanner;

import xadrez.Partida_Xadrez;
import xadrez.Peca_Xadrez;
import xadrez.XadrezPos;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Partida_Xadrez partida=new Partida_Xadrez();
		
		while (true) {
			UI.printTabu(partida.getPecas());
			System.out.print("Origem: ");
			XadrezPos origem=UI.LerEntradaPos(sc);
			System.out.println();
			System.out.print("Destino: ");
			XadrezPos dest=UI.LerEntradaPos(sc);
			Peca_Xadrez pecaCapt= partida.performMovXad(origem, dest);
		}

	}

}
