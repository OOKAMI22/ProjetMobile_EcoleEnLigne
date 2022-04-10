package com.example.projetmobile_ecoleenligne.classes;

import java.util.ArrayList;

public class Formation {
    private long idF;
    private int duree;
    private float prix;
    ArrayList<Cours> cours = new ArrayList<Cours>();
    ArrayList<Examen> examens = new ArrayList<Examen>();

    public Formation( int duree, float prix) {
        this.setDuree(duree);
        this.setPrix(prix);
    }

    public void ajouterCours(Cours cours){
        this.cours.add(cours);
    }
    public void ajouterExamen(Examen examen){
        this.examens.add(examen);
    }

    public long getIdF() {
        return idF;
    }

    public void setIdF(long idF) {
        this.idF = idF;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
