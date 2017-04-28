package tabfragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import analytics.AnalyticsApplication;
import Common.Config;
import pkapoor.wed.R;

/**
 * Created by pkapo8 on 11/23/2016.
 */

public class TheCouple extends Fragment {

    private static final String TAG = "TheCouple";
    CountDownTimer mCountDownTimer;
    long marMilliSec, currentMilli, diff;
    TextView daysValue, HourValue, MinValue, SecValue,
            daysText, tvBriName, tvGroName, tvInviteText, tvTobeHeldOnHeading,
            tvBless, tvUs, tvTextblesss, tvDate, HourText, MinText, SecText;
    ImageView imageHea, imageWedding;
    SharedPreferences sharedPreferences;
    RelativeLayout rlCouple;
    CardView card1;
    private Tracker mTracker;
    private Animation tickmarkZoomIn, tickmarkzoomOutWithBounce;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.couple, container, false);
       /* Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back_six);

        //Bitmap bb = BlurBuilder.blur(getContext(), bitmap);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        view.setBackground(d);*/
        sharedPreferences = getActivity().getSharedPreferences(Config.MyPREFERENCES, Context.MODE_PRIVATE);
        imageHea = (ImageView) view.findViewById(R.id.imageHea);
        imageWedding = (ImageView) view.findViewById(R.id.imageWedding);

        tvGroName = (TextView) view.findViewById(R.id.GroName);
        tvBriName = (TextView) view.findViewById(R.id.BriName);
        tvInviteText = (TextView) view.findViewById(R.id.inviteText);
        tvTobeHeldOnHeading = (TextView) view.findViewById(R.id.tobeHeld);
        tvBless = (TextView) view.findViewById(R.id.tvBless);
        tvUs = (TextView) view.findViewById(R.id.tvUs);
        tvDate = (TextView) view.findViewById(R.id.Date);
        tvTextblesss = (TextView) view.findViewById(R.id.tcTextblesss);
        daysValue = (TextView) view.findViewById(R.id.daysValue);
        HourValue = (TextView) view.findViewById(R.id.HourValue);
        MinValue = (TextView) view.findViewById(R.id.MinValue);
        SecValue = (TextView) view.findViewById(R.id.SecValue);
        daysText = (TextView) view.findViewById(R.id.daysText);
        HourText = (TextView) view.findViewById(R.id.HourText);
        MinText = (TextView) view.findViewById(R.id.MinText);
        SecText = (TextView) view.findViewById(R.id.SecText);
        card1 = (CardView) view.findViewById(R.id.card1);
        rlCouple = (RelativeLayout) view.findViewById(R.id.rlCouple);

        changeTextColor();
        setAnimation();

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Bungasai.ttf");

        tvGroName.setTypeface(type);
        tvBriName.setTypeface(type);

        tvInviteText.setTypeface(type);
        tvTobeHeldOnHeading.setTypeface(type);
        tvBless.setTypeface(type);
        tvUs.setTypeface(type);
        tvTextblesss.setTypeface(type);
        tvDate.setTypeface(type);

        fetchCoupleScreenValues();
        AnalyticsApplication application = (AnalyticsApplication) getActivity().getApplication();
        mTracker = application.getDefaultTracker();

        setTimer();
        return view;
    }

    private void changeTextColor() {
        String colorSelected = sharedPreferences.getString(Config.colorSelected, "colorRed");

        if (colorSelected.equalsIgnoreCase("colorRed")) {
            changeColor(R.color.colorRed, colorSelected);
        } else if (colorSelected.equalsIgnoreCase("PinkKittyToolBar")) {
            changeColor(R.color.PinkKittyToolBar, colorSelected);
        } else if (colorSelected.equalsIgnoreCase("GreenToolBar")) {
            changeColor(R.color.GreenToolBar, colorSelected);
        } else if (colorSelected.equalsIgnoreCase("BlackToolBar")) {
            changeColor(R.color.BlackToolBar, colorSelected);
        } else if (colorSelected.equalsIgnoreCase("BlueToolBar")) {
            changeColor(R.color.BlueToolBar, colorSelected);
        }
        String backImage = sharedPreferences.getString(Config.back_image, "0");
       // card1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.aa));

        if (backImage.equalsIgnoreCase("1")) {
            rlCouple.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.back_seven));
        } else if (backImage.equalsIgnoreCase("2")) {
           /* Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.two);
            Bitmap bb = BlurBuilder.blur(getContext(), bitmap);
            Drawable d = new BitmapDrawable(getResources(), bb);*/
            rlCouple.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.two));
        }

        //rlCouple.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.three));
    }

    private void changeColor(int colorSelected, String color) {

        tvGroName.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        tvBriName.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        tvBless.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        tvDate.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        daysValue.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        HourValue.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        MinValue.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        SecValue.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        daysText.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        HourText.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        MinText.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        SecText.setTextColor(ContextCompat.getColor(getContext(), colorSelected));
        if (color.equalsIgnoreCase("BlackToolBar")) {
            imageWedding.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorRed));
        } else {
            imageWedding.setColorFilter(ContextCompat.getColor(getContext(), colorSelected));
        }
    }

    private void fetchCoupleScreenValues() {
        String grName = sharedPreferences.getString(Config.name_groom, "");
        String brName = sharedPreferences.getString(Config.name_bride, "");
        if (grName.length() > 7 || brName.length() > 7) {
            tvGroName.setTextSize(19);
            tvBriName.setTextSize(19);
        }
        tvGroName.setText(grName);
        tvBriName.setText(brName);

        tvTextblesss.setText(sharedPreferences.getString(Config.blessUs_para, "Bless Us"));
        String dateMarriage = sharedPreferences.getString(Config.marriage_date, "DATE");
        if (!dateMarriage.equalsIgnoreCase("DATE")) {
            try {
                setDateForMarriage(sharedPreferences.getString(Config.marriage_date, "NAME"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void setDateForMarriage(String date) throws ParseException {
        Date d = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
        int dayValue = cal.get(Calendar.DAY_OF_MONTH);
        int yearValue = cal.get(Calendar.YEAR);
        tvDate.setText(monthName + " " + dayValue + ", " + yearValue);
    }

    private void setAnimation() {
        tickmarkZoomIn = AnimationUtils.loadAnimation(getContext(),
                R.anim.zoom_in_without_bounce);
        tickmarkzoomOutWithBounce = AnimationUtils.loadAnimation(getContext(),
                R.anim.zoom_out_with_bounce);

        imageHea.setAnimation(tickmarkZoomIn);

        tickmarkZoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //On end of zoom,adding other animation.
                imageHea.setAnimation(tickmarkzoomOutWithBounce);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setTimer() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, hh:mm a");
        String marTime = sharedPreferences.getString(Config.date_marriage, "22.01.2017, 11:00 AM");

        Date marDate;
        try {
            marDate = formatter.parse(marTime);
            marMilliSec = marDate.getTime();

            currentMilli = System.currentTimeMillis();

            diff = marMilliSec - currentMilli;

            mCountDownTimer = new CountDownTimer(diff, 1000) {
                @Override
                public void onFinish() {
                    daysValue.setText("0");
                    HourValue.setText("0");
                    MinValue.setText("0");
                    SecValue.setText("0");
                }

                @Override
                public void onTick(long millisUntilFinished) {

                    long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.DAYS.toMillis(days);
                    long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);


                    daysValue.setText("" + days);
                    HourValue.setText("" + hours);
                    MinValue.setText("" + minutes);
                    SecValue.setText("" + seconds);

                    if (days > 1)
                        daysText.setText("Days");
                    else {
                        daysText.setText("Day");
                    }
                }
            }.start();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mTracker.setScreenName(TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
