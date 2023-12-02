package com.example.ngampusaman.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
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

    var idIzin = 0

    private lateinit var mSuratIzinViewModel: SuratIzinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIzinFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSuratIzinViewModel = ViewModelProvider(this)[SuratIzinViewModel::class.java]

        if (intent.hasExtra("suratIzin")) {
            val product = intent.getParcelableExtra<SuratIzin>("suratIzin")!!

            idIzin = product.id
            binding.edtNamaDosen.setText(product.NamaDosen)
            binding.edtMataKuliah.setText(product.MatkulDosen)
            binding.edtNamaLengkap.setText(product.NamaLengkap)
            binding.edtNim.setText(product.NIM)
            binding.edtNoHp.setText(product.NoHP)
            binding.edtProdiKonsentrasi.setText(product.Prodi)
            binding.edtAlasanIzin.setText(product.SebabIzin)
            binding.edtDurasiIzin.setText(product.DurasiIzin.toString())
            binding.edtTanggalMulai.setText(product.TanggalMulai)
            binding.edtTanggalSelesai.setText(product.TanggalSelesai)
            binding.edtTanggalSurat.setText(product.TanggalSuratDibuat)
            binding.edtHari.setText(product.HariTabel)
            binding.edtJam.setText(product.JamTabel)
            binding.edtRuang.setText(product.Ruang)

            binding.btnSimpanData.text = "Perbarui Surat"

            binding.btnSimpanData.setOnClickListener {
                perbaruiDatakeDatabase()
            }

            binding.btnMasukkanData.setOnClickListener {
                perbaruiDokumenKeDownload()
            }
        } else {
            binding.btnSimpanData.setOnClickListener {
                masukkanDatakeDatabase()
            }

            binding.btnMasukkanData.setOnClickListener {
                simpanDokumenKeDownload()
            }
        }
    }

    private fun perbaruiDokumenKeDownload() {
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
            val daftarNama = listOf(namaDosen, matkul, namaLengkap, nim,
                noTelp, prodi, alasan, jumlahHari,
                tglAwal, tglAkhir, tglSurat,
                hariTabel, jamTabel, ruangTabel)

            val lokasi_download = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val suratIzinDocx = SuratIzinDocx(namaLengkap, filesDir, applicationContext, daftarNama)
            suratIzinDocx.editDoc()
            suratIzinDocx.saveDoc(lokasi_download)
            Toast.makeText(applicationContext, "File $namaLengkap Tersimpan di $lokasi_download", Toast.LENGTH_SHORT).show()

            val suratIzin = SuratIzin(0, namaDosen, matkul,
                namaLengkap, nim, noTelp, prodi, alasan,
                jumlahHari.toInt(), tglAwal, tglAkhir, tglSurat,
                hariTabel, jamTabel, ruangTabel)
            mSuratIzinViewModel.update(suratIzin)

            Toast.makeText(applicationContext, "${namaLengkap} sukses diperbarui", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(applicationContext, "Tolong isi semua modul", Toast.LENGTH_SHORT).show()
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

        if (cekMasukan(namaDosen, matkul, namaLengkap, nim,
                noTelp, prodi, alasan, jumlahHari,
                tglAwal, tglAkhir, tglSurat,
                hariTabel, jamTabel, ruangTabel)) {
            val daftarNama = listOf(namaDosen, matkul, namaLengkap, nim,
                noTelp, prodi, alasan, jumlahHari,
                tglAwal, tglAkhir, tglSurat,
                hariTabel, jamTabel, ruangTabel)

            val lokasi_download = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val suratIzinDocx = SuratIzinDocx(namaLengkap, filesDir, applicationContext, daftarNama)
            suratIzinDocx.editDoc()
            suratIzinDocx.saveDoc(lokasi_download)
            Toast.makeText(applicationContext, "File $namaLengkap Tersimpan di $lokasi_download", Toast.LENGTH_SHORT).show()

            val suratIzin = SuratIzin(0, namaDosen, matkul,
                namaLengkap, nim, noTelp, prodi, alasan,
                jumlahHari.toInt(), tglAwal, tglAkhir, tglSurat,
                hariTabel, jamTabel, ruangTabel)
            mSuratIzinViewModel.insert(suratIzin)

            Toast.makeText(applicationContext, "${namaLengkap} sukses ditambah", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(applicationContext, "Tolong isi semua modul", Toast.LENGTH_SHORT).show()
        }
    }

    private fun perbaruiDatakeDatabase() {
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
            val suratIzin = SuratIzin(idIzin, namaDosen, matkul,
                namaLengkap, nim, noTelp, prodi, alasan,
                jumlahHari.toInt(), tglAwal, tglAkhir, tglSurat,
                hariTabel, jamTabel, ruangTabel)
            mSuratIzinViewModel.update(suratIzin)
            Toast.makeText(applicationContext, "${namaLengkap} sukses diperbarui", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(applicationContext, "Tolong isi semua modul", Toast.LENGTH_SHORT).show()
        }
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
        } else {
            Toast.makeText(applicationContext, "Tolong isi semua modul", Toast.LENGTH_SHORT).show()
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