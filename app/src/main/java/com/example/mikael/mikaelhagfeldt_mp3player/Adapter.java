package com.example.mikael.mikaelhagfeldt_mp3player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mikael on 2018-02-11.
 */

/*
    En klass som ansvarar för att skapa en "View" för varje element i en lista.
 */

public class Adapter extends BaseAdapter
{
    private ArrayList<Music> fieldMusicArrayList;
    private LayoutInflater fieldLayoutInflator;                                                     // Omvandlar XML layout fil till View objekt.

    public Adapter(Context paramContext, ArrayList<Music> paramList)
    {
        this.fieldMusicArrayList = paramList;
        this.fieldLayoutInflator = LayoutInflater.from(paramContext);
    }

    @Override
    public int getCount()
    {
        return this.fieldMusicArrayList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LinearLayout localLinearLayout = (LinearLayout)fieldLayoutInflator.inflate(R.layout.music, parent, false);
        TextView localTextViewName = localLinearLayout.findViewById(R.id.id_musicTitle);
        TextView localTextViewArtist =
    }
}
