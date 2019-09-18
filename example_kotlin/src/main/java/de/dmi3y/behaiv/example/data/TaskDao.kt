package de.dmi3y.behaiv.example.data

import android.arch.persistence.room.*


@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE task.category = :category")
    fun getByCategory(category: String): List<Task>


    @Insert
    fun insertAll(vararg tasks: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

}