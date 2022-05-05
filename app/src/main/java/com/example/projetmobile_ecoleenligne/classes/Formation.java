package com.example.projetmobile_ecoleenligne.classes;

import java.util.ArrayList;

public class Formation {
    private long id;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String titre;
    private String description;
    private int duree;
    private float prix;
    ArrayList<Cours> cours = new ArrayList<Cours>();
    ArrayList<Examen> examens = new ArrayList<Examen>();

    public Formation(String titre, String description, float prix, int duree) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.duree = duree;
    }

    public void ajouterCours(Cours cours){
        this.cours.add(cours);
    }
    public void ajouterExamen(Examen examen){
        this.examens.add(examen);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
