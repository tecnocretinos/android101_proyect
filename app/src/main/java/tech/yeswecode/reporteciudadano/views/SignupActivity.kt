package tech.yeswecode.reporteciudadano.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.emailTxt
import kotlinx.android.synthetic.main.activity_login.signupBtn
import kotlinx.android.synthetic.main.activity_signup.*
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        this.handleEmailExtra()
    }

    private fun goToHome() {
        val name = nameTxt.text.toString()
        val email = emailTxt.text.toString()
        val password = passwordSignupTxt.text.toString()
        val repeatPassword = repeatPasswordTxt.text.toString()
    }

    private fun handleEmailExtra() {
        val emailExtra = intent.extras?.getString(ExtrasConstants.EMAIL)
        emailExtra?.let { emailTxt.setText(it) }
        backToLoginBtn.setOnClickListener {
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

    private fun showMessage(message: String) {
        Toast
            .makeText(baseContext,
                message,
                Toast.LENGTH_LONG)
            .show()
    }
}