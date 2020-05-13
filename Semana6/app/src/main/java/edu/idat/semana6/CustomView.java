package edu.idat.semana6;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    private int strokeWidth;
    private String strokeColor;
    private String fillColor;

    public CustomView(Context context) {
        super(context);
        strokeWidth = 20;
        strokeColor = "#DDDDDD";
        fillColor = "#AAAAAA";
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        try {
            strokeWidth = a.getInteger(R.styleable.CustomView_strokeWidth, 0);
            strokeColor = a.getString(R.styleable.CustomView_strokeColor);
            fillColor = a.getString(R.styleable.CustomView_fillColor);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Paint paint = new Paint();
//        paint.setStrokeWidth(20);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.BLACK);
//        canvas.drawCircle(150, 150, 100, paint);
//
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.YELLOW);
//        canvas.drawCircle(150, 150, 100, paint);
//
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.argb(255, 87, 37, 64));
//        canvas.drawRect(20, 350, 300, 750, paint);
//
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.parseColor("#03DAC5"));
//        canvas.drawRect(20, 350, 300, 750, paint);

        Paint paint = new Paint();
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.parseColor(strokeColor));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(20, 350, 300, 750, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor(fillColor));
        canvas.drawRect(20, 350, 300, 750, paint);
    }
}
