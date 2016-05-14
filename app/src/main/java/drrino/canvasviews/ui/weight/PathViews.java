package drrino.canvasviews.ui.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Coder on 16/5/14.
 */
public class PathViews extends View {
  private Paint mPaint = new Paint();
  private int mWidth;
  private int mHeight;

  public PathViews(Context context) {
    super(context);
    initPaint();
  }

  public PathViews(Context context, AttributeSet attrs) {
    super(context, attrs);
    initPaint();
  }

  public PathViews(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initPaint();
  }

  //创建画笔
  private void initPaint() {
    mPaint.setColor(Color.BLACK);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(10);
    mPaint.setAntiAlias(true);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mWidth = w;
    mHeight = h;
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.translate(mWidth / 2, mHeight / 2);
    Path path = new Path();

    //PathLineTo(path);

    //PathMoveTo(path);

    //PathSetLastPoint(path);

    //PathClose(path);

    //PathCW(canvas, path);

    //PathCCW(canvas, path);

    //PathAddPath(canvas, path);

    //PathAddArc(canvas,path);

    //PathArcTo(canvas, path);

    //isEmpty(path);

    //isRect(path);

    //PathSet(canvas, path);

    PathOffset(canvas, path);

    canvas.drawPath(path, mPaint);
  }

  /**
   * 对path进行平移
   * 当dst中存在内容时，dst中原有的内容会被清空，而存放平移后的path。
   */
  private void PathOffset(Canvas canvas, Path path) {
    path.addCircle(0, 0, 80, Path.Direction.CW); //添加一个圆形
    Path dst = new Path();                      //添加一个矩形
    dst.addRect(-200, -200, 200, 200, Path.Direction.CW);
    path.offset(200, 0, dst);                     // 平移
    canvas.drawPath(dst, mPaint);
    mPaint.setColor(Color.BLUE);                // 更改画笔颜色
  }

  /**
   * 将新的path赋值到现有path
   */
  private void PathSet(Canvas canvas, Path path) {
    canvas.scale(1, -1);
    path.addRect(-200, -200, 200, 200, Path.Direction.CW);
    Path src = new Path();                      // src添加一个圆
    src.addCircle(0, 0, 100, Path.Direction.CW);
    path.set(src);
  }

  /**
   * 判断是否是一个矩形
   */
  private void isRect(Path path) {
    path.lineTo(0, 400);
    path.lineTo(400, 400);
    path.lineTo(400, 0);
    path.lineTo(0, 0);
    RectF rect = new RectF();
    boolean b = path.isRect(rect);
    Log.e("Rect", "isRect:"
        + b
        + "| left:"
        + rect.left
        + "| top:"
        + rect.top
        + "| right:"
        + rect.right
        + "| bottom:"
        + rect.bottom);
  }

  /**
   * 判断path中是否包含内容
   */
  private void isEmpty(Path path) {
    Log.e("1", path.isEmpty() + "");
    path.lineTo(100, 100);
    Log.e("2", path.isEmpty() + "");
  }

  /**
   * public void arcTo (RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
   * forceMoveTo为true：将最后一个点移动到圆弧起点，即不连接最后一个点与圆弧起点
   * forceMoveTo为false：不移动，而是连接最后一个点与圆弧起点
   */
  private void PathArcTo(Canvas canvas, Path path) {
    canvas.scale(1, -1);
    path.lineTo(100, 100);
    RectF oval = new RectF(0, 0, 200, 200);
    path.arcTo(oval, 0, 270);
    // path.arcTo(oval,0,270,false);             //和上面一句作用等价
  }

  /**
   * 添加一个圆弧到path中
   */
  private void PathAddArc(Canvas canvas, Path path) {
    canvas.scale(1, -1);
    path.lineTo(100, 100);
    RectF oval = new RectF(0, 0, 200, 200);
    path.addArc(oval, 0, 270);
    // path.arcTo(oval,0,270,true);             //和上面一句作用等价
  }

  /**
   * public void addPath (Path src)
   * public void addPath (Path src, Matrix matrix)
   * public void addPath (Path src, float dx, float dy)
   * 第一种将两个Path合并成一个
   * 第二种将src添加到path前先使用Matrix进行变换
   * 第三种是将src进行位移后再添加进path中
   */
  private void PathAddPath(Canvas canvas, Path path) {
    canvas.scale(1, -1);
    Path src = new Path();
    path.addRect(-200, -200, 200, 200, Path.Direction.CW);
    src.addCircle(0, 0, 100, Path.Direction.CW);
    path.addPath(src, 0, 200);
  }

  /**
   * 逆时针
   */
  private void PathCCW(Canvas canvas, Path path) {
    canvas.scale(1, -1);    //翻转y坐标轴
    path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
    path.setLastPoint(-250, 250);   //重置最后一个点
  }

  /**
   * 顺时针
   */
  private void PathCW(Canvas canvas, Path path) {
    canvas.scale(1, -1);    //翻转y坐标轴
    path.addRect(-200, -200, 200, 200, Path.Direction.CW);
    path.setLastPoint(-250, 250);   //重置最后一个点
  }

  /**
   * close作用为封闭路径，与当前最后一个点和第一个点不等价
   * 如果连接了最后一个点和第一个点仍然无法形成封闭图形，则close什么 也不做。
   */
  private void PathClose(Path path) {
    path.lineTo(200, 200);
    path.lineTo(200, 0);
    path.close();
  }

  /**
   * 重置上一次操作的最后一个点
   */
  private void PathSetLastPoint(Path path) {
    path.lineTo(200, 200);
    path.setLastPoint(200, 100);
    path.lineTo(200, 0);
  }

  /**
   * moveTo只改变下次操作的起点
   */
  private void PathMoveTo(Path path) {
    path.lineTo(200, 200);
    path.moveTo(200, 100);
    path.lineTo(200, 0);
  }

  /**
   * 从某点到坐标点的连线，没有进行操作则默认点为坐标原点
   */
  private void PathLineTo(Path path) {
    path.lineTo(200, 200);
    path.lineTo(200, 0);    //原点变为(200,200)
  }
}
