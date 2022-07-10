package tech.yeswecode.reporteciudadano.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import tech.yeswecode.reporteciudadano.databinding.FragmentReportsListBinding
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import tech.yeswecode.reporteciudadano.views.adapters.ReportAdapter
import tech.yeswecode.reporteciudadano.views.adapters.ReportSeeMore

class ReportsListFragment : Fragment() {

    private var _binding: FragmentReportsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var layoutManager: LayoutManager
    private lateinit var adapter: ReportAdapter
    private var reports = Report.mock()
    var listener: ReportSeeMore? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportsListBinding.inflate(inflater, container, false)
        layoutManager = LinearLayoutManager(context)
        adapter = ReportAdapter(reports, listener!!)
        binding.reportsRecyclerView.layoutManager = layoutManager
        binding.reportsRecyclerView.adapter = adapter
        return binding.root
    }

    fun updateList(newList: Array<Report>) {
        reports = newList
        _binding?.let {
            adapter.update(reports)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ReportsListFragment()
    }
}