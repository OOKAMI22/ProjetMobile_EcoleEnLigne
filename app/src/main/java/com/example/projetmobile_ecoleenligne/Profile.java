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
import com.example.projetmobile_ecoleenligne.classes.Etudiant;
import com.example.projetmobile_ecoleenligne.classes.Formation;
import com.example.projetmobile_ecoleenligne.classes.Moderateur;
import com.example.projetmobile_ecoleenligne.classes.Utilisateur;

import java.io.IOException;

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
            Formation formation = extras.getParcelable("formation");
            //variableValue.setText(formation.getTitre());
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
                Toast.makeText(Profile.this,R.string.positif, Toast.LENGTH_LONG).show();
                System.out.println(nom.getText().toString());

                System.out.println(numero.getText().toString());

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