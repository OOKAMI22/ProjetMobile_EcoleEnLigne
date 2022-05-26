package com.example.projetmobile_ecoleenligne;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile_ecoleenligne.classes.CustomRecyclerViewAdapter;
import com.example.projetmobile_ecoleenligne.classes.Formation;
import com.example.projetmobile_ecoleenligne.classes.Serveur;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        String formationString = serveur.getListeFormation();

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