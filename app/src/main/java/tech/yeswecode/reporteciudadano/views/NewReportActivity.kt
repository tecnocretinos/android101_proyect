package tech.yeswecode.reporteciudadano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.yeswecode.reporteciudadano.databinding.ActivityNewReportBinding

class NewReportActivity : AppCompatActivity() {

    private var _binding: ActivityNewReportBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}