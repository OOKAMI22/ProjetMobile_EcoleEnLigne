package com.example.projetmobile_ecoleenligne;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile_ecoleenligne.classes.CustomListAdapterFormation;
import com.example.projetmobile_ecoleenligne.classes.CustomRecyclerViewAdapter;
import com.example.projetmobile_ecoleenligne.classes.Formation;
import com.example.projetmobile_ecoleenligne.classes.Serveur;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormationsActivity extends AppCompatActivity {
    List<Formation> listeFormations = new ArrayList<>();
    public static final String LOG_TAG = "AndroidExample";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations_activity);
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
        //Affichage
        this.recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new CustomRecyclerViewAdapter(this,listeFormations));
        // RecyclerView scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }




}