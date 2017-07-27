package com.idtech.aidanlawfordwickham.asteroids;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.MainThread;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.gameOverPaint;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    Joystick joystick = new Joystick();
    private GameThread thread;
    boolean gameOver;
    Random random;
    ScoreCounter scoreCounter =  new ScoreCounter();
    int ticker;
    int tickerNumber = 40;
    float n = 8;
    ObjectManager objectManager;

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
        objectManager = new ObjectManager();
        random = new Random();
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
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(gameOver) {

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
                objectManager.weapon.fire(2);

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
            // Game Over Animation
            int n = 10;
            for(int i = 0; i < n; i++) {
                int x1 = canvas.getHeight() * (ticker % (n - i)) / n;
                int y1 = canvas.getWidth() * (ticker % (i + 1)) / n;
                int x2 = canvas.getWidth() * (ticker % (i + 1)) / n;
                int y2 = canvas.getHeight() * (ticker % (n - i)) / n;
                switch (i % 3) {
                    case 0: canvas.drawLine(x1, y1, x2, y2, ObjectPaint.laserPaint); break;
                    case 1: canvas.drawLine(x1, y1, x2, y2, ObjectPaint.explosionPaint); break;
                    case 2: canvas.drawLine(x1, y1, x2, y2, ObjectPaint.spaceshipExplosionPaint); break;
                }
            }
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
                if(n == 3) {
                    if(random.nextInt(9) > n) {
                        objectManager.addPlanet(canvas);
                    } else {
                        objectManager.addAsteroid(canvas);
                    }
                } else if(random.nextInt(9) > n) {
                    objectManager.addPlanet(canvas);
                    n -= 0.01;
                } else if(random.nextInt(9) < n) {
                    objectManager.addAsteroid(canvas);
                    n -= 0.01;
                }
            }

            // Draw Stars
            if(ticker % 4 == 0) {
                objectManager.addStar(canvas);
            }
            decreaseTickerNumber();
        }
        ticker++;
    }

    public void scoreCheck(boolean addScore) {
        if(addScore) {
            scoreCounter.addScore();
        }
    }

    public void setgameOver(boolean gameOver) {
        this.gameOver = gameOver;
        if(gameOver) {
            scoreCounter.setHighScore();
            tickerNumber = 40;
            ticker = 0;
            int score = scoreCounter.getScore();
            resetGame();
            MainActivity mainActivity = (MainActivity) getContext();
            if(mainActivity != null) {
                mainActivity.setGameOverView(score, scoreCounter.getHighScore());
            }
        } else {
        }
    }
    public void collisionCheck() {
        if(gameOver) {

        } else {
            setgameOver(objectManager.spaceship.getToBeRemoved());
            objectManager.clearObjects();
        }
    }

    void resetGame() {
        scoreCounter.resetScore();
        objectManager.resetObjects();
        joystick.isActive(false);
        n = 8;
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
        if(gameOver) {

        } else {
            objectManager.preDraw();
        }
    }
}
