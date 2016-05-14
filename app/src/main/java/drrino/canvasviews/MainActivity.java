package drrino.canvasviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.Bind;
import drrino.canvasviews.ui.activity.CanvasActivity;
import drrino.canvasviews.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

  @Bind(R.id.about_canvas) Button canvas;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    canvas.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, CanvasActivity.class));
        finish();
      }
    });
  }

  @Override protected int getLayout() {
    return R.layout.activity_main;
  }
}
