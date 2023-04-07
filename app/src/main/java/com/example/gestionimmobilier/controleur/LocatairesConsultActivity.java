package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Locataire;
import com.example.gestionimmobilier.modele.LocataireDAO;

import java.util.ArrayList;


public class LocatairesConsultActivity extends AppCompatActivity {
    private Button buttonRetourLocatairesConsult;
    private Button  buttonAjouterLocatairesConsult;
    private ListView listViewLocatairesConsult;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locataires_consult);
        buttonRetourLocatairesConsult = (Button) findViewById(R.id.buttonRetourLocatairesConsult);
        buttonAjouterLocatairesConsult = (Button) findViewById(R.id.buttonAjouterLocatairesConsult);
        listViewLocatairesConsult = (ListView) findViewById(R.id.listViewLocatairesConsult);
        image = findViewById(R.id.imageView7);

        image.setImageResource(R.drawable.logovillepau);

        ArrayList<Locataire> UnelisteLocataire = new ArrayList<Locataire>();

        LocataireDAO LocataireDAO2 = new LocataireDAO(getApplicationContext());
        UnelisteLocataire = LocataireDAO2.recupLocataire();



        listViewLocatairesConsult = findViewById(R.id.listViewLocatairesConsult);
        ArrayAdapter monAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, UnelisteLocataire);

        listViewLocatairesConsult.setAdapter(monAdapter);

        listViewLocatairesConsult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Locataire clickedItem = (Locataire) listViewLocatairesConsult.getAdapter().getItem(position);
                Log.d("message","locataire"+clickedItem.getNom());
                Intent i = new Intent(LocatairesConsultActivity.this, LocatairesConsultDetailsActivity.class);
                Toast.makeText(LocatairesConsultActivity.this, "Le client choisi", Toast.LENGTH_LONG).show();



                i.putExtra("id", clickedItem.getId());
                i.putExtra("nom", clickedItem.getNom());
                i.putExtra("prenom", clickedItem.getPrenom());
                i.putExtra("adresse", clickedItem.getAdresse());
                i.putExtra("numTel", clickedItem.getNumTel());
                i.putExtra("email", clickedItem.getEmail());
                i.putExtra("commentaire", clickedItem.getCommentaire());

                startActivity(i);


            }
        });




        buttonRetourLocatairesConsult.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PropositionActivity.class);
                startActivity(intent);
            }
        });

        buttonAjouterLocatairesConsult.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LocatairesAjoutActivity.class);
                startActivity(intent);
            }
        });



    }
}