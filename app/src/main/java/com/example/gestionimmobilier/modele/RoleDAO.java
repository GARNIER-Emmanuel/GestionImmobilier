package com.example.gestionimmobilier.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RoleDAO {
    private static String base = "gestionimmo";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;
    public RoleDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public Role getTypeRole(String type){
        Role leRole = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from roles inner join idRoles ON utilisateur.idRoles=roles.id where type="+type+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leRole = new Role(curseur.getInt(0), type);
        }
        return leRole;
    }
}
