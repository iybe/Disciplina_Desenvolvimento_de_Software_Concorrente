package classes;

import java.util.concurrent.Semaphore;

public class controle implements Runnable{
	
	public controle() {
		variaveis.travaA = new Semaphore(0);
		variaveis.travaB = new Semaphore(0);
		variaveis.AtravaC = new Semaphore(0);
		variaveis.BtravaC = new Semaphore(0);
		variaveis.terminoA = false;
		variaveis.terminoB = false;
	}

	@Override
	public void run() {
		boolean iguais = true;
		while(!variaveis.terminoA || !variaveis.terminoB) {
			
			try {
				variaveis.AtravaC.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				variaveis.BtravaC.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(variaveis.folhaAtualA != variaveis.folhaAtualB) {
				iguais = false;
			}
			
			variaveis.travaA.release();
			variaveis.travaB.release();
		}
		
		if(iguais) {
			System.out.println("Sequencia igual de folhas");
		}else {
			System.out.println("Sequencia Diferente de folhas");
		}
		
	}
}
