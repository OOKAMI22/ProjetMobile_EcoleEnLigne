package com.example.projetmobile_ecoleenligne;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

public class AddCoursActivity extends AppCompatActivity {
    EditText titreC;
    EditText contenuC;
    EditText nbHeuresC;
    Spinner formationC;
    Button btn;
    Cours cours;
    Moderateur moderateur;
    long id_formation;
    ArrayList<Formation> listeFormations = new ArrayList<>();


    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cours);

        titreC = findViewById(R.id.titre);
        contenuC = findViewById(R.id.contenu);
        nbHeuresC = findViewById(R.id.nbHeure);
        formationC = findViewById(R.id.formation);
        btn = findViewById(R.id.btn);

        // get mon intent
        extras = getIntent().getExtras();

        moderateur = new Moderateur();

        // Interroger la BD pour récuperer la liste des formations disponibles
        Serveur serveur = new Serveur();
        String json = "";
        String url = "http://192.168.1.74:8080/EcoleEnLigne/formation/GetFormation";


        String formationString = null;
        try {
            formationString = serveur.PostRequest(url,json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        listeFormations = gson.fromJson(formationString,  new TypeToken<ArrayList<Formation>>(){}.getType());
        System.out.println(listeFormations.get(0).getTitre());

        ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, listeFormations);
        formationC.setAdapter(userAdapter);
        formationC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get la value selectionée by la formation

                Formation f = (Formation) parent.getSelectedItem();
                System.out.println(f.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ajouterCours(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void ajouterCours(View view) throws IOException {
        String titre = titreC.getText().toString();
        String contenu = contenuC.getText().toString();

        String nbHeuresString= nbHeuresC.getText().toString();
        int nbHeures = Integer.parseInt(nbHeuresString);

        //cours crée
        String formationString = formationC.getSelectedItem().toString();
        Fonctions fun = new Fonctions();
        Formation formation = fun.getFormationFromArray(listeFormations,formationString);
        id_formation = formation.getId();
        System.out.println(id_formation);
        cours = new Cours( titre, contenu, nbHeures,moderateur.getId(),id_formation);
        Gson gons = new Gson();
        String json = gons.toJson(cours);
        System.out.println(json);


        // Reste à inserer le tuple dans la bdd via le serveur
        Serveur serveur = new Serveur();
        String url = "http://192.168.1.74:8080/EcoleEnLigne/formation/AddCours";
        serveur.PutRequest(url,json);

        Intent intention= new Intent(AddCoursActivity.this, MesCoursActivity.class);
        intention.putExtra("role","moderateur");
        startActivity(intention);
        Toast.makeText(AddCoursActivity.this, "Le cours a bien été ajouter !", Toast.LENGTH_LONG).show();

    }
}