package com.example.projetmobile_ecoleenligne.classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
    private String url = "http://172.20.10.3:8080/EcoleEnLigne/";
    public Serveur(){}
    public void PutRequest(String url,String json) throws IOException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL adress = null;
                    try {
                        adress = new URL(url);
                        HttpURLConnection httpCon = (HttpURLConnection) adress.openConnection();
                        httpCon.setDoOutput(true);
                        httpCon.setRequestMethod("PUT");
                        httpCon.setRequestProperty("Content-Type", "application/json");
                        httpCon.setRequestProperty("Accept", "application/json");
                        OutputStreamWriter out = new OutputStreamWriter(
                                httpCon.getOutputStream());
                        out.write(json);
                        out.close();
                        httpCon.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String PostRequest(String url,String json) throws IOException {
        final String[] result = {""};
        Thread thread = new Thread(new Runnable() {
            URL adresse = null;

            @Override
            public void run() {
                try  {
                    try {
                        adresse = new URL(url);
                        HttpURLConnection httpCon = (HttpURLConnection) adresse.openConnection();
                        httpCon.setDoOutput(true);
                        httpCon.setRequestMethod("POST");
                        httpCon.setRequestProperty("Content-Type", "application/json");
                        httpCon.setRequestProperty("Accept", "application/json");
                        OutputStreamWriter out = new OutputStreamWriter (
                                httpCon.getOutputStream());
                        out.write(json);
                        out.flush();
                        String builtResponse = "";
                        String line ="";
                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                        while ((line = reader.readLine()) != null) {
                            result[0] += line;
                        }
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result[0];
    }

    public Formation getFormationById(long id){
        Gson gson = new Gson();
        String json = ""+id;

        String formationString = gson.toJson(id);
        String url = this.url+"formation/FormationById";
        try {
            formationString = this.PostRequest(url,json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Formation> listeFormation = gson.fromJson(formationString,  new TypeToken<ArrayList<Formation>>(){}.getType());
        return listeFormation.get(0);
    }
    public void addNote(Note note) throws IOException {
        Gson gons = new Gson();
        String json = gons.toJson(note);
        System.out.println(json);


        // Reste à inserer le tuple dans la bdd via le serveur
        Serveur serveur = new Serveur();
        String url = this.url+"formation/AddNote";
        serveur.PutRequest(url,json);
    }

    public String getEtudiant(String email,String mdp) throws IOException {
        Utilisateur user = new Utilisateur();
        user.setEmail(email);
        user.setMdp(mdp);
        // to Json
        Gson gson = new Gson();
        String json = gson.toJson(user);
        System.out.println(json);
        String role = "";
        // Interroger la bdd sur les 2 string
        Serveur serveur = new Serveur();
        String url = this.url + "utilisateur/ConnexionEtudiant";

        return this.PostRequest(url, json);
    }

    public String getModerateur(String email,String mdp) throws IOException {
        Utilisateur user = new Utilisateur();
        user.setEmail(email);
        user.setMdp(mdp);
        // to Json
        Gson gson = new Gson();
        String json = gson.toJson(user);
        System.out.println(json);
        String role = "";
        // Interroger la bdd sur les 2 string
        String url = this.url + "utilisateur/ConnexionModerateur";

        return this.PostRequest(url, json);
    }
    public void putEtudiant(Etudiant e) throws IOException {
        String url = this.url + "utilisateur/InscriptionEtudiant";
        Gson gons = new Gson();
        String json = gons.toJson(e);
        System.out.println(json);
        this.PutRequest(url,json);
    }
    public void changerMDP(Utilisateur user,String mdp) throws IOException {
        user.setMdp(mdp);
        String url = this.url+"utilisateur/ChangerMdp";
        Gson gson = new Gson();
        String json = gson.toJson(user);
        this.PutRequest(url,json);


    }

    public String getListeFormation(){
        String json = "";
        String url = this.url+"formation/GetFormation";

        String formationString = null;
        try {
            formationString = this.PostRequest(url,json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return formationString;
    }
    public String getFormationById2(long id)  {
        String url = this.url + "formation/FormationById";
        Gson gons = new Gson();
        String json = gons.toJson(id);
        String formationString = null;
        try {
            formationString = this.PostRequest(url, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return formationString;
    }

    public void addCours(Cours c) throws IOException {
        Gson gons = new Gson();
        String json = gons.toJson(c);
        System.out.println(json);

        // Reste à inserer le tuple dans la bdd via le serveur
        Serveur serveur = new Serveur();
        String url = this.url+"formation/AddCours";
        this.PutRequest(url,json);
    }

    public void addExamen(Examen e) throws IOException {
        Gson gons = new Gson();
        String json = gons.toJson(e);
        System.out.println(json);
        // Reste à inserer le tuple dans la bdd via le serveur
        Serveur serveur = new Serveur();
        String url = this.url+"formation/AddExamen";
        this.PutRequest(url,json);
    }

    public String getCours(){
        Gson gson = new Gson();
        String json = "";
        String url = this.url+"formation/GetCours";
        String coursString = "";
        try {
            coursString = this.PostRequest(url,json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coursString;
    }
    public String getMesCours(long id){
        Gson gson = new Gson();
        String json = gson.toJson(id);
        String url = this.url+"formation/GetMesCours";
        String coursString = "";
        try {
            coursString = this.PostRequest(url,json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coursString;
    }
    public String getExamens(){
        Serveur serveur = new Serveur();
        Gson gson = new Gson();
        String json = "";
        String url = this.url+"formation/GetExamens";
        String examenString = "";
        try {
            examenString = serveur.PostRequest(url,json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return examenString;
    }
    public String getMesExamens(long id){
        Gson gson = new Gson();
        String json = gson.toJson(id);
        String url = this.url+"formation/GetMesExamens";
        String examenString = "";
        try {
            examenString = this.PostRequest(url,json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return examenString;
    }

}
