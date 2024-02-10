package com.example.aplicacionfinal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Set;

public class contactosEmergencia extends Activity {

    private static final int PICK_CONTACT_REQUEST = 1;
    private TextView selectedContactsTextView;
    private ContactosPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos_emergencia);

        selectedContactsTextView = findViewById(R.id.selectedContactsTextView);
        prefManager = new ContactosPrefManager(this);

        // Mostrar los contactos guardados
        Set<String> contactosGuardados = prefManager.getContactosEmergencia();
        for (String contacto : contactosGuardados) {
            selectedContactsTextView.append(contacto + "\n");
        }

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickContact();
            }
        });

        ImageView ContactosEmergencia = findViewById(R.id.back);
        ContactosEmergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarContactosEmergencia();
                finish(); // Salir de la actividad
            }
        });
    }

    private void pickContact() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
            String contactName = getContactName(data);
            if (contactName != null && !contactName.isEmpty()) {
                selectedContactsTextView.append(contactName + "\n");
                prefManager.addContactoEmergencia(contactName); // Guardar el contacto
            }
        }
    }

    @SuppressLint("Range")
    private String getContactName(Intent data) {
        String contactName = null;
        Cursor cursor = null;
        try {
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return contactName;
    }

    private void guardarContactosEmergencia() {
        // No se necesita hacer nada más aquí porque ya se han guardado los contactos durante la selección
    }
}
