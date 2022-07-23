package tech.yeswecode.reporteciudadano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.databinding.ActivityReportDetailBinding
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import tech.yeswecode.reporteciudadano.views.fragments.DetailReportMapFragment
import tech.yeswecode.reporteciudadano.views.fragments.NewReportMapFragment

class ReportDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportDetailBinding
    private var report: Report? = null
    private lateinit var mapFragment: DetailReportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mapFragment = DetailReportMapFragment.newInstance()
        this.report = intent.extras?.getSerializable(ExtrasConstants.REPORT) as? Report
        this.report?.let {
            binding.reportDetailTitleTxt.text = it.title
            binding.reportDetailDateTxt.text = it.getDate()
            binding.reportDetailDescriptionTxt.text = it.description
        }
        showFragment(mapFragment)
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mapDetailFragmentContainer, fragment)
        transaction.commit()
    }
}