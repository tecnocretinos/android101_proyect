package tech.yeswecode.reporteciudadano.views

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.databinding.ActivityHomeBinding
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import tech.yeswecode.reporteciudadano.views.adapters.ReportSeeMore
import tech.yeswecode.reporteciudadano.views.fragments.ProfileFragment
import tech.yeswecode.reporteciudadano.views.fragments.ReportsListFragment
import tech.yeswecode.reporteciudadano.views.fragments.ReportsMapFragment

class HomeActivity : AppCompatActivity(), ReportSeeMore {

    private val REQUEST_PERMISSION = 99
    private var user: User? = null
    private lateinit var binding: ActivityHomeBinding
    private lateinit var reportsListFragment: ReportsListFragment
    private lateinit var mapFragment: ReportsMapFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.extras?.getSerializable(ExtrasConstants.USER) as? User
        reportsListFragment = ReportsListFragment.newInstance()
        reportsListFragment.listener = this
        mapFragment = ReportsMapFragment.newInstance()
        mapFragment.listener = this
        profileFragment = ProfileFragment.newInstance(user)

        binding.navigationBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.reports -> showFragment(reportsListFragment)
                R.id.map -> showFragment(mapFragment)
                R.id.profile -> showFragment(profileFragment)
            }
            true
        }
        binding.navigationBar.selectedItemId = R.id.map

        // TODO: Do something when the permisson are not gatenteed key: use onRequestPermissionsResult
        requestLocationPermission()
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
                val newReportIntent = Intent(this, NewReportActivity::class.java)
                startActivity(newReportIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_PERMISSION)
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}