package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by iD Student on 7/21/2017.
 */

public class Weapon {

    public enum Type{laser, rocket, turret}
    private Paint weaponPaint;

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private ArrayList<Bullet> bulletToRemove = new ArrayList<Bullet>();
    private ArrayList<Bullet> tempBullets;
    private int xValueHolsterOne;
    private int yValueHolsterOne;
    private int xValueHolsterTwo;
    private int yValueHolsterTwo;

    public Weapon() {
        weaponPaint = new Paint();
        weaponPaint.setColor(Color.rgb(50,205,50));
    }

    public void setHolsterLocations(int spaceshipX, int spaceshipY) {
        xValueHolsterOne = spaceshipX + 23;
        yValueHolsterOne = spaceshipY + 45;
        xValueHolsterTwo = spaceshipX + 130;
        yValueHolsterTwo = spaceshipY + 45;
    }

    public void fire(Type weaponNumber) {
        switch (weaponNumber) {
            case laser:
                fireTurret();
                break;
            case rocket:
                fireRocket();
                break;
            case turret:
                fireLaser();
                break;
        }
    }

    public void fireLaser() {
        bullets.add(new Laser(xValueHolsterOne, yValueHolsterOne));
        bullets.add(new Laser(xValueHolsterTwo, yValueHolsterTwo));
    }

    public void fireRocket() {
        bullets.add(new Rocket(xValueHolsterOne, yValueHolsterOne));
        bullets.add(new Rocket(xValueHolsterTwo, yValueHolsterTwo));
    }

    public void fireTurret() {
        bullets.add(new Turret(xValueHolsterOne, yValueHolsterOne));
        bullets.add(new Turret(xValueHolsterTwo, yValueHolsterTwo));
    }

    public void clearBullets() {
        bullets = new ArrayList<Bullet>();
    }

    public void draw(Canvas canvas) {

        synchronized (bullets) {
            tempBullets = new ArrayList<Bullet>(bullets);
        }
        for (Bullet bullet : tempBullets) {
            bullet.draw(canvas);
            if (bullet.getY() < 0) {
                bulletToRemove.add(bullet);
            }
        }

    }
}
