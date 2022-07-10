package tech.yeswecode.reporteciudadano.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.databinding.FragmentProfileBinding
import tech.yeswecode.reporteciudadano.databinding.FragmentReportsListBinding

class ReportsListFragment : Fragment() {

    private var _binding: FragmentReportsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportsListBinding.inflate(inflater, container, false)
        // TODO: Use the recycler view and mock the data
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ReportsListFragment()
    }
}