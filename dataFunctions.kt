package com.example.degreemanagment

import android.content.Context
import android.widget.Toast
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// SAVE DATA TO FILE
fun saveDataToFile(context: Context, subject: String, degree: String) {
    if (subject.isEmpty() || degree.isEmpty()) {
        Toast.makeText(context, "not enough data", Toast.LENGTH_SHORT).show()
        return
    }

    val fileName = "data3.txt"
    val file = File(context.filesDir, fileName)

    try {
        if (!file.exists()) {
            file.createNewFile()
        }

        val fileOutputStream = FileOutputStream(file, true)
        val outputStreamWriter = OutputStreamWriter(fileOutputStream)

        outputStreamWriter.write("$subject  $degree\n")

        outputStreamWriter.close()
        fileOutputStream.close()

        Toast.makeText(context, "Data Saved to File", Toast.LENGTH_SHORT).show()
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error Saving Data to File", Toast.LENGTH_SHORT).show()
    }

}

// READ DATA FROM FILE
fun readDataFromFile(context: Context, index: Int): Pair<String, String> {
    val fileName = "data3.txt"
    val file = File(context.filesDir, fileName)

    try {
        if (file.exists()) {
            val fileInputStream = FileInputStream(file)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            var currentLineIndex = 0
            var line: String?

            while (bufferedReader.readLine().also { line = it } != null) {
                if (currentLineIndex == index) {
                    val dataParts = line?.split("\\s+".toRegex())
                    if (dataParts != null && dataParts.size == 2) {
                        return Pair(dataParts[0], dataParts[1])
                    }
                }
                currentLineIndex++
            }

            bufferedReader.close()
            inputStreamReader.close()
            fileInputStream.close()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error reading data from file", Toast.LENGTH_SHORT).show()
    }

    return Pair("", "")
}

// MODIFY DATA IN FILE BY INDEX
fun modifyDataInFile(context: Context, index: Int, newSubject: String, newDegree: String): Boolean {
    val fileName = "data3.txt"
    val file = File(context.filesDir, fileName)

    try {
        if (file.exists()) {
            val lines = file.readLines().toMutableList()

            if (index >= 0 && index < lines.size) {
                val updatedLine = "$newSubject $newDegree"
                lines[index] = updatedLine

                val fileWriter = FileWriter(file, false)
                val bufferedWriter = BufferedWriter(fileWriter)

                for (line in lines) {
                    bufferedWriter.write(line)
                    bufferedWriter.newLine()
                }

                bufferedWriter.close()
                fileWriter.close()

                return true
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error modifying data in file", Toast.LENGTH_SHORT).show()
    }

    return false
}

// DELETE DATA FROM FILE BY INDEX
fun deleteDataFromFile(context: Context, index: Int): Boolean {
    val fileName = "data3.txt"
    val file = File(context.filesDir, fileName)

    try {
        if (file.exists()) {
            val lines = file.readLines().toMutableList()

            if (index >= 0 && index < lines.size) {
                lines.removeAt(index)

                val fileWriter = FileWriter(file, false)
                val bufferedWriter = BufferedWriter(fileWriter)

                for (line in lines) {
                    bufferedWriter.write(line)
                    bufferedWriter.newLine()
                }

                bufferedWriter.close()
                fileWriter.close()

                Toast.makeText(context, "Data deleted from file", Toast.LENGTH_SHORT).show()
                return true
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error deleting data from file", Toast.LENGTH_SHORT).show()
    }

    Toast.makeText(context, "Error deleting data from file", Toast.LENGTH_SHORT).show()
    return false
}
