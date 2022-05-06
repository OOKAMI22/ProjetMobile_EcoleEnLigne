package com.example.projetmobile_ecoleenligne;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.*;
import com.google.gson.Gson;

import java.io.IOException;

public class AddCoursActivity extends AppCompatActivity {
    EditText titreC;
    EditText contenuC;
    EditText nbHeuresC;
    Button btn;
    Cours cours;
    Moderateur moderateur;
    Formation formation;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cours);

        titreC = findViewById(R.id.titre);
        contenuC = findViewById(R.id.contenu);
        nbHeuresC = findViewById(R.id.nbHeure);
        btn = findViewById(R.id.btn);

        // get mon intent
        extras = getIntent().getExtras();
        String[] mod = extras.getString("user").split("$");
        moderateur = new Moderateur(mod[0],mod[1],mod[2],Long.parseLong(mod[3]),mod[4],mod[5]);
        formation = new Formation();

    }
    public void ajouterCours(View view) throws IOException {
        String titre = titreC.getText().toString();
        String contenu = contenuC.getText().toString();

        String nbHeuresString= nbHeuresC.getText().toString();
        int nbHeures = Integer.parseInt(nbHeuresString);

        //cours crée
        cours = new Cours( titre, contenu, nbHeures,moderateur,formation);
        Gson gons = new Gson();
        String json = gons.toJson(cours);
        System.out.println(json);


        // Reste à inserer le tuple dans la bdd via le serveur
        Serveur serveur = new Serveur();
        String url = "http://192.168.1.74:8080/EcoleEnLigne/formation/AddCours";
        serveur.PutRequest(url,json);

        Intent intention= new Intent(AddCoursActivity.this, AcceuilActivity.class);
        startActivity(intention);

    }
}