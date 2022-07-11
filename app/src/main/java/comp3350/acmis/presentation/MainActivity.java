package comp3350.acmis.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.acmis.R;
import comp3350.acmis.application.Main;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

//    private Fragment fragmentBook,fragmentManage;

    private boolean doubleBackToExitPressedOnce = false;

    private NavigationBarView mBottomNavigation;
    private ViewPager viewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (!Main.getDBPathName().equals("UF")) {
            System.out.println("HERE");
            copyDatabaseToDevice();
        }
        Main.startUp();

        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mBottomNavigation.setOnItemSelectedListener(this);

        viewPager = findViewById(R.id.view_pager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBottomNavigation.getMenu().findItem(R.id.tab_1).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigation.getMenu().findItem(R.id.tab_2).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.tab_1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tab_2:
                viewPager.setCurrentItem(1);
                break;
        }
        return true;
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_activity);
//        Main.startUp();
//        fragmentBook = replaceFragment(savedInstanceState,"book");
//
//        NavigationBarView bottomNavView =  findViewById(R.id.bottom_navigation);
//        @SuppressLint("NonConstantResourceId") NavigationBarView.OnItemSelectedListener itemSelectedListener = item -> {
//            Fragment selectedFragment = null;
//            switch (item.getItemId()) {
//                case R.id.tab_1:
//                    fragmentBook = replaceFragment(savedInstanceState,"book");
//                    selectedFragment = fragmentBook;
//                    break;
//                case R.id.tab_2:
//                    fragmentManage = replaceFragment(savedInstanceState,"manage");
//                    selectedFragment = fragmentManage;
//                    break;
//            }
//            assert selectedFragment != null;
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, selectedFragment)
//                    .commit();
//            return true;
//        };
//
//        itemSelectedListener.onNavigationItemSelected(bottomNavView.getMenu().findItem(R.id.tab_1));
//        bottomNavView.setOnItemSelectedListener(itemSelectedListener);
//    }
//
//
//    private Fragment setFragment(String tag, Fragment newFragment) {
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment savedFragment = fm.findFragmentByTag(tag);
//        fm.beginTransaction().replace(R.id.fragment_container, savedFragment != null ? savedFragment : newFragment, tag).commit();
//        return savedFragment;
//    }
//
//    private Fragment replaceFragment(Bundle savedInstanceState,String fragmentName){
//        Fragment currentFragment;
//        if (savedInstanceState != null) {
//            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, fragmentName);
//        }
//        else{
//            if(Objects.equals(fragmentName, "book"))
//                currentFragment = new FragmentBook();
//            else
//                currentFragment = new FragmentManage();
//        }
//        return currentFragment;
//    }
//
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        getSupportFragmentManager().putFragment(outState, "book", fragmentBook);
//        if(fragmentManage.isAdded())
//        getSupportFragmentManager().putFragment(outState, "manage", fragmentManage);
//    }
    //exit the after pressing the back button at this activity
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }

    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

}
