package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by iD Student on 7/26/2017.
 */

public class ObjectPaint {

    static Paint gameOverPaint;
    static Paint laserPaint;
    static Paint rocketPaint;
    static Paint turretPaint;
    static Paint explosionPaint;
    static Paint spaceshipExplosionPaint;
    static Paint spaceshipExplosionTwoPaint;
    static Paint scorePaint;

    public static void createPaint() {

        laserPaint = new Paint();
        laserPaint.setColor(Color.rgb(50, 250, 50));

        rocketPaint = new Paint();
        rocketPaint.setColor(Color.rgb(237, 255, 254));
        rocketPaint.setStrokeWidth(20);

        turretPaint = new Paint();
        turretPaint.setColor(Color.rgb(254, 64, 216));
        turretPaint.setStrokeWidth(5);

        explosionPaint = new Paint();
        explosionPaint.setColor(Color.rgb(178, 128, 128));
        explosionPaint.setShadowLayer(10, 12, 12, Color.argb(50, 214, 92, 0));
        explosionPaint.setStrokeWidth(3);

        spaceshipExplosionPaint = new Paint();
        spaceshipExplosionPaint.setColor(Color.rgb(212, 54, 70));
        spaceshipExplosionPaint.setShadowLayer(10,30,23, Color.argb(70, 219,81,58));
        spaceshipExplosionPaint.setStrokeWidth(10);

        spaceshipExplosionTwoPaint = new Paint();
        spaceshipExplosionPaint.setColor(Color.rgb(254, 86, 53));
        spaceshipExplosionPaint.setShadowLayer(10,30,23, Color.argb(70, 219,81,58));
        spaceshipExplosionPaint.setStrokeWidth(10);

        gameOverPaint = new Paint();
        gameOverPaint.setStrokeWidth(30);
        gameOverPaint.setColor(Color.WHITE);
        gameOverPaint.setTextSize(30);

        scorePaint = new Paint();
        scorePaint.setColor(Color.rgb(181,185,185));
        scorePaint.setTextSize(80);
        scorePaint.setStrokeWidth(30);
    }

//    public static Paint randomPaint() {
//        Random random = new Random();
//        switch (random.nextInt(3) + 1) {
//            case 1:
//                return planetPaintOne;
//            case 2:
//                return planetPaintTwo;
//            case 3:
//                return planetPaintThree;
//            default:
//                return planetPaintFour;
//        }
//    }

}
