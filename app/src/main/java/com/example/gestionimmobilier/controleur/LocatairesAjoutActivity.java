package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Locataire;
import com.example.gestionimmobilier.modele.LocataireDAO;

public class LocatairesAjoutActivity extends AppCompatActivity {
    private ImageView image;
    private Button boutonValider;
    private Button boutonRetour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locataires_ajout);
        EditText editNom,editPrenom,editAdresse,editTel,editEmail,editCom;
        LocataireDAO accesLocataire=new LocataireDAO(this);

        editNom=findViewById(R.id.editTextNomLocatairesAjout);
        editPrenom=findViewById(R.id.editTextPrenomLocatairesAjout);
        editAdresse=findViewById(R.id.editTextAdresseLocatairesAjout);
        editTel=findViewById(R.id.editTextTelephoneLocatairesAjout);
        editEmail=findViewById(R.id.editTextEmailLocatairesAjout);
        editCom=findViewById(R.id.editTextCommentaireLocatairesAjout);
        boutonValider=findViewById(R.id.buttonAjouterLocatairesAjout);
        boutonRetour=findViewById(R.id.buttonRetourLocatairesAjout);

        image = findViewById(R.id.imageView9);

        image.setImageResource(R.drawable.logovillepau);


        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom,prenom,adresse,tel,email,com;

                nom=editNom.getText().toString();
                prenom=editPrenom.getText().toString();
                adresse=editAdresse.getText().toString();
                tel=editTel.getText().toString();
                email=editEmail.getText().toString();
                com=editCom.getText().toString();

                Locataire unLocataire=new Locataire(nom,prenom,adresse,tel,email,com);
                accesLocataire.addLocataire(unLocataire);

                Intent intent=new Intent(LocatairesAjoutActivity.this,LocatairesConsultActivity.class);
                startActivity(intent);

            }
        });

        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LocatairesAjoutActivity.this,LocatairesConsultActivity.class);
                startActivity(intent);
            }
        });
    }
}