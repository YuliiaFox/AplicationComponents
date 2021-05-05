package com.example.calculator

import android.content.Context
import com.example.calculator.calculator.HistoryWriter
import com.example.calculator.history.HistoryReader
import dagger.hilt.android.qualifiers.ActivityContext
import java.io.File
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    @ActivityContext private val context: Context
) : HistoryReader,
    HistoryWriter {

    private lateinit var file: File
    private var fileName = R.string.historyFile.toString()

    override fun readHistory(): List<String> {
        val lineList = mutableListOf<String>()
        if (createFile()) {
            return emptyList()
        } else {
            context.openFileInput(fileName).bufferedReader().useLines { lines ->
                lines.forEach { lineList.add(it) }
            }
        }
        return lineList
    }

    override fun writeHistory(exp: String) {
        if (createFile()) {
            writeToFile(exp)
        } else {
            writeToFile(exp)
        }
    }

    private fun createFile(): Boolean {
        file = File(context.filesDir, fileName)
        return file.createNewFile()
    }

    private fun writeToFile(text: String) {
        context.openFileOutput(fileName, Context.MODE_APPEND).use {
            it.write((text + "\n").toByteArray())
        }
    }
}