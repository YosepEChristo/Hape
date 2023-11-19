package com.example.ngampusaman.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [SuratIzin::class], version=1)
abstract class SuratIzinDatabase: RoomDatabase() {
    abstract fun suratIzinDao(): SuratIzinDao

    companion object {
        // --- SINGLETON ---
        @Volatile
        private var INSTANCE: SuratIzinDatabase? = null

        fun getInstance(context: Context): SuratIzinDatabase? {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    SuratIzinDatabase::class.java, "suratIzin_table")
                    .build()
                INSTANCE = instance
                INSTANCE
            }
        }
    }
}