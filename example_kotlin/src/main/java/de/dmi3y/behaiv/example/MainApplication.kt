package de.dmi3y.behaiv.example

import android.arch.persistence.room.Room
import android.content.Context
import android.support.multidex.MultiDexApplication
import android.util.Log
import de.dmi3y.behaiv.Behaiv
import de.dmi3y.behaiv.provider.DayTimeProvider
import de.dmi3y.behaiv.provider.GpsProvider
import de.dmi3y.behaiv.storage.InternalStorage

class MainApplication : MultiDexApplication() {
    public var behaiv: Behaiv? = null
    public lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "tasks"
        ).build()


        behaiv = Behaiv.with("tasks")
            .setProvider(GpsProvider(this))
            .setProvider(DayTimeProvider())
            .setStorage(InternalStorage(applicationContext))

//            .setProvider(
//                AudioSettingsProvider.Builder(this).checkHeadsetConnected().build()
//            )


        //14 sep 2019 Finally compiled Behaiv for android after 16 hours of debugging
        Log.d("BEHAIV_EXAMPLE", "WE'RE HERE GUYS!!!")

    }
}

public fun Context.db() = (this as MainApplication).db
public fun Context.behaiv() = (this as MainApplication).behaiv
