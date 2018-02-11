package com.example.mikael.mikaelhagfeldt_mp3player;

/**
 * Created by mikael on 2018-02-06.
 */

/*
    Klass för att hämta data från ljudfiler. En ljudfil skall innehålla titel, artist och id.
 */

public class Music
{
    private String fieldName;
    private String fieldArtist;
    private long fieldID;

    public Music(String fieldName, String fieldArtist, long fieldID)
    {
        this.fieldName = fieldName;
        this.fieldArtist = fieldArtist;
        this.fieldID = fieldID;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public void setFieldArtist(String fieldArtist)
    {
        this.fieldArtist = fieldArtist;
    }

    public void setFieldID(int fieldID)
    {
        this.fieldID = fieldID;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public String getFieldArtist()
    {
        return fieldArtist;
    }

    public long getFieldID()
    {
        return fieldID;
    }
}
