package com.idtech.aidanlawfordwickham.asteroids;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by iD Student on 7/20/2017.
 */

public class Joystick {

    private int canvasJoystickCentreX;
    private int canvasJoystickCentreY;
    private int canvasFingerX;
    private int canvasFingerY;
    private boolean joystickNeeded;

    private final double JOYSTICKFACTOR = 0.07;

    Paint joystickPaint;

    public Joystick() {
        joystickPaint = new Paint();
        joystickPaint.setColor(Color.argb(60, 128, 128, 128));
        joystickPaint.clearShadowLayer();
        joystickPaint.setStrokeWidth(5);
    }

    public void setCanvasJoystickCentreX(int canvasJoystickCentreX) {
        this.canvasJoystickCentreX = canvasJoystickCentreX;
    }

    public void setCanvasJoystickCentreY(int canvasJoystickCentreY) {
        this.canvasJoystickCentreY = canvasJoystickCentreY;
    }

    public void setCanvasFingerX(int canvasFingerX) {
        this.canvasFingerX = canvasFingerX;
    }

    public void setCanvasFingerY(int canvasFingerY) {
        this.canvasFingerY = canvasFingerY;
    }

    public double getJoystickX() {
        return (canvasFingerX - canvasJoystickCentreX) * JOYSTICKFACTOR;
    }

    public double getJoystickY() {
        return (canvasFingerY - canvasJoystickCentreY) * JOYSTICKFACTOR;
    }

    public void isActive(boolean touchEventOnGoing) {
        if(!touchEventOnGoing) {
            setJoystickNeeded(false);
            setCanvasJoystickCentreX(0);
            setCanvasJoystickCentreY(0);
            setCanvasFingerX(0);
            setCanvasFingerY(0);
        }
    }

    public void setJoystickNeeded(boolean joystickNeeded) {
        this.joystickNeeded = joystickNeeded;
    }

    public void draw(Canvas canvas) {
        if(joystickNeeded) {
            canvas.drawCircle(canvasJoystickCentreX, canvasJoystickCentreY, 20, joystickPaint);
            canvas.drawCircle(canvasFingerX, canvasFingerY, 50, joystickPaint);
            canvas.drawLine(canvasJoystickCentreX, canvasJoystickCentreY, canvasFingerX, canvasFingerY, joystickPaint);
        }
    }
}
