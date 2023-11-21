package com.example.ngampusaman.data

class SuratIzinRepository(private val suratIzinDao: SuratIzinDao) {
    fun insert(suratIzin: SuratIzin) {
        suratIzinDao.insert(suratIzin)
    }

     fun insertAll(vararg suratIzin: SuratIzin) {
        suratIzinDao.insertAll(*suratIzin)
    }

     fun delete(suratIzin: SuratIzin) {
        suratIzinDao.delete(suratIzin)
    }

     fun get(uid: Int): SuratIzin {
        return suratIzinDao.get(uid)
    }

     fun getAll(): List<SuratIzin> {
        return suratIzinDao.getAll()
    }
}