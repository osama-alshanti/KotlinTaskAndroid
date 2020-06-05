package com.osamadev.kotlintask.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDatabaseDao {

    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("SELECT * from task_table WHERE id = :key")
    fun get(key: Long): Item?

    @Query("UPDATE task_table SET flag = :flag WHERE id = :id")
    fun updateItem(id: Long, flag: Boolean?): Int

    @Query("DELETE FROM task_table")
    fun clear()

    @Query("SELECT * FROM task_table ORDER BY id")
    fun getAllItems(): LiveData<List<Item>>


    @Query("SELECT * from task_table WHERE id = :key")
    fun getItemWithId(key: Long): LiveData<Item>

    @Query("SELECT * FROM task_table ORDER BY id DESC LIMIT 1")
    fun getItem(): Item?

}