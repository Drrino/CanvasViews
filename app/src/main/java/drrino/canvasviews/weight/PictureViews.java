package drrino.canvasviews.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Coder on 16/5/13.
 */
public class PictureViews extends View {
  //1.创建Picture
  private Picture mPicture = new Picture();
  private Paint textPaint = new Paint();

  public PictureViews(Context context) {
    super(context);
    initPaint();
    recording();
  }

  public PictureViews(Context context, AttributeSet attrs) {
    super(context, attrs);
    initPaint();
    recording();
  }

  public PictureViews(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initPaint();
    recording();
  }

  //初始化画笔
  private void initPaint() {
    textPaint.setColor(Color.BLACK);
    textPaint.setStyle(Paint.Style.FILL);
    textPaint.setTextSize(50);
  }

  //2.录制内容方法
  //录制内容不会显示在屏幕中，但已储存起来
  private void recording() {
    //开始录制(接收返回值Canvas)
    Canvas canvas = mPicture.beginRecording(500, 500);
    Paint paint = new Paint();
    paint.setColor(Color.GREEN);
    paint.setStyle(Paint.Style.FILL);
    canvas.translate(250, 250);
    canvas.drawCircle(0, 0, 100, paint);
    mPicture.endRecording();
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //CanvasPicture(canvas);

    //DrawText(canvas, textPaint);

    DrawPosText(canvas, textPaint);
  }

  /**
   * 不推荐使用
   *
   * 1.必须指定所有字符位置，否则直接crash掉
   * 2.性能不佳，在大量使用的时候可能导致卡顿
   * 3.不支持emoji等特殊字符，不支持字形组合与分解
   */
  private void DrawPosText(Canvas canvas, Paint textPaint) {
    String str = "SLOOP";
    canvas.drawPosText(str, new float[] {
        100, 100,    // 第一个字符位置
        200, 200,    // 第二个字符位置
        300, 300,    // ...
        400, 400, 500, 500
    }, textPaint);
  }

  private void DrawText(Canvas canvas, Paint textPaint) {
    String str = "ABCDEFGHIJK";
    // 字符数组(要绘制的内容)
    char[] chars = "ABCDEFGHIJK".toCharArray();

    //canvas.drawText(str, 200, 500, textPaint);
    //canvas.drawText(str,1,3,200,500,textPaint);//截取
    canvas.drawText(chars, 1, 3, 200, 500, textPaint);
  }

  private void CanvasPicture(Canvas canvas) {
    //1.使用Picture提供的draw方法绘制
    //在低版本系统上绘制会影响Canvas状态，一般不会使用
    //mPicture.draw(canvas);

    //2.使用Canvas提供的drawPicture方法绘制
    //canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 200));

    //3.将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。
    PictureDrawable drawable = new PictureDrawable(mPicture);
    // 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
    //setBounds是设置在画布上的绘制区域，并非根据该区域进行缩放，也不是剪裁Picture，每次都从Picture的左上角开始绘制。
    drawable.setBounds(0, 0, 250, mPicture.getHeight());
    drawable.draw(canvas);
  }
}
