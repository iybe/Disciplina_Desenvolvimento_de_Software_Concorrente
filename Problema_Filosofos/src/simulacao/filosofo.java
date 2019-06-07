package simulacao;

import java.awt.Graphics;

import javaPlay.GameObject;
import javaPlay.Sprite;

public class filosofo extends GameObject {

	private int x;
	private int y;
	private Sprite sprite1;
	private Sprite sprite2;
	private Sprite sprite3;
	private int estado;// 1 - fome , 2 - comendo , 3 - comeu
	private int qtdGafosAtual;
	
	public filosofo(int x, int y, Sprite sprite1, Sprite sprite2, Sprite sprite3) {
		super();
		this.x = x;
		this.y = y;
		this.estado = 1;
		this.sprite1 = sprite1;
		this.sprite2 = sprite2;
		this.sprite3 = sprite3;
		this.qtdGafosAtual = 0;
	}

	@Override
	public void step(long timeElapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(this.estado == 1) {
			sprite1.draw(g, this.x, this.y);
		}else if(this.estado == 2) {
			sprite2.draw(g, this.x, this.y);
		}else {
			sprite3.draw(g, this.x, this.y);
		}
	}
	
	public void setEstado(int novoEstado) {
		this.estado = novoEstado;
	}
	
	public void pegouGarfo() {
		this.qtdGafosAtual++;
		if(this.qtdGafosAtual == 2) {
			this.estado = 2;
		}
	}
	
	public void soltouGarfo() {
		this.qtdGafosAtual--;
	}

}
