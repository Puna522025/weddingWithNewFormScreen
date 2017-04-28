package formstepper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.List;

import formstepper.forms.EventTwoFormDetails;
import formstepper.forms.NameMarriageFormDetails;
import formstepper.forms.RSVPformDetails;

/**
 * Created by pkapo8 on 8/10/2016.
 * This file includes all the basic functions used in the stepper like onNext, onback.
 */
public class CommonFunctions {


    private ViewPager mViewPager;
    private FragmentStateCurrentPageAdapter fragmentAdapter;
    public int CurrentFragment = 0;
    public int TotalFragments = 0;
    public Context appContext;

    public CommonFunctions(ViewPager viewPager, List<Class> mStepperFragment, FragmentManager fm, Context context) {

        mViewPager = viewPager;
        appContext = context;
        fragmentAdapter = new FragmentStateCurrentPageAdapter(fm);
        fragmentAdapter.setFragments(mStepperFragment);
        mViewPager.setAdapter(fragmentAdapter);
        TotalFragments = mStepperFragment.size();
    }

    /**
     * On next clicked.
     */
    public void onNextButtonClicked() {
        CurrentFragment = mViewPager.getCurrentItem();
        CurrentFragment = CurrentFragment + 1;
        mViewPager.setCurrentItem(CurrentFragment);
    }

    /**
     * @return  - Whether the fragment is the last one or not.
     */
    public boolean notLastFragment() {
        if (CurrentFragment != (TotalFragments - 1)){
            return true;
        }
        return false;
    }

    /**
     * On back or previous clicked.
     */
    public void onBackButtonClicked(){
        CurrentFragment =  CurrentFragment - 1;
        mViewPager.setCurrentItem(CurrentFragment);
    }

    public boolean isEverythingFilled() {
        boolean isFilled = false;

        Fragment fragment = getCurrentFragment();
        if (fragment instanceof NameMarriageFormDetails)
        {
            NameMarriageFormDetails remainderFragment = (NameMarriageFormDetails) fragment;
            isFilled = remainderFragment.checkField();
        }

        if (fragment instanceof EventTwoFormDetails)
        {
            EventTwoFormDetails remainderFragment = (EventTwoFormDetails) fragment;
            isFilled = remainderFragment.checkField();
        }
        if (fragment instanceof RSVPformDetails)
        {
            RSVPformDetails remainderFragment = (RSVPformDetails) fragment;
            isFilled = remainderFragment.checkField();
        }
        return isFilled;
    }

    public Fragment getCurrentFragment()
    {
        Fragment fragment = null;
        if (mViewPager != null)
        {
            int currentFragmentIndex = mViewPager.getCurrentItem();

            if (fragmentAdapter != null)
            {
                fragment = fragmentAdapter.getFragment(currentFragmentIndex);
            }
        }
        return fragment;
    }

}
