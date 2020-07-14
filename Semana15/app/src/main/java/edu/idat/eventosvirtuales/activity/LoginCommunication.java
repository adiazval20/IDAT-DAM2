package edu.idat.eventosvirtuales.activity;

import android.content.Intent;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

public interface LoginCommunication {
    void loadActivity(Intent intent);

    void loadFragment(Fragment fragment);

    void backFragment();

    void login(String username, String password, ProgressBar progressBar);

    void showAlert(String msg);
}
