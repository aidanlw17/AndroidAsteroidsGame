package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by iD Student on 7/26/2017.
 */

public class EnemyObject extends CanvasObject {

    private Bitmap bitmap;
    private boolean toBeRemoved;
    int yVelocity = 7;


    public EnemyObject(Bitmap bitmap, int x, int y) {
        super(x,y);
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }

    public boolean getToBeRemoved() {
        return this.toBeRemoved;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,x,y,null);
        y = y + yVelocity;
    }

}
