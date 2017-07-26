package com.idtech.aidanlawfordwickham.asteroids;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import static com.idtech.aidanlawfordwickham.asteroids.R.drawable.asteroid;
import static com.idtech.aidanlawfordwickham.asteroids.Util.getResizedBitmap;

/**
 * Created by iD Student on 7/25/2017.
 */

public class ObjectManager {

    private ArrayList<EnemyObject> enemyObjects;
    private ArrayList<EnemyObject> enemyObjectsToRemove;
    private ArrayList<EnemyObject> tempEnemyObjects;

    private ArrayList<Bullet> bulletToRemove = new ArrayList<Bullet>();
    private ArrayList<Bullet> tempBullets;

    public Weapon weapon = new Weapon();
    public Spaceship spaceship;
    private Bitmap asteroidBM;
    private Bitmap planetBM;
    private Resources resources;

    public ObjectManager(Resources resources) {
        this.resources = resources;

        spaceship = new Spaceship(Util.getResizedBitmap(BitmapFactory.decodeResource(this.resources, R.drawable.spaceship), 225, 170), 300, 1000);
        asteroidBM = getResizedBitmap(BitmapFactory.decodeResource(this.resources, asteroid),100,100);
        planetBM = getResizedBitmap(BitmapFactory.decodeResource(this.resources, R.drawable.planet), 100, 100);
    }

    public void preDraw() {
        synchronized (enemyObjects) {
            tempEnemyObjects = new ArrayList<EnemyObject>(enemyObjects);
        }
        synchronized (weapon.bullets) {
            tempBullets = new ArrayList<Bullet>(weapon.bullets);
        }
    }

    public void removeAsteroids() {

        for(EnemyObject asteroid: tempEnemyObjects) {
            if(asteroid.getToBeRemoved()) {
                enemyObjectsToRemove.add(asteroid);
            }
        }

        tempEnemyObjects.removeAll(enemyObjectsToRemove);
        enemyObjects = tempEnemyObjects;
        enemyObjectsToRemove.clear();
    }

    public void removeBullets() {
        tempBullets.removeAll(bulletToRemove);
        weapon.bullets = tempBullets;
        bulletToRemove.clear();
    }

    public boolean collisionsCheckAsteroidSpaceship() {
        Bitmap spaceshipBitmap = spaceship.getBitmap();

        for (EnemyObject asteroid: tempEnemyObjects) {
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

        for(EnemyObject enemyObject: tempEnemyObjects) {
            Bitmap asteroidBitmap = enemyObject.getBitmap();

            for(Bullet bullet:tempBullets) {
                if ((enemyObject.getY() + asteroidBitmap.getHeight() >= bullet.getY() - bullet.getHeight()) &&
                        (bullet.getX() > enemyObject.getX() && bullet.getX() < enemyObject.getX() + asteroidBitmap.getWidth()))  {
                    if(enemyObject instanceof Asteroid){
                        Asteroid asteroid = (Asteroid) enemyObject;
                        asteroid.destruct(bullet.getDamage());
                    }
                    bulletToRemove.add(bullet);
                }
            }
        }
        return false;
    }

    public void clearObjects() {
        collisionsCheckAsteroidWeapon();
        // clearing enemyObjects
        removeAsteroids();
        // clearing bullets
        removeBullets();
    }

    public void resetObjects() {
        spaceship = new Spaceship(Util.getResizedBitmap(BitmapFactory.decodeResource(this.resources, R.drawable.spaceship), 150, 130), 300, 1000);
        enemyObjects = new ArrayList<EnemyObject>();
        enemyObjectsToRemove = new ArrayList<EnemyObject>();
    }

    public void addAsteroid(Canvas canvas) {
        Random generator = new Random();
        int startingXPosition = generator.nextInt(canvas.getWidth() - asteroidBM.getWidth());
        Asteroid asteroid = new Asteroid(asteroidBM, startingXPosition, 0);
        tempEnemyObjects.add(asteroid);
    }

    public void addPlanet(Canvas canvas) {
        Random generator = new Random();
        int startingXPosition = generator.nextInt(canvas.getWidth() - planetBM.getWidth());
        Planet planet = new Planet(planetBM, startingXPosition, 0);
        tempEnemyObjects.add(planet);
    }

    public boolean draw(Canvas canvas) {
        boolean  result = false;
        spaceship.draw(canvas);
        weapon.draw(canvas);
        for (EnemyObject enemyObject : tempEnemyObjects) {
            enemyObject.draw(canvas);
            if (enemyObject.getY() > canvas.getHeight()) {
                enemyObjectsToRemove.add(enemyObject);
                result = true;
            }
        }
        return result;
    }
}
