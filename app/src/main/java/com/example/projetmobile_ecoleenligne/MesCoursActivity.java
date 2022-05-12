package com.example.projetmobile_ecoleenligne;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    Button btn;
    Bundle extras;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_cours);
        btn = findViewById(R.id.btn);
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
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addCours(view);
                }
            });


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
        listeCours = gson.fromJson(coursString,  new TypeToken<ArrayList<Cours>>(){}.getType());
        System.out.println(listeCours.get(0).getContenu());

        afficheCours(listeCours);
    }
    public void afficheCours(ArrayList<Cours> listeCours) {
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapterCours(this,listeCours));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Cours cours = (Cours) o;
                Toast.makeText(MesCoursActivity.this, "Selected :" + " " + cours, Toast.LENGTH_LONG).show();
                Intent intention= new Intent(MesCoursActivity.this, CoursActivity.class);
                intention.putExtra("cours",cours);
                startActivity(intention);
            }
        });
    }
    public void addCours(View view) {
        Intent intention= new Intent(MesCoursActivity.this, AddCoursActivity.class);
        startActivity(intention);
    }
}