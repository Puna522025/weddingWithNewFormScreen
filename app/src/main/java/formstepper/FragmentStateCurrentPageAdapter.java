package formstepper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

public class FragmentStateCurrentPageAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {
    int currentPage = 0;
    List<Class> mStepperFragment;

    private Hashtable<Integer, WeakReference<Fragment>> fragmentReferences = new Hashtable<>();


    FragmentStateCurrentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new NameMarriageFormDetails();
                break;
            case 1:
                fragment = new EventTwoFormDetails();
                break;
            case 2:
                fragment = new RSVPformDetails();
                break;
            case 3:
                fragment = new ThemesForrmDetails();
                break;
        }
        fragmentReferences.put(position, new WeakReference<Fragment>(fragment));

        return fragment;
    }

    public Fragment getFragment(int fragmentId) {
        WeakReference<Fragment> ref = fragmentReferences.get(fragmentId);
        return ref == null ? null : ref.get();
    }

    @Override
    public int getCount() {
        return mStepperFragment.size();
    }

    public void setFragments(List<Class> fragments) {
        mStepperFragment = fragments;
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

