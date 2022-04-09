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

public class Intro extends AppCompatActivity {
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btnSignUp = (Button) this.findViewById(R.id.buttonSignup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              signUP(view);
            }
        });;

    }
    public void signUP(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("êtes vous étudiant ou professeur?");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.etudiant, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int id){
                Toast.makeText(Intro.this,R.string.etudiant, Toast.LENGTH_LONG).show();

                //mon intent
                Intent intention= new Intent(Intro.this, Signup.class);

                startActivity(intention);
            }

        });
        builder.setNegativeButton(R.string.prof, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int id) {
                Toast.makeText(Intro.this, R.string.prof, Toast.LENGTH_LONG).show();
                Intent intention= new Intent(Intro.this, Signup.class);

                startActivity(intention);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void login() {
        Intent intention= new Intent(Intro.this, Login.class);

        startActivity(intention);
    }
}