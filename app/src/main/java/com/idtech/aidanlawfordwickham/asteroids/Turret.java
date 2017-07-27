package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Color;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.turretPaint;

public class Turret extends Bullet{

    public Turret(int x, int y) {
        super(x, y, 25, 20, 5, turretPaint);
    }

}
