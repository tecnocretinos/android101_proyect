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
) {
    companion object {
        fun mock() : Array<Report> {
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
                )
            )
        }
    }
}