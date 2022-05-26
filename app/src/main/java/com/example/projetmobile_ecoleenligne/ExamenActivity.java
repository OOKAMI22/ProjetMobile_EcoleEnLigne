package com.example.projetmobile_ecoleenligne;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetmobile_ecoleenligne.classes.Examen;
import com.example.projetmobile_ecoleenligne.classes.Note;
import com.example.projetmobile_ecoleenligne.classes.Serveur;
import com.example.projetmobile_ecoleenligne.classes.Utilisateur;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamenActivity extends AppCompatActivity {
    Bundle extras;
    TextView titre;
    TextView formation;
    TextView question;
    EditText reponse;
    Button btn;
    Examen exam = new Examen();
    Utilisateur user;
    Note note;
    int point = 0;
    int position = 0;
    private Map<String, String> listeReponse = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);
        titre = findViewById(R.id.titre);
        formation = findViewById(R.id.formation);
        question = findViewById(R.id.question);
        reponse = findViewById(R.id.reponse);
        btn = findViewById(R.id.btn);

        extras = getIntent().getExtras();
        user = extras.getParcelable("user");
        //Examen exam = extras.getParcelable("examen");
        String json = extras.getString("examen");
        Gson gson = new Gson();
        exam = gson.fromJson(json, new TypeToken<Examen>() {
        }.getType());
        titre.setText(exam.getTitre());
        formation.setText(String.valueOf(exam.getIdFormation()));
        listeReponse = exam.getListeReponse();
        System.out.println(listeReponse);

        List<String> listeQuestion = new ArrayList<>();


        for (String q : listeReponse.keySet()) {
            System.out.println(q);
            listeQuestion.add(q);
        }
        List<String> listeValues = new ArrayList<>();
        for (String v : listeReponse.values()) {
            System.out.println(v);
            listeValues.add(v);
        }

        question.setText(listeQuestion.get(0));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Envoyer(listeQuestion,listeValues,view);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public void Envoyer(List<String> listeQuestion,List<String> listeValues, View view) throws IOException {
        String laReponse = listeValues.get(position);

        System.out.println(laReponse);
        System.out.println(reponse.getText().toString());

        if (reponse.getText().toString().equals(laReponse)) {
            System.out.println("Bingoooooooooooooooooo");
            point++;
        }
        position++;
        if (position < listeQuestion.size()) {
            question.setText(listeQuestion.get(position));

        }
        else {
            System.out.println("c'est fini à la position : " + position);
            question.setText("Voici votre score "+point);
            reponse.setVisibility(View.GONE);
            btn.setVisibility(View.GONE);
            Note note = new Note(user.getId(),exam.getId(),point,position);
            Serveur serveur = new Serveur();
            serveur.addNote(note);
        }

    }
}