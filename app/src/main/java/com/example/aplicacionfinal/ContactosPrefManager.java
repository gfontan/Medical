package com.example.aplicacionfinal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class ContactosPrefManager {

    private static final String PREF_NAME = "ContactosEmergenciaPrefs";
    private static final String KEY_CONTACTOS = "contactos";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public ContactosPrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public Set<String> getContactosEmergencia() {
        return pref.getStringSet(KEY_CONTACTOS, new HashSet<String>());
    }

    public void addContactoEmergencia(String contacto) {
        Set<String> contactos = getContactosEmergencia();
        contactos.add(contacto);
        editor.putStringSet(KEY_CONTACTOS, contactos);
        editor.apply();
    }

    public void removeContactoEmergencia(String contacto) {
        Set<String> contactos = getContactosEmergencia();
        contactos.remove(contacto);
        editor.putStringSet(KEY_CONTACTOS, contactos);
        editor.apply();
    }
}
