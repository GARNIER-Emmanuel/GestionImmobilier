package com.example.gestionimmobilier.controleur;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;

import java.util.ArrayList;

public class VillaConsultActivity extends AppCompatActivity {
    private EditText nomV, adresseV,descriptionV, descriptionP, superficieV, anneesConstructionV, cautionV;
    private String nom,adresse,descriptionVilla,descriptionPiece,superficie;
    private Integer anneesConstruction;
    private Float caution;
    Button retour, buttonModifier,buttonSupprimer, voirEquip;
    private Villa villaRecup;
    private VillaDAO recupVilla;
    private ImageView image;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villa_consult);

        retour = findViewById(R.id.buttonRetourVillaConsult);
        buttonModifier = findViewById(R.id.buttonModifierConsult);
        buttonSupprimer = findViewById(R.id.buttonSupprimerConsult);
        voirEquip = findViewById(R.id.buttonVoirEquipementsVillaConsult);
        image = findViewById(R.id.imageVillaConsult);

        image.setImageResource(R.drawable.logovillepau);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VillaConsultActivity.this, TypeVillaConsultActivity.class);
                startActivity(i);
            }
        });



        int idV = getIntent().getIntExtra("id",0);
        Log.d("idVilla recup","id"+idV);
        VillaDAO VillaAccesDAO = new VillaDAO(this);
        villaRecup = VillaAccesDAO.recupVillaId(idV);
        Log.d("idVilla recup","id"+villaRecup.toString());


        nomV = findViewById(R.id.editTextNomVillaConsult);
        adresseV = findViewById(R.id.editTextAdresseVillaConsult);
        descriptionV = findViewById(R.id.editTextDescriptionVillaConsult);
        descriptionP = findViewById(R.id.editTextDescriptionPieceVillaConsult);
        superficieV = findViewById(R.id.editTextSuperficieVillaConsult);
        anneesConstructionV = findViewById(R.id.editTextAnneeVillaConsult);
        cautionV = findViewById(R.id.editTextCautionVillaConsult);
        image = findViewById(R.id.imageVillaConsult);

        image.setImageResource(R.drawable.logovillepau);

        nom = villaRecup.getNom();
        adresse = villaRecup.getAdresse();
        descriptionVilla = villaRecup.getDescriptionV();
        descriptionPiece = villaRecup.getDescriptionP();
        superficie = villaRecup.getSuperficie();
        anneesConstruction = villaRecup.getAnneeConstru();
        caution = villaRecup.getCaution();

        nomV.setText(nom);
        adresseV.setText(adresse);
        descriptionV.setText(descriptionVilla);
        descriptionP.setText(descriptionPiece);
        superficieV.setText(superficie);
        anneesConstructionV.setText(anneesConstruction.toString());
        cautionV.setText(caution.toString());


        recupVilla = new VillaDAO(this);
        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomNv = nomV.getText().toString();
                String adresseNv = adresseV.getText().toString();
                String descriptionNv = descriptionV.getText().toString();
                String descriptionPieceNv = descriptionP.getText().toString();
                String superficieNv = superficieV.getText().toString();
                int anneesConstructionNv = Integer.parseInt(anneesConstructionV.getText().toString());
                float cautionNv = Float.parseFloat(cautionV.getText().toString());

                Villa laNouvelleVilla = new Villa(nomNv, adresseNv, descriptionNv, descriptionPieceNv, superficieNv, anneesConstructionNv, cautionNv);

                int res = recupVilla.modifierVilla(laNouvelleVilla, villaRecup);

                if (res != 1) {
                    Toast.makeText(getApplicationContext(), "Echec de la modification !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Modification réussi !", Toast.LENGTH_LONG).show();
                }

                Intent i = new Intent(VillaConsultActivity.this, TypeVillaConsultActivity.class);
                startActivity(i);

            }
        });

        buttonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long res = recupVilla.supprimerVilla(villaRecup);
                if (res != 0){
                    Toast.makeText(getApplicationContext(),"Suppression réussi !",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(VillaConsultActivity.this, TypeVillaConsultActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Echec de la suppression !",Toast.LENGTH_LONG).show();
                }

            }
        });


        voirEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(VillaConsultActivity.this, EquipementsConsultActivity.class);
                intent2.putExtra("id",idV);
                startActivity(intent2);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(VillaConsultActivity.this, TypeVillaConsultActivity.class);
                startActivity(intent2);
            }
        });
    }
}
