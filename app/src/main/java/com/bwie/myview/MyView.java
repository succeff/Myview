package com.bwie.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 类的描述：
 * 时间：  2017/8/3.14:19
 * 姓名：chenlong
 */

public class MyView  extends View{
    Region region;//圆形区域
    Path path;//圆形路径
    Path rpath;//矩形路径
    Region rregion;
    Paint paint;//画笔
    int x;
    int y;
    OnMyClickListener listener;//定义事件监听

    public void setListener(OnMyClickListener listener) {
        this.listener = listener;
    }

    public MyView(Context context) {
        super(context);
        lint();
    }

    private void lint() {
        paint = new Paint();
        paint.setColor(Color.BLUE);

        //圆形
        region = new Region();
        path = new Path();

        //矩形
        rpath = new Path();
        rregion = new Region();

    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        lint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            //获取点击坐标
            int x = (int) event.getX();
            int y = (int) event.getY();

            //判断点击区域
            if (region.contains(x, y)) {
                if (listener!=null){
                    listener.onCircleInnerClick(this);
                }
            } else if (rregion.contains(x, y)) {
                if (listener!=null){
                    listener.onCircleOuterClick(this);
                }
            } else {
                if (listener!=null){
                    listener.onWriteClick(this);
                }
            }
        }

        return super.onTouchEvent(event);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x = w/2;
        y = h/2;
        int r = 100;
        path.addCircle(x,y,r,Path.Direction.CW);
        rpath.addRect(x - r, y - r, x + r, y + r, Path.Direction.CW);

        //设置圆形区域
        Region region1 = new Region(0, 0, w, h);
        region.setPath(path, region1);

        rregion.setPath(rpath, region1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path tempRPath = rpath;//将圆绘制到画布上

        canvas.drawPath(tempRPath, paint);

        Path tempPath = path;//将圆绘制到画布上
        paint.setColor(Color.RED);

        canvas.drawPath(tempPath, paint);

        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(32);
//        canvas.drawText("圆心",x,y,paint);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    public interface OnMyClickListener {
        void onCircleInnerClick(View var1);
        void onCircleOuterClick(View var1);
        void onWriteClick(View var1);
    }

}
