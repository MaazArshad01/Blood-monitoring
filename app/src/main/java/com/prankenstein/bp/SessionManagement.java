package com.prankenstein.bp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abc on 12/10/2016.
 */
public class SessionManagement {
    SharedPreferences myPrefs;
    SharedPreferences.Editor editor;


    public SessionManagement(Context context) {

        myPrefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        editor = myPrefs.edit();
    }


    public boolean issoundCheckedd() {
        return myPrefs.getBoolean("issoundCheckedd", false);
    }

    public void setsoundCheckedd(boolean issoundCheckedd) {
        editor.putBoolean("issoundCheckedd", issoundCheckedd);
        editor.commit();
    }


    public boolean isvibCheckedd() {
        return myPrefs.getBoolean("isvibCheckedd", false);
    }

    public void setvibCheckedd(boolean isvibCheckedd) {
        editor.putBoolean("isvibCheckedd", isvibCheckedd);
        editor.commit();
    }







}
