package tech.yeswecode.reporteciudadano.views.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.models.Report
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class ReportAdapter(private val dataSet: Array<Report>,
                    private val delegate: ReportSeeMore) :
    RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTxt: TextView
        val dateTxt: TextView
        val descriptionTxt: TextView
        val seeMoreBtn: TextView

        init {
            titleTxt = view.findViewById(R.id.reportItemTitleTxt)
            dateTxt = view.findViewById(R.id.reportItemDateTxt)
            descriptionTxt = view.findViewById(R.id.reportItemDescriptionTxt)
            seeMoreBtn = view.findViewById(R.id.reportItemSeeBtn)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_report, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val report = dataSet[position]
        viewHolder.titleTxt.text = report.title
        viewHolder.dateTxt.text = report.date.dateToString("dd-MM-yyyy")
        viewHolder.descriptionTxt.text = report.description
        viewHolder.seeMoreBtn.setOnClickListener {
            this.delegate.reportSelected(report)
        }
    }

    override fun getItemCount() = dataSet.size
}

private fun Date.dateToString(format: String): String {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(this)
}