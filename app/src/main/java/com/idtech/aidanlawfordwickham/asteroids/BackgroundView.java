package com.idtech.aidanlawfordwickham.asteroids;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by iD Student on 7/27/2017.
 */

public class BackgroundView extends SurfaceView implements SurfaceHolder.Callback, BaseGameView {

    private GameThread thread;
    private int ticker;
    Random random;
    private Spaceship spaceship;
    private Title title;

    private ArrayList<Star> stars;
    private ArrayList<Star> starsToRemove;
    private ArrayList<Star> tempStars;

    public BackgroundView(Context context, AttributeSet as) {
        super(context, as);
        getHolder().addCallback(this);
        setFocusable(true);
        random = new Random();
        spaceship = new Spaceship(ObjectBitmap.spaceshipBM, 10, 60);
//        title = new Title(ObjectBitmap.titleBM, (canvas.getWidth() - ObjectBitmap.titleBM.getWidth() / 2), canvas.getHeight() /4);

        thread = new GameThread(getHolder(), this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!thread.isRunning()) {
            resetGame();
            thread = new GameThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public void preDraw() {
        preDrawStars();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(canvas == null) {
            return;
        }
        canvas.drawColor(Color.rgb(0,0,0));

        spaceship.drawBackground(canvas);

        for(Star star: tempStars) {
            star.draw(canvas);
        }

        if(ticker % 4 == 0) {
            addStar(canvas);
        }
        ticker++;
    }

    @Override
    public void collisionCheck() {
        clearStars();
    }

    public void addStar(Canvas canvas) {
        Random generator = new Random();
        int radius = generator.nextInt(10) + 1;
        int startingXPosition = generator.nextInt(canvas.getWidth() - 5);
        Star star = new Star(startingXPosition, 0, radius);
        tempStars.add(star);
    }

    public void preDrawStars() {
        synchronized (stars) {
            tempStars = new ArrayList<Star>(stars);
        }
    }

    public void clearStars() {
        tempStars.remove(starsToRemove);
        stars = tempStars;
        starsToRemove.clear();
    }

    public void resetGame() {
        spaceship = new Spaceship(ObjectBitmap.spaceshipBM, 10, 60);
        resetStars();
    }

    public void resetStars() {
        stars = new ArrayList<Star>();
        starsToRemove = new ArrayList<Star>();
    }
}
