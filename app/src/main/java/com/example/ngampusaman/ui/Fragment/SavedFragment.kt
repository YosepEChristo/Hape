package com.example.ngampusaman.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ngampusaman.R
import com.example.ngampusaman.databinding.FragmentSavedBinding
import com.example.ngampusaman.viewmodel.SuratIzinViewModel


class SavedFragment : Fragment() {
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private lateinit var mSuratIzinViewModel: SuratIzinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)

        val suratIzinAdapter = SuratIzinTersimpanAdapter()
        binding.rvSaved.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = suratIzinAdapter
        }

        mSuratIzinViewModel = ViewModelProvider(this)[SuratIzinViewModel::class.java]
        mSuratIzinViewModel.readAllData.observe(viewLifecycleOwner, Observer { suratIzin ->
            suratIzinAdapter.setDaftar(suratIzin)
        })

        return binding.root
    }
}