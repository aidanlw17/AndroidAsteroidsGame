package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by iD Student on 7/23/2017.
 */

public class Bullet extends CanvasObject{

    private int height;
    private Paint bulletPaint;
    private int damage;

    public Bullet(int x, int y, int damage) {
        super(x,y);
        bulletPaint = new Paint();
        bulletPaint.setColor(Color.rgb(50,250,50));
        this.height = 10;
        this.damage = damage;
    }

    public int getHeight() {
        return this.height;
    }

    public int getDamage() {
        return this.damage;
    }

    public void draw(Canvas canvas) {
        canvas.drawLine(x, y, x, y - height, bulletPaint);
        canvas.drawLine(x, y, x, y - height, bulletPaint);
        y = y - 10;
    }
}
