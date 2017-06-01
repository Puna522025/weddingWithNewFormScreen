package formstepper.forms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import Common.Config;
import formstepper.StepperClass;
import pkapoor.wed.R;
import pkapoor.wed.ViewGeneratedInvite;
import tabfragments.RSVP;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pkapo8 on 4/24/2017.
 */

public class ThemesForrmDetails extends Fragment implements View.OnClickListener {

    ImageView tickRed, tickBlue, tickBlack, tickGreen, tickcoupleback, tickPink, tickPinkPetals, tickNoImage, pinkPetals, coupleback;
    RelativeLayout rlBackground;

    CardView rlPink, rlRed, rlGreen, rlBlack, rlBlue, rlImagePinkPetals, rlImageNoImage, rlCoupleMainBack;

    private ProgressDialog progressDialog;
    String colorSelected = "colorRed";

    String backgroundSelected = "0";
    private TextView continueToInvite;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.themes_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tickRed = (ImageView) view.findViewById(R.id.tickRed);
        tickBlue = (ImageView) view.findViewById(R.id.tickBlue);
        tickBlack = (ImageView) view.findViewById(R.id.tickBlack);
        tickGreen = (ImageView) view.findViewById(R.id.tickGreen);
        tickPink = (ImageView) view.findViewById(R.id.tickPink);
        tickPinkPetals = (ImageView) view.findViewById(R.id.tickPinkPetals);
        tickNoImage = (ImageView) view.findViewById(R.id.tickNoImage);
        tickcoupleback = (ImageView) view.findViewById(R.id.tickcoupleback);

        pinkPetals = (ImageView) view.findViewById(R.id.pinkPetals);
        coupleback = (ImageView) view.findViewById(R.id.coupleback);

        rlBackground = (RelativeLayout) view.findViewById(R.id.rlBackground);
        continueToInvite = (TextView) view.findViewById(R.id.continueToInvite);

        rlPink = (CardView) view.findViewById(R.id.rlPink);
        rlRed = (CardView) view.findViewById(R.id.rlRed);
        rlGreen = (CardView) view.findViewById(R.id.rlGreen);
        rlBlack = (CardView) view.findViewById(R.id.rlBlack);
        rlBlue = (CardView) view.findViewById(R.id.rlBlue);
        rlImagePinkPetals = (CardView) view.findViewById(R.id.rlImagePinkPetals);
        rlImageNoImage = (CardView) view.findViewById(R.id.rlImageNoImage);
        rlCoupleMainBack = (CardView) view.findViewById(R.id.rlImageCoupleBack);

        rlPink.setOnClickListener(this);
        rlRed.setOnClickListener(this);
        rlGreen.setOnClickListener(this);
        rlBlack.setOnClickListener(this);
        rlBlue.setOnClickListener(this);
        rlImagePinkPetals.setOnClickListener(this);
        rlImageNoImage.setOnClickListener(this);
        rlCoupleMainBack.setOnClickListener(this);
        continueToInvite.setOnClickListener(this);

        tickRed.setVisibility(View.VISIBLE);
        tickNoImage.setVisibility(View.VISIBLE);

        Picasso.with(getActivity())
                .load(R.drawable.back_seven)
                .fit()
                .into(pinkPetals);

        Picasso.with(getActivity())
                .load(R.drawable.two)
                .fit()
                .into(coupleback);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

          /*  case R.id.continueToInvite:
                continueToInviteScreen();
                break;*/
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
            case R.id.continueToInvite:

                boolean isMarriageFilled = true;
                boolean isEventTwoFilled = true;
                boolean isRSVPfilled = true;

