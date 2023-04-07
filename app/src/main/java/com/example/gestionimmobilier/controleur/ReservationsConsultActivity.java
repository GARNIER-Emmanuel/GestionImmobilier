package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Reservation;
import com.example.gestionimmobilier.modele.ReservationDAO;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;


import java.util.ArrayList;

public class ReservationsConsultActivity extends AppCompatActivity {
    ImageView img;
    private Spinner spinVilla;
    private ArrayList<Villa> listeVilla;
    private int idVilla;
    private ListView listeView;
    Button retour, ajouter;
    private ArrayList<Reservation> listeReservation;
    private ImageView image;

    public void actualiser(int id){

        ReservationDAO rAcces = new ReservationDAO(this);
        listeReservation = rAcces.getVillaFiltre(id);
        for (Reservation uneReservation : listeReservation){
            Log.d("reservation","*********" + uneReservation.toString());
        }
        ArrayAdapter<String> monAdapteur = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listeReservation);
        listeView.setAdapter(monAdapteur);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations_consult);
        ajouter = findViewById(R.id.buttonAjouterReservationsConsult);
        retour = findViewById(R.id.buttonRetourReservationsConsult);
        listeView = findViewById(R.id.listViewReservationsAjout);
        spinVilla = findViewById(R.id.spinnerChoixVillaReservationsConsult);
        image = findViewById(R.id.imageView10);

        image.setImageResource(R.drawable.logovillepau);


        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (ReservationsConsultActivity.this, ReservationsAjoutActivity.class );
                startActivity(i);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent (ReservationsConsultActivity.this, PropositionActivity.class );
                startActivity(i2);
            }
        });

        VillaDAO villaAcces = new VillaDAO(this);
        listeVilla = villaAcces.recupVilla();
        ArrayAdapter<Villa> spinVillaAdapter = new ArrayAdapter<Villa>(this, android.R.layout.simple_spinner_item);


        for (int i=0; i<listeVilla.size(); i++){
            spinVillaAdapter.add(listeVilla.get(i));
            Log.d("message","***" +listeVilla.get(i));
        }
        spinVilla.setAdapter(spinVillaAdapter);

        //.setSelection(getIntent().getIntExtra("id",0));

        spinVilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Villa uneVilla = (Villa) spinVilla.getSelectedItem();
                Toast.makeText(getApplicationContext(), "Voici les réservations de la " + uneVilla.getNom(),  Toast.LENGTH_SHORT).show();
                actualiser(uneVilla.getId());

                //idVilla = listeVilla.get(position).getId();
                //Toast.makeText(getApplicationContext(), "nom: " + id,  Toast.LENGTH_SHORT).show();
                //actualiser(listeReservation.get(position).getId());

                //villa = (Villa) spinVilla.getSelectedItem();
                //Toast.makeText(getApplicationContext(), "id: " + id,  Toast.LENGTH_SHORT).show();
                //actualiser(villa.getId());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        ReservationDAO reservationAcces = new ReservationDAO(this);
        listeReservation = reservationAcces.getLesReservations();

        ArrayAdapter lesReservations = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listeReservation);
        listeView.setAdapter(lesReservations);

        listeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ReservationsConsultActivity.this, ReservationsConsultDetailsActivity.class);
                Reservation clickedItem = (Reservation) listeView.getAdapter().getItem(position);
                i.putExtra("id", clickedItem.getId());
                i.putExtra("dateArrivee", clickedItem.getDateArrivee());
                i.putExtra("dateDepart", clickedItem.getDateDepart());
                i.putExtra("nbAdultes", clickedItem.getNbAdultes());
                i.putExtra("nbEnfants", clickedItem.getNbEnfants());
                i.putExtra("dateReservation", clickedItem.getDateReservation());
                i.putExtra("optionMenahe", clickedItem.getOptionMenage());
                i.putExtra("montantR", clickedItem.getMontantR());
                i.putExtra("idVilla", clickedItem.getIdVilla());
                i.putExtra("idLocataires", clickedItem.getIdLocataires());
                startActivity(i);
            }

        });
    }
}