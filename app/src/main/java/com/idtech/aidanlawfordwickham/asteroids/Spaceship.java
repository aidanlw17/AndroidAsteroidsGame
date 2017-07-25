package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by iD Student on 7/20/2017.
 */

public class Spaceship {

    private Bitmap bitmap;
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;

    public Spaceship(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setX(int x, Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        if(x < 0) {
            setX(0);
        } else if(x > canvasWidth - bitmap.getWidth()) {
            setX(canvasWidth-bitmap.getWidth());
        } else {
            setX(x);
        }
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setY(int y, Canvas canvas) {
        int canvasHeight = canvas.getHeight();
        if(y < 0) {
            setY(0);
        } else if(y > canvasHeight - bitmap.getHeight()){
            setY(canvasHeight - bitmap.getHeight());
        } else {
            setY(y);
        }
    }

    public int getY() {
        return this.y;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getyVelocity() {
        return this.yVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getxVelocity() {
        return this.xVelocity;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
        setX(x + xVelocity, canvas);
        setY(y + yVelocity, canvas);
    }
}