                for (int i = 0; i < 3; i++){

                    Fragment fragment = StepperClass.getCurrentFragment(i);
                    if (fragment instanceof NameMarriageFormDetails) {
                        NameMarriageFormDetails remainderFragment = (NameMarriageFormDetails) fragment;
                        isMarriageFilled = remainderFragment.checkField();
                    }

                    if (fragment instanceof EventTwoFormDetails) {
                        EventTwoFormDetails remainderFragment = (EventTwoFormDetails) fragment;
                        isEventTwoFilled = remainderFragment.checkField();
                    }
                    if (fragment instanceof RSVPformDetails) {
                        RSVPformDetails remainderFragment = (RSVPformDetails) fragment;
                        isRSVPfilled = remainderFragment.checkField();
                    }
                }
                    if (isMarriageFilled && isEventTwoFilled && isRSVPfilled) {
                        continueToInviteScreen();
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        if(!isMarriageFilled)
                            stringBuilder.append(" - Marriage details.\n");
                        if(!isEventTwoFilled)
                            stringBuilder.append(" - Event Two details. \n");
                        if(!isRSVPfilled)
                            stringBuilder.append(" - Family details. \n");

                        new AlertDialog.Builder(getContext())
                                .setTitle("")
                                .setMessage("Please fill the following - \n"+ stringBuilder)
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                break;
        }
        storeInSharedPreference();
    }

    public void continueToInviteScreen() {
        //TODO: check if all the fields are filled properly or not..
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Creating your invite..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        SharedPreferences.Editor editor = getActivity().getSharedPreferences(Config.MyPREFERENCES, MODE_PRIVATE).edit();

        SharedPreferences tempSharedPref = getActivity().getSharedPreferences(Config.MyTEMPORARY_PREFERENCES, MODE_PRIVATE);

        editor.putString(Config.blessUs_para, tempSharedPref.getString(Config.Temp_InviteMes_Marr, ""));
        String marDateTime = tempSharedPref.getString(Config.Temp_MarriageDate, "");

        if (!TextUtils.isEmpty(marDateTime)) {
            String marDate = marDateTime.substring(0, marDateTime.indexOf(","));
            String marTime = marDateTime.substring(marDateTime.indexOf(",") + 2, marDateTime.length());
            editor.putString(Config.date_marriage, marDateTime);
            editor.putString(Config.marriage_date, marDate);
            editor.putString(Config.marriage_time, marTime);
        }

        editor.putString(Config.marriage_location, tempSharedPref.getString(Config.Temp_Location_Mar, "") + "," + tempSharedPref.getString(Config.Temp_PinCode_marriage, ""));
        editor.putString(Config.marriage_tobe, "true");
        editor.putString(Config.name_bride, tempSharedPref.getString(Config.Temp_BrideName, ""));
        editor.putString(Config.name_groom, tempSharedPref.getString(Config.Temp_GroomName, ""));
        editor.putString(Config.rsvp_name1, tempSharedPref.getString(Config.Temp_RSVP_Name_One, ""));
        editor.putString(Config.rsvp_name2, tempSharedPref.getString(Config.Temp_RSVP_Name_Two, ""));
        editor.putString(Config.rsvp_phone_one, tempSharedPref.getString(Config.Temp_RSVP_Mobile_One, ""));
        editor.putString(Config.rsvp_phone_two, tempSharedPref.getString(Config.Temp_RSVP_Mobile_Two, ""));
        editor.putString(Config.event_two_name, tempSharedPref.getString(Config.Temp_EventName, ""));
        editor.putString(Config.rsvp_text, tempSharedPref.getString(Config.Temp_RSVP_Invite, ""));
        editor.putString(Config.colorSelected, tempSharedPref.getString(Config.Temp_ColorSelected, ""));
        editor.putString(Config.back_image, tempSharedPref.getString(Config.Temp_backgroundSelected, ""));

        boolean isRSVPEntered = tempSharedPref.getBoolean(Config.Temp_RSVP_checked, false);
        if (isRSVPEntered) {
            editor.putString(Config.rsvp_tobe, "true");
        } else {
            editor.putString(Config.rsvp_tobe, "false");
        }

        String eventTwoDateTime = tempSharedPref.getString(Config.Temp_EventTwoDate, "");
        if (!TextUtils.isEmpty(eventTwoDateTime)) {
            String eventTwoDate = eventTwoDateTime.substring(0, eventTwoDateTime.indexOf(","));
            String eventTwoTime = eventTwoDateTime.substring(eventTwoDateTime.indexOf(",") + 2, eventTwoDateTime.length());
            editor.putString(Config.event_Two_date, eventTwoDate);
            editor.putString(Config.event_Two_time, eventTwoTime);
        }

        boolean isEventTwoEntered = tempSharedPref.getBoolean(Config.Temp_EventTwoChecked, false);

        if (isEventTwoEntered) {
            editor.putString(Config.event_two_tobe, "true");
        } else {
            editor.putString(Config.event_two_tobe, "false");
        }

        editor.putString(Config.event_Two_location, tempSharedPref.getString(Config.Temp_LocationValue_EventTwo, "") + "," + tempSharedPref.getString(Config.Temp_PinCode_EventTwo, ""));
        editor.apply();

        progressDialog.dismiss();

        Intent intentOne = getActivity().getIntent();
        Bundle extras = intentOne.getExtras();

        Intent intent = new Intent(getActivity(), ViewGeneratedInvite.class);
        intent.putExtra(Config.setToolbarMenuIcons, extras.get(Config.setToolbarMenuIcons).toString());
        startActivity(intent);
    }

    public void storeInSharedPreference() {

        SharedPreferences.Editor editor = getActivity().getSharedPreferences(Config.MyTEMPORARY_PREFERENCES, MODE_PRIVATE).edit();

        editor.putString(Config.Temp_ColorSelected, colorSelected);
        editor.putString(Config.Temp_backgroundSelected, backgroundSelected);

        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        setValuesBack();
    }

    private void setValuesBack() {

        SharedPreferences sharedPreference = getActivity().getSharedPreferences(Config.MyTEMPORARY_PREFERENCES, MODE_PRIVATE);

        switch (sharedPreference.getString(Config.Temp_ColorSelected, "")) {

            case "colorRed":
                tickRed.setVisibility(View.VISIBLE);
                tickGreen.setVisibility(View.GONE);
                tickBlack.setVisibility(View.GONE);
                tickBlue.setVisibility(View.GONE);
                tickPink.setVisibility(View.GONE);

                break;
            case "PinkKittyToolBar":
                tickRed.setVisibility(View.GONE);
                tickGreen.setVisibility(View.GONE);
                tickBlack.setVisibility(View.GONE);
                tickBlue.setVisibility(View.GONE);
                tickPink.setVisibility(View.VISIBLE);

                break;
            case "GreenToolBar":
                tickRed.setVisibility(View.GONE);
                tickGreen.setVisibility(View.VISIBLE);
                tickBlack.setVisibility(View.GONE);
                tickBlue.setVisibility(View.GONE);
                tickPink.setVisibility(View.GONE);

                break;
            case "BlackToolBar":
                tickRed.setVisibility(View.GONE);
                tickGreen.setVisibility(View.GONE);
                tickBlack.setVisibility(View.VISIBLE);
                tickBlue.setVisibility(View.GONE);
                tickPink.setVisibility(View.GONE);

                break;
            case "BlueToolBar":
                tickRed.setVisibility(View.GONE);
                tickGreen.setVisibility(View.GONE);
                tickBlack.setVisibility(View.GONE);
                tickBlue.setVisibility(View.VISIBLE);
                tickPink.setVisibility(View.GONE);
                break;
        }

        switch (sharedPreference.getString(Config.Temp_backgroundSelected, "")) {

            case "0":
                tickNoImage.setVisibility(View.VISIBLE);
                tickPinkPetals.setVisibility(View.GONE);
                tickcoupleback.setVisibility(View.GONE);
                break;
            case "1":
                tickNoImage.setVisibility(View.GONE);
                tickcoupleback.setVisibility(View.GONE);
                tickPinkPetals.setVisibility(View.VISIBLE);
                break;
            case "2":
                tickcoupleback.setVisibility(View.VISIBLE);
                tickNoImage.setVisibility(View.GONE);
                tickPinkPetals.setVisibility(View.GONE);
                break;
        }
    }
}
