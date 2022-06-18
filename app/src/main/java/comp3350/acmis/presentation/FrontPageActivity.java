package comp3350.acmis.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import comp3350.acmis.R;
import comp3350.acmis.application.Main;

public class FrontPageActivity extends Activity {

    //it shows the front-page and finishes the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(FrontPageActivity.this,MainActivity.class));
                finish();
            }
        }, 1500);

    }
}
