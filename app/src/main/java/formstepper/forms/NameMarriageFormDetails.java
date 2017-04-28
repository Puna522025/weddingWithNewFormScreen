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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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

public class NameMarriageFormDetails extends Fragment implements View.OnClickListener {

    EditText etNameBri, etNameGro, etLocationValue, etPinCodeValue, etInviteMesValue;
    ScrollView scrollView;
    ImageView imgDateCalender;
    int mYear, mMonth, mDay, mHour, mMinute;
    TextView tvmarDateTimeText, DateText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.name_marriage_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  scrollView = (ScrollView) view.findViewById(R.id.scrollViewDetails);

        etNameBri = (EditText) view.findViewById(R.id.etNameBri);
        etNameGro = (EditText) view.findViewById(R.id.etNameGro);
        etLocationValue = (EditText) view.findViewById(R.id.LocationValue);
        etPinCodeValue = (EditText) view.findViewById(R.id.PinCodeValue);
        etInviteMesValue = (EditText) view.findViewById(R.id.InviteMesValue);

        imgDateCalender = (ImageView) view.findViewById(R.id.imgDateCalender);
        tvmarDateTimeText = (TextView) view.findViewById(R.id.marDateTimeText);
        DateText = (TextView) view.findViewById(R.id.DateText);

        imgDateCalender.setOnClickListener(this);

        etInviteMesValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                   // scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            }
        });
    }

    public void setValuesBack() {
        SharedPreferences sharedPreference = getActivity().getSharedPreferences(Config.MyTEMPORARY_PREFERENCES, MODE_PRIVATE);

        tvmarDateTimeText.setText(sharedPreference.getString(Config.Temp_MarriageDate, ""));
        tvmarDateTimeText.setVisibility(View.VISIBLE);
       /* etNameGro.setText(sharedPreference.getString(Config.Temp_GroomName, ""));
        etNameBri.setText(sharedPreference.getString(Config.Temp_BrideName, ""));
        etLocationValue.setText(sharedPreference.getString(Config.Temp_Location_Mar, ""));
        etPinCodeValue.setText(sharedPreference.getString(Config.Temp_PinCode_marriage, ""));
        etInviteMesValue.setText(sharedPreference.getString(Config.Temp_InviteMes_Marr, ""));*/
    }

    @Override
    public void onResume() {
        super.onResume();
        setValuesBack();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imgDateCalender:
                Config.hideKeyboard(getActivity().getCurrentFocus(), getContext());
                setDate("wedding");
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
                            if (type.equalsIgnoreCase("wedding")) {
                                tvmarDateTimeText.setVisibility(View.VISIBLE);
                                DateText.setTextColor(ContextCompat.getColor(getContext(), R.color.textColor));
                                updateTime(mHour, mMinute, tvmarDateTimeText, formatedDate);
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

    public boolean checkField() {
        if (Config.isEditTextEmpty(etNameBri) || Config.isEditTextEmpty(etNameGro) ||
                Config.isEditTextEmpty(etLocationValue) || Config.isEditTextEmpty(etPinCodeValue) ||
                Config.isEditTextEmpty(etInviteMesValue) || Config.checkDateText(tvmarDateTimeText) ||
                Config.checkDateText(tvmarDateTimeText)) {

            Config.changeDateTextcolor(tvmarDateTimeText, DateText,
                    ContextCompat.getColor(getContext(), R.color.colorRed), ContextCompat.getColor(getContext(), R.color.textColor));
            Config.checkEditTextNullandSetError(etNameBri);
            Config.checkEditTextNullandSetError(etNameGro);
            Config.checkEditTextNullandSetError(etLocationValue);
            Config.checkEditTextNullandSetError(etPinCodeValue);
            Config.checkEditTextNullandSetError(etInviteMesValue);
            new AlertDialog.Builder(getContext())
                    .setTitle("")
                    .setMessage("Please fill the details")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return false;
        } else {
            storeInSharedPreference();
            return true;
        }
    }

    public void storeInSharedPreference() {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(Config.MyTEMPORARY_PREFERENCES, MODE_PRIVATE).edit();

        editor.putString(Config.Temp_MarriageDate, tvmarDateTimeText.getText().toString());
        editor.putString(Config.Temp_GroomName, etNameGro.getText().toString());
        editor.putString(Config.Temp_BrideName, etNameBri.getText().toString());
        editor.putString(Config.Temp_Location_Mar, etLocationValue.getText().toString());
        editor.putString(Config.Temp_PinCode_marriage, etPinCodeValue.getText().toString());
        editor.putString(Config.Temp_InviteMes_Marr, etInviteMesValue.getText().toString());

        editor.apply();
    }
}
