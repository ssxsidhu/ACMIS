package comp3350.acmis.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import comp3350.acmis.R;

public class BookFlightActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.view_pager2);

        tabLayout.setupWithViewPager(viewPager);

        BookFlightAdapter bookFlightAdapter = new BookFlightAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        bookFlightAdapter.addFragment(new OneWayFragment(), "ONE WAY");
        bookFlightAdapter.addFragment(new RoundTripFragment(), "ROUND TRIP");

        viewPager.setAdapter(bookFlightAdapter);









    }
}