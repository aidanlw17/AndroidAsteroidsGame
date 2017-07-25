package com.idtech.aidanlawfordwickham.asteroids;

/**
 * Created by iD Student on 7/20/2017.
 */

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.view.MotionEvent;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;
        import android.graphics.BitmapFactory;

        import java.util.ArrayList;
        import java.util.Random;
        import java.util.Timer;

        import static com.idtech.aidanlawfordwickham.asteroids.R.drawable.asteroid;
        import static com.idtech.aidanlawfordwickham.asteroids.Util.getResizedBitmap;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    Joystick joystick = new Joystick();
    private GameThread thread;
    boolean gameOver;
    Paint gameOverPaint;

    ScoreCounter scoreCounter =  new ScoreCounter();
    int ticker;
    int tickerNumber = 40;
    ObjectManager objectManager;

    public void setgameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
        objectManager = new ObjectManager(getResources());

        gameOverPaint = new Paint();
        gameOverPaint.setColor(Color.WHITE);
        gameOverPaint.setTextSize(40);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        resetGame();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;
        while(retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(gameOver) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                resetGame();
            }
        } else {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                joystick.setJoystickNeeded(true);
                joystick.setCanvasJoystickCentreX((int) event.getX());
                joystick.setCanvasJoystickCentreY((int) event.getY());
                joystick.setCanvasFingerX((int) event.getX());
                joystick.setCanvasFingerY((int) event.getY());
                // Set locations for firing weapon
                objectManager.weapon.setHolsterLocations(objectManager.spaceship.getX(), objectManager.spaceship.getY());
                // Fire Bullet
                objectManager.weapon.fire();

            } else if(event.getAction() == MotionEvent.ACTION_MOVE) {
                joystick.setCanvasFingerX((int) event.getX());
                joystick.setCanvasFingerY((int) event.getY());
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                joystick.isActive(false);
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(canvas == null) {
            return;
        }
        canvas.drawColor(Color.rgb(0,0,0));

        if(gameOver) {
            scoreCounter.resetScore();
            tickerNumber = 40;
            ticker = 0;
            canvas.drawText("Game Over! Tap the screen to restart.", 30, getHeight() / 2, gameOverPaint);
            canvas.drawText("Your score: " + Integer.toString(scoreCounter.getScore()), 100, getHeight() / 2 + 100, gameOverPaint);
            canvas.drawText("High score: " + Integer.toString(scoreCounter.getHighScore()), 100, getHeight() / 2 + 200, gameOverPaint);
        } else {
            // Draw Ship
            objectManager.spaceship.setxVelocity((int) joystick.getJoystickX());
            objectManager.spaceship.setyVelocity((int) joystick.getJoystickY());
            // Draw Joystick
            joystick.draw(canvas);


            scoreCheck(objectManager.draw(canvas));


            // Draw scoreboard
            scoreCounter.draw(canvas);

            // Draw Asteroids
            if(ticker % tickerNumber == 0) {
                objectManager.addAsteroid(canvas);
            }

            decreaseTickerNumber();
            ticker++;
        }
    }

    public void scoreCheck(boolean addScore) {
        if(addScore) {
            scoreCounter.addScore();
        }
    }

    public void collisionCheck() {
        setgameOver(objectManager.collisionsCheckAsteroidSpaceship());
        objectManager.clearObjects();
    }

    void resetGame() {
        scoreCounter.resetScore();
        objectManager.resetObjects();
        setgameOver(false);
    }

    public void decreaseTickerNumber() {
        if(ticker == 200) {
            tickerNumber = 35;
        } else if(ticker == 300) {
            tickerNumber = 30;
        } else if(ticker == 400) {
            tickerNumber = 25;
        } else if(ticker == 500) {
            tickerNumber = 20;
        } else if(ticker == 600) {
            tickerNumber = 15;
        }
    }

    public void preDraw() {
        objectManager.preDraw();
    }
}