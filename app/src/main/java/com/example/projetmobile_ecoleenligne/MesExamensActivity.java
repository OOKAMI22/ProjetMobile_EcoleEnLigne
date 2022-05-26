package com.example.projetmobile_ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetmobile_ecoleenligne.classes.CustomListAdapterExamen;
import com.example.projetmobile_ecoleenligne.classes.Examen;
import com.example.projetmobile_ecoleenligne.classes.Serveur;
import com.example.projetmobile_ecoleenligne.classes.Utilisateur;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MesExamensActivity extends AppCompatActivity {
    ArrayList<Examen> listeExamens = new ArrayList<>();
    Button btn;
    Bundle extras;
    String role;
    Utilisateur user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_examens);
        btn = findViewById(R.id.btn);
        // get mon intent
        extras = getIntent().getExtras();
        role = extras.getString("role");
        user = extras.getParcelable("user");

        // Interroger la BD pour récuperer la liste des formations disponibles
        Serveur serveur = new Serveur();
        Gson gson = new Gson();
        String examenString = "";

        if(role.equals("moderateur")){
            examenString = serveur.getExamens();

            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addExamen(view);
                }
            });


        }
        else{
            String formation = extras.getString("formation");
            examenString = serveur.getMesExamens(Long.parseLong(formation));
        }
        System.out.println(examenString);
        listeExamens = gson.fromJson(examenString,  new TypeToken<ArrayList<Examen>>(){}.getType());
        System.out.println(listeExamens.get(0).getTitre());
        for(Examen e : listeExamens){
            System.out.println("Titre " + e.getTitre()+ " Formation "+e.getIdFormation());
        }

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
                Toast.makeText(MesExamensActivity.this, "Selected :" + " " + examen.getTitre(), Toast.LENGTH_LONG).show();
                Intent intention= new Intent(MesExamensActivity.this, ExamenActivity.class);
                //intention.putExtra("examen",examen); on ne peut pas car listeReponse n'est pas parceable
                Gson gson = new Gson();
                String json = gson.toJson(examen);
                intention.putExtra("examen",json);
                intention.putExtra("user",user);
                System.out.println(examen.getListeReponse());
                startActivity(intention);
            }
        });
    }
    public void addExamen(View view) {
        Intent intention= new Intent(MesExamensActivity.this, AddExamenActivity.class);
        intention.putExtra("user",user);
        intention.putExtra("role",user);
        intention.putExtra("grade",user);
        intention.putExtra("formation",user);
        startActivity(intention);
    }

}