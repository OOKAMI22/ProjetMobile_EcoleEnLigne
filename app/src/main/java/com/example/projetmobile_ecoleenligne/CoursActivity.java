package com.example.projetmobile_ecoleenligne;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.Cours;

public class CoursActivity extends AppCompatActivity {
    Bundle extras;
    Cours cours = new Cours();
    TextView titre;
    TextView duree;
    TextView contenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);
        extras = getIntent().getExtras();
        cours = extras.getParcelable("cours");

        titre = findViewById(R.id.titre);
        duree = findViewById(R.id.duree);
        contenu = findViewById(R.id.contenu);

        titre.setText(cours.getTitre());
        duree.setText("nombre d'heures "+cours.getNbHeure()+" heures "+"idFormation = "+cours.getIdFormation());
        contenu.setText(cours.getContenu());

    }
}