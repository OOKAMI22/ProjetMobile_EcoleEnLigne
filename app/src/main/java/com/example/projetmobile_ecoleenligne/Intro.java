package com.example.projetmobile_ecoleenligne;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.Utilisateur;

public class Intro extends AppCompatActivity {
    Button btnLogin;
    Button btnSignUp;
    Button btnFormations;
    // car on sait pas encore si c'est un etudiant ou un moderateur
    Utilisateur user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btnSignUp = this.findViewById(R.id.btnSignup);
        btnLogin =  this.findViewById(R.id.btnLogin);
        btnFormations = this.findViewById(R.id.btnFormations);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              signUP(view);
            }
        });

        btnFormations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFormation(view);
            }
        });

    }
    public void signUP(View view) {
        Intent intention= new Intent(Intro.this, Signup.class);
        startActivity(intention);
    }

    public void login(View view) {
        Intent intention= new Intent(Intro.this, Login.class);

        startActivity(intention);
    }
    public void goFormation(View view) {
        Intent intention= new Intent(Intro.this, FormationsActivity.class);

        startActivity(intention);
    }
}