package drrino.canvasviews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import butterknife.Bind;
import drrino.canvasviews.R;
import drrino.canvasviews.ui.base.BaseActivity;

/**
 * Created by Coder on 16/5/14.
 */
public class PathActivity extends BaseActivity {
  @Bind(R.id.path) Button path;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    path.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(PathActivity.this, BezierActivity.class));
      }
    });
  }

  @Override protected int getLayout() {
    return R.layout.layout_path;
  }
}
