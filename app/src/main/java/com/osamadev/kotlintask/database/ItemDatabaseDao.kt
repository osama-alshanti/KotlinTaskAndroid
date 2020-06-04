package com.osamadev.kotlintask.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDatabaseDao {

    @Insert
    fun insert(night: Item)

    @Update
    fun update(night: Item)

    @Query("SELECT * from task_table WHERE id = :key")
    fun get(key: Long): Item?

    @Query("DELETE FROM task_table")
    fun clear()

    @Query("SELECT * FROM task_table ORDER BY id DESC")
    fun getAllItems(): LiveData<List<Item>>


    @Query("SELECT * from task_table WHERE id = :key")
    fun getItemWithId(key: Long): LiveData<Item>

}