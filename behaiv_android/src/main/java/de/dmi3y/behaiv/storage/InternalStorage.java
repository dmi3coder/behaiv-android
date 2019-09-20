package de.dmi3y.behaiv.storage;


import android.content.Context;

public class InternalStorage extends SimpleStorage {

    public InternalStorage(Context applicationContext) {
        super(applicationContext.getFilesDir());
    }
}
