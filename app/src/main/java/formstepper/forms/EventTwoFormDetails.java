package formstepper.forms;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Common.Config;
import pkapoor.wed.R;

import static android.content.Context.MODE_PRIVATE;

public class EventTwoFormDetails extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    ImageView imgDateCalenderEventTwo;
    TextView tvEventTwoDateTimeText, tvEventNameText, DateTextEventTwo;
    EditText etEventNameText,
            etLocationValueEventTwo, etPinCodeValueEventTwo;

    RelativeLayout rlEventTwo;
    SwitchCompat switchEventTwo;
   // ScrollView scrollViewEventTwo;
    int mYear, mMonth, mDay, mHour, mMinute;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.event_two_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgDateCalenderEventTwo = (ImageView) view.findViewById(R.id.imgDateCalenderEventTwo);
        tvEventTwoDateTimeText = (TextView) view.findViewById(R.id.EventTwoDateTimeText);
        tvEventNameText = (TextView) view.findViewById(R.id.EventNameText);
        DateTextEventTwo = (TextView) view.findViewById(R.id.DateTextEventTwo);

        rlEventTwo = (RelativeLayout) view.findViewById(R.id.rlEventTwo);
        etEventNameText = (EditText) view.findViewById(R.id.etEventNameText);
        etLocationValueEventTwo = (EditText) view.findViewById(R.id.LocationValueEventTwo);
        etPinCodeValueEventTwo = (EditText) view.findViewById(R.id.PinCodeValueEventTwo);

        switchEventTwo = (SwitchCompat) view.findViewById(R.id.switchEventTwo);

      //  scrollViewEventTwo = (ScrollView) view.findViewById(R.id.scrollViewEventTwo);

        imgDateCalenderEventTwo.setOnClickListener(this);
        switchEventTwo.setOnCheckedChangeListener(this);
        etLocationValueEventTwo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                   // scrollViewEventTwo.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        rlEventTwo.setVisibility(View.VISIBLE);
        tvEventNameText.setVisibility(View.VISIBLE);
        etEventNameText.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgDateCalenderEventTwo:
                Config.hideKeyboard(getActivity().getCurrentFocus(), getContext());
                setDate("eventTwo");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setValuesBack();
    }

    private void setValuesBack() {

        SharedPreferences sharedPreference = getActivity().getSharedPreferences(Config.MyTEMPORARY_PREFERENCES, MODE_PRIVATE);

      /*  etEventNameText.setText(sharedPreference.getString(Config.Temp_EventName,""));
        etLocationValueEventTwo.setText(sharedPreference.getString(Config.Temp_LocationValue_EventTwo,""));
        etPinCodeValueEventTwo.setText(sharedPreference.getString(Config.Temp_PinCode_EventTwo,""));*/
        tvEventTwoDateTimeText.setText(sharedPreference.getString(Config.Temp_EventTwoDate,""));
        tvEventTwoDateTimeText.setVisibility(View.VISIBLE);
        //switchEventTwo.setChecked(sharedPreference.getBoolean(Config.Temp_EventTwoChecked, false));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
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
        }
    }

    private void setDate(final String type) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(getContext(), R.style.DialogTheme,
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
        final TimePickerDialog tpd = new TimePickerDialog(getContext(), R.style.DialogTheme,
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
                            if (type.equalsIgnoreCase("eventTwo")) {
                                tvEventTwoDateTimeText.setVisibility(View.VISIBLE);
                                DateTextEventTwo.setTextColor(ContextCompat.getColor(getContext(), R.color.textColor));
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
        Toast.makeText(getContext(), "Oops!!Invalid Time..", Toast.LENGTH_SHORT).show();
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

    public boolean checkField(){
       boolean isFilled = checkEventTwoDetails();
        if(!isFilled){
            if (switchEventTwo.isChecked()) {
                Config.changeDateTextcolor(tvEventTwoDateTimeText, DateTextEventTwo,
                        ContextCompat.getColor(getContext(), R.color.colorRed), ContextCompat.getColor(getContext(), R.color.textColor));

                Config.checkEditTextNullandSetError(etEventNameText);
                Config.checkEditTextNullandSetError(etLocationValueEventTwo);
                Config.checkEditTextNullandSetError(etPinCodeValueEventTwo);
            }
        }
        return isFilled;
    }
    private boolean checkEventTwoDetails() {
        if (switchEventTwo.isChecked()) {
            if (Config.isEditTextEmpty(etEventNameText) || Config.isEditTextEmpty(etLocationValueEventTwo) ||
                    Config.isEditTextEmpty(etPinCodeValueEventTwo) || Config.checkDateText(tvEventTwoDateTimeText)) {
                return false;
            } else {
                storeInSharedPreference();
                return true;
            }
        } else {
            storeInSharedPreference();
            return true;
        }
    }
    public void storeInSharedPreference() {

        SharedPreferences.Editor editor = getActivity().getSharedPreferences(Config.MyTEMPORARY_PREFERENCES, MODE_PRIVATE).edit();

        editor.putString(Config.Temp_EventName, etEventNameText.getText().toString());
        editor.putString(Config.Temp_LocationValue_EventTwo, etLocationValueEventTwo.getText().toString());
        editor.putString(Config.Temp_PinCode_EventTwo, etPinCodeValueEventTwo.getText().toString());
        editor.putString(Config.Temp_EventTwoDate, tvEventTwoDateTimeText.getText().toString());
        editor.putBoolean(Config.Temp_EventTwoChecked, switchEventTwo.isChecked());

        editor.apply();
    }
}
