package com.example.projetmobile_ecoleenligne.classes;

public class Cours {
    private long id;
    private String titre;
    private String contenu;
    private int nbHeure;
    private Moderateur mod;
    private Formation formation;

    public Cours(String titre, String contenu, int nbHeure, Moderateur mod, Formation formation) {
        this.titre = titre;
        this.contenu = contenu;
        this.nbHeure = nbHeure;
        this.mod = mod;
        this.formation = formation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getNbHeure() {
        return nbHeure;
    }

    public void setNbHeure(int nbHeure) {
        this.nbHeure = nbHeure;
    }

    public Moderateur getMod() {
        return mod;
    }

    public void setMod(Moderateur mod) {
        this.mod = mod;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
