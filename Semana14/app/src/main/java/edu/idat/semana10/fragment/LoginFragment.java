package edu.idat.semana10.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.idat.semana10.R;
import edu.idat.semana10.activity.LoginCommunication;
import edu.idat.semana10.activity.SignupActivity;
import edu.idat.semana10.fragment.AccessFragment;

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
        btnSignup = view.findViewById(R.id.btnSignup);
        btnLogin = view.findViewById(R.id.btnLogin);

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