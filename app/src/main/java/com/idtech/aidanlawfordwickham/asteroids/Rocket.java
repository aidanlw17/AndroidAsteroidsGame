package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Color;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.rocketPaint;

public class Rocket extends Bullet{

    public Rocket(int x, int y) {
        super(x, y, 100, 5, 20, rocketPaint);
        rocketPaint.setColor(Color.rgb(237,255,254));
        rocketPaint.setStrokeWidth(20);
    }
}
