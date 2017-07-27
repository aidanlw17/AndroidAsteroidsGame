package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Canvas;

/**
 * Created by iD Student on 7/27/2017.
 */

interface BaseGameView {

    void preDraw();
    void onDraw(Canvas canvas);
    void collisionCheck();
}
