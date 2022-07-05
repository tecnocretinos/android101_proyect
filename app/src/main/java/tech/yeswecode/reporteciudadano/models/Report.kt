package tech.yeswecode.reporteciudadano.models

import java.util.*
import kotlin.collections.ArrayList

class Report(val id: String,
             val title: String,
             val description: String,
             val longitude: Double,
             val latitude: Double,
             val date: Date,
             val images: ArrayList<String>
)