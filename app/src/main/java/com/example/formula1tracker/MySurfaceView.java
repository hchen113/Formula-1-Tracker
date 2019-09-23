package com.example.formula1tracker;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.List;

public class MySurfaceView extends SurfaceView{


    private SurfaceHolder surfaceHolder = null;
    private Paint paint = null;
    List<MainActivity.driver> data_list;


    public MySurfaceView(Context context, List<MainActivity.driver> data_list){
        super(context);

        surfaceHolder = getHolder();
        paint = new Paint();
        paint.setColor(Color.RED);
        this.data_list = data_list;
    }


    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawGraph();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        paint = null;

    }

    public void drawGraph(){

        surfaceHolder = getHolder();


        Log.d("WINNIE", "IN DRAW GRAPH");
        Canvas canvas = surfaceHolder.lockCanvas();

        int number_of_bar = data_list.size();
        int max_point = 0;
        for (MainActivity.driver driver: data_list){
            if (driver.points > max_point){
                max_point = driver.points;
            }
        }

        Log.d("WINNIE", "Number of selected drivers: " + Integer.toString(number_of_bar));

        int width= canvas.getWidth();
        int height = canvas.getHeight();

        int bar_width = 10;

        int max_bar_height = 3 * (height/4);

        Log.d("WINNIE", "STARTING TO DRAW BARS");

        int x_pos_counter = 0;
        for (MainActivity.driver driver : data_list){
            Log.d("WINNIE", driver.lastname + " scored " + Integer.toString(height) + " points");

            x_pos_counter = x_pos_counter + 200;
            Rect temp = new Rect();
            temp.set(x_pos_counter, (max_bar_height * (driver.points/max_point))+200, x_pos_counter + bar_width, canvas.getHeight());
            canvas.drawRect(temp, paint);

        }

        Log.d("WINNIE", "DONE WITH BARS");
        surfaceHolder.unlockCanvasAndPost(canvas);

    }


}
