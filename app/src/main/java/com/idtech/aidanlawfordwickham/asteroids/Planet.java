package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by iD Student on 7/26/2017.
 */

public class Planet extends EnemyObject{

    public Planet(Bitmap bitmap, int x, int y) {
        super(bitmap, x, y);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.getBitmap(), x, y, null);
        y = y + yVelocity;
    }



}
