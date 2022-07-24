package tech.yeswecode.reporteciudadano.models

import android.text.Editable
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Report(
    val id: String,
    val title: String,
    val description: String,
    val longitude: Double,
    val latitude: Double,
    val date: Date,
    val images: ArrayList<String>
): Serializable

fun Date.dateToString(format: String): String {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(this)
}