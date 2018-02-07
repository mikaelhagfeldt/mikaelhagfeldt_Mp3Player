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


    }

    /*
        En metod för att hämta musikfilerna med ContentResolver, och använda Cursor
        för att komma åt dem.
     */

    public void getMusic()
    {
        ContentResolver contentResolver = getContentResolver();                                     // Från Developer Android
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
    }
}
