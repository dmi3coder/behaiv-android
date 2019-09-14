package de.dmi3y.behaiv.example;

import android.support.multidex.MultiDexApplication;
import android.util.Log;
import de.dmi3y.behaiv.Behaiv;
import de.dmi3y.behaiv.provider.AudioSettingsProvider;
import de.dmi3y.behaiv.provider.DayTimeProvider;
import de.dmi3y.behaiv.provider.GpsProvider;

public class MainApplication extends MultiDexApplication {
    private Behaiv behaiv;

    @Override
    public void onCreate() {
        super.onCreate();
        behaiv = Behaiv.with("chats")
                .setProvider(new GpsProvider(this))
                .setProvider(new DayTimeProvider())
                .setProvider(
                        new AudioSettingsProvider.Builder(this).checkHeadsetConnected().build()
                );
        //14 sep 2019 Finally compiled Behaiv for android after 16 hours of debugging
        Log.d("BEHAIV_EXAMPLE", "WE'RE HERE GUYS!!!");

    }
}
