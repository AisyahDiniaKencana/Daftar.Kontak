package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button tambah;
    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
//    private dbKontak MyDatabase;
//    private ArrayList<String> ListData;
    dbKontak mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tambah = findViewById(R.id.btnadd);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, tambahData.class);
                startActivity(i);
            }
        });
//        getSupportActionBar().setTitle("Daftar Mahasiswa");
//        obj = findViewById(R.id.listView1);
//        ListData = new ArrayList<>();
//        MyDatabase = new dbKontak(getBaseContext());
//        getData();
//        obj.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListData));
//    }
//
//    //Berisi Statement-Statement Untuk Mengambi Data dari Database
//    @SuppressLint("Recycle")
//    private void getData(){
//        //Mengambil Repository dengan Mode Membaca
//        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
//        Cursor cursor = ReadData.rawQuery("SELECT * FROM "+ dbKontak.MHS_TABLE_NAME,null);
//
//        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal
//
//        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
//        for(int count=0; count < cursor.getCount(); count++){
//
//            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir
//
//            ListData.add(cursor.getString(4));//Menambil Data Dari Kolom 1 (Nama)
//            //Lalu Memasukan Semua Datanya kedalam ArrayList
//        }


        mydb = new dbKontak(this);
        ArrayList array_list = mydb.getAllContacs();
        ArrayAdapter arrayAdapter=new
                ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        obj = (ListView)findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int
                    arg2, long arg3) {
                // TODO Auto-generated method stub
                int phone_To_Search = arg2 + 1;
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("phone", phone_To_Search);
                Intent intent = new
                        Intent(getApplicationContext(), profil.class);
                intent.putExtras(dataBundle);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.btnadd:Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);
                Intent intent = new
                        Intent(getApplicationContext(), tambahData.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
