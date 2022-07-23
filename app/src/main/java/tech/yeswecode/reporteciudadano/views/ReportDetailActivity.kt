package tech.yeswecode.reporteciudadano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.yeswecode.reporteciudadano.databinding.ActivityReportDetailBinding
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants

class ReportDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportDetailBinding
    private var report: Report? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.report = intent.extras?.getSerializable(ExtrasConstants.REPORT) as? Report
        this.report?.let {
            binding.reportDetailTitleTxt.text = it.title
            binding.reportDetailDateTxt.text = it.getDate()
            binding.reportDetailDescriptionTxt.text = it.description
        }
        /* TODO: Add a new map fragment to the view, use the space of the FrameLayout
            Set a marker and camera on the selected report position
         */
    }
}