package com.example.ngampusaman.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface SuratIzinDao {
    @Insert
    fun insertAll(vararg suratIzin: SuratIzin)

    @Insert
    fun insert(suratIzin: SuratIzin)

    @Delete
    fun delete(suratIzin: SuratIzin)

    @Query("SELECT * FROM user WHERE uid=:uid")
    fun get(uid: Int): SuratIzin

    @Query("SELECT * FROM tableSuratIzin")
    fun getAll(): List<SuratIzin>
}