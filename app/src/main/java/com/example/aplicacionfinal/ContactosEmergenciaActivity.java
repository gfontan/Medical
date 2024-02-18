package com.example.aplicacionfinal;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ContactosEmergenciaActivity extends Activity {

    private static final int REQUEST_READ_CONTACTS = 1;
    private ArrayList<String> contactosList;
    private ArrayAdapter<String> adapter;
    private Set<String> contactosGuardados;
    private ContactoPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos_emergencia);

        ImageView btnbackpage = findViewById(R.id.back);
        btnbackpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactosEmergenciaActivity.this, Seguridad.class);
                startActivity(intent);
            }
        });


        ListView listView = findViewById(R.id.listView);
        Button addButton = findViewById(R.id.addButton);

        prefManager = new ContactoPrefManager(this);
        contactosGuardados = prefManager.getContactos();

        contactosList = new ArrayList<>(contactosGuardados);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactosList);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ContactosEmergenciaActivity.this, Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContactosEmergenciaActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
                } else {
                    openContacts();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contactoSeleccionado = contactosList.get(position);
                realizarLlamada(contactoSeleccionado);
            }
        });
    }

    private void openContacts() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, REQUEST_READ_CONTACTS);
    }

    private void realizarLlamada(String contactName) {
        String phoneNumber = getContactPhoneNumber(contactName);
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        } else {
            Toast.makeText(this, "No se encontró el número de teléfono del contacto", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("Range")
    private String getContactPhoneNumber(String contactName) {
        String phoneNumber = null;
        Cursor cursor = null;
        try {
            ContentResolver contentResolver = getContentResolver();
            Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(contactName));
            cursor = contentResolver.query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                @SuppressLint("Range") String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                if (phones != null && phones.moveToFirst()) {
                    phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phones.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return phoneNumber;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openContacts();
            } else {
                Toast.makeText(this, "Permiso de lectura de contactos denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_READ_CONTACTS && resultCode == RESULT_OK) {
            String contactName = getContactName(data);
            if (contactName != null && !contactName.isEmpty()) {
                if (!contactosGuardados.contains(contactName)) {
                    contactosList.add(contactName);
                    contactosGuardados.add(contactName);
                    adapter.notifyDataSetChanged();
                    prefManager.guardarContactos(contactosGuardados);
                } else {
                    Toast.makeText(this, "El contacto ya está añadido", Toast.LENGTH_SHORT).show();
                }
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
}
