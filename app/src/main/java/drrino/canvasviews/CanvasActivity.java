package drrino.canvasviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Coder on 16/5/12.
 */
public class CanvasActivity extends AppCompatActivity {

  @Bind(R.id.image_text) Button imageText;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_canvas);
    ButterKnife.bind(this);

    imageText.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(CanvasActivity.this,ImageViewActivity.class));
      }
    });
  }
}
