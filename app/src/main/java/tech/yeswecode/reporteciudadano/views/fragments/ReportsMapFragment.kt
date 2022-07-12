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
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.databinding.FragmentProfileBinding
import tech.yeswecode.reporteciudadano.databinding.FragmentReportsMapBinding
import tech.yeswecode.reporteciudadano.models.Report

class ReportsMapFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        loadMarkers(googleMap)
    }

    private var _binding: FragmentReportsMapBinding? = null
    private val binding get() = _binding!!
    private var reports = Report.mock()

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
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
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

    companion object {
        @JvmStatic
        fun newInstance() = ReportsMapFragment()
    }
}