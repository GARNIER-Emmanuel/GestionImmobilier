package com.example.gestionimmobilier.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class TypeVillaDAO {
    private static String base = "gestionimmo";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;

    public TypeVillaDAO(Context ct) { accesBD = new BD_SQLiteOpenHelper(ct, base, null, version); }

    public ArrayList<TypeVilla> recupTypeVilla(){
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("Select * from typeVilla ; ",null);
        ArrayList<TypeVilla> listeTypeVilla = new ArrayList<TypeVilla>();

        int idV, nbChambrePossible;
        String  nomV;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            idV = curseur.getInt(0);
            nbChambrePossible = curseur.getInt(1);
            nomV = curseur.getString(2);
            listeTypeVilla.add(new TypeVilla(idV,nbChambrePossible, nomV));
            curseur.moveToNext();

        }
        return listeTypeVilla;
    }

    public ArrayList<TypeVilla> getTypeVilla(){
        Cursor curseur;
        String req = "select * from TypeVilla";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToTypeVillaArrayList(curseur);
    }

    public ArrayList<Villa> getTypeVillaStudio(int idV) {
        Cursor curseur;
        String req = "select * from villa where idTypeVilla =  '" + idV + "'";
        Log.d("msg","requete" + req);
        curseur = accesBD.getReadableDatabase().rawQuery(req, null);
        ArrayList<Villa> listeVilla = new ArrayList<Villa>();
        int id;
        String nom;
        String adresse;
        String descriptionV;
        String descriptionP;
        String superficie;
        int anneeConstru;
        float caution;
        int idTypeVilla;


        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            id = curseur.getInt(0);
            nom = curseur.getString(1);
            adresse = curseur.getString(2);
            descriptionV = curseur.getString(3);
            descriptionP = curseur.getString(4);
            superficie = curseur.getString(5);
            anneeConstru = curseur.getInt(6);
            caution = curseur.getFloat(7);
            idTypeVilla= curseur.getInt(8);
            listeVilla.add(new Villa(id,nom, adresse,descriptionV,descriptionP,superficie,anneeConstru,caution,idTypeVilla));
            curseur.moveToNext();
        }
        return listeVilla;
    }


    private ArrayList<TypeVilla> cursorToTypeVillaArrayList(Cursor curseur){
        ArrayList<TypeVilla> listeTypeVilla = new ArrayList<TypeVilla>();
        int id;
        int nbChambresPossible;
        String nom;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            id = curseur.getInt(0);
            nbChambresPossible = curseur.getInt(1);
            nom = curseur.getString(2);
            listeTypeVilla.add(new TypeVilla(id,nbChambresPossible,nom));
            curseur.moveToNext();
        }

        return listeTypeVilla;
    }

}