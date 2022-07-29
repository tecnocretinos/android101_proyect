package tech.yeswecode.reporteciudadano.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.yeswecode.reporteciudadano.databinding.ActivityRecoverPasswordBinding
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants

class RecoverPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecoverPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoverPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val emailExtra = intent.extras?.getString(ExtrasConstants.EMAIL)
        emailExtra?.let { binding.emailTxt.setText(it) }

        binding.recoverBackToLoginBtn.setOnClickListener {
            this.goBackToLogin()
        }

        binding.recoverPasswordBtn.setOnClickListener {
            // TODO: Recover password with firebase auth
        }
    }

    private fun goBackToLogin() {
        val email = binding.emailTxt.text.toString()
        val intent = Intent().apply {
            putExtra(ExtrasConstants.EMAIL, email)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}