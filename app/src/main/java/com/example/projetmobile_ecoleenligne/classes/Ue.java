package com.example.projetmobile_ecoleenligne.classes;

import java.util.ArrayList;

public class Ue {
    private long id;
    private String intitule;
    private int ects;
    ArrayList<Cours> cours = new ArrayList<Cours>();


    public void addCours(Cours cours) {
       this.cours.add(cours);
    }
}
