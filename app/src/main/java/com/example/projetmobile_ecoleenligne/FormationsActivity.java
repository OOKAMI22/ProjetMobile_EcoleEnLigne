package com.example.projetmobile_ecoleenligne;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.CustomListAdapterFormation;
import com.example.projetmobile_ecoleenligne.classes.Formation;
import com.example.projetmobile_ecoleenligne.classes.Serveur;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.util.ArrayList;

public class FormationsActivity extends AppCompatActivity {
    ArrayList<Formation> listeFormations = new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations_activity);
        // Interroger la BD pour récuperer la liste des formations disponibles
        Serveur serveur = new Serveur();
        String json = "";
        String url = "http://192.168.1.74:8080/EcoleEnLigne/formation/GetFormation";
        System.out.println("*********************");

        String formationString = null;
       try {
            formationString = serveur.PostRequest(url,json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        listeFormations = gson.fromJson(formationString,  new TypeToken<ArrayList<Formation>>(){}.getType());
        System.out.println(listeFormations.get(0).getTitre());
        afficheFormations( listeFormations);
    }
    public void afficheFormations(ArrayList<Formation> listeFormations) {
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapterFormation(this,listeFormations));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Formation formation = (Formation) o;
                Toast.makeText(FormationsActivity.this, "Selected :" + " " + formation, Toast.LENGTH_LONG).show();
            }
        });
    }



}