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
        // Behövs inte?

        return null;
    }

    @Override
    public long getItemId(int i)
    {
        // Behövs inte??

        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LinearLayout localLinearLayout = (LinearLayout)fieldLayoutInflator.inflate(R.layout.music, viewGroup, false);
        TextView localTextViewName = localLinearLayout.findViewById(R.id.id_musicTitle);
        TextView localTextViewArtist = localLinearLayout.findViewById(R.id.id_musicArtist);
        Music localMusic = this.fieldMusicArrayList.get(i);
        localTextViewName.setText(localMusic.getFieldName());
        localTextViewArtist.setText(localMusic.getFieldArtist());
        localLinearLayout.setTag(i);
        return localLinearLayout;
    }
}
