package tech.yeswecode.reporteciudadano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.databinding.ActivityReportDetailBinding
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.models.dateToString
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import tech.yeswecode.reporteciudadano.views.fragments.DetailReportMapFragment
import tech.yeswecode.reporteciudadano.views.fragments.NewReportMapFragment
import java.util.*

class ReportDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportDetailBinding
    private var report: Report? = null
    private lateinit var mapFragment: DetailReportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.report = intent.extras?.getSerializable(ExtrasConstants.REPORT) as? Report
        this.report?.let {
            binding.reportDetailTitleTxt.text = it.title
            binding.reportDetailDateTxt.text = getDate(it.date)
            binding.reportDetailDescriptionTxt.text = it.description
        }
        mapFragment = DetailReportMapFragment.newInstance(this.report?.longitude, this.report?.latitude)
        showFragment(mapFragment)
        showImage()
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mapDetailFragmentContainer, fragment)
        transaction.commit()
    }

    private fun getDate(date: Date): String {
        return date.dateToString("dd-MM-yyyy")
    }

    private fun showImage() {
        if(!this.report?.images.isNullOrEmpty()) {
            // TODO: Refactor to show multiple images, loop the list
            binding.reportDetailImg.visibility = View.VISIBLE
            Glide.with(this).load(this.report!!.images[0]).into(binding.reportDetailImg);
        }
    }
}