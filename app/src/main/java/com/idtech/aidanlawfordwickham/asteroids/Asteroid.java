package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by iD Student on 7/20/2017.
 */

public class Asteroid {

    private int x;
    private int y;
    private Bitmap bitmap;
    private int height = y + 30;
    private int width = x + 30;
    private boolean toBeRemoved;
    private boolean exploding;
    private int health = 100;
    private int ticker;


    public Asteroid(Bitmap bitmap, int x, int y) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setExploding(boolean destruct) {
        this.exploding = destruct;
    }

    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }

    public boolean getToBeRemoved() {
        return this.toBeRemoved;
    }

    public void draw(Canvas canvas) {
        if(this.exploding) {
            canvas.drawBitmap(bitmap, x, y, null);
            if(ticker % 30 == 0) {
                setToBeRemoved(true);
            }
        } else {
            canvas.drawBitmap(bitmap, x, y, null);
            y = y + 7;
        }
        ticker ++;
    }

    public void destruct(int damage) {
        if(this.health - damage > 0) {
            this.health -= damage;
        } else {
            this.health = 0;
            setExploding(true);
        }
    }
}
