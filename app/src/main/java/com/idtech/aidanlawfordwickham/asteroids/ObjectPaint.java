package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by iD Student on 7/26/2017.
 */

public class ObjectPaint {

    static Paint laserPaint;
    static Paint rocketPaint;
    static Paint turretPaint;
    static Paint explosionPaint;
//    static Paint planetPaintOne;
//    static Paint planetPaintTwo;
//    static Paint planetPaintThree;
//    static Paint planetPaintFour;

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

//        planetPaintOne = new Paint();
//        planetPaintOne.setColor(Color.rgb(206,93,0));
//
//        planetPaintTwo = new Paint();
//        planetPaintTwo.setColor(Color.rgb(215,120,32));
//
//        planetPaintThree = new Paint();
//        planetPaintThree.setColor(Color.rgb(73,1,0));
//
//        planetPaintFour = new Paint();
//        planetPaintFour.setColor(Color.rgb(250,0,0));

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
