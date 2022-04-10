package com.example.projetmobile_ecoleenligne.classes;

public class Etudiant extends Utilisateur{
    private float moyenne;
    private Formation formation;
    private int avancement;


    public Etudiant( String nom, String prenom, String email, long numero, String mdp, String pays,Formation formation) {
        super( nom, prenom, email, numero, mdp, pays);
        this.setMoyenne(0);
        this.setFormation(formation);
    }
    public Etudiant( String nom, String prenom, String email, long numero, String mdp, String pays) {
        super( nom, prenom, email, numero, mdp, pays);
        this.setMoyenne(0);
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
