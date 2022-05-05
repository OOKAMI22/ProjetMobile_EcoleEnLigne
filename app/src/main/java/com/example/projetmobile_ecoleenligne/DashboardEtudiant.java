package com.example.projetmobile_ecoleenligne;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.Utilisateur;

public class DashboardEtudiant extends AppCompatActivity {
    Utilisateur user;
    Bundle extras;
    String nom;
    String prenom;
    String email;
    String numero;
    String pays;
    String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_etudiant);
        // get mon intent
        extras = getIntent().getExtras();
        System.out.println("cest moi " + extras.getString("user"));
        nom = extras.getString("nom");
        prenom = extras.getString("prenom");
        email = extras.getString("email");
        numero = extras.getString("numero");
        pays = extras.getString("pays");
        role = extras.getString("role");


    }
}