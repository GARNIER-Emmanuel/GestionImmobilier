package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gestionimmobilier.R;


public class PropositionActivity extends AppCompatActivity {
    Button villa, locataire, reservation, retour;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);
        villa = findViewById(R.id.buttonVillaMain);
        locataire = findViewById(R.id.buttonLocatairesMain);
        reservation = findViewById(R.id.buttonReservationsMain);
        retour = findViewById(R.id.buttonRetourMain);
        image = findViewById(R.id.imageViewMain);

        image.setImageResource(R.drawable.logovillepau);


        villa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent villa = new Intent(PropositionActivity.this, TypeVillaConsultActivity.class);
                startActivity(villa);

            }
        });

        locataire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loc = new Intent(PropositionActivity.this, LocatairesConsultActivity.class);
                startActivity(loc);


            }
        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reserv = new Intent(PropositionActivity.this, ReservationsConsultActivity.class);
                startActivity(reserv);

            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ret = new Intent(PropositionActivity.this, MainActivity.class);
                startActivity(ret);

            }
        });
    }
}