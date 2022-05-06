package com.example.projetmobile_ecoleenligne;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.Etudiant;
import com.example.projetmobile_ecoleenligne.classes.Formation;
import com.example.projetmobile_ecoleenligne.classes.Moderateur;
import com.example.projetmobile_ecoleenligne.classes.Utilisateur;

public class AcceuilActivity extends AppCompatActivity {
    Bundle extras;
    String role;
    String userInfo;
    ImageView quizz;
    ImageView cours;
    ImageView formation;
    ImageView profile;
    Utilisateur user;

    Formation formationEtu = new Formation();
    String grade = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_etudiant);
        // get mon intent
        extras = getIntent().getExtras();
        userInfo = extras.getString("userInfo");
        role = extras.getString("role");

        user =  getIntent().getParcelableExtra("user");
        System.out.println("cest moi " +userInfo+" et je suis "+role);
        if(role.equals("moderateur")){
            grade = extras.getString("grade");
        }
        else{
            formationEtu = getIntent().getParcelableExtra("formation");
        }
        System.out.println("Accueil "+grade);




        quizz = findViewById(R.id.imageQuizz);
        quizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention= new Intent(AcceuilActivity.this, Quizz.class);
                intention.putExtra("user",user);
                intention.putExtra("role",role);
                intention.putExtra("formation",formationEtu);
                intention.putExtra("grade",grade);
                startActivity(intention);
            }
        });
        cours = findViewById(R.id.imageCours);
        cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention= new Intent(AcceuilActivity.this, Quizz.class);
                intention.putExtra("user",user);
                intention.putExtra("role",role);
                intention.putExtra("formation",formationEtu);
                intention.putExtra("grade",grade);
                startActivity(intention);
            }
        });
        formation = findViewById(R.id.imageFormation);
        formation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention= new Intent(AcceuilActivity.this, Formations.class);
                intention.putExtra("user",user);
                intention.putExtra("role",role);
                intention.putExtra("formation",formationEtu);
                intention.putExtra("grade",grade);
                startActivity(intention);
            }
        });
        profile = findViewById(R.id.imageProfile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention= new Intent(AcceuilActivity.this, Profile.class);
                intention.putExtra("user",user);
                intention.putExtra("role",role);
                intention.putExtra("formation",formationEtu);
                intention.putExtra("grade",grade);
                startActivity(intention);
            }
        });

    }
}