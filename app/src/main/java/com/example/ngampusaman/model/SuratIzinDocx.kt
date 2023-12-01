package com.example.ngampusaman.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.ngampusaman.R
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.*

class SuratIzinDocx(private val name: String, private val ourAppFileDirectory: File, private val context: Context, private val dataSurat:List<String>) {
    private var ourWordDoc: XWPFDocument = XWPFDocument()

    private val daftarGanti = listOf("\$NAMA_DOSEN\$", "\$MATA_KULIAH\$", "\$NAMA_LENGKAP\$", "\$NIM\$", "\$NO_TELP\$", "\$PRODI\$", "\$ALASAN\$", "\$JUMLAH_HARI\$", "\$TGL_AWAL\$", "\$TGL_AKHIR\$", "\$TGL_DIBUAT\$", "\$HARI_TABEL\$", "\$JAM_TABEL\$", "\$RUANG_TABEL\$")

    fun loadDoc() : InputStream? {

        ourAppFileDirectory.let {
            if (it.exists()) {
                return context.assets.open("permIzinKuliah.docx")
            }
        }

        return null
    }

    fun editDoc() {
        loadDoc().let {
            try {
                val doc = XWPFDocument(it)

                for (p in doc.paragraphs) {

                    val runs = p.runs
                    if (runs != null) {
                        for (r in runs) {
                            var text = r.getText(0)
                            if (text != null)
                                daftarGanti.forEachIndexed { index, ganti ->
                                    if (text.contains(ganti)) {
                                        text = text.replace(ganti, dataSurat[index])
                                        r.setText(text, 0)
                                    }
                                }
                        }
                    }
                }
                for (tbl in doc.tables) {
                    for (row in tbl.rows) {
                        for (cell in row.tableCells) {
                            for (p in cell.paragraphs) {
                                for (r in p.runs) {
                                    var text = r.getText(0)
                                    if (text != null)
                                        daftarGanti.forEachIndexed { index, ganti ->
                                            if (text.contains(ganti)) {
                                                text = text.replace(ganti, dataSurat[index])
                                                r.setText(text, 0)
                                            }
                                        }
                                }
                            }
                        }
                    }
                }

                ourWordDoc = doc
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveDoc(lokasiFile: File) {
        //Check whether it exists or not, and create one if it does not exist.

        val wordFile = File(lokasiFile, "${name}.docx")
        try {
            val fileOut = FileOutputStream(wordFile)
            ourWordDoc.write(fileOut)
            fileOut.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}