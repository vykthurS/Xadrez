package application;

import xadrez.Partida_Xadrez;

public class Main {

	public static void main(String[] args) {
		Partida_Xadrez partida=new Partida_Xadrez();
		UI.printTabu(partida.getPecas());

	}

}
