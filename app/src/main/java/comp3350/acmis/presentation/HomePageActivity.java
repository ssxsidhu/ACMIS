package comp3350.acmis.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import comp3350.acmis.R;
import comp3350.acmis.application.Main;

public class HomePageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.home_page_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(HomePageActivity.this, BottomTabActivity.class));
                
            }
        }, 3000);
    }



}
