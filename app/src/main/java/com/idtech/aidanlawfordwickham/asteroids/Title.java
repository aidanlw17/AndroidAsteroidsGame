package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by iD Student on 7/28/2017.
 */

public class Title {

    Bitmap bitmap;
    int x;
    int y;
    int ticker;
    private enum State{movingUp, movingDown, hoveringBottom, hoveringTop }

    public Title(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void draw(Canvas canvas) {
        if(ticker % 2 == 0) {
            canvas.drawBitmap(bitmap, x, y + 4, null);
        } else {
            canvas.drawBitmap(bitmap, x, y, null);
        }
        ticker++;

    }

    public void movingUp() {

    }
}
