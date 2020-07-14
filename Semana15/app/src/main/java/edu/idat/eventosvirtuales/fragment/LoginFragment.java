package edu.idat.eventosvirtuales.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.idat.eventosvirtuales.activity.LoginCommunication;
import edu.idat.eventosvirtuales.R;
import edu.idat.eventosvirtuales.activity.SignupActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private LoginCommunication communication;

    private Button btnSignup, btnLogin;

    public LoginFragment() {
    }

    public LoginFragment(LoginCommunication communication) {
        this.communication = communication;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        initListeners();
    }

    private void initViews(View view) {
        btnSignup = view.findViewById(R.id.btnSignup);
        btnLogin = view.findViewById(R.id.btnLogin);
    }

    private void initListeners() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communication.loadActivity(new Intent(getContext(), SignupActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communication.loadFragment(new AccessFragment(communication));
            }
        });
    }
}
