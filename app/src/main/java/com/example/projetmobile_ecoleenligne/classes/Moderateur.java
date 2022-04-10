package com.example.projetmobile_ecoleenligne.classes;

import java.util.ArrayList;

public class Moderateur extends Utilisateur{
    Grade grade;
    ArrayList<Cours> cours = new ArrayList<Cours>();



    public Moderateur( String nom, String prenom, String email, long numero, String mdp, String pays) {
        super(nom, prenom, email, numero, mdp, pays);
        this.grade = Grade.Nouveau;
    }
    public Cours creerCours(String titre, String contenu,int nbHeures,Formation formation){
        Cours cours = new Cours(titre, contenu, nbHeures,this, formation);
        this.cours.add(cours);
        formation.ajouterCours(cours);
        return cours;
    }
    /*public Examen creerExamen(String titre, String contenu,int nbHeures,Formation formation){
        Examen cours = new Cours(titre, contenu, nbHeures,this, formation);
        this.cours.add(cours);
        formation.ajouterCours(cours);
        return cours;

    }*/

}
