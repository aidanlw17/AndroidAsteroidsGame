package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Canvas;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.bigStarPaint;
import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.smallStarPaint;

/**
 * Created by iD Student on 7/27/2017.
 */

public class Star extends CanvasObject{

    int radius;
    int bigStarYVelocity = 5;
    int smallStarYVelocity = 2;


    public Star(int x, int y, int radius) {
        super(x,y);
        this.radius = radius;
    }

    public void draw(Canvas canvas) {
        if(radius > 3) {
            canvas.drawCircle(x, y, (float) radius, bigStarPaint);
            y = y + bigStarYVelocity;
        } else {
            canvas.drawCircle(x, y, (float) radius, smallStarPaint);
            y = y + smallStarYVelocity;
        }
    }



}
