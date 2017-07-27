package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Color;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.rocketPaint;

public class Rocket extends Bullet{

    public Rocket(int x, int y) {
        super(x, y, 100, 5, 20, rocketPaint);
    }
}
