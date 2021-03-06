package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import static com.idtech.aidanlawfordwickham.asteroids.ObjectPaint.scorePaint;

/**
 * Created by iD Student on 7/21/2017.
 */

public class ScoreCounter {

    private int highScore;
    private int score;

    public ScoreCounter() {
        this.score = 0;
        this.highScore = 0;
    }

    public void setHighScore() {
        if(score > highScore) {
            highScore = score;
        }
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return this.score;
    }

    public int getHighScore() {
        return this.highScore;
    }

    public void addScore() {
        score++;
    }

    public void draw(Canvas canvas) {
        canvas.drawText(Integer.toString(score), 15, 80, scorePaint);
    }



}
