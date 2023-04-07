package com.example.gestionimmobilier.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class LocataireDAO {

    private static String base = "gestionimmo";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;
    public LocataireDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }
    public ArrayList<Locataire> recupLocataire(){
        Cursor curseur;
        String req = "select * from locataires";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToLocataireArrayList(curseur);
    }

    private ArrayList<Locataire> cursorToLocataireArrayList(Cursor curseur){
        ArrayList<Locataire> listeLocataire = new ArrayList<Locataire>();
        int id;
        String nom;
        String prenom;
        String adresse;
        String numTel;
        String email;
        String commentaire;


        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            id = curseur.getInt(0);
            nom = curseur.getString(1);
            prenom = curseur.getString(2);
            adresse = curseur.getString(3);
            numTel  = curseur.getString(4);
            email  = curseur.getString(5);
            commentaire  = curseur.getString(6);


            listeLocataire.add(new Locataire(id,nom,prenom,adresse,numTel,email,commentaire));
            curseur.moveToNext();
        }
        return listeLocataire;
    }




    public int modifierLocataire(Locataire newLocataire,Locataire ancLocataire){
        int ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("nom", newLocataire.getNom());
        value.put("prenom", newLocataire.getPrenom());
        value.put("adresse", newLocataire.getAdresse());
        value.put("numeroTelephone", newLocataire.getNumTel());
        value.put("email", newLocataire.getEmail());
        value.put("commentaire", newLocataire.getCommentaire());
        String condition="id = '"+ancLocataire.getId()+"'";
        String condition2 = "nom ='"+newLocataire.getNom()+"' AND prenom='"+newLocataire.getPrenom()+"'";
        ret = bd.update("locataires", value,condition,null);

        return ret;
    }


    public Locataire getLocataire(int id){
        Locataire leLocataire = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from locataires where id='"+id+"';",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leLocataire = new Locataire(id, curseur.getString(1), curseur.getString(2), curseur.getString(3), curseur.getString(4), curseur.getString(5),curseur.getString(6));
            Log.d("******","*********"+leLocataire.toString());
        }
        return leLocataire;
    }

    public long supprimerLocataire(Locataire unLocataire){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "nom ='"+unLocataire.getNom()+"' AND prenom='"+unLocataire.getPrenom()+"'";
        Log.d("locataire supprime", condition);
        ret = bd.delete("locataires", condition ,null);
        return ret;
    }


    public long addLocataire(Locataire unLocataire){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("nom", unLocataire.getNom());
        value.put("prenom",unLocataire.getPrenom());
        value.put("adresse", unLocataire.getAdresse());
        value.put("numeroTelephone",unLocataire.getNumTel());
        value.put("adresse", unLocataire.getAdresse());
        value.put("commentaire", unLocataire.getCommentaire());
        ret = bd.insert("locataires", null, value);

        return ret;
    }



}
