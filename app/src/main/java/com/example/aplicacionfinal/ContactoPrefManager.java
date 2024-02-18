package com.example.aplicacionfinal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class ContactoPrefManager {
    private static final String PREF_NAME = "ContactosEmergenciaPref";
    private static final String KEY_CONTACTOS = "contactos";

    private SharedPreferences pref;

    public ContactoPrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void guardarContactos(Set<String> contactos) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(KEY_CONTACTOS, contactos);
        editor.apply();
    }

    public Set<String> getContactos() {
        return pref.getStringSet(KEY_CONTACTOS, new HashSet<String>());
    }
}
