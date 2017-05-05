package formstepper;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import Common.Config;
import formstepper.forms.EventTwoFormDetails;
import formstepper.forms.NameMarriageFormDetails;
import formstepper.forms.RSVPformDetails;
import formstepper.forms.ThemesForrmDetails;
import pkapoor.wed.R;

/**
 * Created by pkapo8 on 8/10/2016.
 * This class handles the fragments sent by the client, adds them to the adapter and generate a viewPager.
 */

public class StepperClass extends AppCompatActivity {

    List<Fragment> fragmentList;
    static ViewPager mViewPager;
    LinearLayout linearLayout;
    RelativeLayout viewPagerIndicator;
    private ImageView[] dots;
    private Toolbar toolbar;
    private static FragmentStateCurrentPageAdapter fragmentAdapter;
    public int totalFragments = 0;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        linearLayout = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        viewPagerIndicator = (RelativeLayout) findViewById(R.id.viewPagerIndicator);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //mViewPager.setOffscreenPageLimit(10);
        tabLayout = (TabLayout) findViewById(R.id.tabsForm);
        tabLayout.setupWithViewPager(mViewPager);

        toolbar.setTitle("Create your Invite..");
        setSupportActionBar(toolbar);

        fragmentList = initializeFragments();

        fragmentAdapter = new FragmentStateCurrentPageAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(fragmentAdapter);
        totalFragments = fragmentList.size();

        drawPageSelectionIndicators(0);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Config.hideKeyboard(getCurrentFocus(),getApplicationContext());
                drawPageSelectionIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    /**
     * This method handles the scroll of the dots according to the fragments changed.
     *
     * @param mPosition
     */
    private void drawPageSelectionIndicators(int mPosition) {
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        dots = new ImageView[totalFragments];
        for (int i = 0; i < totalFragments; i++) {
            dots[i] = new ImageView(getBaseContext());
            if (i == mPosition) {
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
            } else {
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(15, 0, 15, 0);
            linearLayout.addView(dots[i], params);
        }
    }

    /**
     * Method which the client overrides and send the fragments.
     */
    public List<Fragment> initializeFragments() {
        List<Fragment> stepperFragmentList = new ArrayList<>();

        stepperFragmentList.add(new NameMarriageFormDetails());
        stepperFragmentList.add(new EventTwoFormDetails());
        stepperFragmentList.add(new RSVPformDetails());
        stepperFragmentList.add(new ThemesForrmDetails());

        return stepperFragmentList;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("Your Progress will not be saved. Do you want to continue?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPreferences.Editor editor = getSharedPreferences(Config.MyTEMPORARY_PREFERENCES, MODE_PRIVATE).edit();
                        editor.clear().apply();
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static Fragment getCurrentFragment(int position) {
        Fragment fragment = null;
        if (mViewPager != null) {
            if (fragmentAdapter != null) {
                fragment = fragmentAdapter.getFragment(position);
            }
        }
        return fragment;
    }

}