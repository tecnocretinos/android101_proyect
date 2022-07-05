package tech.yeswecode.reporteciudadano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import tech.yeswecode.reporteciudadano.views.adapters.ReportAdapter

class HomeActivity : AppCompatActivity() {
    private var user: User? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: ReportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        this.user = intent.extras?.getSerializable(ExtrasConstants.USER) as? User
        this.layoutManager = LinearLayoutManager(this)
        this.adapter = ReportAdapter(Report.mock())

        reportsRecycler.layoutManager = layoutManager
        reportsRecycler.adapter = adapter
    }
}