package com.neoress.movebitmapusingcanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
    Bitmap bitmap;
    float x = 200;
    float y = 200;

    GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_happy);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float posX, posY;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                posX = event.getX();
                posY = event.getY();

                if(posX >= 0 && posX < getWidth() - bitmap.getWidth() &&
                    posY >= 0 && posY < getHeight() - bitmap.getHeight()) {
                    x = posX;
                    y = posY;

                    BeFine();
                }

                // redraw
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                posX = event.getX();
                posY = event.getY();

                if(posX >= 0 && posX < getWidth() - bitmap.getWidth() &&
                        posY >= 0 && posY < getHeight() - bitmap.getHeight()) {
                    x = posX;
                    y = posY;

                    BeFine();
                } else {
                    FeelAngry();
                }

                // redraw
                invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, x, y, null);
    }

    private Bitmap getBitmap(String status) {
        int resourceID;
        switch (status) {
            case "angry":
                resourceID = R.drawable.ic_angry;
                break;
            default:
                resourceID = R.drawable.ic_happy;
                break;
        }

        return BitmapFactory.decodeResource(this.getResources(), resourceID);
    }

    private void FeelAngry() {
        bitmap = getBitmap("angry");
    }

    private void BeFine() {
        bitmap = getBitmap("happy");
    }
}
