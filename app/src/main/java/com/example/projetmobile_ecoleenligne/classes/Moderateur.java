package com.example.projetmobile_ecoleenligne.classes;

import java.util.ArrayList;

import static com.example.projetmobile_ecoleenligne.classes.Grade.*;


public class Moderateur extends Utilisateur {

    private Grade grade;
    private ArrayList<Cours> cours = new ArrayList<Cours>();

    public Grade getGrade() {
        return grade;
    }
    public String getGradeString() {

        switch(this.grade){

            case Createur:
                return "Createur";

            case DataScientist:
               return " Data Scientist";

            case Mathematicien:
                return "Mathematicien";
            case ProPython:
                return "Pro Python";

            default:
                return "Nouveau";
        }

    }

    public ArrayList<Cours> getCours() {
        return cours;
    }

    public Moderateur( ) {
        super();
        this.grade = Nouveau;
    }

    public Moderateur( String nom, String prenom, String email, long numero, String mdp, String pays) {
        super(nom, prenom, email, numero, mdp, pays);
        this.grade = Nouveau;
    }
    /*
    public Cours creerCours(String titre, String contenu,int nbHeures,long formation){
        Cours cours = new Cours(titre, contenu, nbHeures,this, formation);
        this.cours.add(cours);
        //formation.ajouterCours(cours);
        return cours;
    }
    public Examen creerExamen(String titre, String contenu,int nbHeures,Formation formation){
        Examen cours = new Cours(titre, contenu, nbHeures,this, formation);
        this.cours.add(cours);
        formation.ajouterCours(cours);
        return cours;

    }*/

}
