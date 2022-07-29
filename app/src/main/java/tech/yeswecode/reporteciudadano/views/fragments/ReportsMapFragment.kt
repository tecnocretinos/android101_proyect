package tech.yeswecode.reporteciudadano.views.fragments

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.databinding.FragmentReportsMapBinding
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.models.dateToString
import tech.yeswecode.reporteciudadano.utilities.FirestoreConstants
import tech.yeswecode.reporteciudadano.views.adapters.ReportSeeMore
import java.util.*

class ReportsMapFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        loadMarkers(googleMap)
        googleMap.setOnMarkerClickListener { marker ->
            val reportFilter = reports.indexOfFirst { it.title == marker.title }
            reportFilter?.let { setSelectedReport(reportFilter) }
            false
        }
    }

    private var _binding: FragmentReportsMapBinding? = null
    private val binding get() = _binding!!
    private var reports: Array<Report> = arrayOf<Report>()
    private val db = Firebase.firestore
    var listener: ReportSeeMore? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportsMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getReports()
    }

    private fun loadMarkers(map: GoogleMap) {
        reports.forEach {
            addMarker(map, it.latitude, it.longitude, it.title)
        }
        if(reports.isNotEmpty()) {
            val last =reports.last()
            val position = LatLng(last.latitude, last.longitude)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 8.0f))
        }
    }

    private fun addMarker(map: GoogleMap,
                          lat: Double,
                          lng: Double,
                          title: String): Marker {
        val position = LatLng(lat, lng)
        return map.addMarker(MarkerOptions().position(position).title(title))
    }

    private fun setSelectedReport(position: Int) {
        if(reports.isNotEmpty()) {
            setUpSelectedReportView(reports[position])
        }
    }

    private fun setUpSelectedReportView(report: Report) {
        if(binding.mapReportCardView.visibility == View.GONE) {
            binding.mapReportCardView.visibility = View.VISIBLE
        }
        binding.reportMapItemTitleTxt.text = report.title
        binding.reportMapItemDescriptionTxt.text = report.description
        binding.reportMapItemDateTxt.text = getDate(report.date)
        binding.reportMapItemSeeBtn.setOnClickListener {
            listener?.reportSelected(report)
        }
    }

    private fun getReports() {
        db.collection(FirestoreConstants.REPORTS)
            .orderBy(FirestoreConstants.DATE, Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                var reportsTemp = ArrayList<Report>()
                for (document in result) {
                    reportsTemp.add(document.toObject(Report::class.java))
                }
                reports = reportsTemp.toTypedArray()
                if(reports.isNullOrEmpty()) {
                    binding.mapReportCardView.visibility = View.GONE
                } else {
                    setSelectedReport(reports.size-1)
                }
                val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                mapFragment?.getMapAsync(callback)
            }
            .addOnFailureListener { exception ->
                // TODO: Handle the error
            }
    }

    private fun getDate(date: Date): String {
        return date.dateToString("dd-MM-yyyy")
    }

    companion object {
        @JvmStatic
        fun newInstance() = ReportsMapFragment()
    }
}