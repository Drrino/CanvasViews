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
 * Created by Coder on 16/5/13.
 */
public class ImageViewActivity extends BaseActivity {
  @Bind(R.id.picture) Button picture;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    picture.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(ImageViewActivity.this, PathActivity.class));
      }
    });
  }

  @Override protected int getLayout() {
    return R.layout.layout_imageview;
  }
}
