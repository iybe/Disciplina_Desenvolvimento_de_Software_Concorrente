package simulacao;

import java.awt.Graphics;
import java.util.ArrayList;
import acoes.jantar;
import javaPlay.GameStateController;
import javaPlay.Sprite;

public class principal implements GameStateController {

	private Sprite mesa;
	private Sprite imagemFilosofo1Fome;
	private Sprite imagemFilosofo2Fome;
	private Sprite imagemFilosofo3Fome;
	private Sprite imagemFilosofo4Fome;
	private Sprite imagemFilosofo1Comendo;
	private Sprite imagemFilosofo2Comendo;
	private Sprite imagemFilosofo3Comendo;
	private Sprite imagemFilosofo4Comendo;
	private Sprite imagemFilosofo1Comeu;
	private Sprite imagemFilosofo2Comeu;
	private Sprite imagemFilosofo3Comeu;
	private Sprite imagemFilosofo4Comeu;
	private filosofo filosofo1;
	private filosofo filosofo2;
	private filosofo filosofo3;
	private filosofo filosofo4;
	
	private prato prato1;
	private prato prato2;
	private prato prato3;
	private prato prato4;
	private Sprite pratoCheio;
	private Sprite pratoVazio;
	
	private faca faca1;
	private faca faca2;
	private faca faca3;
	private faca faca4;
	private Sprite spriteFaca1;
	private Sprite spriteFaca2;
	private Sprite spriteFaca3;
	private Sprite spriteFaca4;
	
	private ArrayList<String> detalhesJanta;
	private int indiceAcoes;
	private boolean agiu; 
	
	private long ultimoClique = System.currentTimeMillis();
	
