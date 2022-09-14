package com.login.read.write.file.jpg.png.myapplication.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File

// Получение имени файла, снятого на камеру
// Добавить разешение в Манифест
// <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
fun Uri.getNameFromUri(context: Context): String {
    val returnCursor = context.contentResolver.query(this, null, null, null, null)
    val nameIndex = returnCursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor?.moveToFirst()
    val fileName = nameIndex?.let { returnCursor.getString(it) }
    returnCursor?.close()
    return fileName.toString()
}

fun Uri.getPathFromUri(): String {
    this.path?.let {
        return File(it).name
    }
    return ""
}