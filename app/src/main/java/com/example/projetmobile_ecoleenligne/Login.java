package com.example.projetmobile_ecoleenligne;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.Etudiant;
import com.example.projetmobile_ecoleenligne.classes.Moderateur;
import com.example.projetmobile_ecoleenligne.classes.Serveur;
import com.example.projetmobile_ecoleenligne.classes.Utilisateur;
import com.google.gson.Gson;

import java.io.IOException;

public class Login extends AppCompatActivity {
    EditText emailET;
    EditText mdpET;
    Button btnLogin;
    Utilisateur user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET = (EditText) this.findViewById(R.id.email);
        mdpET = (EditText) this.findViewById(R.id.mdp);
        btnLogin = (Button) this.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    connexion(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void connexion(View view) throws IOException {
        String email = emailET.getText().toString();
        String mdp = mdpET.getText().toString();
        user = new Utilisateur();
        user.setEmail(email);
        user.setMdp(mdp);

        // Interroger la bdd sur les 2 string
        Serveur serveur = new Serveur();
        serveur.getEtudiant(email,mdp);
        // to Json
        Gson gson = new Gson();
        String role = "";

        if(!serveur.getEtudiant(email,mdp).equals("")) {
            String userString =  serveur.getEtudiant(email,mdp);
            System.out.println(userString);
            Etudiant userConnected = gson.fromJson(userString, Etudiant.class);

            role = "etudiant";
            // Envoyer les information de l'utilisateur ?? l'intent suivante
            Intent intention= new Intent(Login.this, AcceuilActivity.class);

            intention.putExtra("user", userConnected);
            intention.putExtra("userInfo",userConnected.toString());
            intention.putExtra("role",role);
            System.out.println(""+userConnected.getFormation());
            intention.putExtra("formation",""+userConnected.getFormation());
            startActivity(intention);
        }

        else{
            if(!serveur.getModerateur(email,mdp).equals("")) {
                String userString = serveur.getModerateur(email,mdp);
                System.out.println(userString);
                Moderateur userConnected = gson.fromJson(userString, Moderateur.class);
                role = "moderateur";

                // Envoyer les information de l'utilisateur ?? l'intent suivante
                Intent intention = new Intent(Login.this, AcceuilActivity.class);
                intention.putExtra("user", userConnected);
                intention.putExtra("userInfo", userConnected.toString());
                intention.putExtra("role", role);
                intention.putExtra("grade", userConnected.getGradeString());
                System.out.println("Login " + userConnected.getGradeString());
                startActivity(intention);
            }
            else{
                TextView erreur = findViewById(R.id.erreur);
                String messageErreur = "Adresse mail ou mot de passe incorrecte";
                erreur.setText(messageErreur);
            }
        }




    }
}