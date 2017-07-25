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
    private boolean hasCollided;

    public Asteroid(Bitmap bitmap, int x, int y) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        this.hasCollided = false;
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

    public void setHasCollided(boolean hasCollided) {
        this.hasCollided = hasCollided;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
        y = y + 7;
    }
}