	public void load() {
		try {
			mesa = new Sprite("img/mesa.png", 1, 900, 700);
			imagemFilosofo1Fome = new Sprite("img/f1_fome.png",1,149,96);
			imagemFilosofo2Fome = new Sprite("img/f2_fome.png",1,96,149);
			imagemFilosofo3Fome = new Sprite("img/f3_fome.png",1,149,96);
			imagemFilosofo4Fome = new Sprite("img/f4_fome.png",1,96,149);
			imagemFilosofo1Comendo = new Sprite("img/f1_comendo.png",1,149,96);
			imagemFilosofo2Comendo = new Sprite("img/f2_comendo.png",1,96,149);
			imagemFilosofo3Comendo = new Sprite("img/f3_comendo.png",1,149,96);
			imagemFilosofo4Comendo = new Sprite("img/f4_comendo.png",1,96,149);
			imagemFilosofo1Comeu = new Sprite("img/f1_comeu.png",1,149,96);
			imagemFilosofo2Comeu = new Sprite("img/f2_comeu.png",1,96,149);
			imagemFilosofo3Comeu = new Sprite("img/f3_comeu.png",1,149,96);
			imagemFilosofo4Comeu = new Sprite("img/f4_comeu.png",1,96,149);
			
			filosofo1 = new filosofo(375,29,imagemFilosofo1Fome,imagemFilosofo1Comendo,imagemFilosofo1Comeu);
			filosofo2 = new filosofo(687,280,imagemFilosofo2Fome,imagemFilosofo2Comendo,imagemFilosofo2Comeu);
			filosofo3 = new filosofo(375,580,imagemFilosofo3Fome,imagemFilosofo3Comendo,imagemFilosofo3Comeu);
			filosofo4 = new filosofo(117,280,imagemFilosofo4Fome,imagemFilosofo4Comendo,imagemFilosofo4Comeu);
			
			pratoCheio = new Sprite("img/pratocheio.png",1,74,75);
			pratoVazio = new Sprite("img/pratovazio.png",1,74,75);
			
			prato1 = new prato(412,165,pratoCheio,pratoVazio);
			prato2 = new prato(575,317,pratoCheio,pratoVazio);
			prato3 = new prato(412,465,pratoCheio,pratoVazio);
			prato4 = new prato(263,317,pratoCheio,pratoVazio);
			
			spriteFaca1 = new Sprite("img/garfo1.png",1,48,49);
			spriteFaca2 = new Sprite("img/garfo2.png",1,48,49);
			spriteFaca3 = new Sprite("img/garfo3.png",1,48,49);
			spriteFaca4 = new Sprite("img/garfo4.png",1,48,49);
			
			faca1 = new faca(502,230,spriteFaca1,1);
			faca2 = new faca(502,411,spriteFaca2,2);
			faca3 = new faca(333,411,spriteFaca3,3);
			faca4 = new faca(333,230,spriteFaca4,4);
			
			this.detalhesJanta = new ArrayList<String>();
			for(String s : jantar.passos()) {
				System.out.println(s);
				this.detalhesJanta.add(s);
			}
			
			this.indiceAcoes = -1;
			this.agiu = true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void unload() {

	}

	public void start() {

	}

	public void step(long timeElapsed) {
		
		if((System.currentTimeMillis() - this.ultimoClique) > 100) {
			this.ultimoClique = System.currentTimeMillis();
            this.indiceAcoes++;
            if(this.indiceAcoes <= 19) {
            	System.out.println(detalhesJanta.get(indiceAcoes));
            	this.agiu = false;
            }else {
            	this.detalhesJanta = new ArrayList<String>();
            	for(String s : jantar.passos()) {
    				System.out.println(s);
    				this.detalhesJanta.add(s);
    			}
            	this.indiceAcoes = -1;
    			this.agiu = true;
    			
    			filosofo1.setEstado(1);
    			filosofo2.setEstado(1);
    			filosofo3.setEstado(1);
    			filosofo4.setEstado(1);
    			prato1.setEstado(1);
    			prato2.setEstado(1);
    			prato3.setEstado(1);
    			prato4.setEstado(1);
    			
            }
		}
		
		if(!this.agiu) {
			this.agiu = true;
			
			String acaoAtual = detalhesJanta.get(indiceAcoes);
			int qtdVazios = 0;
			for(int ind = 0; ind < acaoAtual.length(); ind++) {
				if(acaoAtual.charAt(ind) == ' ') {
					qtdVazios++;
				}
			}
			// 0 -> filosofo 1 , n -> filosofo n+1
			// 0 -> faca 1 , n -> faca n+1
			if(qtdVazios == 1) {
				if(acaoAtual.charAt(0) == '0') {
					this.prato1.setEstado(2);
					filosofo1.setEstado(3);
				}else if(acaoAtual.charAt(0) == '1') {
					this.prato2.setEstado(2);
					filosofo2.setEstado(3);
				}else if(acaoAtual.charAt(0) == '2') {
					this.prato3.setEstado(2);
					filosofo3.setEstado(3);
				}else {
					filosofo4.setEstado(3);
					this.prato4.setEstado(2);
				}
			}else {
				String arr[] = new String[3];
				arr = acaoAtual.split(" ");
				System.out.println(arr[1]);
				
				if(arr[1].equals("pegou")) {
					if(acaoAtual.charAt(0) == '0') {
						//filofo 1 pegou
						filosofo1.pegouGarfo();
						if(acaoAtual.charAt(acaoAtual.length()-1) == '0') {
							//faca 1
							faca1.pegou(1);
						}else {
							//faca 4
							faca4.pegou(1);
						}
					}else if(acaoAtual.charAt(0) == '1') {
						filosofo2.pegouGarfo();
						if(acaoAtual.charAt(acaoAtual.length()-1) == '1') {
							faca2.pegou(2);
						}else {
							faca1.pegou(2);
						}
					}else if(acaoAtual.charAt(0) == '2') {
						filosofo3.pegouGarfo();
						if(acaoAtual.charAt(acaoAtual.length()-1) == '2') {
							faca3.pegou(3);
						}else {
							faca2.pegou(3);
						}
					}else {
						filosofo4.pegouGarfo();
						if(acaoAtual.charAt(acaoAtual.length()-1) == '3') {
							faca4.pegou(4);
						}else {
							faca3.pegou(4);
						}
					}
				}else {
					if(acaoAtual.charAt(0) == '0') {
						//filofo 1 devolve
						filosofo1.soltouGarfo();
						if(acaoAtual.charAt(acaoAtual.length()-1) == '0') {
							//faca 1
							faca1.devolve();
						}else {
							//faca 4
							faca4.devolve();
						}
					}else if(acaoAtual.charAt(0) == '1') {
						filosofo2.soltouGarfo();
						if(acaoAtual.charAt(acaoAtual.length()-1) == '1') {
							faca2.devolve();
						}else {
							faca1.devolve();
						}
					}else if(acaoAtual.charAt(0) == '2') {
						filosofo3.soltouGarfo();
						if(acaoAtual.charAt(acaoAtual.length()-1) == '2') {
							faca3.devolve();
						}else {
							faca2.devolve();
						}
					}else {
						filosofo4.soltouGarfo();
						if(acaoAtual.charAt(acaoAtual.length()-1) == '3') {
							faca4.devolve();
						}else {
							faca3.devolve();
						}
					}
				}
				
			}
		}
		
	}

	public void draw(Graphics g) {
		mesa.draw(g, 0, 0);
		filosofo1.draw(g);
		filosofo2.draw(g);
		filosofo3.draw(g);
		filosofo4.draw(g);
		
		prato1.draw(g);
		prato2.draw(g);
		prato3.draw(g);
		prato4.draw(g);
		
		faca1.draw(g);
		faca2.draw(g);
		faca3.draw(g);
		faca4.draw(g);
	}

	public void stop() {

	}

}
