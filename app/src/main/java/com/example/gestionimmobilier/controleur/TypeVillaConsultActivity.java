package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.TypeVilla;
import com.example.gestionimmobilier.modele.TypeVillaDAO;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;


import java.util.ArrayList;

public class TypeVillaConsultActivity extends AppCompatActivity {
    private ListView listViewVillaConsult;
    private Spinner spinTypeVilla;
    private Button retour,ajouter;
    private ListView listeTypeVillaView;
    private ArrayList<TypeVilla> listeTypeVilla;
    private ArrayList<Villa> listeVilla;
    private ImageView image;


    public void actualiser(int id){

        TypeVillaDAO typeVillaAcces = new TypeVillaDAO(this);
        listeVilla = typeVillaAcces.getTypeVillaStudio(id);
        ArrayAdapter<String> monAdapteur = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listeVilla);
        listeTypeVillaView.setAdapter(monAdapteur);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_villa_consult);

        retour = findViewById(R.id.buttonRetourTypeVillaConsult);
        ajouter = findViewById(R.id.buttonAjouterTypeVillaConsult);
        listeTypeVillaView =  findViewById(R.id.listViewTypaVillaConsult);
        image = findViewById(R.id.imageView);

        image.setImageResource(R.drawable.logovillepau);


        retour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(TypeVillaConsultActivity.this, PropositionActivity.class);
                startActivity(i);
            }
        });

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TypeVillaConsultActivity.this, VillaAjoutActivity.class);
                startActivity(i);
            }
        });

        spinTypeVilla = (Spinner) findViewById(R.id.spinnerTypeVillaConsult);

        TypeVillaDAO TypeVillaAccesDAO = new TypeVillaDAO(this);
        listeTypeVilla = TypeVillaAccesDAO.getTypeVilla();
        ArrayAdapter<TypeVilla> spinTypeVillaAdapter = new ArrayAdapter<TypeVilla>(this.getBaseContext(),android.R.layout.simple_spinner_item);

        for(int i=0;i<listeTypeVilla.size();i++){
            spinTypeVillaAdapter.add(listeTypeVilla.get(i));
        }
        spinTypeVilla.setAdapter(spinTypeVillaAdapter);



        spinTypeVilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TypeVilla typeVilla = (TypeVilla) spinTypeVilla.getSelectedItem();
                //     Toast.makeText(getApplicationContext(), "nom: " + ,  Toast.LENGTH_SHORT).show();
                actualiser(typeVilla.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        VillaDAO villaAcess = new VillaDAO(this);
        listeVilla = villaAcess.recupVilla();



        listViewVillaConsult = findViewById(R.id.listViewTypaVillaConsult);
        ArrayAdapter monAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listeVilla);


        listeTypeVillaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(parent.getContext(), VillaConsultActivity.class);
                Villa clickItem = (Villa) listeTypeVillaView.getAdapter().getItem(position);

                i.putExtra("id",clickItem.getId());
                i.putExtra("nom",clickItem.getNom());
                i.putExtra("adresse",clickItem.getAdresse());
                i.putExtra("descriptionV",clickItem.getDescriptionV());
                i.putExtra("descriptionP",clickItem.getDescriptionP());
                i.putExtra("superficie",clickItem.getSuperficie());
                i.putExtra("anneesConstru",clickItem.getAnneeConstru());
                i.putExtra("caution",clickItem.getCaution());
                i.putExtra("idTypeVilla", clickItem.getIdTypeVilla());

                startActivity(i);
            }
        });

    }
}