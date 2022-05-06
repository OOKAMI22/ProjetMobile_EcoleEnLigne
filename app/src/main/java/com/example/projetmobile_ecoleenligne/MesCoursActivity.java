package com.example.projetmobile_ecoleenligne;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

public class MesCoursActivity extends AppCompatActivity {
    ArrayList<Cours> listeCours = new ArrayList<>();
    Bundle extras;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_cours);
        // get mon intent
        extras = getIntent().getExtras();
        role = extras.getString("role");

        // Interroger la BD pour récuperer la liste des formations disponibles
        Serveur serveur = new Serveur();
        Gson gson = new Gson();
        String json = "";
        String url = "http://192.168.1.74:8080/EcoleEnLigne/formation";
        String coursString = "";

        if(role.equals("moderateur")){
            url += "/GetCours";
            try {
                coursString = serveur.PostRequest(url,json);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else{
            Formation formation = extras.getParcelable("formation");
            //Formation to Json
            json = gson.toJson(formation);
            url += "/GetMesCours";
            try {
                coursString = serveur.PostRequest(url,json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        listeCours = gson.fromJson(coursString,  new TypeToken<ArrayList<Formation>>(){}.getType());
        System.out.println(listeCours);
        afficheCours(listeCours);
    }
    public void afficheCours(ArrayList<Cours> listeCours) {
        /*final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapterCours(this,listeCours));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Cours cours = (Cours) o;
                Toast.makeText(MesCoursActivity.this, "Selected :" + " " + cours, Toast.LENGTH_LONG).show();
            }
        });

         */
    }
}