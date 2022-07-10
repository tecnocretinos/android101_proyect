package tech.yeswecode.reporteciudadano.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.yeswecode.reporteciudadano.R

class ReportsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reports_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ReportsListFragment()
    }
}