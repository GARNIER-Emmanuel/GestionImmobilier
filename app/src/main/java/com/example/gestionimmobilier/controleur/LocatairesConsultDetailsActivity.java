package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Locataire;
import com.example.gestionimmobilier.modele.LocataireDAO;

import java.util.ArrayList;

public class LocatairesConsultDetailsActivity extends AppCompatActivity {
    private Button buttonRetourLocatairesConsultDetails, buttonSupprimerLocatairesConsultDetails, buttonModifierLocatairesConsultDetails;
    private EditText editTextNomLocatairesConsultDetails, editTextPrenomLocatairesConsultDetails, editTextAdresseLocatairesConsultDetails, editTextTelephoneLocatairesConsultDetails, editTextEmailLocatairesConsultDetails, editTextCommentaireLocatairesConsultDetails;
    private ArrayList<Locataire> listeLocataire;
    private LocataireDAO recupLocataire;
    private Locataire unLocataire;
    private ImageView image;
    private String nom, prenom,adresse, tel, email, com;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locataires_consult_details);
        buttonRetourLocatairesConsultDetails = (Button) findViewById(R.id.buttonRetourLocatairesConsultDetails);
        buttonSupprimerLocatairesConsultDetails = (Button) findViewById(R.id.buttonSupprimerLocatairesConsultDetails);
        buttonModifierLocatairesConsultDetails = (Button) findViewById(R.id.buttonModifierLocatairesConsultDetails);
        editTextNomLocatairesConsultDetails = findViewById(R.id.editTextNomLocatairesConsultDetails);
        editTextPrenomLocatairesConsultDetails = findViewById(R.id.editTextPrenomLocatairesConsultDetails);
        editTextAdresseLocatairesConsultDetails = findViewById(R.id.editTextAdresseLocatairesConsultDetails);
        editTextTelephoneLocatairesConsultDetails = findViewById(R.id.editTextTelephoneLocatairesConsultDetails);
        editTextEmailLocatairesConsultDetails = findViewById(R.id.editTextEmailLocatairesConsultDetails);
        editTextCommentaireLocatairesConsultDetails = findViewById(R.id.editTextCommentaireLocatairesConsultDetails);
        image = findViewById(R.id.imageView8);

        image.setImageResource(R.drawable.logovillepau);

        recupLocataire = new LocataireDAO(this);
        Intent intent = getIntent();
        id = getIntent().getIntExtra("id",0);
        nom = getIntent().getStringExtra("nom");
        prenom = getIntent().getStringExtra("prenom");
        adresse = getIntent().getStringExtra("adresse");
        tel = getIntent().getStringExtra("numTel");
        email = getIntent().getStringExtra("email");
        com = getIntent().getStringExtra("commentaire");

        unLocataire = recupLocataire.getLocataire(id);
        editTextNomLocatairesConsultDetails.setText(nom);
        editTextPrenomLocatairesConsultDetails.setText(prenom);
        editTextAdresseLocatairesConsultDetails.setText(adresse);
        editTextTelephoneLocatairesConsultDetails.setText(tel);
        editTextEmailLocatairesConsultDetails.setText(email);
        editTextCommentaireLocatairesConsultDetails.setText(com);

        buttonRetourLocatairesConsultDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocatairesConsultDetailsActivity.this, LocatairesConsultActivity.class);
                startActivity(intent);
            }
        });

        buttonModifierLocatairesConsultDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String resNom = editTextNomLocatairesConsultDetails.getText().toString();
                String resPrenom = editTextPrenomLocatairesConsultDetails.getText().toString();
                String resAdresse = editTextAdresseLocatairesConsultDetails.getText().toString();
                String resTel = editTextTelephoneLocatairesConsultDetails.getText().toString();
                String resEmail = editTextEmailLocatairesConsultDetails.getText().toString();
                String resCom = editTextCommentaireLocatairesConsultDetails.getText().toString();

                Locataire leNouveauLocataire = new Locataire( id,resNom, resPrenom, resAdresse, resTel, resEmail, resCom);

                int res = recupLocataire.modifierLocataire(leNouveauLocataire, unLocataire);

                if (res != 1) {
                    Toast.makeText(getApplicationContext(), "Echec de la modification !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Modification réussi !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LocatairesConsultDetailsActivity.this, LocatairesConsultActivity.class);
                    startActivity(intent);
                }

            }

        });

        buttonSupprimerLocatairesConsultDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long res = recupLocataire.supprimerLocataire(unLocataire);
                if (res != 0){
                    Toast.makeText(getApplicationContext(),"Suppression réussi !",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LocatairesConsultDetailsActivity.this, LocatairesConsultActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Echec de la suppression !",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}