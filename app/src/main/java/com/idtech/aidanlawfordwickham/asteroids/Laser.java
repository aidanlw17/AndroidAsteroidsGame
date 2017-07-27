package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Color;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.laserPaint;

public class Laser extends Bullet {

    public Laser(int x, int y) {
        super(x, y, 50, 10, 10, laserPaint);
    }

}
