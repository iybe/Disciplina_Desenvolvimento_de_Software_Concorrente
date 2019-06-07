package simulacao;

import javaPlay.GameEngine;

public class Main {

	public static void main(String[] args) {

		GameEngine.getInstance().addGameStateController(Global.ID_PRINCIPAL, new principal());

		GameEngine.getInstance().setStartingGameStateController(Global.ID_PRINCIPAL);

		GameEngine.getInstance().run();

	}

}
