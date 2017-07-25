package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by iD Student on 7/23/2017.
 */

public class Bullet {

    private float x;
    private float y;
    private int height;
    private Paint bulletPaint;

    public Bullet(float x, float y) {
        bulletPaint = new Paint();
        bulletPaint.setColor(Color.rgb(50,250,50));
        this.x = x;
        this.y = y;
        this.height = 10;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return this.x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return this.y;
    }

    public int getHeight() {
        return this.height;
    }

    public void draw(Canvas canvas) {
        canvas.drawLine(x, y, x, y - height, bulletPaint);
        canvas.drawLine(x, y, x, y - height, bulletPaint);
        y = y - 10;
    }
}
