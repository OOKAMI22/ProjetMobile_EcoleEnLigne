package com.example.projetmobile_ecoleenligne;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetmobile_ecoleenligne.classes.Examen;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.*;

public class ExamenActivity extends AppCompatActivity {
    Bundle extras;
    TextView titre;
    TextView formation;
    TextView question;
    EditText reponse;
    Button btn;
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
        //Examen exam = extras.getParcelable("examen");
        String json = extras.getString("examen");
        Gson gson = new Gson();
        Examen exam = gson.fromJson(json, new TypeToken<Examen>() {
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
                Envoyer(listeQuestion,listeValues,view);

            }
        });
    }


    public void Envoyer(List<String> listeQuestion,List<String> listeValues, View view) {
        String laReponse = listeValues.get(position);

        System.out.println(laReponse);
        System.out.println(reponse.getText().toString());

        if (reponse.getText().toString().equals(laReponse)) {
            System.out.println("BIngoooooooooooooooooo");
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
        }

    }
}