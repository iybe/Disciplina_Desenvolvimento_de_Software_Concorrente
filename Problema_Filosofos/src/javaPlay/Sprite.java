/*
 * Sprite
 */

package javaPlay;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * @author VisionLab/PUC-Rio
 */
public class Sprite 
{    
    private Image image;
    private int animFrameCount;
    private int currAnimFrame;
    private int animFrameWidth;
    private int animFrameHeight;
    private int MAX_COUNT = 50;

    public Sprite(String filename, int animFrameCount, int animFrameWidth,
            int animFrameHeight) throws Exception
    {
        image = Toolkit.getDefaultToolkit().getImage(filename);

        int count = 0;

        while(image.getWidth(null) == -1)
        {
            Thread.sleep(1);
            count++;

            if(count == MAX_COUNT)
            {
                throw new Exception("image could not be loaded");
            }
        }

        this.animFrameCount = animFrameCount;
        this.setAnimFrameWidth(animFrameWidth);
        this.setAnimFrameHeight(animFrameHeight);
        
        this.currAnimFrame = 0;
    }

    public void setCurrAnimFrame(int frame)
    {
        currAnimFrame = frame;
    }

    public void draw(Graphics g, int x, int y)
    {
        GameCanvas canvas = GameEngine.getInstance().getGameCanvas();

        int xpos = canvas.getRenderScreenStartX() + x;
        int ypos = canvas.getRenderScreenStartY() + y;

        g.drawImage(image, xpos, ypos, xpos + getAnimFrameWidth(), ypos + getAnimFrameHeight(),
                currAnimFrame * getAnimFrameWidth(), 0, (currAnimFrame + 1) * getAnimFrameWidth(), getAnimFrameHeight(), null);
    }

    private Sprite(Image image, int animFrameCount,
            int currAnimFrame, int animFrameWidth, int animFrameHeight)
    {
        this.image = image;
        this.animFrameCount = animFrameCount;
        this.currAnimFrame = currAnimFrame;
        this.setAnimFrameWidth(animFrameWidth);
        this.setAnimFrameHeight(animFrameHeight);
    }

    public Sprite clone()
    {
        return new Sprite(image, animFrameCount, currAnimFrame,
                getAnimFrameWidth(), getAnimFrameHeight());
    }

	public int getAnimFrameWidth() {
		return animFrameWidth;
	}

	public void setAnimFrameWidth(int animFrameWidth) {
		this.animFrameWidth = animFrameWidth;
	}

	public int getAnimFrameHeight() {
		return animFrameHeight;
	}

	public void setAnimFrameHeight(int animFrameHeight) {
		this.animFrameHeight = animFrameHeight;
	}
}
