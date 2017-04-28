package Common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by DELL on 11/29/2016.
 */
public class Config {
    public static final String MyPREFERENCES = "myPreference";
    public static final String MyTEMPORARY_PREFERENCES = "mytemporary_Preference";
    public static final String URL_FETCH = "http://vnnps.esy.es/getData.php?unique_wed_code=";
    public static final String TAG_JSON_ARRAY = "result";
    public static final String unique_wed_code = "unique_wed_code";
    public static final String name_bride = "name_bride";
    public static final String name_groom = "name_groom";
    public static final String date_marriage = "date_marriage";
    public static final String blessUs_para = "blessUs_para";
    public static final String event_two_tobe = "event_two_tobe";
    public static final String event_Two_date = "event_two_date";
    public static final String event_Two_time = "event_two_time";
    public static final String event_Two_location = "event_two_location";
    public static final String marriage_tobe = "marriage_tobe";
    public static final String marriage_date = "marriage_date";
    public static final String marriage_time = "marriage_time";
    public static final String marriage_location = "marriage_location";
    public static final String rsvp_tobe = "rsvp_tobe";
    public static final String rsvp_name1 = "rsvp_name1";
    public static final String rsvp_name2 = "rsvp_name2";
    public static final String rsvp_phone_one = "rsvp_phone_one";
    public static final String rsvp_phone_two = "rsvp_phone_two";
    public static final String event_two_name = "event_two_name";
    public static final String rsvp_text = "rsvp_text";
    public static final String setToolbarMenuIcons = "setToolbarMenuIcons";
    public static final String setLatestViewedId = "setLatestViewedId";
    public static final  String TYPE_WED_CREATED = "created";
    public static final  String TYPE_WED_VIEWED = "viewed";
    public static final String ONLY_SHARE = "only_Share";
    public static final String colorSelected = "color";
    public static final String back_image = "back_image";

    public static final String Temp_MarriageDate = "Temp_MarriageDate";
    public static final String Temp_GroomName = "Temp_GroomName";
    public static final  String Temp_BrideName = "Temp_BrideName";
    public static final  String Temp_Location_Mar = "Temp_Location_Mar";
    public static final String Temp_PinCode_marriage = "Temp_PinCode_marriage";
    public static final String Temp_InviteMes_Marr = "Temp_InviteMes_Marr";

    public static final String Temp_EventName = "Temp_EventName";
    public static final String Temp_LocationValue_EventTwo = "Temp_LocationValue_EventTwo";
    public static final String Temp_PinCode_EventTwo = "Temp_PinCode_EventTwo";
    public static final String Temp_EventTwoDate = "Temp_EventTwoDate";
    public static final String Temp_RSVP_Name_One = "Temp_RSVP_Name_One";
    public static final String Temp_RSVP_Mobile_One = "Temp_RSVP_Mobile_One";
    public static final String Temp_RSVP_Invite = "Temp_RSVP_Invite";
    public static final String Temp_RSVP_Name_Two = "Temp_RSVP_Name_Two";
    public static final String Temp_RSVP_Mobile_Two = "Temp_RSVP_Mobile_Two";
    public static final String Temp_EventTwoChecked = "Temp_EventTwoChecked";
    public static final String Temp_RSVP_checked = "Temp_RSVP_hecked";
    public static final String Temp_ColorSelected = "Temp_ColorSelected";
    public static final String Temp_backgroundSelected = "Temp_backgroundSelected";

    public static boolean isOnline(@NonNull Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void checkEditTextNullandSetError(EditText editText) {
        if (editText != null && TextUtils.isEmpty(editText.getText().toString())) {
            editText.setError("Please fill the details..");
        }
    }

    public static void changeDateTextcolor(TextView toCheck, TextView toChaneColor, int colorRed, int colorNormal) {
        if (toCheck != null && TextUtils.isEmpty(toCheck.getText().toString())) {
            toChaneColor.setTextColor(colorRed);
        } else {
            toChaneColor.setTextColor(colorNormal);
        }
    }

    public static boolean checkDateText(TextView toCheck) {
        if (toCheck != null && TextUtils.isEmpty(toCheck.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEditTextEmpty(EditText editText) {
        if (editText != null && TextUtils.isEmpty(editText.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public static void hideKeyboard(View currentFocus, Context context) {
        if (currentFocus != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public static void shareIntent(Context context, String uniqueCode) {

        String shareBody ="Here's the wedding invite. Looking forward to see you there..Enter "+" "+
                    uniqueCode.toUpperCase();

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Wedding Invite");
        String finalStr="\n https://gr83f.app.goo.gl/?link=http://play.google.com&apn=pkapoor.wed&afl=" +
                "http://play.google.com/store/apps/details?id%3Dpkapoor.wed&utm_medium="+uniqueCode;

        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody + " " + finalStr);
        PackageManager packageManager = context.getPackageManager();

        if (sharingIntent.resolveActivity(packageManager) != null) {
            context.startActivity(Intent.createChooser(sharingIntent, "Share the invite via"));
        } else {
            Toast.makeText(context, "No message support", Toast.LENGTH_SHORT).show();
        }
    }
    public static void changeTheme(int toolbarColor, int statusBar, Toolbar toolbar, Context context, Window window, TabLayout tabLayout) {

        toolbar.setBackgroundColor(ContextCompat.getColor(context, toolbarColor));
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor((ContextCompat.getColor(context, statusBar)));
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, toolbarColor));
    }
}
