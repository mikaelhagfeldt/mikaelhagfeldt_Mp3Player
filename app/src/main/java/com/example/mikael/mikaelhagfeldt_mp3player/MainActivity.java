package com.example.mikael.mikaelhagfeldt_mp3player;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

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
    }

    /*
        En metod för att hämta musikfilerna med ContentResolver, och använda Cursor
        för att komma åt dem. Uppenbarligen försöker man komma åt data som finns i kolumner först,
        innan man lägger till detta i en arraylist.
     */

    public void getMusic()
    {
        ContentResolver contentResolver = getContentResolver();                                     // Från Developer Android
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        if (cursor.moveToFirst() && cursor != null)
        {

            int intColumn1 = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);                   // Hämtar data från kolumner
            int intColumn2 = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int intColumn3 = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            do
            {
                int intID = cursor.getInt(intColumn2);                                              // Lägger till musik i arraylist
                String strTitle = cursor.getString(intColumn1);
                String strArtist = cursor.getString(intColumn3);
                Music newMusic = new Music(strTitle, strArtist, intID);
                this.fieldArrayList.add(newMusic);
            }
            while (cursor.moveToNext());
        }
    }
}
