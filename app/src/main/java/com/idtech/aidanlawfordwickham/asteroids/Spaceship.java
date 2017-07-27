package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.spaceshipExplosionPaint;
import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.spaceshipExplosionTwoPaint;

/**
 * Created by iD Student on 7/20/2017.
 */

public class Spaceship extends CanvasObject {

    private Bitmap bitmap;
    private int xVelocity;
    private int yVelocity;
    private boolean exploding;
    private boolean toBeRemoved;
    private int ticker;
    private Random random;

    public Spaceship(Bitmap bitmap, int x, int y) {
        super(x,y);
        this.bitmap = bitmap;
        random = new Random();
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
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

    public void setExploding(boolean exploding) {
        this.exploding = exploding;
    }

    public void destruct() {
        setExploding(true);
    }

    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }

    public boolean getToBeRemoved() {
        return this.toBeRemoved;
    }

    public void draw(Canvas canvas) {
        if(this.exploding) {
//            canvas.drawLine((float) random.nextInt(x), (float) random.nextInt(y), x + bitmap.getWidth() / 2, y + bitmap.getHeight() / 2, spaceshipExplosionPaint);
//            canvas.drawLine((float) random.nextInt(x), (float) random.nextInt(y), x + bitmap.getWidth() / 2, y + bitmap.getHeight() / 2, spaceshipExplosionPaint);
//            canvas.drawLine((float) random.nextInt(x), (float) random.nextInt(y), x + bitmap.getWidth() / 2, y + bitmap.getHeight() / 2, spaceshipExplosionPaint);
//            canvas.drawLine((float) random.nextInt(x), (float) random.nextInt(y), x + bitmap.getWidth() / 2, y + bitmap.getHeight() / 2, spaceshipExplosionPaint);
            canvas.drawCircle(x + bitmap.getWidth() / 2, y + bitmap.getHeight(), random.nextInt(bitmap.getHeight()) + 1, spaceshipExplosionPaint);
            canvas.drawCircle(x + bitmap.getWidth() / 2, y + bitmap.getHeight(), random.nextInt(bitmap.getHeight()) + 1, spaceshipExplosionTwoPaint);
            if(ticker % 20 == 0) {
                setToBeRemoved(true);
            }
        } else {
            canvas.drawBitmap(bitmap, x, y, null);
            setX(x + xVelocity, canvas);
            setY(y + yVelocity, canvas);
        }
        ticker++;
    }
}
