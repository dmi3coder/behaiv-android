package de.dmi3y.behaiv.storage;


import android.app.Application;
import android.content.Context;

import java.io.File;
import java.io.IOException;

public class InternalStorage extends SimpleStorage {

    public InternalStorage(Context applicationContext) {
        super(applicationContext.getFilesDir());
    }
}
