package com.example.ngampusaman.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ngampusaman.data.SuratIzinDatabase
import com.example.ngampusaman.data.SuratIzinRepository
import com.example.ngampusaman.model.SuratIzin
import kotlinx.coroutines.launch

class SuratIzinViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<SuratIzin>>
    private val repository: SuratIzinRepository

    init {
        val suratIzinDao = SuratIzinDatabase.getInstance(application).suratIzinDao()
        repository = SuratIzinRepository(suratIzinDao)
        readAllData = suratIzinDao.getAll()
    }

    fun insert(suratIzin: SuratIzin) {
        viewModelScope.launch {
            repository.insert(suratIzin)
        }
    }

    fun update(suratIzin: SuratIzin) {
        viewModelScope.launch {
            repository.update(suratIzin)
        }
    }

    fun delete(suratIzin: SuratIzin) {
        viewModelScope.launch {
            repository.delete(suratIzin)
        }
    }
}