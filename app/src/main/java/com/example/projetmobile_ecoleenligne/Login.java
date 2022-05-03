package com.example.projetmobile_ecoleenligne;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Login extends AppCompatActivity {
    EditText emailET;
    EditText mdpET;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET = (EditText) this.findViewById(R.id.email);
        mdpET = (EditText) this.findViewById(R.id.mdp);
        btnLogin = (Button) this.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connexion(view);
            }
        });;
    }

    public void connexion(View view){
        String email = emailET.getText().toString();
        String mdp = mdpET.getText().toString();
        // Interroger la bdd sur les 2 string

    }
}