package acoes;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class jantar {
	
	public static ConcurrentLinkedQueue<String> passos() {
		Semaphore[] prov = new Semaphore[4];
		Semaphore[] perm = new Semaphore[4];
		for(int j = 0; j < 4; j++) {
			Semaphore sp = new Semaphore(1);
			prov[j] = sp;
			Semaphore pp = new Semaphore(1);
			perm[j] = pp;
		}
		filosofos.setSem(prov);
		filosofos.setPerms(perm);
		
		ConcurrentLinkedQueue<String> lista = new ConcurrentLinkedQueue<String>();
		filosofos.setFila(lista);
		
		for(int i = 0; i < 4; i++) {
			filosofos t = new filosofos(i);
			Thread te = new Thread(t);
			try {
				filosofos.getPerms()[i].acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			te.start();
		}
		
		for(int i = 0; i < 4; i++) {
			try {
				filosofos.getPerms()[i].acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*for(String s : filosofos.getFila()) {
			System.out.println(s);
		}
		
		System.out.println(filosofos.getFila().size());
		*/
		return filosofos.getFila();
	}
	
}
