package tech.yeswecode.reporteciudadano.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.models.Report

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
        // TODO: Add method to format date to string
        viewHolder.dateTxt.text = report.date.toString()
        viewHolder.descriptionTxt.text = report.description
        viewHolder.seeMoreBtn.setOnClickListener {
            this.delegate.reportSelected(report)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}