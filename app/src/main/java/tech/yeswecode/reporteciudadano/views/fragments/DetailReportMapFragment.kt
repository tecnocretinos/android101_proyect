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
import com.google.android.gms.maps.model.MarkerOptions
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.databinding.FragmentDetailReportMapBinding
import tech.yeswecode.reporteciudadano.databinding.FragmentNewReportMapBinding
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants

class DetailReportMapFragment : Fragment() {

    private var _binding: FragmentDetailReportMapBinding? = null
    private val binding get() = _binding!!
    private var longitude: Double? = null
    private var latitude: Double? = null

    private val callback = OnMapReadyCallback { googleMap ->
        longitude?.let { lng ->
            latitude?.let { lat ->
                val marker = LatLng(lat, lng)
                googleMap.addMarker(MarkerOptions().position(marker))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 8.0f))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            latitude = it.getDouble(ExtrasConstants.LATITUDE)
            longitude = it.getDouble(ExtrasConstants.LONGITUDE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailReportMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        @JvmStatic
        fun newInstance(longitude: Double?, latitude: Double?) = DetailReportMapFragment().apply {
            arguments = Bundle().apply {
                longitude?.let {
                    putDouble(ExtrasConstants.LONGITUDE, it) }
                latitude?.let {
                    putDouble(ExtrasConstants.LATITUDE, it) }
            }
        }
    }
}