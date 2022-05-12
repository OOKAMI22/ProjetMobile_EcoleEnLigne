package com.example.projetmobile_ecoleenligne;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    Utilisateur user;
    Bundle extras;
    TextView nom;
    TextView email;
    TextView numero;
    TextView variable;
    TextView variableValue;
    String role;
    ImageView logoutImg;
    TextView changerMdp;
    String formationString = "";
    List<Formation> listeFormations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // get mon intent
        extras = getIntent().getExtras();
        role = extras.getString("role");

        user =  getIntent().getParcelableExtra("user");
        // get les text View
        nom = findViewById(R.id.textView);
        email = findViewById(R.id.textView2);
        numero = findViewById(R.id.textnumtel2);
        variable = findViewById(R.id.textformation);
        variableValue = findViewById(R.id.textformation2);

        //Saisie de l'information
        nom.setText(user.getPrenom()+" "+user.getNom());
        email.setText(user.getEmail());
        numero.setText(String.valueOf(user.getNumero()));

        if(role.equals("moderateur")){
            variable.setText("Grade ");
            String grade = extras.getString("grade");
            variableValue.setText(grade);
            System.out.println("Profile "+grade);
        }
        else{
            //Formation formation = extras.getParcelable("formation");
            formationString = extras.getString("formation");
            Serveur serveur = new Serveur();
            String json = "";
            String url = "http://192.168.1.74:8080/EcoleEnLigne/formation/GetFormation";


            String formationString = null;
            try {
                formationString = serveur.PostRequest(url,json);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String formation = " Python";
            Gson gson = new Gson();
            listeFormations = gson.fromJson(formationString,  new TypeToken<ArrayList<Formation>>(){}.getType());
            for(Formation f : listeFormations){
                if ((""+f.getId()).equals(formationString)){
                    formation = f.getTitre();
                }
            }
            variableValue.setText(formation);
        }

        logoutImg = findViewById(R.id.logout);
        logoutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    deconnexion();
            }
        });;
        changerMdp = findViewById(R.id.texteditmdp);
        changerMdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChangerMdp();
            }
        });;
    }
    public void deconnexion(){
        Intent intention = new Intent(Profile.this, Intro.class);
        startActivity(intention);
    }
    public void setChangerMdp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("êtes vous sûr ?");
        final EditText edittext = new EditText(this);
        builder.setView(edittext);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.valider, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int id){
                String mdpValue = edittext.getText().toString();
                //Toast.makeText(Profile.this,R.string.positif, Toast.LENGTH_LONG).show();
                user.setMdp(mdpValue);
                String url = "http://192.168.1.74:8080/EcoleEnLigne/utilisateur/ChangerMdp";
                Gson gson = new Gson();
                String json = gson.toJson(user);
                Serveur serveur = new Serveur();
                try {
                    serveur.PutRequest(url,json);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //mon intent
                /*
                Intent intention= new Intent(MainActivity.this, MainActivity2.class);
                intention.putExtra("nom", nom.getText().toString());
                intention.putExtra("numero", numero.getText().toString());

                startActivity(intention);

                 */
            }

        });
        builder.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int id) {
                Toast.makeText(Profile.this, R.string.negatif, Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}