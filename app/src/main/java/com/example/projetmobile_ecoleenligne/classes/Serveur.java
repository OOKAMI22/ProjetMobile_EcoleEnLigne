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
        String json = "";
        String url = "http://192.168.1.74:8080/EcoleEnLigne/formation";
        String formationString = gson.toJson(id);
        url += "/FormationById";
        try {
            formationString = this.PostRequest(url,json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Formation> listeFormation = gson.fromJson(formationString,  new TypeToken<ArrayList<Formation>>(){}.getType());
        return listeFormation.get(0);
    }
}
