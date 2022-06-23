package tech.yeswecode.reporteciudadano.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_login.*
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import java.util.*

class LoginActivity : AppCompatActivity() {

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener {
            this.validateLogin()
        }

        signupBtn.setOnClickListener {
            this.goToSignup()
        }

        recoverBtn.setOnClickListener {
            this.goToRecoverPassword()
        }
    }

    private fun validateLogin() {
        val email = emailTxt.text.toString()
        val password = passwordTxt.text.toString()

        if(email.isNotEmpty() &&
            email.isNotBlank() &&
            password.isNotEmpty() &&
            password.isNotBlank()
        ) {
            val mockUser = User(UUID.randomUUID().toString(), "Mock User", email)
            val homeIntent = Intent(this, HomeActivity::class.java).apply {
                putExtra(ExtrasConstants.USER, mockUser)
            }
            startActivity(homeIntent)
        } else {
            this.showMessage("Por favor complete todos los campos para continuar.")
        }
    }

    private fun goToSignup() {
        val email = emailTxt.text.toString()
        val recoveryIntent = Intent(this, SignupActivity::class.java).apply {
            putExtra(ExtrasConstants.EMAIL, email)
        }
        launcher.launch(recoveryIntent)
    }

    private fun goToRecoverPassword() {
        val email = emailTxt.text.toString()
        val recoveryIntent = Intent(this, RecoverPasswordActivity::class.java).apply {
            putExtra(ExtrasConstants.EMAIL, email)
        }
        launcher.launch(recoveryIntent)
    }

    private fun showMessage(message: String) {
        Toast
            .makeText(baseContext,
                message,
                Toast.LENGTH_LONG)
            .show()
    }

    private fun onResult(result: ActivityResult) {
        if(result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val newEmail = data?.extras?.getString(ExtrasConstants.EMAIL)
            newEmail.let { emailTxt.setText(it) }
        }
    }
}