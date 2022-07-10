package tech.yeswecode.reporteciudadano.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.MapFragment
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.databinding.ActivityHomeBinding
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import tech.yeswecode.reporteciudadano.views.adapters.ReportAdapter
import tech.yeswecode.reporteciudadano.views.adapters.ReportSeeMore
import tech.yeswecode.reporteciudadano.views.fragments.ProfileFragment
import tech.yeswecode.reporteciudadano.views.fragments.ReportsListFragment
import tech.yeswecode.reporteciudadano.views.fragments.ReportsMapFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var reportsListFragment: ReportsListFragment
    private lateinit var mapFragment: ReportsMapFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reportsListFragment = ReportsListFragment.newInstance()
        mapFragment = ReportsMapFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()

        binding.navigationBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.reports -> showFragment(reportsListFragment)
                R.id.map -> showFragment(mapFragment)
                R.id.profile -> showFragment(profileFragment)
            }
            true
        }
        binding.navigationBar.selectedItemId = R.id.map
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add -> {
                // TODO: Mock a new report added to the ReportsListFragment
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}