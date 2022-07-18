package comp3350.acmis.presentation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import comp3350.acmis.presentation.booktab.FragmentBook;
import comp3350.acmis.presentation.managetab.FragmentManage;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentBook();
            case 1:
                return new FragmentManage();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}