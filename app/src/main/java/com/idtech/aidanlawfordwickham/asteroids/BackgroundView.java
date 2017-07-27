package com.idtech.aidanlawfordwickham.asteroids;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by iD Student on 7/27/2017.
 */

public class BackgroundView extends SurfaceView implements SurfaceHolder.Callback, BaseGameView {

    private GameThread thread;

    public BackgroundView(Context context, AttributeSet as) {
        super(context, as);
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(false);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // resetGame();
        if (!thread.isRunning()) {
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

    }

    @Override
    public void onDraw(Canvas canvas) {
        if(canvas == null) {
            return;
        }
    }

    @Override
    public void collisionCheck() {

    }

}
