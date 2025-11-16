package com.example.preferencesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    TextView tvName, tvEmail, tvPassword, tvTheme, tvNotifications;
    SharedPreferences prefs;
    View rootView;

    SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        tvName = rootView.findViewById(R.id.tvName);
        tvEmail = rootView.findViewById(R.id.tvEmail);
        tvPassword = rootView.findViewById(R.id.tvPassword);
        tvTheme = rootView.findViewById(R.id.tvTheme);
        tvNotifications = rootView.findViewById(R.id.tvNotifications);

        prefs = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        updateProfile();

        listener = (sharedPreferences, key) -> updateProfile();
        prefs.registerOnSharedPreferenceChangeListener(listener);

        return rootView;
    }

    private void updateProfile() {
        tvName.setText("Name: " + prefs.getString("username", "No Name Saved"));
        tvEmail.setText("Email: " + prefs.getString("email", "No Email Saved"));
        tvPassword.setText("Password: " + prefs.getString("password", "No Password Saved"));
        String theme = prefs.getString("theme", "Light");
        tvTheme.setText("Theme: " + theme);
        tvNotifications.setText("Notifications: " + (prefs.getBoolean("notifications", false) ? "Enabled" : "Disabled"));

        // Apply theme background
        switch (theme) {
            case "Light":
                rootView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case "Dark":
                rootView.setBackgroundColor(Color.parseColor("#696969"));
                break;
            case "Blue":
                rootView.setBackgroundColor(Color.parseColor("#ADD8E6"));
                break;
            case "Green":
                rootView.setBackgroundColor(Color.parseColor("#90EE90"));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
