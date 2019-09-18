package de.dmi3y.behaiv.example.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    @PrimaryKey
    var id: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "checked")
    var checked: String,
    //Don't have time to write convertest for now
    @ColumnInfo(name = "created_at")
    var createdAt: String,
    @ColumnInfo(name = "finished_at")
    var finishedAt: String
)