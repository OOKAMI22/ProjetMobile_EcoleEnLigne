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

public class MesExamensActivity extends AppCompatActivity {
    ArrayList<Examen> listeExamens = new ArrayList<>();
    Button btn;
    Bundle extras;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_examens);
        btn = findViewById(R.id.btn);
        // get mon intent
        extras = getIntent().getExtras();
        role = extras.getString("role");

        // Interroger la BD pour récuperer la liste des formations disponibles
        Serveur serveur = new Serveur();
        Gson gson = new Gson();
        String json = "";
        String url = "http://192.168.1.74:8080/EcoleEnLigne/formation";
        String examenString = "";

        if(role.equals("moderateur")){
            url += "/GetExamens";
            try {
                examenString = serveur.PostRequest(url,json);
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
            url += "/GetMesExamens";
            try {
                examenString = serveur.PostRequest(url,json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        listeExamens = gson.fromJson(examenString,  new TypeToken<ArrayList<Examen>>(){}.getType());
        System.out.println(listeExamens.get(0).getTitre());

        afficheExamens(listeExamens);
    }
    public void afficheExamens(ArrayList<Examen> listeExamens) {
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapterExamen(this,listeExamens));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Examen examen = (Examen) o;
                Toast.makeText(MesExamensActivity.this, "Selected :" + " " + examen, Toast.LENGTH_LONG).show();
            }
        });
    }
    public void addCours(View view) {
        Intent intention= new Intent(MesExamensActivity.this, AddCoursActivity.class);
        startActivity(intention);
    }

}