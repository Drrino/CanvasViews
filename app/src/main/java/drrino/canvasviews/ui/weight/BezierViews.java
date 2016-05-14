package drrino.canvasviews.ui.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Coder on 16/5/14.
 */
public class BezierViews extends View {
  private Paint mPaint;
  private int centerX, centerY;
  private PointF start, end, control, control2;
  private boolean mode = true;

  public BezierViews(Context context) {
    super(context);
    initPaint();
    initPointF();
  }

  public BezierViews(Context context, AttributeSet attrs) {
    super(context, attrs);
    initPaint();
    initPointF();
  }

  public BezierViews(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initPaint();
    initPointF();
  }

  protected void setMode(boolean mode) {
    this.mode = mode;
  }

  private void initPointF() {
    start = new PointF(0, 0);
    end = new PointF(0, 0);
    control = new PointF(0, 0);
    control2 = new PointF(0, 0);
  }

  private void initPaint() {
    mPaint = new Paint();
    mPaint.setColor(Color.BLACK);
    mPaint.setStrokeWidth(10);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setTextSize(50);
    mPaint.setAntiAlias(true);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    centerX = w / 2;
    centerY = h / 2;
    //初始化数据点和控制点的位置
    start.x = centerX - 200;
    start.y = centerY;
    end.x = centerX + 200;
    end.y = centerY;
    control.x = centerX;
    control.y = centerY - 100;
    control2.x = centerX + 50;
    control2.y = centerY - 100;
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    //control.x = event.getX();
    //control.y = event.getY();

    if (mode) {
      control.x = event.getX();
      control.y = event.getY();
    } else {
      control2.x = event.getX();
      control2.y = event.getY();
    }
    invalidate();
    return true;
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //Path path = QuadTo(canvas);

    Path path = CubicTo(canvas);

    canvas.drawPath(path, mPaint);
  }

  //三阶曲线
  @NonNull private Path CubicTo(Canvas canvas) {
    // 绘制数据点和控制点
    mPaint.setColor(Color.GRAY);
    mPaint.setStrokeWidth(20);
    canvas.drawPoint(start.x, start.y, mPaint);
    canvas.drawPoint(end.x, end.y, mPaint);
    canvas.drawPoint(control.x, control.y, mPaint);
    canvas.drawPoint(control2.x, control2.y, mPaint);
    // 绘制辅助线
    mPaint.setStrokeWidth(4);
    canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
    canvas.drawLine(control.x, control.y, control2.x, control2.y, mPaint);
    canvas.drawLine(control2.x, control2.y, end.x, end.y, mPaint);
    // 绘制贝塞尔曲线
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(10);
    Path path = new Path();
    path.moveTo(start.x, start.y);
    path.cubicTo(control.x, control.y, control2.x, control2.y, end.x, end.y);
    return path;
  }

  //二阶曲线
  @NonNull private Path QuadTo(Canvas canvas) {
    //绘制数据点和控制点
    mPaint.setColor(Color.GRAY);
    mPaint.setStrokeWidth(20);
    canvas.drawPoint(start.x, start.y, mPaint);
    canvas.drawPoint(end.x, end.y, mPaint);
    canvas.drawPoint(control.x, control.y, mPaint);
    //绘制辅助线
    mPaint.setStrokeWidth(4);
    canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
    canvas.drawLine(end.x, end.y, control.x, control.y, mPaint);
    //绘制贝塞尔曲线
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(10);
    Path path = new Path();
    path.moveTo(start.x, start.y);
    path.quadTo(control.x, control.y, end.x, end.y);  //二阶曲线对应的方法
    return path;
  }
}
