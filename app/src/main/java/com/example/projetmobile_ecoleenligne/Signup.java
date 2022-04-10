package com.example.projetmobile_ecoleenligne;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.Etudiant;
import com.example.projetmobile_ecoleenligne.classes.Formation;

public class Signup extends AppCompatActivity {
    EditText nomET;
    EditText prenomET;
    EditText emailET;
    EditText numeroET;
    EditText paysET;
    EditText mdpET;
    EditText formationET;
    Button btnSignUp;
    Etudiant etudiant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nomET = (EditText) this.findViewById(R.id.nom);
        prenomET = (EditText) this.findViewById(R.id.prenom);
        emailET = (EditText) this.findViewById(R.id.email);
        numeroET = (EditText) this.findViewById(R.id.numero);
        paysET = (EditText) this.findViewById(R.id.pays);
        mdpET = (EditText) this.findViewById(R.id.mdp);
        // A traiter plus tard
        formationET = (EditText) this.findViewById(R.id.formation);
        btnSignUp = (Button) this.findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inscription(view);
            }
        });;
    }
    public void inscription(View view){
        String nom = nomET.getText().toString();
        String prenom = prenomET.getText().toString();
        String email = emailET.getText().toString();
        String numeroString = numeroET.getText().toString();
        long numero = Long.parseLong(numeroString);
        String pays = paysET.getText().toString();
        String mdp = mdpET.getText().toString();
        //utilisateur étudiant crée
        etudiant = new Etudiant( nom, prenom, email, numero, mdp, pays);
        // Reste à inserer le tuple dans la bdd via le serveur

    }
}