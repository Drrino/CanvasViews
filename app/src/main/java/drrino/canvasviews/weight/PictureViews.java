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

  public PictureViews(Context context) {
    super(context);
    recording();
  }

  public PictureViews(Context context, AttributeSet attrs) {
    super(context, attrs);
    recording();
  }

  public PictureViews(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    recording();
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
    CanvasPicture(canvas);
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
    drawable.setBounds(0,0,250,mPicture.getHeight());
    drawable.draw(canvas);
  }
}
