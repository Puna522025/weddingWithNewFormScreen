package pkapoor.wed;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Common.Config;

/**
 * Created by pkapo8 on 11/30/2016.
 */

public class FormDetails extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    ImageView imgDateCalender, imgDateCalenderEventTwo,tickRed,tickBlue,tickBlack,tickGreen,tickcoupleback,tickPink,tickPinkPetals,tickNoImage;
    int mYear, mMonth, mDay, mHour, mMinute;
    TextView tvmarDateTimeText, tvEventTwoDateTimeText, tvEventNameText, tvcontinueToInvite, DateText, DateTextEventTwo;

    EditText etEventNameText, etNameBri, etNameGro, etLocationValue, etPinCodeValue, etInviteMesValue,
            etLocationValueEventTwo, etPinCodeValueEventTwo, etRSVPNameOne, etRSVPMobileOne, etRSVPNameTwo, etRSVPMobileTwo, etInviteMesRSVPValue;

    RelativeLayout rlEventTwo, rlEventRSVP, rlBackground;
    SwitchCompat switchEventTwo, switchEventRSVP;
    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    CardView rlPink,rlRed,rlGreen,rlBlack,rlBlue,rlImagePinkPetals,rlImageNoImage,rlCoupleMainBack;

    String colorSelected = "colorRed";

    String backgroundSelected = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_details);

        initializingVariables();

        setSupportActionBar(toolbar);

        imgDateCalender.setOnClickListener(this);
        tvcontinueToInvite.setOnClickListener(this);
        imgDateCalenderEventTwo.setOnClickListener(this);
        switchEventTwo.setOnCheckedChangeListener(this);
        switchEventRSVP.setOnCheckedChangeListener(this);

        rlEventTwo.setVisibility(View.VISIBLE);
        tvEventNameText.setVisibility(View.VISIBLE);
        etEventNameText.setVisibility(View.VISIBLE);
        rlEventRSVP.setVisibility(View.VISIBLE);
        tickRed.setVisibility(View.VISIBLE);
        tickNoImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("Your Progress will not be saved. Do you want to continue?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_invite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save_continue) {
            checkNull();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void checkNull() {
        if (Config.isEditTextEmpty(etNameBri) || Config.isEditTextEmpty(etNameGro) ||
                Config.isEditTextEmpty(etLocationValue) || Config.isEditTextEmpty(etPinCodeValue) ||
                Config.isEditTextEmpty(etInviteMesValue) || Config.checkDateText(tvmarDateTimeText) ||
                Config.checkDateText(tvmarDateTimeText) || checkEventTwoDetails() || checkRSVPDetails()) {

            Config.changeDateTextcolor(tvmarDateTimeText, DateText,
                    ContextCompat.getColor(this, R.color.colorRed), ContextCompat.getColor(this, R.color.textColor));
            Config.checkEditTextNullandSetError(etNameBri);
            Config.checkEditTextNullandSetError(etNameGro);
            Config.checkEditTextNullandSetError(etLocationValue);
            Config.checkEditTextNullandSetError(etPinCodeValue);
            Config.checkEditTextNullandSetError(etInviteMesValue);
            if (switchEventTwo.isChecked()) {
                Config.changeDateTextcolor(tvEventTwoDateTimeText, DateTextEventTwo,
                        ContextCompat.getColor(this, R.color.colorRed), ContextCompat.getColor(this, R.color.textColor));

                Config.checkEditTextNullandSetError(etEventNameText);
                Config.checkEditTextNullandSetError(etLocationValueEventTwo);
                Config.checkEditTextNullandSetError(etPinCodeValueEventTwo);
            }
            if (switchEventRSVP.isChecked()) {
                Config.checkEditTextNullandSetError(etRSVPNameOne);
                Config.checkEditTextNullandSetError(etRSVPMobileOne);
                Config.checkEditTextNullandSetError(etInviteMesRSVPValue);
            }
            new AlertDialog.Builder(this)
                    .setTitle("")
                    .setMessage("Please fill the details")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            continueToInviteScreen();
        }
    }

    private boolean checkEventTwoDetails() {
        if (switchEventTwo.isChecked()) {
            if (Config.isEditTextEmpty(etEventNameText) || Config.isEditTextEmpty(etLocationValueEventTwo) ||
                    Config.isEditTextEmpty(etPinCodeValueEventTwo) || Config.checkDateText(tvEventTwoDateTimeText)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean checkRSVPDetails() {
        if (switchEventRSVP.isChecked()) {
            if (Config.isEditTextEmpty(etRSVPNameOne) || Config.isEditTextEmpty(etRSVPMobileOne)
                    || Config.isEditTextEmpty(etInviteMesRSVPValue)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void initializingVariables() {
        imgDateCalender = (ImageView) findViewById(R.id.imgDateCalender);
        imgDateCalenderEventTwo = (ImageView) findViewById(R.id.imgDateCalenderEventTwo);

        tickRed = (ImageView) findViewById(R.id.tickRed);
        tickBlue = (ImageView) findViewById(R.id.tickBlue);
        tickBlack = (ImageView) findViewById(R.id.tickBlack);
        tickGreen = (ImageView) findViewById(R.id.tickGreen);
        tickPink = (ImageView) findViewById(R.id.tickPink);
        tickPinkPetals = (ImageView) findViewById(R.id.tickPinkPetals);
        tickNoImage = (ImageView) findViewById(R.id.tickNoImage);
        tickcoupleback = (ImageView) findViewById(R.id.tickcoupleback);

        tvmarDateTimeText = (TextView) findViewById(R.id.marDateTimeText);
        tvEventTwoDateTimeText = (TextView) findViewById(R.id.EventTwoDateTimeText);
        tvEventNameText = (TextView) findViewById(R.id.EventNameText);
        tvcontinueToInvite = (TextView) findViewById(R.id.continueToInvite);
        DateText = (TextView) findViewById(R.id.DateText);
        DateTextEventTwo = (TextView) findViewById(R.id.DateTextEventTwo);

        rlEventTwo = (RelativeLayout) findViewById(R.id.rlEventTwo);
        rlEventRSVP = (RelativeLayout) findViewById(R.id.rlEventRSVP);
        rlBackground = (RelativeLayout) findViewById(R.id.rlBackground);


        etNameBri = (EditText) findViewById(R.id.etNameBri);
        etNameGro = (EditText) findViewById(R.id.etNameGro);
        etLocationValue = (EditText) findViewById(R.id.LocationValue);
        etPinCodeValue = (EditText) findViewById(R.id.PinCodeValue);
        etInviteMesValue = (EditText) findViewById(R.id.InviteMesValue);

        etEventNameText = (EditText) findViewById(R.id.etEventNameText);
        etLocationValueEventTwo = (EditText) findViewById(R.id.LocationValueEventTwo);
        etPinCodeValueEventTwo = (EditText) findViewById(R.id.PinCodeValueEventTwo);

        etRSVPNameOne = (EditText) findViewById(R.id.etRSVPNameOne);
        etRSVPMobileOne = (EditText) findViewById(R.id.etRSVPMobileOne);
        etRSVPNameTwo = (EditText) findViewById(R.id.etRSVPNameTwo);
        etRSVPMobileTwo = (EditText) findViewById(R.id.etRSVPMobileTwo);
        etInviteMesRSVPValue = (EditText) findViewById(R.id.InviteMesRSVPValue);

        switchEventTwo = (SwitchCompat) findViewById(R.id.switchEventTwo);
        switchEventRSVP = (SwitchCompat) findViewById(R.id.switchEventRSVP);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        rlPink = (CardView)findViewById(R.id.rlPink);
        rlRed = (CardView)findViewById(R.id.rlRed);
        rlGreen = (CardView)findViewById(R.id.rlGreen);
        rlBlack = (CardView)findViewById(R.id.rlBlack);
        rlBlue = (CardView)findViewById(R.id.rlBlue);
        rlImagePinkPetals = (CardView)findViewById(R.id.rlImagePinkPetals);
        rlImageNoImage = (CardView)findViewById(R.id.rlImageNoImage);
        rlCoupleMainBack = (CardView)findViewById(R.id.rlImageCoupleBack);

        rlPink.setOnClickListener(this);
        rlRed.setOnClickListener(this);
        rlGreen.setOnClickListener(this);
        rlBlack.setOnClickListener(this);
        rlBlue.setOnClickListener(this);
        rlImagePinkPetals.setOnClickListener(this);
        rlImageNoImage.setOnClickListener(this);
        rlCoupleMainBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imgDateCalender:
                Config.hideKeyboard(getCurrentFocus(), this);
                setDate("wedding");
                break;
            case R.id.imgDateCalenderEventTwo:
                Config.hideKeyboard(getCurrentFocus(), this);
                setDate("eventTwo");
                break;
            case R.id.continueToInvite:
                checkNull();
                break;
            case R.id.rlRed:
                colorSelected = "colorRed";
                tickRed.setVisibility(View.VISIBLE);
                tickGreen.setVisibility(View.GONE);
                tickBlack.setVisibility(View.GONE);
                tickBlue.setVisibility(View.GONE);
                tickPink.setVisibility(View.GONE);

                break;
            case R.id.rlPink:
                colorSelected = "PinkKittyToolBar";
                tickRed.setVisibility(View.GONE);
                tickGreen.setVisibility(View.GONE);
                tickBlack.setVisibility(View.GONE);
                tickBlue.setVisibility(View.GONE);
                tickPink.setVisibility(View.VISIBLE);

                break;
            case R.id.rlGreen:
                colorSelected = "GreenToolBar";
                tickRed.setVisibility(View.GONE);
                tickGreen.setVisibility(View.VISIBLE);
                tickBlack.setVisibility(View.GONE);
                tickBlue.setVisibility(View.GONE);
                tickPink.setVisibility(View.GONE);

                break;
            case R.id.rlBlack:
                colorSelected = "BlackToolBar";
                tickRed.setVisibility(View.GONE);
                tickGreen.setVisibility(View.GONE);
                tickBlack.setVisibility(View.VISIBLE);
                tickBlue.setVisibility(View.GONE);
                tickPink.setVisibility(View.GONE);

                break;
            case R.id.rlBlue:
                colorSelected = "BlueToolBar";
                tickRed.setVisibility(View.GONE);
                tickGreen.setVisibility(View.GONE);
                tickBlack.setVisibility(View.GONE);
                tickBlue.setVisibility(View.VISIBLE);
                tickPink.setVisibility(View.GONE);
                break;
            case R.id.rlImageNoImage:
                backgroundSelected = "0";
                tickNoImage.setVisibility(View.VISIBLE);
                tickPinkPetals.setVisibility(View.GONE);
                tickcoupleback.setVisibility(View.GONE);
                break;
            case R.id.rlImagePinkPetals:
                backgroundSelected = "1";
                tickNoImage.setVisibility(View.GONE);
                tickcoupleback.setVisibility(View.GONE);
                tickPinkPetals.setVisibility(View.VISIBLE);
                break;
            case R.id.rlImageCoupleBack:
                backgroundSelected = "2";
                tickcoupleback.setVisibility(View.VISIBLE);
                tickNoImage.setVisibility(View.GONE);
                tickPinkPetals.setVisibility(View.GONE);
                break;
        }
    }

    private void continueToInviteScreen() {
        //TODO: check if all the fields are filled properly or not..
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating your invite..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        SharedPreferences.Editor editor = getSharedPreferences(Config.MyPREFERENCES, MODE_PRIVATE).edit();
        editor.putString(Config.blessUs_para, etInviteMesValue.getText().toString());
        // editor.putString(Config.unique_wed_code, etInviteMesValue.getText().toString());
        String marDateTime = tvmarDateTimeText.getText().toString();

        if (!TextUtils.isEmpty(marDateTime)) {
            String marDate = marDateTime.substring(0, marDateTime.indexOf(","));
            String marTime = marDateTime.substring(marDateTime.indexOf(",") + 2, marDateTime.length());
            editor.putString(Config.date_marriage, marDateTime);
            editor.putString(Config.marriage_date, marDate);
            editor.putString(Config.marriage_time, marTime);
        }

        editor.putString(Config.marriage_location, etLocationValue.getText().toString() + "," + etPinCodeValue.getText().toString());
        editor.putString(Config.marriage_tobe, "true");
        editor.putString(Config.name_bride, etNameBri.getText().toString());
        editor.putString(Config.name_groom, etNameGro.getText().toString());
        editor.putString(Config.rsvp_name1, etRSVPNameOne.getText().toString());
        editor.putString(Config.rsvp_name2, etRSVPNameTwo.getText().toString());
        editor.putString(Config.rsvp_phone_one, etRSVPMobileOne.getText().toString());
        editor.putString(Config.rsvp_phone_two, etRSVPMobileTwo.getText().toString());
        editor.putString(Config.event_two_name, etEventNameText.getText().toString());
        editor.putString(Config.rsvp_text, etInviteMesRSVPValue.getText().toString());
        editor.putString(Config.colorSelected, colorSelected);
        editor.putString(Config.back_image, backgroundSelected);

        if (switchEventRSVP.isChecked()) {
            editor.putString(Config.rsvp_tobe, "true");
        } else if (!switchEventRSVP.isChecked()) {
            editor.putString(Config.rsvp_tobe, "false");
        }

        String eventTwoDateTime = tvEventTwoDateTimeText.getText().toString();
        if (!TextUtils.isEmpty(eventTwoDateTime)) {
            String eventTwoDate = eventTwoDateTime.substring(0, eventTwoDateTime.indexOf(","));
            String eventTwoTime = eventTwoDateTime.substring(eventTwoDateTime.indexOf(",") + 2, eventTwoDateTime.length());
            editor.putString(Config.event_Two_date, eventTwoDate);
            editor.putString(Config.event_Two_time, eventTwoTime);
        }

        if (switchEventTwo.isChecked()) {
            editor.putString(Config.event_two_tobe, "true");
        } else if (!switchEventTwo.isChecked()) {
            editor.putString(Config.event_two_tobe, "false");
        }

        editor.putString(Config.event_Two_location, etLocationValueEventTwo.getText().toString() + "," + etPinCodeValueEventTwo.getText().toString());
        editor.apply();

        progressDialog.dismiss();

        Intent intentOne = getIntent();
        Bundle extras = intentOne.getExtras();

        Intent intent = new Intent(this, ViewGeneratedInvite.class);
        intent.putExtra(Config.setToolbarMenuIcons, extras.get(Config.setToolbarMenuIcons).toString());
        startActivity(intent);
    }

    private void setDate(final String type) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mYear = view.getYear() - 1900;
                        mMonth = view.getMonth();
                        mDay = view.getDayOfMonth();
                        showTimePicker(type);
                    }
                }, mYear, mMonth, mDay);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dpd.getWindow().getAttributes().windowAnimations = R.style.DialogDate;
        dpd.show();
    }

    private void showTimePicker(final String type) {
        final TimePickerDialog tpd = new TimePickerDialog(this, R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker viewTime, int hourOfDay,
                                          int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        SimpleDateFormat sdf;
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(System.currentTimeMillis());
                        cal.clear();
                        sdf = new SimpleDateFormat("yyyy");
                        int formatedyear = Integer.parseInt(sdf.format(new Date(mYear, mMonth, mDay)));
                        sdf = new SimpleDateFormat("MM");
                        int formatedmonth = Integer.parseInt(sdf.format(new Date(mYear, mMonth, mDay)));
                        int month = formatedmonth - 1;
                        cal.set(formatedyear, month, mDay, mHour, mMinute, 0);

                        Calendar calSystem = Calendar.getInstance();
                        calSystem.setTimeInMillis(System.currentTimeMillis());

                        if (calSystem.getTimeInMillis() > cal.getTimeInMillis()) {
                            checkTime();
                        } else {
                            mHour = hourOfDay;
                            mMinute = minute;
                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
                            String formatedDate = sdf1.format(new Date(mYear, mMonth, mDay));
                            if (type.equalsIgnoreCase("wedding")) {
                                tvmarDateTimeText.setVisibility(View.VISIBLE);
                                DateText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textColor));
                                updateTime(mHour, mMinute, tvmarDateTimeText, formatedDate);
                            } else if (type.equalsIgnoreCase("eventTwo")) {
                                tvEventTwoDateTimeText.setVisibility(View.VISIBLE);
                                DateTextEventTwo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textColor));
                                updateTime(mHour, mMinute, tvEventTwoDateTimeText, formatedDate);
                            }
                        }
                    }
                }
                , mHour, mMinute, false);

        tpd.getWindow().getAttributes().windowAnimations = R.style.DialogTime;
        tpd.show();
    }

    private void checkTime() {
        Toast.makeText(this, "Oops!!Invalid Time..", Toast.LENGTH_SHORT).show();
    }

    // Used to convert 24hr format to 12hr format with AM/PM values
    private void updateTime(int hours, int mins, TextView marDateTimeText, String formatedDate) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";

        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        marDateTimeText.setText(formatedDate + ", " + aTime);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId()) {
            case R.id.switchEventTwo:
                if (compoundButton.isChecked()) {
                    rlEventTwo.setVisibility(View.VISIBLE);
                    tvEventNameText.setVisibility(View.VISIBLE);
                    etEventNameText.setVisibility(View.VISIBLE);
                } else {
                    rlEventTwo.setVisibility(View.GONE);
                    tvEventNameText.setVisibility(View.GONE);
                    etEventNameText.setVisibility(View.GONE);
                }
                break;
            case R.id.switchEventRSVP:
                if (compoundButton.isChecked()) {
                    rlEventRSVP.setVisibility(View.VISIBLE);
                } else {
                    rlEventRSVP.setVisibility(View.GONE);
                }
                break;
        }
    }
}
