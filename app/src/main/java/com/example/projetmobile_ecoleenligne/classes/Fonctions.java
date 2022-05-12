package com.example.projetmobile_ecoleenligne.classes;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Fonctions {
    public Fonctions(){}
    public Formation getFormationFromArray(ArrayList<Formation> listeFormations, String formation){
        for(Formation f : listeFormations){
            if (f.getTitre().equals(formation)){
                return f;
            }
        }
        return null;
    }


}
