package com.example.projetmobile_ecoleenligne;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.Etudiant;
import com.example.projetmobile_ecoleenligne.classes.Formation;
import com.example.projetmobile_ecoleenligne.classes.Serveur;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Signup extends AppCompatActivity {
    EditText nomET;
    EditText prenomET;
    EditText emailET;
    EditText numeroET;
    EditText paysET;
    EditText mdpET;
    EditText formationET;
    Button btnSignUp;
    Etudiant etudiant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nomET = (EditText) this.findViewById(R.id.nom);
        prenomET = (EditText) this.findViewById(R.id.prenom);
        emailET = (EditText) this.findViewById(R.id.email);
        numeroET = (EditText) this.findViewById(R.id.numero);
        paysET = (EditText) this.findViewById(R.id.pays);
        mdpET = (EditText) this.findViewById(R.id.mdp);
        // A traiter plus tard
        formationET = (EditText) this.findViewById(R.id.formation);
        btnSignUp = (Button) this.findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    inscription(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });;
    }
    public void inscription(View view) throws IOException {
        String nom = nomET.getText().toString();
        String prenom = prenomET.getText().toString();
        String email = emailET.getText().toString();
        String numeroString = numeroET.getText().toString();
        long numero = Long.parseLong(numeroString);
        String pays = paysET.getText().toString();
        String mdp = mdpET.getText().toString();
        //utilisateur étudiant crée
        etudiant = new Etudiant( nom, prenom, email, numero, mdp, pays,null);
        Gson gons = new Gson();
        String json = gons.toJson(etudiant);
        System.out.println(json);


        // Reste à inserer le tuple dans la bdd via le serveur
        Serveur serveur = new Serveur();
        String url = "http://192.168.1.74:8080/EcoleEnLigne/utilisateur/InscriptionEtudiant";
        serveur.PutRequest(url,json);

        Intent intention= new Intent(Signup.this, DashboardEtudiant.class);
        intention.putExtra("etudiant",etudiant.toString());


        startActivity(intention);


    }


}