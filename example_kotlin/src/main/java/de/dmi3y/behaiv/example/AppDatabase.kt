package de.dmi3y.behaiv.example

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import de.dmi3y.behaiv.example.data.Task
import de.dmi3y.behaiv.example.data.TaskDao


@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}