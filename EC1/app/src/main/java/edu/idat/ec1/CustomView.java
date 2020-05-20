package edu.idat.ec1;

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
    private int width;
    private int height;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);

        try {
            strokeWidth = a.getInteger(R.styleable.CustomView_cStrokeWidth, 0);
            strokeColor = a.getString(R.styleable.CustomView_cStrokeColor);
            fillColor = a.getString(R.styleable.CustomView_cFillColor);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.parseColor(strokeColor));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(strokeWidth / 2, strokeWidth / 2, width - strokeWidth / 2, height - strokeWidth / 2, paint);

        paint.setColor(Color.parseColor(fillColor));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(strokeWidth, strokeWidth, width - strokeWidth, height - strokeWidth, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }
}
