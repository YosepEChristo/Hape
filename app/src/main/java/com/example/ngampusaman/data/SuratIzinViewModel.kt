package com.example.ngampusaman.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SuratIzinViewModel(private val suratIzinRepository: SuratIzinRepository): ViewModel() {
    fun insert(suratIzin: SuratIzin) {
        viewModelScope.launch {
            suratIzinRepository.insert(suratIzin)
        }
    }

    fun insertAll(vararg suratIzin: SuratIzin) {
        viewModelScope.launch {
            suratIzinRepository.insertAll(*suratIzin)
        }
    }

    fun delete(suratIzin: SuratIzin) {
        viewModelScope.launch {
            suratIzinRepository.delete(suratIzin)
        }
    }

    fun get(uid: Int): SuratIzin {
        return suratIzinRepository.get(uid)
    }

    fun getAll(): List<SuratIzin> {
        return suratIzinRepository.getAll()
    }
}