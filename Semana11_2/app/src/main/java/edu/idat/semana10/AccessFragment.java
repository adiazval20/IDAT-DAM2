package edu.idat.semana10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class AccessFragment extends Fragment {
    private LoginCommunication communication;
    private Button btnCancel, btnLogin;
    private ProgressBar pgbLoading;
    private EditText edtUsername, edtPassword;

    public AccessFragment() {
    }

    public AccessFragment(LoginCommunication communication) {
        this.communication = communication;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_access, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnCancel = view.findViewById(R.id.btnCancel);
        btnLogin = view.findViewById(R.id.btnLogin);
        pgbLoading = view.findViewById(R.id.pgbLoading);
        edtUsername = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.edtPassword);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communication.backFragment();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgbLoading.setVisibility(View.VISIBLE);

                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                communication.login(username, password, pgbLoading);
            }
        });
    }
}