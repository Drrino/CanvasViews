package drrino.canvasviews.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Coder on 16/5/13.
 */
public class CanvasViews extends View {
  private Paint mPaint = new Paint();
  private int mWidth;
  private int mHeight;

  public CanvasViews(Context context) {
    super(context);
  }

  public CanvasViews(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CanvasViews(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    mWidth = right - left;
    mHeight = bottom - top;
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //CanvasTranslate(canvas);

    //CanvasScale(canvas);

    //CanvasRotate(canvas);

    CanvasSkew(canvas);
  }

  /**
   * 倾斜
   *
   * public void skew (float sx, float sy)
   * sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
   * sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
   *
   * 倾斜也可以叠加，次序不同绘制结果不同
   */
  private void CanvasSkew(Canvas canvas) {
    // 将坐标系原点移动到画布正中心
    canvas.translate(mWidth / 2, mHeight / 2);
    RectF rect = new RectF(0, 0, 100, 100);   // 矩形区域
    mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
    canvas.drawRect(rect, mPaint);
    canvas.skew(1, 0);                       // 在x轴倾斜45度 <-- tan45 = 1
    //canvas.skew(1,0);                       // 在x轴倾斜45度 <-- tan45 = 1
    //canvas.skew(0,1);                       // 在y轴倾斜45度 <-- tan45 = 1
    mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
    canvas.drawRect(rect, mPaint);
  }

  /**
   * 旋转
   *
   * public final void rotate (float degrees, float px, float py)
   * 后面两个参数控制旋转中心点
   */
  private void CanvasRotate(Canvas canvas) {
    // 将坐标系原点移动到画布正中心
    canvas.translate(mWidth / 2, mHeight / 2);
    //RectF rect = new RectF(0, -200, 200, 0);   // 矩形区域
    //mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
    //canvas.drawRect(rect, mPaint);
    ////canvas.rotate(180);                     // 旋转180度 <-- 默认旋转中心为原点
    //canvas.rotate(180,100,0);               // 旋转180度 <-- 旋转中心向右偏移100个单位
    //mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
    //canvas.drawRect(rect, mPaint);

    ////////////////////////////旋转叠加///////////////////////////////
    mPaint.setStyle(Paint.Style.STROKE);
    canvas.drawCircle(0, 0, 200, mPaint);          // 绘制两个圆形
    canvas.drawCircle(0, 0, 180, mPaint);
    for (int i = 0; i <= 360; i += 10) {               // 绘制圆形之间的连接线
      canvas.drawLine(0, 180, 0, 200, mPaint);
      canvas.rotate(10);
    }
  }

  /**
   * 缩放
   *
   * public final void scale (float sx, float sy, float px, float py)
   * 后面两个参数用来控制缩放中心位置
   *
   * 当缩放比例为负数的时候会根据缩放中心轴进行翻转
   */
  private void CanvasScale(Canvas canvas) {
    //将坐标系原点放置画布正中心
    canvas.translate(mWidth / 2, mHeight / 2);
    //RectF rectF = new RectF(0, -200, 200, 0);
    //mPaint.setColor(Color.BLACK);
    //canvas.drawRect(rectF, mPaint);
    ////canvas.scale(0.5f, 0.5f, 200, 0);// 画布缩放<-- 缩放中心向右偏移了200个单位
    ////canvas.scale(-0.5f, -0.5f);          // 画布缩放
    //canvas.scale(-0.5f,-0.5f,100,0);      // 画布缩放  <-- 缩放中心向右偏移了100个单位
    //mPaint.setColor(Color.BLUE);
    //canvas.drawRect(rectF, mPaint);

    //////////////////////////缩放可以叠加/////////////////////////////////
    RectF rect = new RectF(-400, -400, 400, 400);   // 矩形区域
    mPaint.setStyle(Paint.Style.STROKE);
    for (int i = 0; i <= 20; i++) {
      canvas.scale(0.9f, 0.9f);
      canvas.drawRect(rect, mPaint);
    }
  }

  /**
   * 位移
   *
   * 位移是基于当前位置移动，而不是每次基于屏幕左上角的(0,0)点移动
   */
  private void CanvasTranslate(Canvas canvas) {
    // 在坐标原点绘制一个黑色圆形
    mPaint.setColor(Color.BLACK);
    canvas.translate(200, 200);
    canvas.drawCircle(0, 0, 100, mPaint);

    // 在坐标原点绘制一个蓝色圆形
    mPaint.setColor(Color.BLUE);
    canvas.translate(200, 200);
    canvas.drawCircle(0, 0, 100, mPaint);
  }
}
