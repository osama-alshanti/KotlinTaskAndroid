package com.osamadev.kotlintask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.osamadev.kotlintask.adapter.ItemAdapter

@Database(entities = [Item::class], version = 16,  exportSchema = false)
abstract class ItemDatabase: RoomDatabase() {

    abstract val itemDatabaseDao: ItemDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null


        fun getInstance(context: Context): ItemDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, ItemDatabase::class.java,
                        "task-database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance

                }
                return instance
            }
        }

    }

}