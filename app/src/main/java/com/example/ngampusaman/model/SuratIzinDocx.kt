package com.example.ngampusaman.model

import android.content.Context
import android.os.Build.VERSION_CODES.R
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.*

class SuratIzinDocx(private val name: String, private val ourAppFileDirectory: File, private val context: Context, private val dataSurat:List<String>) {
    private var ourWordDoc: XWPFDocument = XWPFDocument()

    private val daftarGanti = context.resources.getStringArray(R.array.daftar_nama)

    fun loadDoc() : InputStream? {
        ourAppFileDirectory.let {
            if (it.exists()) {
                return context.assets.open("permohonanSuratIzin.docx")
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
                            fun checkAndReplace(text: String, oldVal: String, newVal: String) {
                                val ubahText = text.replace(oldVal, newVal)
                                r.setText(ubahText, 0)
                            }
                            val text = r.getText(0)
                            if (text != null)
                                daftarGanti.forEachIndexed { index, ganti ->
                                    if (text.contains(ganti)) {
                                        checkAndReplace(text, ganti, dataSurat[index])
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
                                    fun checkAndReplace(text: String, oldVal: String, newVal: String) {
                                        val ubahText = text.replace(oldVal, newVal)
                                        r.setText(ubahText, 0)
                                    }
                                    val text = r.getText(0)
                                    if (text != null)
                                        daftarGanti.forEachIndexed { index, ganti ->
                                            if (text.contains(ganti)) {
                                                checkAndReplace(text, ganti, dataSurat[index])
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

    fun saveDoc() {
        //Check whether it exists or not, and create one if it does not exist.
        if (!ourAppFileDirectory.exists()) {
            ourAppFileDirectory.mkdirs()
        }

        val wordFile = File(ourAppFileDirectory, "${name}.docx")
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