package formstepper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.List;

import formstepper.forms.EventTwoFormDetails;
import formstepper.forms.NameMarriageFormDetails;
import formstepper.forms.RSVPformDetails;
import formstepper.forms.ThemesForrmDetails;

/**
 * Created by pkapo8 on 8/10/2016.
 * This is the adapter for the viewPager.
 */

public class FragmentStateCurrentPageAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    int currentPage = 0;
    List<Fragment> mStepperFragment;

    FragmentStateCurrentPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mStepperFragment = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return mStepperFragment.get(position);
    }

    public Fragment getFragment(int position) {
        return mStepperFragment.get(position);
    }

    @Override
    public int getCount() {
        return mStepperFragment.size();
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int newPageIndex) {
        currentPage = newPageIndex;
    }

}

