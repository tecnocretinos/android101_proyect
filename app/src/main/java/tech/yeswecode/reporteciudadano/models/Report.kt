package tech.yeswecode.reporteciudadano.models

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Report(val id: String,
             val title: String,
             val description: String,
             val longitude: Double,
             val latitude: Double,
             val date: Date,
             val images: ArrayList<String>
): Serializable {

    fun getDate(): String {
        return date.dateToString("dd-MM-yyyy")
    }

    companion object {
        fun mockOne(id: Int): Report {
            return Report("${id}",
                "Titulo del reporte ${id}",
                "Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos. Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos.",
                10.0,
                10.0,
                Date(),
                ArrayList<String>()
            )
        }
        fun mock(): Array<Report> {
            return arrayOf(
                Report("01",
                    "Titulo del reporte 1",
                    "Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos. Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos.",
                    10.0,
                    10.0,
                    Date(),
                    ArrayList<String>()
                ),
                Report("02",
                    "Titulo del reporte 2",
                    "Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos. \n Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos",
                    10.0,
                    10.0,
                    Date(),
                    ArrayList<String>()
                ),
                Report("03",
                    "Titulo del reporte 3",
                    "Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos. Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos.",
                    10.0,
                    10.0,
                    Date(),
                    ArrayList<String>()
                ),
                Report("04",
                    "Titulo del reporte 4",
                    "Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos. \n Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos",
                    10.0,
                    10.0,
                    Date(),
                    ArrayList<String>()
                ),
                Report("05",
                    "Titulo del reporte 5",
                    "Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos. \n Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos",
                    10.0,
                    10.0,
                    Date(),
                    ArrayList<String>()
                ),
                Report("06",
                    "Titulo del reporte 6",
                    "Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos. \n Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos",
                    10.0,
                    10.0,
                    Date(),
                    ArrayList<String>()
                ),
                Report("07",
                    "Titulo del reporte 7",
                    "Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos. \n Descripción del reporte con mucho texto pero en esta parte deberían a parecer solo don lineas o incluso más. Aqui deberia hacer un salto de linea y agregar puntos suspensivos",
                    10.0,
                    10.0,
                    Date(),
                    ArrayList<String>()
                )
            )
        }
    }
}

private fun Date.dateToString(format: String): String {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(this)
}