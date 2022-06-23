package tech.yeswecode.reporteciudadano.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.emailTxt
import kotlinx.android.synthetic.main.activity_recover_password.*
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants

class RecoverPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)

        val emailExtra = intent.extras?.getString(ExtrasConstants.EMAIL)
        emailExtra?.let { emailTxt.setText(it) }

        recoverBackToLoginBtn.setOnClickListener {
            this.goBackToLogin()
        }
    }

    private fun goBackToLogin() {
        val email = emailTxt.text.toString()
        val intent = Intent().apply {
            putExtra(ExtrasConstants.EMAIL, email)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}