package com.example.projetmobile_ecoleenligne;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.Formation;
import com.example.projetmobile_ecoleenligne.classes.Serveur;
import com.example.projetmobile_ecoleenligne.classes.FormationList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Formations extends AppCompatActivity {
    ArrayList<Formation> listeFormations = new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations);
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
        /*
        //************* ObjectMapper *************
        ObjectMapper mapper = new ObjectMapper();
        try {
            listeFormations = Arrays.asList(mapper.readValue(formationString, Formation[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(listeFormations);

        System.out.println("*********************");
         */
        /*
        //************* Gson via classeIntermediare *************
        Gson gson = new Gson();
         FormationList nameList = gson.fromJson(formationString, FormationList.class);

        List<Formation> list = nameList.getList();

         */
        Gson gson = new Gson();
        listeFormations = gson.fromJson(formationString,  new TypeToken<ArrayList<Formation>>(){}.getType());
        System.out.println(listeFormations.get(0).getTitre());
        afficheFormations( listeFormations);
    }
    public void afficheFormations(ArrayList<Formation> listeFormations) {

        for (Formation formation : listeFormations) {

        }


    }

}