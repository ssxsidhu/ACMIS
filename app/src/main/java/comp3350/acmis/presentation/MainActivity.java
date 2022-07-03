package comp3350.acmis.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import comp3350.acmis.R;
import comp3350.acmis.application.Main;

public class MainActivity extends AppCompatActivity {

    private Fragment fragmentBook,fragmentManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Main.startUp();

        fragmentBook = replaceFragment(savedInstanceState,"book");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentBook)
                .commit();
        NavigationBarView bottomNavView =  findViewById(R.id.bottom_navigation);
        bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.tab_1:
                        fragmentBook = replaceFragment(savedInstanceState,"book");
                        selectedFragment = fragmentBook;
                        break;
                    case R.id.tab_2:
                        fragmentManage = replaceFragment(savedInstanceState,"manage");
                        selectedFragment = fragmentManage;
                        break;
                }
                assert selectedFragment != null;
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            }
        });
    }


    private Fragment setFragment(String tag, Fragment newFragment) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment savedFragment = fm.findFragmentByTag(tag);
        fm.beginTransaction().replace(R.id.fragment_container, savedFragment != null ? savedFragment : newFragment, tag).commit();
        return savedFragment;
    }

    private Fragment replaceFragment(Bundle savedInstanceState,String fragmentName){
        Fragment currentFragment;
        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, fragmentName);
        }
        else{
            if(Objects.equals(fragmentName, "book"))
                currentFragment = new FragmentBook();
            else
                currentFragment = new FragmentManage();
        }
        return currentFragment;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, "book", fragmentBook);
        getSupportFragmentManager().putFragment(outState, "manage", fragmentManage);
    }
    //exit the after pressing the back button at this activity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
