package com.crumet.khata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomDialog extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_bottom_menu, container, false);

        UserSession userSession = new UserSession(getContext());
        HashMap<String, String> userDetails = userSession.getUserDetails();


        TextView mEmail = v.findViewById(R.id.profile_email);
        mEmail.setText(userDetails.get(UserSession.KEY_EMAIL));
        return v;
    }


    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }
}
