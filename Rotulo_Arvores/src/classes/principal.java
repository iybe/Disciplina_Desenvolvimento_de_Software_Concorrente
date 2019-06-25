package classes;

import java.util.Scanner;

public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		arvoreBinariaBusca A = new arvoreBinariaBusca(1);
		arvoreBinariaBusca B = new arvoreBinariaBusca(0);
		controle checagem = new controle();
		
		Scanner ler = new Scanner(System.in);
		
		System.out.print("Digite a quantidade de nos da arvore A: ");
		int n = ler.nextInt();
		
		int ent1;
		System.out.println("Digite a sequencia de nos da arvore A:");
		for(int i = 0; i < n; i++) {
			ent1 = ler.nextInt();
			A.inserir(ent1);
		}
		
		System.out.println("Digite a quantidade de nos da arvore B: ");
		n = ler.nextInt();
		
		System.out.println("Digite a sequencia de nos da arvore B:");
		for(int i = 0; i < n; i++) {
			ent1 = ler.nextInt();
			B.inserir(ent1);
		}
		
		if(A.qtdFolhas() != B.qtdFolhas()) {
			System.out.println("Quantidade diferente de folhas");
		}else {
			Thread Checar = new Thread(checagem);
			Checar.start();
			
			Thread arvoreA = new Thread(A);
			arvoreA.start();
			
			Thread arvoreB = new Thread(B);
			arvoreB.start();
		}
		
	}

}
