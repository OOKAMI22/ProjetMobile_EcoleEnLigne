package com.example.projetmobile_ecoleenligne;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projetmobile_ecoleenligne.classes.Formation;

import java.util.ArrayList;

public class AddExamenActivity extends AppCompatActivity {
    EditText q1;
    EditText q2;
    EditText r1;
    EditText r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_examen);
        q1 = findViewById(R.id.Q1);
        q2 = findViewById(R.id.Q2);
        r1 = findViewById(R.id.R1);
        r2= findViewById(R.id.R2);
    }
    public Formation getFormationFromArray(ArrayList<Formation> listeFormations, String formation){
        for(Formation f : listeFormations){
            if (f.getTitre().equals(formation)){
                return f;
            }
        }
        return null;
    }

}