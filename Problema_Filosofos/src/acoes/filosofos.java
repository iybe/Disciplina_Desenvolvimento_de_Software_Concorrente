package acoes;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class filosofos implements Runnable{
	
	private int id;
	private static Semaphore[] sem;
	private static ConcurrentLinkedQueue<String> fila;
	private static Semaphore[] perms;
	
	public filosofos(int id) {
		super();
		this.id = id;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(id == 0) {
			try {
				sem[id].acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getFila().add(id+" "+"pegou"+" "+id);
			//System.out.println(id+"pegou garfo"+id);
			
			try {
				sem[sem.length-1].acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getFila().add(id+" "+"pegou"+" "+(sem.length-1));
			//System.out.println(id+"pegou garfo"+(sem.length-1));
			
			getFila().add(id+" "+"comeu");
			//System.out.println(id+"comeu");
			
			sem[sem.length-1].release();
			getFila().add(id+" "+"soltou"+" "+(sem.length-1));
			//System.out.println(id+"soltou o garfo"+(sem.length-1));
			
			sem[id].release();
			getFila().add(id+" "+"soltou"+" "+id);
			//System.out.println(id+"soltou o garfo"+id);
		}else {
			try {
				sem[id-1].acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getFila().add(id+" "+"pegou"+" "+(id-1));
			//System.out.println(id+"pegou garfo"+(id-1));
			
			try {
				sem[id].acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getFila().add(id+" "+"pegou"+" "+id);
			//System.out.println(id+"pegou garfo"+id);
			
			getFila().add(id+" "+"comeu");
			//System.out.println(id+"comeu");
			
			sem[id-1].release();
			getFila().add(id+" "+"soltou"+" "+(id-1));
			//System.out.println(id+"soltou o garfo"+(id-1));
			
			sem[id].release();
			getFila().add(id+" "+"soltou"+" "+id);
			//System.out.println(id+"soltou o garfo"+id);
		}
		filosofos.perms[this.id].release();
	}
	
	public static Semaphore[] getSem() {
		return sem;
	}
	
	public static void setSem(Semaphore[] sem) {
		filosofos.sem = sem;
	}

	public static ConcurrentLinkedQueue<String> getFila() {
		return fila;
	}

	public static void setFila(ConcurrentLinkedQueue<String> fila) {
		filosofos.fila = fila;
	}

	public static Semaphore[] getPerms() {
		return perms;
	}

	public static void setPerms(Semaphore[] perms) {
		filosofos.perms = perms;
	}
	
}
