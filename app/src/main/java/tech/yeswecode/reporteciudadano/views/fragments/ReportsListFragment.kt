package tech.yeswecode.reporteciudadano.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tech.yeswecode.reporteciudadano.databinding.FragmentReportsListBinding
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import tech.yeswecode.reporteciudadano.utilities.FirestoreConstants
import tech.yeswecode.reporteciudadano.views.adapters.ReportAdapter
import tech.yeswecode.reporteciudadano.views.adapters.ReportSeeMore
import java.util.*

class ReportsListFragment : Fragment() {

    private var _binding: FragmentReportsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var layoutManager: LayoutManager
    private lateinit var adapter: ReportAdapter
    private var reports: Array<Report> = arrayOf<Report>()
    private val db = Firebase.firestore
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getReports()
    }

    private fun updateList(newList: Array<Report>) {
        reports = newList
        _binding?.let {
            adapter.update(reports)
        }
    }

    private fun getReports() {
        db.collection(FirestoreConstants.REPORTS)
            .orderBy(FirestoreConstants.DATE, Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                var reportsTemp = ArrayList<Report>()
                for (document in result) {
                    reportsTemp.add(document.toObject(Report::class.java))
                }
                updateList(reportsTemp.toTypedArray())
            }
            .addOnFailureListener { exception ->
                // TODO: Handle the error
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