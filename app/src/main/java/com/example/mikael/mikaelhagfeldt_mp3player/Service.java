package com.example.mikael.mikaelhagfeldt_mp3player;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by mikae on 2018-02-13.
 */

public class Service extends android.app.Service
{

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
