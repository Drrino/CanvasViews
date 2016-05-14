package drrino.canvasviews.ui.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Coder on 16/5/12.
 */
public class PaintViews extends View {
  //创建画笔
  private Paint mPaint = new Paint();

  //初始化画笔
  private void initPaint() {
    mPaint.setColor(Color.BLACK);         //设置画笔颜色
    mPaint.setAntiAlias(true);            //抗锯齿
    PaintStyle();
    mPaint.setStrokeWidth(10f);           //设置画笔宽度为10px
  }

  //设置画笔模式
  private void PaintStyle() {
    // 描边
    //mPaint.setStyle(Paint.Style.STROKE);

    // 填充
    mPaint.setStyle(Paint.Style.FILL);

    // 描边加填充
    //mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
  }

  //在构造函数中初始化
  public PaintViews(Context context, AttributeSet attrs) {
    super(context, attrs);
    initPaint();
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //CanvasPoints(canvas);

    //CanvasLines(canvas);

    //CanvasRect(canvas);

    //CanvasRoundedRect(canvas);

    //CanvasOval(canvas);

    CanvasCircle(canvas);

    //CanvasArc(canvas);
  }

  /**
   * 绘制圆弧
   *
   * drawArc多出的三个参数
   * startAngle:开始角度
   * sweepAngle:扫过角度
   * useCenter:是否使用中心
   *
   * 使用了中心点之后绘制出来类似于一个扇形，
   * 而不使用中心点则是圆弧起始点和结束点之间的连线加上圆弧围成的图形。
   */
  private void CanvasArc(Canvas canvas) {
    RectF rectF = new RectF(50, 50, 500, 300);
    // 绘制背景矩形
    mPaint.setColor(Color.GRAY);
    canvas.drawRect(rectF, mPaint);

    // 绘制圆弧
    mPaint.setColor(Color.BLUE);
    canvas.drawArc(rectF, 0, 90, false, mPaint);

    //-------------------------------------

    RectF rectF2 = new RectF(50, 500, 500, 800);
    // 绘制背景矩形
    mPaint.setColor(Color.GRAY);
    canvas.drawRect(rectF2, mPaint);

    // 绘制圆弧
    mPaint.setColor(Color.BLUE);
    canvas.drawArc(rectF2, 0, 90, true, mPaint);
  }

  /**
   * 绘制圆心(300,300)半径100的圆
   */
  private void CanvasCircle(Canvas canvas) {
    canvas.drawCircle(300, 300, 100, mPaint);
  }

  /**
   * 绘制椭圆
   *
   * 一般使用方式一
   */
  private void CanvasOval(Canvas canvas) {
    // 方式一
    //RectF rectF = new RectF(50,50,600,400);
    //canvas.drawOval(rectF,mPaint);

    // 方式二
    canvas.drawOval(50, 50, 500, 400, mPaint);
  }

  /**
   * 绘制圆角矩形
   *
   * 与矩形相比，圆角矩形多出来两参数rx和ry
   * 圆角矩形的角不是正圆的圆弧，而是椭圆的圆弧，这两参数是椭圆的两半径
   *
   * 当rx大于宽度的一半，ry大于高度的一半时，实际上是无法计算出圆弧的，
   * 所以drawRoundRect对大于该数值的参数进行了限制(修正)，凡是大于一半的参数均按照一半来处理。
   *
   * 方式二在API21时添加上，使用用需注意
   */
  private void CanvasRoundedRect(Canvas canvas) {
    //方式一
    //RectF rectF = new RectF(50,50,300,100);
    //canvas.drawRoundRect(rectF,15,15,mPaint);

    //方式二
    canvas.drawRoundRect(100, 100, 500, 400, 30, 30, mPaint);
  }

  /**
   * 绘制矩形
   *
   * 通过设置距离屏幕左上右下距离来确定矩形
   *
   * Rect和RectF最大的区别就是精度不同，Rect是int(整形)，RectF为float(单精度浮点型).
   * 其次就是两种提供的方法存在差异
   */
  private void CanvasRect(Canvas canvas) {
    //方式一
    //canvas.drawRect(50,50,300,200,mPaint);

    //方式二
    //Rect rect = new Rect(100,100,300,200);
    //canvas.drawRect(rect,mPaint);

    //方式三
    RectF rectF = new RectF(80, 80, 200, 150);
    canvas.drawRect(rectF, mPaint);
  }

  //绘制直线
  private void CanvasLines(Canvas canvas) {
    canvas.drawLine(400, 400, 500, 600, mPaint);    // 在坐标(300,300)(500,600)之间绘制一条直线
    canvas.drawLines(new float[] {               // 绘制一组线 每四数字(两个点的坐标)确定一条线
        100, 200, 200, 200, 100, 300, 200, 300
    }, mPaint);
  }

  //绘制坐标点
  private void CanvasPoints(Canvas canvas) {
    canvas.drawPoint(200, 200, mPaint);     //在坐标(200,200)位置绘制一个点
    canvas.drawPoints(new float[] {         //绘制一组点，坐标位置由float数组指定
        300, 500, 300, 600, 300, 700
    }, mPaint);
  }
}
