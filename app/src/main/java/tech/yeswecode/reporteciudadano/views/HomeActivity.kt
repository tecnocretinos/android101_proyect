package tech.yeswecode.reporteciudadano.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import tech.yeswecode.reporteciudadano.views.adapters.ReportAdapter
import tech.yeswecode.reporteciudadano.views.adapters.ReportSeeMore

class HomeActivity : AppCompatActivity(), ReportSeeMore {
    private var user: User? = null
    private var reports = Report.mock()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: ReportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        this.user = intent.extras?.getSerializable(ExtrasConstants.USER) as? User
        this.layoutManager = LinearLayoutManager(this)
        this.adapter = ReportAdapter(reports, this)

        reportsRecycler.layoutManager = layoutManager
        reportsRecycler.adapter = adapter
    }

    override fun reportSelected(selection: Report) {
        val detailIntent = Intent(this, ReportDetailActivity::class.java).apply {
            putExtra(ExtrasConstants.REPORT, selection)
        }
        startActivity(detailIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add -> {
                val newReport = Report.mockOne(reports.size +1)
                reports += newReport
                adapter.update(reports)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun append(arr: Array<Report>, element: Report): Array<Report> {
        val list: MutableList<Report> = reports.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }
}