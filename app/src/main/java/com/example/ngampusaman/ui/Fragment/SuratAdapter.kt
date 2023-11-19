package com.example.ngampusaman.ui.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ngampusaman.R

class SuratAdapter(private val dataJudul: Array<String>, private val dataKeterangan: Array<String>) :RecyclerView.Adapter<SuratAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJudulItem: TextView
        val tvKeteranganItem: TextView
        val cvTombolItem: CardView

        init {
            tvJudulItem =view.findViewById(R.id.tv_judul_item)
            tvKeteranganItem =view.findViewById(R.id.tv_keterangan_item)
            cvTombolItem = view.findViewById(R.id.cv_tombol_item)
        }
    }

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(vg.context).inflate(R.layout.item_surat, vg, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataJudul.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvJudulItem.text = dataJudul[position]
        viewHolder.tvKeteranganItem.text = dataKeterangan[position]

        viewHolder.cvTombolItem.setOnClickListener {
            Toast.makeText(it.context, "Posisi ke ${position}", Toast.LENGTH_SHORT).show()
        }
    }
}