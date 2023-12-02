package com.example.ngampusaman.ui.Fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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
import com.example.ngampusaman.viewmodel.SuratIzinViewModel

class SuratIzinTersimpanAdapter(private val suratIzinViewModel: SuratIzinViewModel): RecyclerView.Adapter<SuratIzinTersimpanAdapter.ViewHolder>() {
    private var suratIzinList = emptyList<SuratIzin>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJudulItem: TextView
        val tvNamaLengkapItem: TextView
        val tvNimItem: TextView
        val tvAlasanIzin: TextView
        val btnEditItemIzin: Button
        val btnHapusItemIzin: Button

        init {
            tvJudulItem = view.findViewById(R.id.tv_judul_item)
            tvNamaLengkapItem = view.findViewById(R.id.tv_nama_lengkap_item)
            tvNimItem = view.findViewById(R.id.tv_nim_item)
            tvAlasanIzin = view.findViewById(R.id.tv_alasan_izin)
            btnEditItemIzin = view.findViewById(R.id.btn_edit_item_izin)
            btnHapusItemIzin = view.findViewById(R.id.btn_hapus_item_izin)
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

        holder.btnHapusItemIzin.setOnClickListener {
            deletePengguna(it.context, suratKe)
        }
    }

    private fun deletePengguna(context: Context, sIzin: SuratIzin) {
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Hapus Surat ID ${sIzin.id}")
        builder.setMessage("Are you sure to delete Surat ID ${sIzin.id}?")
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
            suratIzinViewModel.delete(sIzin)
            Toast.makeText(context, "Sukses untuk menghapus surat ID ${sIzin.id}", Toast.LENGTH_SHORT).show()
        })
        builder.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->  })
        builder.create().show()
    }
}