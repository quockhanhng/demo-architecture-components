package com.khanhnq.demo_architecture_components.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.khanhnq.demo_architecture_components.data.model.Launch
import com.khanhnq.demo_architecture_components.data.source.local.dao.LaunchDao
import com.khanhnq.demo_architecture_components.utils.DATABASE_NAME

@Database(entities = [Launch::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchDao(): LaunchDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
