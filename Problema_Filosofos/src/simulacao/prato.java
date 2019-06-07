package simulacao;

import java.awt.Graphics;

import javaPlay.GameObject;
import javaPlay.Sprite;

public class prato extends GameObject{

	private int x;
	private int y;
	private int estado;//1 - cheio , 2 - vazio
	private Sprite cheio;
	private Sprite vazio;
	
	public prato(int x, int y, Sprite cheio, Sprite vazio) {
		super();
		this.x = x;
		this.y = y;
		this.setEstado(1);
		this.cheio = cheio;
		this.vazio = vazio;
	}

	@Override
	public void step(long timeElapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(this.estado == 1) {
			cheio.draw(g, x, y);
		}else {
			vazio.draw(g, x, y);
		}
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
