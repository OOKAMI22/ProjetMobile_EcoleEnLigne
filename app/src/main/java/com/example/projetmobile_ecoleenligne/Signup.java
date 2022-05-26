package com.example.projetmobile_ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetmobile_ecoleenligne.classes.Etudiant;
import com.example.projetmobile_ecoleenligne.classes.Formation;
import com.example.projetmobile_ecoleenligne.classes.Serveur;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

public class Signup extends AppCompatActivity {
    EditText nomET;
    EditText prenomET;
    EditText emailET;
    EditText numeroET;
    Spinner paysET;
    EditText mdpET;
    //EditText formationET;
    Spinner formationET;
    ArrayList<Formation> listeFormations = new ArrayList<>();
    Button btnSignUp;
    Etudiant etudiant;
    String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nomET = this.findViewById(R.id.nom);
        prenomET = this.findViewById(R.id.prenom);
        emailET = this.findViewById(R.id.email);
        numeroET = this.findViewById(R.id.numero);

        mdpET = (EditText) this.findViewById(R.id.mdp);
        // A traiter plus tard
        //formationET = (EditText) this.findViewById(R.id.formation);
        formationET = findViewById(R.id.user);

        btnSignUp = (Button) this.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    inscription(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });;
        // Interroger la BD pour récuperer la liste des formations disponibles
        Serveur serveur = new Serveur();
        String formationString = serveur.getListeFormation();

        Gson gson = new Gson();
        listeFormations = gson.fromJson(formationString,  new TypeToken<ArrayList<Formation>>(){}.getType());
        System.out.println(listeFormations.get(0).getTitre());

        ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, listeFormations);
        formationET.setAdapter(userAdapter);
        formationET.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get la value selectionée by la formation

                Formation f = (Formation) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        paysET =  this.findViewById(R.id.paysSp);
        ArrayAdapter countriesAdapter = new ArrayAdapter(this, R.layout.spinner, countries);
        paysET.setAdapter(countriesAdapter);


    }
    public void inscription(View view) throws IOException {
        String nom = nomET.getText().toString();
        String prenom = prenomET.getText().toString();
        String email = emailET.getText().toString();
        String numeroString = numeroET.getText().toString();
        long numero = Long.parseLong(numeroString);
        String pays = paysET.getSelectedItem().toString();
        String formationString = formationET.getSelectedItem().toString();
        Formation formation = getFormationFromArray(listeFormations,formationString);
        String mdp = mdpET.getText().toString();
        //utilisateur étudiant crée
        etudiant = new Etudiant( nom, prenom, email, numero, mdp, pays,formation);



        // Reste à inserer le tuple dans la bdd via le serveur
        Serveur serveur = new Serveur();
        serveur.putEtudiant(etudiant);

        Intent intention= new Intent(Signup.this, AcceuilActivity.class);
        intention.putExtra("user",etudiant.toString());
        intention.putExtra("role","etudiant");


        startActivity(intention);
    }
    public Formation getFormationFromArray(ArrayList<Formation> listeFormations,String formation){
        for(Formation f : listeFormations){
            if (f.getTitre().equals(formation)){
                return f;
            }
        }
        return null;
    }



}