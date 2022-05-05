package com.example.projetmobile_ecoleenligne;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DashboardEtudiant extends AppCompatActivity {
    Bundle extras;
    String role;
    String user;
    ImageView quizz;
    ImageView cours;
    ImageView formation;
    ImageView profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_etudiant);
        // get mon intent
        extras = getIntent().getExtras();
        user = extras.getString("user");
        role = extras.getString("role");
        System.out.println("cest moi " +user+" et je suis "+role);

        quizz = findViewById(R.id.imageQuizz);
        quizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention= new Intent(DashboardEtudiant.this, Quizz.class);
                intention.putExtra("user",user);
                intention.putExtra("role",role);
                startActivity(intention);
            }
        });
        cours = findViewById(R.id.imageCours);
        cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention= new Intent(DashboardEtudiant.this, Quizz.class);
                intention.putExtra("user",user);
                intention.putExtra("role",role);
                startActivity(intention);
            }
        });
        formation = findViewById(R.id.imageFormation);
        formation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention= new Intent(DashboardEtudiant.this, Formations.class);
                intention.putExtra("user",user);
                intention.putExtra("role",role);
                startActivity(intention);
            }
        });
        profile = findViewById(R.id.imageProfile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention= new Intent(DashboardEtudiant.this, Profile.class);
                intention.putExtra("user",user);
                intention.putExtra("role",role);
                startActivity(intention);
            }
        });

    }
}