package com.example.projetmobile_ecoleenligne.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Cours implements Parcelable {

    private long id;
    private String titre;
    private String contenu;
    private int nbHeure;
    private long id_moderateur;
    private long id_formation;

    public Cours( String titre, String contenu,int nbheure,long id_moderateur,long id_formation) {
        this.titre = titre;
        this.contenu = contenu;
        this.nbHeure = nbheure;
        this.id_formation = id_formation;
        this.id_moderateur = id_moderateur;
    }

    public Cours() {}

    protected Cours(Parcel in) {
        id = in.readLong();
        titre = in.readString();
        contenu = in.readString();
        nbHeure = in.readInt();
        id_moderateur = in.readLong();
        id_formation = in.readLong();
    }

    public static final Creator<Cours> CREATOR = new Creator<Cours>() {
        @Override
        public Cours createFromParcel(Parcel in) {
            return new Cours(in);
        }

        @Override
        public Cours[] newArray(int size) {
            return new Cours[size];
        }
    };

    public long getId() {
        return this.id;
    }
    public String getTitre() {
        return this.titre;
    }
    public String getContenu() {
        return this.contenu;
    }
    public int getNbHeure() {
        return this.nbHeure;
    }
    public long getIdFormation() {
        return this.id_formation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(titre);
        parcel.writeString(contenu);
        parcel.writeInt(nbHeure);
        parcel.writeLong(id_moderateur);
        parcel.writeLong(id_formation);
    }
}