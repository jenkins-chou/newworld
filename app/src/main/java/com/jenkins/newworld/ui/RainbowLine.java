package com.jenkins.newworld.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jenkins.newworld.R;

/**
 * Created by zhouzhenjian on 2018/4/16.
 */

//彩虹带
public class RainbowLine extends View {
    private int[] colors = {R.color.rainbow_red,R.color.rainbow_orange,R.color.rainbow_yellow,R.color.rainbow_green,R.color.rainbow_cyan,R.color.rainbow_blue,R.color.rainbow_violet};
    private int colorP = 0;
    private Paint paint;
    private int width=0,height=0;
    private int chunkNum = 14;
    public RainbowLine(Context context) {
        super(context);
    }

    public RainbowLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RainbowLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        for (int i =0;i<chunkNum;i++){
            paint.setColor(getResources().getColor(colors[colorP++]));
            colorP = colorP % colors.length;
            canvas.drawRect(0,0,width/chunkNum,height,paint);
            canvas.translate(width/chunkNum,0);
        }

    }
}
