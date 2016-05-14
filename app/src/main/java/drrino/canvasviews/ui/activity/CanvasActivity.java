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
 * Created by Coder on 16/5/12.
 */
public class CanvasActivity extends BaseActivity {

  @Bind(R.id.image_text) Button imageText;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    imageText.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(CanvasActivity.this,ImageViewActivity.class));
      }
    });
  }

  @Override protected int getLayout() {
    return R.layout.layout_canvas;
  }
}
