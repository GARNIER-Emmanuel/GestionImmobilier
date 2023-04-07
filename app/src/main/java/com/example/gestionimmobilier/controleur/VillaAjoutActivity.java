package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Reservation;
import com.example.gestionimmobilier.modele.TypeVilla;
import com.example.gestionimmobilier.modele.TypeVillaDAO;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;

import java.util.ArrayList;

public class VillaAjoutActivity extends AppCompatActivity {
    int idTypeVilla, anneeConstru;
    String nom, adresse, descriptionV, descriptionP, superficie;
    float caution;
    private Spinner spinTypeVilla;
    private Button btnAjout, btnRetour;
    private Villa uneVilla;
    private ArrayList<TypeVilla> listeTypeVilla;
    private EditText editTextNomVillaAjout, editTextAdresseVillaAjout, editTextDescriptionVillaAjout, editTextDescriptionPiecesVillaAjout, editTextSuperficieVillaAjout, editTextAnneeVillaAjout, editTextCautionVillaAjout;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villa_ajout);


         btnAjout = (Button) findViewById(R.id.buttonAjouterVillaAjout);
         btnRetour = (Button) findViewById(R.id.buttonRetourVillaAjout);


        editTextNomVillaAjout = (EditText) findViewById(R.id.editTextNomVillaAjout);
        editTextAdresseVillaAjout = (EditText) findViewById(R.id.editTextAdresseVillaAjout);
        editTextDescriptionVillaAjout = (EditText) findViewById(R.id.editTextDescriptionVillaAjout);
        editTextDescriptionPiecesVillaAjout = (EditText) findViewById(R.id.editTextDescriptionPiecesVillaAjout);
        editTextSuperficieVillaAjout = (EditText) findViewById(R.id.editTextSuperficieVillaAjout);
        editTextAnneeVillaAjout = (EditText) findViewById(R.id.editTextAnneeVillaAjout);
        editTextCautionVillaAjout = (EditText) findViewById(R.id.editTextCautionVillaAjout);
        spinTypeVilla = (Spinner) findViewById(R.id.spinnerTypeDeVillaAjout);
        image = findViewById(R.id.imageView6);

        image.setImageResource(R.drawable.logovillepau);


        VillaDAO villaAcces = new VillaDAO(this);
        TypeVillaDAO typeVillaAcces = new TypeVillaDAO(this);

        listeTypeVilla = typeVillaAcces.getTypeVilla();

        ArrayAdapter<TypeVilla> spinTypeVillaAdapter = new ArrayAdapter<TypeVilla>(this.getBaseContext(),android.R.layout.simple_spinner_item);

        for(int i=0;i<listeTypeVilla.size();i++){
            spinTypeVillaAdapter.add(listeTypeVilla.get(i));
        }
        spinTypeVilla.setAdapter(spinTypeVillaAdapter);
        Log.d("log","test");

        spinTypeVilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                idTypeVilla=listeTypeVilla.get(arg2).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(VillaAjoutActivity.this, TypeVillaConsultActivity.class);
                startActivity(intent3);
            }
        });

        btnAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                nom=editTextNomVillaAjout.getText().toString();
                adresse=editTextAdresseVillaAjout.getText().toString();
                descriptionV=editTextDescriptionVillaAjout.getText().toString();
                descriptionP=editTextDescriptionPiecesVillaAjout.getText().toString();
                superficie=editTextSuperficieVillaAjout.getText().toString();
                anneeConstru=Integer.parseInt(editTextAnneeVillaAjout.getText().toString());
                caution= Float.parseFloat(editTextCautionVillaAjout.getText().toString());

                uneVilla = new Villa(villaAcces.dernierId(), nom, adresse, descriptionV, descriptionP, superficie, anneeConstru, caution, idTypeVilla);
                villaAcces.addVilla(uneVilla);

                if (uneVilla.getNom().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez saisir un nom.",Toast.LENGTH_LONG).show();

                }
                else {
                    Intent i = new Intent (VillaAjoutActivity.this, TypeVillaConsultActivity.class );
                    villaAcces.addVilla(uneVilla);
                    Toast.makeText(getApplicationContext(), "La  villa " + uneVilla.getNom() + " a été ajoutée.", Toast.LENGTH_LONG).show();
                    startActivity(i);
                }
            }
        });



    }
}