package classes;

import java.util.Stack;

public class arvoreBinariaBusca implements Runnable{
	private no raiz;
	private int id;

	public arvoreBinariaBusca(int id) {
		super();
		this.raiz = null;
		this.id = id;
	}
	
	public void inserir(int valor) {
		if(this.raiz == null) {
			this.raiz = new no(valor);
		}else {
			this.raiz = inserirArvore(this.raiz,valor);
		}
	}

	private no inserirArvore(no raiz2, int valor) {
		if(valor >= raiz2.chave) {
			if(raiz2.dir == null) {
				raiz2.dir = new no(valor);
			}else {
				raiz2.dir = this.inserirArvore(raiz2.dir, valor);
			}
		}else {
			if(raiz2.esq == null) {
				raiz2.esq = new no(valor);
			}else {
				raiz2.esq = this.inserirArvore(raiz2.esq, valor);
			}
		}
		return raiz2;
	}
	
	public void preOrdem() {
		this.preOrdemA(this.raiz);
	}
	
	private void preOrdemA(no raiz2) {
		if(raiz2 == null) {
			return;
		}
		this.preOrdemA(raiz2.esq);
		System.out.println(raiz2.chave);
		this.preOrdemA(raiz2.dir);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Stack<no> pilha = new Stack<no>();
		pilha.push(this.raiz);
		
		while(!pilha.empty()) {
			no atual = pilha.pop();
			if(atual.esq == null && atual.dir == null) {
				if(this.id == 1) {
					variaveis.folhaAtualA = atual.chave;
					variaveis.AtravaC.release();
					try {
						variaveis.travaA.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					variaveis.folhaAtualB = atual.chave;
					variaveis.BtravaC.release();
					try {
						variaveis.travaB.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else {
				if(atual.dir != null) {
					pilha.push(atual.dir);
				}
				if(atual.esq != null){
					pilha.push(atual.esq);
				}
			}
		}
		
		if(this.id == 1) {
			variaveis.terminoA = true;
			variaveis.AtravaC.release();
		}else {
			variaveis.terminoB = true;
			variaveis.BtravaC.release();
		}
		
	}
	
	public int qtdFolhas() {
		return this.qtdFolhasArvore(this.raiz);
	}

	private int qtdFolhasArvore(no raiz2) {
		if(raiz2 == null) {
			return 0;
		}
		if(raiz2.esq == null && raiz2.dir == null) {
			return 1;
		}
		return (qtdFolhasArvore(raiz2.esq) + qtdFolhasArvore(raiz2.dir));
	}
	
}
