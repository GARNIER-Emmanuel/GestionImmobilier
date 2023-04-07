package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Locataire;
import com.example.gestionimmobilier.modele.LocataireDAO;
import com.example.gestionimmobilier.modele.Reservation;
import com.example.gestionimmobilier.modele.ReservationDAO;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;

import java.util.ArrayList;

public class ReservationsAjoutActivity extends AppCompatActivity {
    private Button ajouter, retour;
    private ArrayList<Locataire> listeLocataire;
    private ArrayList<Villa> listeVilla;
    private Spinner spinVilla, spinLocataire;
    private EditText txtDateArrivee, txtDateDepart, txtNbAdultes, txtNbEnfants, txtDateReservation, txtOptionMenage, txtMontantR;
    private int idVilla,idLocataire;
    private Reservation uneReservation;
    String dateArrivee, dateDepart, dateReservation;
    int nbAdultes, nbEnfants;
    float montantR;
    boolean optionMenage;

    private ImageView image;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations_ajout);
        txtDateArrivee = findViewById(R.id.editTextDateArriveeReservationsAjout);
        txtDateDepart = findViewById(R.id.editTextDateDepartReservationsAjout);
        txtNbAdultes = findViewById(R.id.editTextNombreAdultesReservationsAjout);
        txtNbEnfants = findViewById(R.id.editTextNombreEnfantsReservationsAjout);
        txtDateReservation = findViewById(R.id.editTextDateReservationsAjout);
        txtOptionMenage = findViewById(R.id.editTextOptionMenageReservationsAjout);
        txtMontantR = findViewById(R.id.editTextMontantReservationsAjout);
        spinLocataire = findViewById(R.id.spinnerAjoutLoc);
        spinVilla = findViewById(R.id.spinnerAjoutVilla);
        ajouter = findViewById(R.id.buttonAjouterReservationsAjout);
        retour = findViewById(R.id.buttonRetourReservationsAjout);
        image = findViewById(R.id.imageView12);

        image.setImageResource(R.drawable.logovillepau);


        ReservationDAO reservationAcces = new ReservationDAO(this);
        LocataireDAO locaAcces = new LocataireDAO(this);
        VillaDAO villaAcces = new VillaDAO(this);

        listeLocataire = locaAcces.recupLocataire();
        listeVilla = villaAcces.recupVilla();

        ArrayAdapter<Locataire> spinLocataireAdapter = new ArrayAdapter<Locataire>(this, android.R.layout.simple_spinner_item);

        for (int i=0; i<listeLocataire.size(); i++){
            spinLocataireAdapter.add(listeLocataire.get(i));
        }
        spinLocataire.setAdapter(spinLocataireAdapter);

        ArrayAdapter<Villa> spinVillaAdapter = new ArrayAdapter<Villa>(this, android.R.layout.simple_spinner_item);

        for (int i=0; i<listeVilla.size(); i++){
            spinVillaAdapter.add(listeVilla.get(i));
        }
        spinVilla.setAdapter(spinVillaAdapter);

        spinLocataire.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    idLocataire=listeLocataire.get(position).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinVilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                idVilla=listeVilla.get(position).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });




        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dateArrivee=txtDateArrivee.getText().toString();
                dateDepart=txtDateDepart.getText().toString();
                nbAdultes=Integer.parseInt(txtNbAdultes.getText().toString());
                nbEnfants=Integer.parseInt(txtNbEnfants.getText().toString());
                dateReservation=txtDateReservation.getText().toString();
                optionMenage= Boolean.parseBoolean(txtOptionMenage.getText().toString());
                montantR= Float.parseFloat(txtMontantR.getText().toString());



                uneReservation = new Reservation(reservationAcces.dernierId(), dateArrivee,dateDepart, nbAdultes, nbEnfants, dateReservation, optionMenage, montantR, idVilla, idLocataire);
                reservationAcces.addReservation(uneReservation);

                if (uneReservation.getDateArrivee().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez saisir une date de réservation.",Toast.LENGTH_LONG).show();
                    Intent i = new Intent (ReservationsAjoutActivity.this, ReservationsConsultActivity.class );

                }
                else {
                    Intent i = new Intent (ReservationsAjoutActivity.this, ReservationsConsultActivity.class );
                    reservationAcces.addReservation(uneReservation);
                    Toast.makeText(getApplicationContext(), "La  réservation du " + uneReservation.getDateReservation() + " a été ajoutée.", Toast.LENGTH_LONG).show();
                    startActivity(i);                }
                }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (ReservationsAjoutActivity.this, ReservationsConsultActivity.class );
                startActivity(i);
            }
        });
    }
}