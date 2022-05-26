package com.example.projetmobile_ecoleenligne.classes;

public class Etudiant extends Utilisateur{
    private float moyenne;
    private long id_formation;
    private int avancement;


    public Etudiant( String nom, String prenom, String email, long numero, String mdp, String pays,Formation formation) {
        super( nom, prenom, email, numero, mdp, pays);
        this.setMoyenne(0);
        this.setFormation(formation.getId());
    }
    public Etudiant( String nom, String prenom, String email, long numero, String mdp, String pays,long formation) {
        super( nom, prenom, email, numero, mdp, pays);
        this.setMoyenne(0);
        this.setFormation(formation);
    }
    public Etudiant() {
        super();
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public long getFormation() {
        return this.id_formation;
    }

    public void setFormation(long formation) {
        this.id_formation = formation;
    }



}
