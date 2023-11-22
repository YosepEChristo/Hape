package com.example.ngampusaman.ui.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ngampusaman.R
import com.example.ngampusaman.databinding.FragmentIzinFormBinding
import com.example.ngampusaman.model.SuratIzin
import com.example.ngampusaman.viewmodel.SuratIzinViewModel


class IzinFormFragment : Fragment() {
    private var _binding: FragmentIzinFormBinding? = null
    private val binding get() = _binding!!

    private lateinit var suratIzinViewModel: SuratIzinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIzinFormBinding.inflate(inflater, container, false)

        suratIzinViewModel = ViewModelProvider(this)[SuratIzinViewModel::class.java]

        binding.btnKirimSekarang.setOnClickListener {
            Toast.makeText(requireContext(), "Kirim Sekarang", Toast.LENGTH_SHORT).show()
        }

        binding.btnSimpanData.setOnClickListener {
            masukkanDataKeDatabase()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun masukkanDataKeDatabase() {
        val NamaDosen = binding.edtNamaDosen.text.toString()
        val MatkulDosen = binding.edtMatkulDosen.text.toString()
        val NamaLengkap = binding.edtNamaLengkap.text.toString()
        val NIM = binding.edtNim.text.toString()
        val NoHP = binding.edtNoHp.text.toString()
        val Prodi = binding.edtProdi.text.toString()
        val SebabIzin = binding.edtSebabIzin.text.toString()
        val DurasiIzin = binding.edtDurasiIzin.text
        val TanggalMulai = binding.edtTanggalMulai.text.toString()
        val TanggalSelesai = binding.edtTanggalSurat.text.toString()
        val TanggalSuratDibuat = binding.edtTanggalSurat.text.toString()
        val HariTabel = binding.edtHari.text.toString()
        val JamTabel = binding.edtJam.text.toString()
        val Ruang = binding.edtRuang.text.toString()

        if (cekMasukan(NamaDosen, MatkulDosen,
            NamaLengkap, NIM,
            NoHP, Prodi,
            SebabIzin, DurasiIzin,
            TanggalMulai, TanggalSelesai,
            TanggalSuratDibuat, HariTabel,
            JamTabel, Ruang)) {
            val suratIzin = SuratIzin(0,
                NamaDosen, MatkulDosen,
                NamaLengkap, NIM, NoHP, Prodi, SebabIzin,
                Integer.parseInt(DurasiIzin.toString()),
                TanggalMulai, TanggalSelesai, TanggalSuratDibuat,
                HariTabel, JamTabel, Ruang)
            Toast.makeText(requireContext(), "Surat izin telah ditambahkan", Toast.LENGTH_SHORT).show()
            suratIzinViewModel.insert(suratIzin)

        } else {
            Toast.makeText(requireContext(), "Tolong isi semua", Toast.LENGTH_LONG).show()
        }
    }

    private fun cekMasukan(namaDosen: String,
                           matkulDosen: String,
                           namaLengkap: String,
                           NIM: String,
                           noHP: String,
                           prodi: String,
                           sebabIzin: String,
                           durasiIzin: Editable,
                           tanggalMulai: String,
                           tanggalSelesai: String,
                           tanggalSurat: String,
                           hari: String,
                           jam: String,
                           ruang: String) : Boolean {
        return !(TextUtils.isEmpty(namaDosen) &&
                TextUtils.isEmpty(matkulDosen) &&
                TextUtils.isEmpty(namaLengkap) &&
                TextUtils.isEmpty(NIM) &&
                TextUtils.isEmpty(noHP) &&
                TextUtils.isEmpty(prodi) &&
                TextUtils.isEmpty(sebabIzin) &&
                durasiIzin.isEmpty() &&
                TextUtils.isEmpty(tanggalMulai) &&
                TextUtils.isEmpty(tanggalSelesai) &&
                TextUtils.isEmpty(tanggalSurat) &&
                TextUtils.isEmpty(hari) &&
                TextUtils.isEmpty(jam) &&
                TextUtils.isEmpty(ruang))
    }
}