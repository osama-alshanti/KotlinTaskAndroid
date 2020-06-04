package com.osamadev.kotlintask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1,  exportSchema = false)
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
                        "sleep_history_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance

                }
                return instance
            }
        }

    }

}