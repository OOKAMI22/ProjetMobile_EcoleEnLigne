package com.example.projetmobile_ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetmobile_ecoleenligne.classes.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddExamenActivity extends AppCompatActivity {
    EditText q1;
    EditText q2;
    EditText r1;
    EditText r2;
    EditText titre;
    ArrayList<Formation> listeFormations = new ArrayList<>();
    Spinner formationET;
    Moderateur moderateur;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_examen);
        q1 = findViewById(R.id.Q1);
        q2 = findViewById(R.id.Q2);
        r1 = findViewById(R.id.R1);
        r2 = findViewById(R.id.R2);
        titre = findViewById(R.id.titre);
        formationET = findViewById(R.id.user);
        moderateur = new Moderateur();
        btn = findViewById(R.id.btn);


        Serveur serveur = new Serveur();
        String formationString = serveur.getListeFormation();

        Gson gson = new Gson();
        listeFormations = gson.fromJson(formationString,  new TypeToken<ArrayList<Formation>>(){}.getType());
        System.out.println(listeFormations.get(0).getTitre());

        ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, listeFormations);
        formationET.setAdapter(userAdapter);
        formationET.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get la value selectionée by la formation

                Formation f = (Formation) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ajouterExamen(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
    public Formation getFormationFromArray(ArrayList<Formation> listeFormations, String formation){
        for(Formation f : listeFormations){
            if (f.getTitre().equals(formation)){
                return f;
            }
        }
        return null;
    }
    public void ajouterExamen(View view) throws IOException {
        String quest1 = q1.getText().toString();
        String quest2 = q2.getText().toString();
        String rep1 = r1.getText().toString();
        String rep2 = r2.getText().toString();
        String title = titre.getText().toString();

        Map<String, String> listeReponse = new HashMap<>();
        listeReponse.put(quest1, rep1);
        listeReponse.put(quest2, rep2);

        String formationString = formationET.getSelectedItem().toString();
        Formation formation = getFormationFromArray(listeFormations,formationString);
        Examen exam = new Examen(title,moderateur.getId(),listeReponse,formation.getId());

        // Reste à inserer le tuple dans la bdd via le serveur
        Serveur serveur = new Serveur();

        serveur.addExamen(exam);

        Intent intention= new Intent(AddExamenActivity.this, MesExamensActivity.class);
        intention.putExtra("role","moderateur");
        startActivity(intention);
        Toast.makeText(AddExamenActivity.this, "Le cours a bien été ajouter !", Toast.LENGTH_LONG).show();

    }

}