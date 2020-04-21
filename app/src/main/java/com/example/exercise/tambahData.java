package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class tambahData extends AppCompatActivity {

    int from_Where_I_Am_Coming = 0;
    private dbKontak mydb ;
    EditText nama;
    EditText alamat;
    EditText email;
    EditText phone;
    int phone_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);


        nama = (EditText) findViewById(R.id.masukan);
        alamat = (EditText) findViewById(R.id.masukanalamat);
        email = (EditText) findViewById(R.id.masukanemail);
        phone = (EditText) findViewById(R.id.masukantelpon);
        mydb = new dbKontak(this);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("phone");
            if (Value > 0) {
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                phone_To_Update = Value;
                rs.moveToFirst();
                String nam  =
                        rs.getString(rs.getColumnIndex(dbKontak.MHS_COLUMN_NAMA));
                String phon  =
                        rs.getString(rs.getColumnIndex(dbKontak.MHS_COLUMN_TELEPON));
                String ala =
                        rs.getString(rs.getColumnIndex(dbKontak.MHS_COLUMN_ALAMAT));
                String ema =
                        rs.getString(rs.getColumnIndex(dbKontak.MHS_COLUMN_EMAIL));
                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.btnok);
                b.setVisibility(View.INVISIBLE);
                nama.setText((CharSequence) nam);
                nama.setFocusable(false);
                nama.setClickable(false);
                phone.setText((CharSequence) phon);
                phone.setFocusable(false);
                phone.setClickable(false);
                alamat.setText((CharSequence) ala);
                alamat.setFocusable(false);
                alamat.setClickable(false);
                email.setText((CharSequence) ema);
                email.setFocusable(false);
                email.setClickable(false);
            }
        }
    }
    public void run(View view){
        if(nama.getText().toString().equals("") || phone.getText().toString().equals("") || alamat.getText().toString().equals("") || email.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Data Harus Diidi Semua!", Toast.LENGTH_LONG).show();
        } else {
            mydb.insertContact(nama.getText().toString(), phone.getText().toString(), alamat.getText().toString(), email.getText().toString());
            Toast.makeText(getApplicationContext(), "Insert Data Berhasil!", Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("phone");
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.menu_main, menu);
            } else {
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        }
        return true;
    }
}

