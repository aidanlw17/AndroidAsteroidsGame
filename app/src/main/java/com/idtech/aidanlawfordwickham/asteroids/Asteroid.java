package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.explosionPaint;

public class Asteroid extends EnemyObject{

    private boolean toBeRemoved;
    private boolean exploding;
    private int health = 100;
    private int ticker;
    Random random;

    public Asteroid(Bitmap bitmap, int x, int y) {
        super(bitmap, x, y);
        random = new Random();
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
