package com.example.projetmobile_ecoleenligne.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Utilisateur implements Parcelable {
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

    protected Utilisateur(Parcel in) {
        id = in.readLong();
        nom = in.readString();
        prenom = in.readString();
        email = in.readString();
        numero = in.readLong();
        mot_de_passe = in.readString();
        pays = in.readString();
    }

    public static final Creator<Utilisateur> CREATOR = new Creator<Utilisateur>() {
        @Override
        public Utilisateur createFromParcel(Parcel in) {
            return new Utilisateur(in);
        }

        @Override
        public Utilisateur[] newArray(int size) {
            return new Utilisateur[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeString(email);
        parcel.writeLong(numero);
        parcel.writeString(mot_de_passe);
        parcel.writeString(pays);
    }
}
