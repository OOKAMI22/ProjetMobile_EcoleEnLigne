package com.example.projetmobile_ecoleenligne.classes;

import java.util.ArrayList;

public class Utilisateur {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private long numero;
    private String mot_de_passe;
    private String pays;

    public Utilisateur( ) {
    }

    public Utilisateur( String nom, String prenom, String email, long numero, String mdp, String pays) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setEmail(email);
        this.setMdp(email);
        this.setNumero(numero);
        this.setMdp(mdp);
        this.setPays(pays);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getMdp() {
        return mot_de_passe;
    }

    public void setMdp(String mdp) {
        this.mot_de_passe = mdp;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String toString(){
        return this.getNom()+"$"+this.getPrenom()+"$"+this.getEmail()+"$"+this.getNumero()+"$"+this.getPays();
    }

}
