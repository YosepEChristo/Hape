package com.example.ngampusaman.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ngampusaman.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewItem = requireView().findViewById<RecyclerView>(R.id.rv_most_liked)
        val daftarJudul = arrayOf<String>("Surat Izin Sakit", "Surat Pengunduran Diri", "Surat Ingin Menikah")
        val daftarKeterangan = arrayOf<String>("Ketika Mahasiswa tidak enak badan dan direkomendasikan pihak medis untuk beristirahat", "Ketika Capek Kuliah DO Solusinya", "Jomblo jangan tekan ini")

        val suratAdapter = SuratAdapter(daftarJudul, daftarKeterangan)
        recyclerViewItem.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = suratAdapter
        }
    }

}