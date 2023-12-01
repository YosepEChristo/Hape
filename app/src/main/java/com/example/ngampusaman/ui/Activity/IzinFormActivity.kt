package com.example.ngampusaman.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.ngampusaman.R
import com.example.ngampusaman.databinding.ActivityIzinFormBinding
import com.example.ngampusaman.model.SuratIzin
import com.example.ngampusaman.model.SuratIzinDocx
import com.example.ngampusaman.viewmodel.SuratIzinViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class IzinFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIzinFormBinding

    private lateinit var mSuratIzinViewModel: SuratIzinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIzinFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSuratIzinViewModel = ViewModelProvider(this)[SuratIzinViewModel::class.java]

        binding.btnSimpanData.setOnClickListener {
            masukkanDatakeDatabase()
        }

        binding.btnMasukkanData.setOnClickListener {
            simpanDokumenKeDownload()
        }
    }

    private fun simpanDokumenKeDownload() {
        val namaDosen = binding.edtNamaDosen.text.toString()
        val matkul = binding.edtMataKuliah.text.toString()
        val namaLengkap = binding.edtNamaLengkap.text.toString()
        val nim = binding.edtNim.text.toString()
        val noTelp = binding.edtNoHp.text.toString()
        val prodi = binding.edtProdiKonsentrasi.text.toString()
        val alasan = binding.edtAlasanIzin.text.toString()
        val jumlahHari = binding.edtDurasiIzin.text.toString()
        val tglAwal = binding.edtTanggalMulai.text.toString()
        val tglAkhir = binding.edtTanggalSelesai.text.toString()
        val tglSurat = binding.edtTanggalSurat.text.toString()
        val hariTabel = binding.edtHari.text.toString()
        val jamTabel = binding.edtJam.text.toString()
        val ruangTabel = binding.edtRuang.text.toString()

        val daftarNama = listOf(namaDosen, matkul, namaLengkap, nim,
            noTelp, prodi, alasan, jumlahHari,
            tglAwal, tglAkhir, tglSurat,
            hariTabel, jamTabel, ruangTabel)

        val lokasi_download = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val suratIzin = SuratIzinDocx(namaLengkap, filesDir, applicationContext, daftarNama)
        suratIzin.editDoc()
        suratIzin.saveDoc(lokasi_download)
        Toast.makeText(applicationContext, "Tersimpan di $lokasi_download", Toast.LENGTH_SHORT).show()
    }

    private fun masukkanDatakeDatabase() {
        val namaDosen = binding.edtNamaDosen.text.toString()
        val matkul = binding.edtMataKuliah.text.toString()
        val namaLengkap = binding.edtNamaLengkap.text.toString()
        val nim = binding.edtNim.text.toString()
        val noTelp = binding.edtNoHp.text.toString()
        val prodi = binding.edtProdiKonsentrasi.text.toString()
        val alasan = binding.edtAlasanIzin.text.toString()
        val jumlahHari = binding.edtDurasiIzin.text.toString()
        val tglAwal = binding.edtTanggalMulai.text.toString()
        val tglAkhir = binding.edtTanggalSelesai.text.toString()
        val tglSurat = binding.edtTanggalSurat.text.toString()
        val hariTabel = binding.edtHari.text.toString()
        val jamTabel = binding.edtJam.text.toString()
        val ruangTabel = binding.edtRuang.text.toString()

        if (cekMasukan(namaDosen, matkul, namaLengkap, nim,
            noTelp, prodi, alasan, jumlahHari,
            tglAwal, tglAkhir, tglSurat,
            hariTabel, jamTabel, ruangTabel)) {
            val suratIzin = SuratIzin(0, namaDosen, matkul,
                namaLengkap, nim, noTelp, prodi, alasan,
                jumlahHari.toInt(), tglAwal, tglAkhir, tglSurat,
                hariTabel, jamTabel, ruangTabel)
            mSuratIzinViewModel.insert(suratIzin)
            Toast.makeText(applicationContext, "${namaLengkap} sukses ditambah", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun cekMasukan(vararg teks: String): Boolean {
        var berisi = true
        teks.forEach {
            berisi = berisi && (!TextUtils.isEmpty(it))
        }
        return berisi
    }
}