package com.example.mikael.mikaelhagfeldt_mp3player;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity
{
    private Service fieldService;                                                                   // Åtkomst till klass Service där musiken spelas
    private Intent fieldIntent;                                                                     // En intent för att överföra väsentlig data
    private boolean fieldIsBound = false;                                                           // Hålla reda på om MAIN är kopplad till Service
    private ListView fieldListView;                                                                 // Musiken kommer att synas här
    private ArrayList<Music> fieldArrayList;                                                        // Musiken läggs in här

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)                                                             // Jag måste be om tillåtelse?
        {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                return;
            }
        }

        this.fieldListView = findViewById(R.id.id_listView);
        this.fieldArrayList = new ArrayList<>();

        getMusic();

        Collections.sort(this.fieldArrayList, new Comparator<Music>()                               // Listar låtarna i alfabetisk ordning
        {
            @Override
            public int compare(Music music, Music t1)
            {
                return music.getFieldName().compareTo(t1.getFieldName());
            }
        });

        Adapter localAdapter = new Adapter(this, this.fieldArrayList);
        this.fieldListView.setAdapter(localAdapter);
    }

    private ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            Binder musicBinder = (Binder) iBinder;




        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {

        }
    }

    /*
        En metod för att hämta musikfilerna med ContentResolver, och använda Cursor
        för att komma åt dem. Uppenbarligen försöker man komma åt data som finns i kolumner först,
        innan man lägger till detta i en arraylist.
     */

    public void getMusic()
    {
        ContentResolver contentResolver = getContentResolver();                                     // Från Developer Android
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        if (cursor.moveToFirst() && cursor != null)
        {

            int intColumn1 = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);                   // Hämtar data från kolumner
            int intColumn2 = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
            int intColumn3 = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);

            do
            {
                long intID = cursor.getLong(intColumn2);                                              // Lägger till musik i arraylist
                String strTitle = cursor.getString(intColumn1);
                String strArtist = cursor.getString(intColumn3);
                this.fieldArrayList.add(new Music(strTitle, strArtist, intID));
            }
            while (cursor.moveToNext());
        }
    }
}
