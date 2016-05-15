package drrino.canvasviews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import butterknife.Bind;
import drrino.canvasviews.R;
import drrino.canvasviews.ui.base.BaseActivity;
import drrino.canvasviews.ui.weight.BezierViews;

/**
 * Created by Coder on 16/5/14.
 */
public class BezierActivity extends BaseActivity {
  @Bind(R.id.heart) Button heart;
  @Bind(R.id.bezier_view) BezierViews bezierView;
  @Bind(R.id.radio_group) RadioGroup radioGroup;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
          case R.id.radio_1:
            bezierView.setMode(true);
            break;
          case R.id.radio_2:
            bezierView.setMode(false);
            break;
        }
      }
    });

    heart.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(BezierActivity.this, HeartActivity.class));
        finish();
      }
    });
  }

  @Override protected int getLayout() {
    return R.layout.layout_bezier;
  }
}
