package themightykam.productivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 207493 on 7/26/16.
 */

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter{

    public Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext =context;
    }
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page
        // Return a PlaceholderFragment (defined as a static inner class below)
        return MainActivity.PlaceholderFragment.newInstance(position + 1);
    }
    @Override
    public int getCount() {
        // Show 3 total pages
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Tasks";
            case 1:
                return "Leaderboard";
            case 2:
                return "Friends";
        }
        return null;
    }
}