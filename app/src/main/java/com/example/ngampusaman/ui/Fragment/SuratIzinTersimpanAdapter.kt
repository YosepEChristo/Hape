package com.example.ngampusaman.ui.Fragment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ngampusaman.R
import com.example.ngampusaman.model.SuratIzin
import com.example.ngampusaman.ui.Activity.IzinFormActivity

class SuratIzinTersimpanAdapter: RecyclerView.Adapter<SuratIzinTersimpanAdapter.ViewHolder>() {
    private var suratIzinList = emptyList<SuratIzin>()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJudulItem: TextView
        val tvNamaLengkapItem: TextView
        val tvNimItem: TextView
        val tvAlasanIzin: TextView
        val btnEditItemIzin: Button

        init {
            tvJudulItem = view.findViewById(R.id.tv_judul_item)
            tvNamaLengkapItem = view.findViewById(R.id.tv_nama_lengkap_item)
            tvNimItem = view.findViewById(R.id.tv_nim_item)
            tvAlasanIzin = view.findViewById(R.id.tv_alasan_izin)
            btnEditItemIzin = view.findViewById(R.id.btn_edit_item_izin)
        }
    }

    fun setDaftar(suratIzin: List<SuratIzin>) {
        this.suratIzinList = suratIzin
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tampilan_izin, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = suratIzinList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val suratKe = suratIzinList[position]
        holder.tvJudulItem.text = "Surat Izin ID ${suratKe.id.toString()}"
        holder.tvNamaLengkapItem.text = suratKe.NamaLengkap
        holder.tvNimItem.text = suratKe.NIM
        holder.tvAlasanIzin.text = suratKe.SebabIzin

        holder.btnEditItemIzin.setOnClickListener {
            val intent = Intent(it.context, IzinFormActivity::class.java)
            intent.apply {
                putExtra("suratIzin", suratKe)
            }
            it.context.startActivity(intent)
        }
    }
}