package edu.idat.eventosvirtuales.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;

import edu.idat.eventosvirtuales.activity.LoginCommunication;
import edu.idat.eventosvirtuales.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccessFragment extends Fragment {
    private LoginCommunication communication;
    private TextInputEditText edtUsername, edtPassword;
    private Button btnCancel, btnLogin;
    private ProgressBar pgbLoading;

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
        initViews(view);
        initListeners();
    }

    private void initViews(View view) {
        edtUsername = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.edtPassword);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnLogin = view.findViewById(R.id.btnLogin);
        pgbLoading = view.findViewById(R.id.pgbLoading);
    }

    private void initListeners() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communication.backFragment();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                if (username.trim().equals("")) {
                    communication.showAlert("Debe ingresar un usuario");
                    return;
                }
                if (password.trim().equals("")) {
                    communication.showAlert("Debe ingresar una contraseña");
                    return;
                }

                pgbLoading.setVisibility(View.VISIBLE);
                communication.login(username, password, pgbLoading);
            }
        });
    }
}
