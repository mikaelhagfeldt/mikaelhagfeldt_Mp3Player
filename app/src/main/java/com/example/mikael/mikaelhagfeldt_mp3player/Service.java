package com.example.mikael.mikaelhagfeldt_mp3player;

import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
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
    private IBinder fieldIBinder = new Binder();                                                    // Vår "binder" som kopplas till MAIN.
    private ArrayList<Music> fieldList;                                                             // Lista med låtar
    private MediaPlayer fieldMediaPlayer;                                                           // Min Media Player klass
    private int fieldMusicIndex;                                                                    // Låtens index i listan

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return this.fieldIBinder;                                                                   // Agerar som getter metod för "binder"
    }

    /*
        Måste ha denna metod för att göra sig av med onödig data då kopplingen till Service
        går bort. Dvs när man stänger av applikationen.
     */

    @Override
    public boolean onUnbind(Intent intent)
    {
        this.fieldMediaPlayer.stop();
        this.fieldMediaPlayer.release();
        return false;
    }

    /*
        Metoden som ser till att musik faktiskt spelas upp. Enligt Developer Android ska man helst
        lägga i logiken för MediaPlayer inuti Service klassen, eftersom det är där logiken för
        uppspelning ska ligga generellt.

        Metoden hämtar aktuell låt, tar dess ID och kopplar denna till URI.
     */

    public void playMusic()
    {
        this.fieldMediaPlayer.reset();                                                              // Startar alltid med en reset() eft. spela upp ny musik utan trubbel
        Music localMusicFile = this.fieldList.get(this.fieldMusicIndex);
        long localCurrentSong = localMusicFile.getFieldID();
        Uri localUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, localCurrentSong);
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

        establishMP3Player();
    }

    /*
        En metod som hämtar listan på låtar som skapas från MAIN.
     */

    public void receiveListFromMain(ArrayList<Music> paramList)
    {
        this.fieldList = paramList;
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

    /*
        Lägger till nödvändiga "features" till vår MediaPlayer för att få allting att fungera.
     */

    public void establishMP3Player()
    {
        this.fieldMediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK); // Från Developer Android, WAKE LOCK gör så att uppspelning i bakgrunden är möjlig.
        this.fieldMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);                        // Enligt Developer Android gammal kod, men finner ingen work around.

        // Kopplar de implementerade metoderna till "state of media player"

        this.fieldMediaPlayer.setOnPreparedListener(this);
        this.fieldMediaPlayer.setOnCompletionListener(this);
        this.fieldMediaPlayer.setOnErrorListener(this);
    }

    /*
        Implementerar ett "binder objekt" för att agera som brygga mellan MAIN och Service klassen.
        Använder "inre klass" för detta. Använder denna klass från MAIN.
        Kodsnutt från YouTube.
     */

    public class Binder extends android.os.Binder
    {
        Service getService()
        {
            return Service.this;
        }
    }


}
