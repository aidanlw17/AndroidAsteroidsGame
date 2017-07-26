package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.explosionPaint;

/**
 * Created by iD Student on 7/20/2017.
 */

public class Asteroid extends EnemyObject{

    private boolean toBeRemoved;
    private boolean exploding;
    private int health = 100;
    private int ticker;
    Random random;
    Paint explosionPaint;

    public Asteroid(Bitmap bitmap, int x, int y) {
        super(bitmap, x, y);
        random = new Random();
        explosionPaint = new Paint();
        explosionPaint.setColor(Color.rgb(178,128,128));
        explosionPaint.setShadowLayer(10,12,12, Color.argb(50, 214, 92, 0));
        explosionPaint.setStrokeWidth(3);
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

    @Override
    public void draw(Canvas canvas) {
        if(this.exploding) {
            canvas.drawCircle(x + getBitmap().getWidth() / 2, y + getBitmap().getHeight() / 2, (float) random.nextInt(getBitmap().getWidth() / 2) + 1, explosionPaint);
            canvas.drawARGB(90, 254, 69, random.nextInt(146));
            if(ticker % 30 == 0) {
                setToBeRemoved(true);
            }
        } else {
            super.draw(canvas);
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
