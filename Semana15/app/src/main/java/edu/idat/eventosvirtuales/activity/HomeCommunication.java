package edu.idat.eventosvirtuales.activity;

import android.content.Intent;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

public interface HomeCommunication {
    void loadActivity(Intent intent);

    void loadFragment(Fragment fragment);

    void backFragment();

    void showAlert(String msg);
}
