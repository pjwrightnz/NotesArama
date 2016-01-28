package com.example.pjw527.notesarama;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by pjw527 on 28/01/2016.
 */
public class BoundWordCountService extends Service {

    protected LocalBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        public BoundWordCountService getService() {
            return BoundWordCountService.this;
        }
    }

    public void onCreate() {
    }

    public IBinder onBind(Intent intent) {
        return binder;
    }


    public static int countWords(ArrayList<String> al) {

        int wordCount = 0;
        for (String s : al) {
             wordCount = wordCount + s.split(" ").length;
        }
        return wordCount;
    }
}
