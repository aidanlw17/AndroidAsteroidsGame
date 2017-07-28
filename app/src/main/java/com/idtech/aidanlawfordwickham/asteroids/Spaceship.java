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
    private int xDirection;
    private int yDirection;
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

    public void drawBackground(Canvas canvas) {
        xVelocity = 8;
        yVelocity = 8;
        canvas.drawBitmap(bitmap, x, y, null);

        switch(xDirection) {
            case 1:
                setXVelocityPositive(canvas);
                break;
            default:
                setXVelocityNegative(canvas);
                break;
        }
        switch(yDirection) {
            case 1:
                setYVelocityPositive(canvas);
                break;
            default:
                setYVelocityNegative(canvas);
                break;
        }
        if(x + bitmap.getWidth() == canvas.getWidth()) {
            setXDirection(0);
        }
        if (x == 0) {
            setXDirection(1);
        }
        if(y + bitmap.getHeight() == canvas.getHeight()) {
            setYDirection(0);
        }
        if(y == 0) {
            setYDirection(1);
        }
    }

    public void setXDirection(int xDirection) {
        this.xDirection = xDirection;
    }
    public void setYDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public void setXVelocityPositive(Canvas canvas) {
        setX(x + xVelocity, canvas);
    }
    public void setXVelocityNegative(Canvas canvas) {
        setX(x - xVelocity, canvas);
    }
    public void setYVelocityPositive(Canvas canvas) {
        setY(y + yVelocity, canvas);
    }
    public void setYVelocityNegative(Canvas canvas) {
        setY(y - yVelocity, canvas);
    }
}
