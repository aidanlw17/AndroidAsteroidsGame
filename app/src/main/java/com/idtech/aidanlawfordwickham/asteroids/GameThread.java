package com.idtech.aidanlawfordwickham.asteroids;

/**
 * Created by iD Student on 7/20/2017.
 */

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private BaseGameView gameView;

    public boolean isRunning() {
        return running;
    }

    private boolean running;
    public void setRunning(boolean running) {
        this.running = running;
    }

    public GameThread(SurfaceHolder surfaceHolder, BaseGameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    @SuppressLint("WrongCall")
    public void run() {

        Canvas canvas;
        while (running) {
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.preDraw();
                    this.gameView.onDraw(canvas);
                    this.gameView.collisionCheck();
                }
            } finally {
                if(canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

    }
}