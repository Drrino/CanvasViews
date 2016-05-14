package drrino.canvasviews.ui.activity;

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
public class BezierActivity extends BaseActivity {
  @Bind(R.id.bezier) Button bezier;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    bezier.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

      }
    });
  }

  @Override protected int getLayout() {
    return R.layout.layout_bezier;
  }
}
