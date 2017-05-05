package formstepper.forms;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;

import Common.Config;
import pkapoor.wed.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pkapo8 on 8/10/2016.
 * Dummy fragment.
 */
public class RSVPformDetails extends Fragment implements CompoundButton.OnCheckedChangeListener {

    EditText etRSVPNameOne, etRSVPMobileOne, etRSVPNameTwo, etRSVPMobileTwo, etInviteMesRSVPValue;
    RelativeLayout rlEventRSVP;
    SwitchCompat switchEventRSVP;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.rsvp_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rlEventRSVP = (RelativeLayout) view.findViewById(R.id.rlEventRSVP);
        etRSVPNameOne = (EditText) view.findViewById(R.id.etRSVPNameOne);
        etRSVPMobileOne = (EditText) view.findViewById(R.id.etRSVPMobileOne);
        etRSVPNameTwo = (EditText) view.findViewById(R.id.etRSVPNameTwo);
        etRSVPMobileTwo = (EditText) view.findViewById(R.id.etRSVPMobileTwo);
        etInviteMesRSVPValue = (EditText) view.findViewById(R.id.InviteMesRSVPValue);
        switchEventRSVP = (SwitchCompat) view.findViewById(R.id.switchEventRSVP);
        switchEventRSVP.setOnCheckedChangeListener(this);

        rlEventRSVP.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.switchEventRSVP:
                if (compoundButton.isChecked()) {
                    rlEventRSVP.setVisibility(View.VISIBLE);
                } else {
                    rlEventRSVP.setVisibility(View.GONE);
                }
                break;
        }
    }

    public boolean checkField() {
        if (checkRSVPDetails()) {
            if (switchEventRSVP.isChecked()) {
                Config.checkEditTextNullandSetError(etRSVPNameOne);
                Config.checkEditTextNullandSetError(etRSVPMobileOne);
                Config.checkEditTextNullandSetError(etInviteMesRSVPValue);
                return false;
            }
        }
        return true;
    }

    private boolean checkRSVPDetails() {
        if (switchEventRSVP.isChecked()) {
            if (Config.isEditTextEmpty(etRSVPNameOne) || Config.isEditTextEmpty(etRSVPMobileOne)
                    || Config.isEditTextEmpty(etInviteMesRSVPValue)) {
                return true;
            } else {
                storeInSharedPreference();
                return false;
            }
        } else {
            storeInSharedPreference();
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void storeInSharedPreference() {

        SharedPreferences.Editor editor = getActivity().getSharedPreferences(Config.MyTEMPORARY_PREFERENCES, MODE_PRIVATE).edit();

        editor.putString(Config.Temp_RSVP_Name_One, etRSVPNameOne.getText().toString());
        editor.putString(Config.Temp_RSVP_Mobile_One, etRSVPMobileOne.getText().toString());
        editor.putString(Config.Temp_RSVP_Invite, etInviteMesRSVPValue.getText().toString());
        editor.putString(Config.Temp_RSVP_Name_Two, etRSVPNameTwo.getText().toString());
        editor.putString(Config.Temp_RSVP_Mobile_Two, etRSVPMobileTwo.getText().toString());
        editor.putBoolean(Config.Temp_RSVP_checked, switchEventRSVP.isChecked());

        editor.apply();
    }

}
