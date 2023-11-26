package com.example.ngampusaman.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ngampusaman.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val toTemplateFragment: CardView = view.findViewById(R.id.cv_one)
        toTemplateFragment.setOnClickListener {
            findNavController()
                .navigate(
                    R.id.action_homeFragment_to_templateFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.homeFragment, true)
                        .build()
                )
        }

        val toFAQFragment: CardView = view.findViewById(R.id.cv_two)
        toFAQFragment.setOnClickListener {
            findNavController()
                .navigate(
                    R.id.action_homeFragment_to_faqFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.homeFragment, true)
                        .build()
                )
        }

        val toContactUsFragment: CardView = view.findViewById(R.id.cv_three)
        toContactUsFragment.setOnClickListener {
            findNavController()
                .navigate(
                    R.id.action_homeFragment_to_contactus,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.homeFragment, true)
                        .build()
                )
        }

        val toAboutUsFragment: CardView = view.findViewById(R.id.cv_four)
        toAboutUsFragment.setOnClickListener {
            findNavController()
                .navigate(
                    R.id.action_homeFragment_to_aboutusFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.homeFragment, true)
                        .build()
                )
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewItem = requireView().findViewById<RecyclerView>(R.id.rv_most_liked)
        val daftarJudul =
            arrayOf<String>("Surat Izin Sakit", "Surat Pengunduran Diri", "Surat Ingin Menikah")
        val daftarKeterangan = arrayOf<String>(
            "Ketika Mahasiswa tidak enak badan dan direkomendasikan pihak medis untuk beristirahat",
            "Ketika Capek Kuliah DO Solusinya",
            "Jomblo jangan tekan ini"
        )

        val suratAdapter = SuratAdapter(daftarJudul, daftarKeterangan)
        recyclerViewItem.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = suratAdapter
        }
    }

}