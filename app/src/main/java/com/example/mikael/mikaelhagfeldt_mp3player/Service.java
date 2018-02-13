package com.example.mikael.mikaelhagfeldt_mp3player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by mikael on 2018-02-13.
 */

/*
    En klass som låter applikationen spela upp musik i bakgrunden, trots att annan aktivitet
    utförs. Det verkar också som att det är Service klassen som interagerar med klassen MediaPlayer
    som jag använde i min tidigare applikation.

    Låtar --> Service klass & spelas upp med MediaPlayer klass.
 */

public class Service extends android.app.Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener   // Developer Android
{
    private ArrayList<Music> fieldList;                                                             // Lista med låtar
    private MediaPlayer fieldMediaPlayer;                                                           // Min Media Player klass
    private int fieldMusicIndex;                                                                         // Låtens index i listan

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    /*
        Verkar inte vara samma onCreate() som i Main. Enligt post på stack så måste man använda metod
        från Service, instansiera MediaPlayer klassen och skapa en form av counter för att hålla koll
        på aktuellt index.
     */

    public void onCreate()
    {
        super.onCreate();
        this.fieldMediaPlayer = new MediaPlayer();

        this.fieldMusicIndex = 0; // Från början, index 0
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer)
    {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1)
    {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer)
    {

    }
}
