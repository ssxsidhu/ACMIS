package comp3350.acmis.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import comp3350.acmis.R;
import comp3350.acmis.application.Main;

public class FrontPageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.front_page_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }



}
