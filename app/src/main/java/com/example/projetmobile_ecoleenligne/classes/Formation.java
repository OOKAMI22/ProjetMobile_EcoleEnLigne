package com.example.projetmobile_ecoleenligne.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Formation implements Parcelable {
    private long id;

    protected Formation(Parcel in) {
        id = in.readLong();
        titre = in.readString();
        description = in.readString();
        duree = in.readInt();
        prix = in.readFloat();
    }

    public static final Creator<Formation> CREATOR = new Creator<Formation>() {
        @Override
        public Formation createFromParcel(Parcel in) {
            return new Formation(in);
        }

        @Override
        public Formation[] newArray(int size) {
            return new Formation[size];
        }
    };

    public String getTitre() {
        return this.titre;
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

    public Formation(){

    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(titre);
        parcel.writeString(description);
        parcel.writeInt(duree);
        parcel.writeFloat(prix);
    }
}
