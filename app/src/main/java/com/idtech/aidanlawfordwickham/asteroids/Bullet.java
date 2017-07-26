package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by iD Student on 7/23/2017.
 */

public class Bullet extends CanvasObject{

    private int height;
    private int damage;
    private int yVelocity;
    private Paint paint;

    public Bullet(int x, int y, int damage, int yVelocity, int height, Paint paint) {
        super(x,y);
        this.damage = damage;
        this.yVelocity = yVelocity;
        this.height = height;
        this.paint = paint;
    }

    public int getHeight() {
        return this.height;
    }

    public int getDamage() {
        return this.damage;
    }

    public void draw(Canvas canvas) {
        canvas.drawLine(x, y, x, y - height, paint);
        canvas.drawLine(x, y, x, y - height, paint);
        y = y - yVelocity;
    }
}
