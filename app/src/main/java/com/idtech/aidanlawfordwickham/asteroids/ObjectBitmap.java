package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.res.Resources;

import static com.idtech.aidanlawfordwickham.asteroids.Util.getResizedBitmap;

/**
 * Created by iD Student on 7/26/2017.
 */

public class ObjectBitmap {

    static Bitmap spaceshipBM;
    static Bitmap spaceshipTwoBM;
    static Bitmap spaceshipThreeBM;
    // static Bitmap spaceshipFourBM;
    // Breakable
    static Bitmap asteroidOneBM;
    static Bitmap asteroidTwoBM;
    static Bitmap asteroidThreeBM;
    static Bitmap asteroidFourBM;
    // NonBreakable
    static Bitmap asteroidgreenBM;
    static Bitmap asteroidredBM;

    public static void initializeBitmaps(Resources resources) {

        spaceshipBM = getResizedBitmap(BitmapFactory.decodeResource(resources, R.drawable.spaceship), 150, 130);
        spaceshipTwoBM = getResizedBitmap(BitmapFactory.decodeResource(resources, R.drawable.spaceshiptwo), 150, 130);
        spaceshipThreeBM = getResizedBitmap(BitmapFactory.decodeResource(resources, R.drawable.spaceshipthree), 150, 130);
        // spaceshipFourBM = getResizedBitmap(BitmapFactory.decodeResource(resources, R.drawable.spaceshipfour), 150, 130);
        asteroidOneBM = getResizedBitmap(BitmapFactory.decodeResource(resources, R.drawable.asteroid),100,100);
        asteroidTwoBM = getResizedBitmap(BitmapFactory.decodeResource(resources, R.drawable.asteroid),123,130);
        asteroidThreeBM = getResizedBitmap(BitmapFactory.decodeResource(resources, R.drawable.asteroid),155,140);
        asteroidFourBM = getResizedBitmap(BitmapFactory.decodeResource(resources, R.drawable.asteroid),88,90);
        asteroidgreenBM =  getResizedBitmap(BitmapFactory.decodeResource(resources, R.drawable.asteroidgreen), 200, 155);
        asteroidredBM = getResizedBitmap(BitmapFactory.decodeResource(resources, R.drawable.asteroidred), 150, 200);
    }

}
