package comp3350.acmis.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.jakewharton.threetenabp.AndroidThreeTen;

import comp3350.acmis.R;
import comp3350.acmis.application.Main;

public class FrontPageActivity extends Activity {

    //it shows the front-page and finishes the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);

        setContentView(R.layout.activity_front_page);
        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent i = new Intent(FrontPageActivity.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, 2000);


    }
}
