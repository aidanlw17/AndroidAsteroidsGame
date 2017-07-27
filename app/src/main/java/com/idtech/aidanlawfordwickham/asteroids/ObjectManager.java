package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectBitmap.asteroidFourBM;
import static com.idtech.aidanlawfordwickham.asteroids.ObjectBitmap.asteroidOneBM;
import static com.idtech.aidanlawfordwickham.asteroids.ObjectBitmap.asteroidThreeBM;
import static com.idtech.aidanlawfordwickham.asteroids.ObjectBitmap.asteroidTwoBM;
import static com.idtech.aidanlawfordwickham.asteroids.ObjectBitmap.asteroidgreenBM;
import static com.idtech.aidanlawfordwickham.asteroids.ObjectBitmap.asteroidredBM;
import static com.idtech.aidanlawfordwickham.asteroids.ObjectBitmap.spaceshipBM;

public class ObjectManager {

    private ArrayList<EnemyObject> enemyObjects;
    private ArrayList<EnemyObject> enemyObjectsToRemove;
    private ArrayList<EnemyObject> tempEnemyObjects;

    private ArrayList<Star> stars;
    private ArrayList<Star> starsToRemove;
    private ArrayList<Star> tempStars;

    private ArrayList<Bullet> bulletToRemove = new ArrayList<Bullet>();
    private ArrayList<Bullet> tempBullets;

    public Weapon weapon = new Weapon();
    public Spaceship spaceship;

    public ObjectManager() {
        spaceship = new Spaceship(spaceshipBM, 300, 1000);
    }

    public void preDraw() {
        synchronized (enemyObjects) {
            tempEnemyObjects = new ArrayList<EnemyObject>(enemyObjects);
        }
        synchronized (weapon.bullets) {
            tempBullets = new ArrayList<Bullet>(weapon.bullets);
        }
        synchronized (stars) {
            tempStars = new ArrayList<Star>(stars);
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

    public void removeStars() {
        tempStars.removeAll(starsToRemove);
        stars = tempStars;
        starsToRemove.clear();
    }

    public boolean collisionsCheckAsteroidSpaceship() {
        Bitmap spaceshipBitmap = spaceship.getBitmap();

        for (EnemyObject asteroid: tempEnemyObjects) {
            Bitmap asteroidBitmap = asteroid.getBitmap();

            if (asteroid.getX() < spaceship.getX() + (spaceshipBitmap.getWidth() / 2)
                    && asteroid.getX() + (asteroidBitmap.getWidth() /2 )> spaceship.getX()) {


                if (asteroid.getY() < spaceship.getY() + (spaceshipBitmap.getHeight() /2)
                        && asteroid.getY() + (asteroidBitmap.getHeight() /2) > spaceship.getY()){
                    spaceship.destruct();
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
        collisionsCheckAsteroidSpaceship();
        collisionsCheckAsteroidWeapon();
        // clearing enemyObjects
        removeAsteroids();
        // clearing bullets
        removeBullets();
        // clearing background
        removeStars();
    }

    public void resetObjects() {
        spaceship = new Spaceship(spaceshipBM, 300, 1000);
        enemyObjects = new ArrayList<EnemyObject>();
        enemyObjectsToRemove = new ArrayList<EnemyObject>();
        tempEnemyObjects = new ArrayList<EnemyObject>();
        weapon.clearBullets();
        bulletToRemove = new ArrayList<Bullet>();
        stars = new ArrayList<Star>();
        starsToRemove = new ArrayList<Star>();
    }

    public void addAsteroid(Canvas canvas) {
        Random generator = new Random();
        int startingXPosition = generator.nextInt(canvas.getWidth() - 100);
        Asteroid asteroid = new Asteroid(selectBitmapBreakable(), startingXPosition, 0);
        tempEnemyObjects.add(asteroid);
    }

    public void addPlanet(Canvas canvas) {
        Random generator = new Random();
        int startingXPosition = generator.nextInt(canvas.getWidth() - asteroidgreenBM.getWidth());
        Planet planet = new Planet(selectBitmapNonBreakable(), startingXPosition, 0);
        tempEnemyObjects.add(planet);
    }

    public void addStar(Canvas canvas) {
        Random generator = new Random();
        int radius = generator.nextInt(10) + 1;
        int startinXPosition = generator.nextInt(canvas.getWidth() - 5);
        Star star = new Star(startinXPosition, 0, radius);
        tempStars.add(star);
    }

    public boolean draw(Canvas canvas) {
        boolean  result = false;
        spaceship.draw(canvas);
        weapon.draw(canvas);
        for (EnemyObject enemyObject : tempEnemyObjects) {
            if(enemyObject instanceof Planet) {
                Planet planet = (Planet) enemyObject;
                planet.draw(canvas);
            } else {
                enemyObject.draw(canvas);
            }
            if (enemyObject.getY() > canvas.getHeight()) {
                enemyObjectsToRemove.add(enemyObject);
                result = true;
            }
        }
        for(Star star : tempStars) {
            star.draw(canvas);
            if(star.getY() > canvas.getHeight()) {
                starsToRemove.add(star);
            }
        }
        return result;
    }

    public Bitmap selectBitmapNonBreakable() {
        Random random = new Random();
        switch (random.nextInt(2) + 1) {
            case 1:
                return asteroidgreenBM;
            case 2:
                return asteroidredBM;
        }
        return asteroidOneBM;
    }

    public Bitmap selectBitmapBreakable() {
        Random random = new Random();
        switch (random.nextInt(4) + 1) {
            case 1:
                return asteroidOneBM;
            case 2:
                return asteroidTwoBM;
            case 3:
                return asteroidThreeBM;
            case 4:
                return asteroidFourBM;
        }
        return asteroidOneBM;
    }
}
