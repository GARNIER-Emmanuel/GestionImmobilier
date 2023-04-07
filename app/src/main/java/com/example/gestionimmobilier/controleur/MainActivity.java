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
import com.example.gestionimmobilier.modele.Role;
import com.example.gestionimmobilier.modele.RoleDAO;
import com.example.gestionimmobilier.modele.Utilisateur;
import com.example.gestionimmobilier.modele.UtilisateurDAO;


public class MainActivity extends AppCompatActivity {


    EditText txtLogin, txtMdp;
    Button btnConnecter;
    ImageView image;

    String typeRole;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLogin = findViewById(R.id.editTextIdLogin);
        txtMdp = findViewById(R.id.editTextMotDePasseLogin);
        btnConnecter = findViewById(R.id.buttonConnexionLogin);
        image = findViewById(R.id.imageView13);

        image.setImageResource(R.drawable.logovillepau);


        UtilisateurDAO unUtilisateurDAO = new UtilisateurDAO(this);
        RoleDAO  recupTypeRole = new RoleDAO(this);


        btnConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login=txtLogin.getText().toString();
                String motDePasse=txtMdp.getText().toString();
                Log.d("test connexion","******"+login+ " " +motDePasse);
                Utilisateur utilisateurConnexion=null;

                try{
                    utilisateurConnexion= unUtilisateurDAO.seConnecter(login,motDePasse);
                    if(utilisateurConnexion!=null){
                        Intent intent = new Intent(MainActivity.this, PropositionActivity.class);
                        Log.d("connexion","connexion");
                        startActivity(intent);


                        Toast.makeText(getApplicationContext(), "Bienvenue à toi !", Toast.LENGTH_LONG).show();
                        Log.d("testMess", typeRole);

                    }else{

                        Toast.makeText(getApplicationContext(), "Identifiant ou mot de passe incorrect", Toast.LENGTH_LONG).show();


                    }
                }catch(Exception e){
                    Log.d("testConnexion",e.getMessage());
                }
            }
        });
    }
}
