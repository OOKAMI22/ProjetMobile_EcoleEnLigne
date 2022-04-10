package com.example.projetmobile_ecoleenligne.classes;

public class Annonce {
    private long id;
    TypeAnnonce type;
    String contenu;

    public Annonce(TypeAnnonce type, String contenu) {
        this.type = type;
        this.contenu = contenu;
    }

    public long getId() {
        return id;
    }


    public TypeAnnonce getType() {
        return type;
    }

    public void setType(TypeAnnonce type) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
