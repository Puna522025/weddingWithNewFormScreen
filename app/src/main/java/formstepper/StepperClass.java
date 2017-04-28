package formstepper;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.Serializable;
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

public class StepperClass extends AppCompatActivity implements View.OnClickListener {

    List<Class> fragmentList;
    ViewPager mViewPager;
    LinearLayout linearLayout;
    SwitchCompat switchControl;
    RelativeLayout viewPagerIndicator;
    private Button mPrevious;
    private CommonFunctions commonFunctions;
    private int CURRENTPAGE = 0;
    //  private ScrollView mScroll;
    private ProgressBar mStepperProgress;
    private ImageView[] dots;
    private Button nextButton;
   // private TextView continueToInvite;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        mPrevious = (Button) findViewById(R.id.back);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mStepperProgress = (ProgressBar) findViewById(R.id.stepperprogressbar);
        switchControl = (SwitchCompat) findViewById(R.id.switchControl);

        nextButton = (Button) findViewById(R.id.next);
        //continueToInvite = (TextView) findViewById(R.id.continueToInvite);

        linearLayout = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        viewPagerIndicator = (RelativeLayout) findViewById(R.id.viewPagerIndicator);
        mViewPager.setOffscreenPageLimit(0);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        nextButton.setOnClickListener(this);
        mPrevious.setOnClickListener(this);
      //  continueToInvite.setOnClickListener(this);

        if (savedInstanceState != null && savedInstanceState.getSerializable("FRAGMENTLIST") != null) {
            try {
                fragmentList = (List<Class>) savedInstanceState.getSerializable("FRAGMENTLIST");
                CURRENTPAGE = savedInstanceState.getInt("CURRENTFRAGMENT");
            } catch (Exception e) {
                Log.d("Error:", "error while fetching fragments");
            }
        } else {
            fragmentList = init();
        }

        commonFunctions = new CommonFunctions(mViewPager, fragmentList, getSupportFragmentManager(), this);
        commonFunctions.CurrentFragment = CURRENTPAGE;
        CURRENTPAGE = 0;

        updateUI();

        mStepperProgress.setMax(commonFunctions.TotalFragments);
        drawPageSelectionIndicators(0);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                drawPageSelectionIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        switchControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    viewPagerIndicator.setVisibility(View.GONE);
                    mStepperProgress.setVisibility(View.VISIBLE);
                } else {
                    viewPagerIndicator.setVisibility(View.VISIBLE);
                    mStepperProgress.setVisibility(View.GONE);
                }
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
        dots = new ImageView[commonFunctions.TotalFragments];
        for (int i = 0; i < commonFunctions.TotalFragments; i++) {
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

            params.setMargins(4, 0, 4, 0);
            linearLayout.addView(dots[i], params);
        }
    }

    /**
     * Storing the current state of the fragment as well as the list of all fragments.
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("FRAGMENTLIST", (Serializable) fragmentList);
        outState.putInt("CURRENTFRAGMENT", commonFunctions.CurrentFragment);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.next:
                Config.hideKeyboard(getCurrentFocus(), getApplicationContext());
                if (commonFunctions.isEverythingFilled()) {
                    commonFunctions.onNextButtonClicked();
                    if (commonFunctions.notLastFragment()) {
                       // continueToInvite.setVisibility(View.GONE);
                        nextButton.setVisibility(View.VISIBLE);
                        updateUI();
                    } else {
                        //continueToInvite.setVisibility(View.VISIBLE);
                        nextButton.setVisibility(View.INVISIBLE);
                    }
                } else {
                    break;
                }
                break;
            case R.id.back:
                Config.hideKeyboard(getCurrentFocus(), getApplicationContext());
                if (nextButton.getVisibility() == View.INVISIBLE) {
                   // continueToInvite.setVisibility(View.GONE);
                    nextButton.setVisibility(View.VISIBLE);
                }
                commonFunctions.onBackButtonClicked();
                updateUI();
                break;
           /* case R.id.continueToInvite:
                Fragment fragment = commonFunctions.getCurrentFragment();
                if (fragment instanceof ThemesForrmDetails) {
                    ThemesForrmDetails themesForrmDetails = (ThemesForrmDetails) fragment;
                    themesForrmDetails.continueToInviteScreen();
                }

                break;*/
        }
    }

    /**
     * Updates the UI - Sets the text and updates the progress.
     */
    public void updateUI() {

        if (commonFunctions.CurrentFragment == 0)
            mPrevious.setVisibility(View.INVISIBLE);
        else
            mPrevious.setVisibility(View.VISIBLE);
        mStepperProgress.setProgress(commonFunctions.CurrentFragment + 1);
    }

    /**
     * Method which the client overrides and send the fragments.
     */
    public List<Class> init() {
        List<Class> stepperFragmentList = new ArrayList<>();

        stepperFragmentList.add(NameMarriageFormDetails.class);
        stepperFragmentList.add(EventTwoFormDetails.class);
        stepperFragmentList.add(RSVPformDetails.class);
        stepperFragmentList.add(ThemesForrmDetails.class);

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

}