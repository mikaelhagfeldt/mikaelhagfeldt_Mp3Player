package com.example.mikael.mikaelhagfeldt_mp3player;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity
{
    private ListView fieldListView;                                                                 // Musiken kommer att synas här
    private ArrayList<Music> fieldArrayList;                                                        // Musiken läggs in här

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
