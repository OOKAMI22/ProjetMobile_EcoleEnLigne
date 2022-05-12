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
    private long id_formation;

    public Examen(){}
    public Examen(String titre,long id_moderateur,Map<String, String> listeReponse,long id_formation) {
        this.titre = titre;
        this.id_moderateur = id_moderateur;
        this.listeReponse = listeReponse;
        this.id_formation = id_formation;
    }
    public long getId_moderateur() {
        return this.id_moderateur;
    }
    public Map<String, String> getListeReponse() {
        return this.listeReponse;
    }
    public long getIdFormation() {
        return this.id_formation;
    }
    public String getTitre() {return this.titre;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
