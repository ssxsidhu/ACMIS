package comp3350.acmis.presentation;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import comp3350.acmis.R;
import comp3350.acmis.ui.main.SectionsPagerAdapter;
import comp3350.acmis.databinding.ActivityBottomTabBinding;

public class bottom_tab_activity extends AppCompatActivity {

    private ActivityBottomTabBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBottomTabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
//        ArrayList<String> items = new ArrayList<>();
////                ("Option 1", "Option 2", "Option 3", "Option 4")
//        items.add("Option 1");
//        items.add("Option 2");
//        items.add("Option 3");
//         ArrayAdapter<String> ddArrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item,items);

        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }
}