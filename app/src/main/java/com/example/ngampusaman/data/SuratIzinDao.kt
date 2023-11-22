package com.example.ngampusaman.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ngampusaman.model.SuratIzin


@Dao
interface SuratIzinDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(suratIzin: SuratIzin)

    @Update
    suspend fun update(suratIzin: SuratIzin)

    @Delete
    suspend fun delete(suratIzin: SuratIzin)

    @Query("SELECT * FROM surat_izin_database")
    fun getAll(): LiveData<List<SuratIzin>>
}