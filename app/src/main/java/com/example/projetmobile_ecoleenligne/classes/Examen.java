package com.example.projetmobile_ecoleenligne.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Examen implements Parcelable {
    private long id;
    private String titre;
    private long id_moderateur;
    private Map<String, String> listeReponse = new HashMap<>();
    private long idFormation;

    public Examen(){}
    public Examen(String titre,long id_moderateur,Map<String, String> listeReponse,long id_formation) {
        this.titre = titre;
        this.id_moderateur = id_moderateur;
        this.listeReponse = listeReponse;
        this.idFormation = id_formation;
    }

    protected Examen(Parcel in) {
        id = in.readLong();
        titre = in.readString();
        id_moderateur = in.readLong();
        idFormation = in.readLong();
    }

    public static final Creator<Examen> CREATOR = new Creator<Examen>() {
        @Override
        public Examen createFromParcel(Parcel in) {
            return new Examen(in);
        }

        @Override
        public Examen[] newArray(int size) {
            return new Examen[size];
        }
    };

    public long getId(){return this.id;}
    public long getId_moderateur() {
        return this.id_moderateur;
    }
    public Map<String, String> getListeReponse() {
        return this.listeReponse;
    }
    public long getIdFormation() {
        return this.idFormation;
    }
    public String getTitre() {return this.titre;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(titre);
        parcel.writeLong(id_moderateur);
        parcel.writeLong(idFormation);
    }
}

