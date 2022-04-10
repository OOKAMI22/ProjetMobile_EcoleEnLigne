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
}
