package simulacao;

import java.awt.Graphics;

import javaPlay.GameObject;
import javaPlay.Sprite;

public class faca extends GameObject{

	private int x;
	private int y;
	private Sprite sprite;
	private int xo;
	private int yo;
	private int id;
	
	public faca(int x, int y, Sprite sprite, int id) {
		super();
		this.x = x;
		this.y = y;
		this.xo = x;
		this.yo = y;
		this.sprite = sprite;
		this.id = id;
	}

	@Override
	public void step(long timeElapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		sprite.draw(g, x, y);
	}
	
	public void pegou(int idFilosofo) {
		if(this.id == 1) {
			if(idFilosofo == 1) {
				this.x = 495;
				this.y = 182;
			}else {
				this.x = 559;
				this.y = 243;
			}
		}else if(this.id == 2) {
			if(idFilosofo == 2) {
				this.x = 559;
				this.y = 399;
			}else {
				this.x = 495;
				this.y = 464;
			}
		}else if(this.id == 3) {
			if(idFilosofo == 3) {
				this.x = 344;
				this.y = 458;
			}else {
				this.x = 278;
				this.y = 392;
			}
		}else {
			if(idFilosofo == 4) {
				this.x = 284;
				this.y = 243;
			}else {
				this.x = 351;
				this.y = 182;
			}
		}
	}

	public void devolve() {
		this.x = this.xo;
		this.y = this.yo;
	}
	
}
