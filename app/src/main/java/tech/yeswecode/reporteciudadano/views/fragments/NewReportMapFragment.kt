package tech.yeswecode.reporteciudadano.views.fragments

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
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
import tech.yeswecode.reporteciudadano.databinding.FragmentNewReportMapBinding

class NewReportMapFragment : Fragment() {

    private var _binding: FragmentNewReportMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var manager: LocationManager

    private val callback = OnMapReadyCallback { googleMap ->
        setInitialPosition(googleMap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewReportMapBinding.inflate(inflater, container, false)
        manager = activity?.getSystemService(LOCATION_SERVICE) as LocationManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    @SuppressLint("MissingPermission")
    private fun setInitialPosition(map: GoogleMap) {
        val pos = manager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        pos?.let {
            val marker = LatLng(it.latitude, it.longitude)
            map.addMarker(MarkerOptions().position(marker).title("Mi posición"))
            map.moveCamera(CameraUpdateFactory.newLatLng(marker))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewReportMapFragment()
    }
}