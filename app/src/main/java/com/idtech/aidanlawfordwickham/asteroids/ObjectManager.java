package com.idtech.aidanlawfordwickham.asteroids;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.Space;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import static com.idtech.aidanlawfordwickham.asteroids.R.drawable.asteroid;
import static com.idtech.aidanlawfordwickham.asteroids.Util.getResizedBitmap;

/**
 * Created by iD Student on 7/25/2017.
 */

public class ObjectManager {

    private ArrayList<Asteroid> asteroids;
    private ArrayList<Asteroid> asteroidsToRemove;
    private ArrayList<Asteroid> tempAsteroids;

    private ArrayList<Bullet> bulletToRemove = new ArrayList<Bullet>();
    private ArrayList<Bullet> tempBullets;

    public Weapon weapon = new Weapon();
    public Spaceship spaceship;
    private Bitmap asteroidBM;
    private Resources resources;

    public ObjectManager(Resources resources) {
        this.resources = resources;

        spaceship = new Spaceship(Util.getResizedBitmap(BitmapFactory.decodeResource(this.resources, R.drawable.spaceship), 225, 170), 300, 1000);
        asteroidBM = getResizedBitmap(BitmapFactory.decodeResource(this.resources, asteroid),100,100);
    }

    public void preDraw() {
        synchronized (asteroids) {
            tempAsteroids = new ArrayList<Asteroid>(asteroids);
        }
        synchronized (weapon.bullets) {
            tempBullets = new ArrayList<Bullet>(weapon.bullets);
        }
    }

    public void removeAsteroids() {

        for(Asteroid asteroid:tempAsteroids) {
            if(asteroid.getToBeRemoved()) {
                asteroidsToRemove.add(asteroid);
            }
        }

        tempAsteroids.removeAll(asteroidsToRemove);
        asteroids = tempAsteroids;
        asteroidsToRemove.clear();
    }

    public void removeBullets() {
        tempBullets.removeAll(bulletToRemove);
        weapon.bullets = tempBullets;
        bulletToRemove.clear();
    }

    public boolean collisionsCheckAsteroidSpaceship() {
        Bitmap spaceshipBitmap = spaceship.getBitmap();

        for (Asteroid asteroid:tempAsteroids) {
            Bitmap asteroidBitmap = asteroid.getBitmap();

            if (asteroid.getX() < spaceship.getX() + (spaceshipBitmap.getWidth() / 2)
                    && asteroid.getX() + (asteroidBitmap.getWidth() /2 )> spaceship.getX()) {


                if (asteroid.getY() < spaceship.getY() + (spaceshipBitmap.getHeight() /2)
                        && asteroid.getY() + (asteroidBitmap.getHeight() /2) > spaceship.getY()){
                    return true;
                    // End the game.
                }
            }
        }
        return false;
    }

    public boolean collisionsCheckAsteroidWeapon() {

        for(Asteroid asteroid:tempAsteroids) {
            Bitmap asteroidBitmap = asteroid.getBitmap();

            for(Bullet bullet:tempBullets) {
                if ((asteroid.getY() + asteroidBitmap.getHeight() >= bullet.getY() - bullet.getHeight()) &&
                        (bullet.getX() > asteroid.getX() && bullet.getX() < asteroid.getX() + asteroidBitmap.getWidth()))  {
                    asteroid.destruct(bullet.getDamage());
                    bulletToRemove.add(bullet);
                }
            }
        }
        return false;
    }

    public void clearObjects() {
        collisionsCheckAsteroidWeapon();
        // clearing asteroids
        removeAsteroids();
        // clearing bullets
        removeBullets();
    }

    public void resetObjects() {
        spaceship = new Spaceship(Util.getResizedBitmap(BitmapFactory.decodeResource(this.resources, R.drawable.spaceship), 150, 130), 300, 1000);
        asteroids = new ArrayList<Asteroid>();
        asteroidsToRemove = new ArrayList<Asteroid>();
    }

    public void addAsteroid(Canvas canvas) {
        Random generator = new Random();
        int startingXPosition = generator.nextInt(canvas.getWidth() - asteroidBM.getWidth());
        Asteroid asteroid = new Asteroid(asteroidBM, startingXPosition, 0);
        tempAsteroids.add(asteroid);
    }

    public boolean draw(Canvas canvas) {
        boolean  result = false;
        spaceship.draw(canvas);
        weapon.draw(canvas);
        for (Asteroid asteroid : tempAsteroids) {
            asteroid.draw(canvas);
            if (asteroid.getY() > canvas.getHeight()) {
                asteroidsToRemove.add(asteroid);
                result = true;
            }
        }
        return result;
    }
}
