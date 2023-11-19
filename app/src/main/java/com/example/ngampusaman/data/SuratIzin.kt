package com.example.ngampusaman.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "suratIzin_table")
data class SuratIzin(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val NamaDosen: String,
    val MatkulDosen: String,
    val NamaLengkap: String,
    val NIM: String,
    val NoHP: String,
    val Prodi: String,
    val SebabIzin: String,
    val DurasiIzin: Int,
    val TanggalMulai: String,
    val TanggalSelesai: String,
    val TanggalSuratDibuat: String,
    val HariTabel: String,
    val JamTabel: String,
    val Ruang: String
) {
    fun getId(): Int = id
}
