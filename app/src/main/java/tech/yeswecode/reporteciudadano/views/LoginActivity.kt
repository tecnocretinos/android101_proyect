package tech.yeswecode.reporteciudadano.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import java.util.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener {
            val email = emailTxt.text.toString()
            val password = passwordTxt.text.toString()

            if(email.isNotEmpty() &&
                email.isNotBlank() &&
                password.isNotEmpty() &&
                password.isNotBlank()
            ) {
                val homeIntent = Intent(this, HomeActivity::class.java).apply {
                    val mockUser = User(UUID.randomUUID().toString(), "Mock User", email)
                    putExtra(ExtrasConstants.USER, mockUser)
                }
                startActivity(homeIntent)
            } else {
                this.showMessage("Por favor complete todos los campos para continuar.")
            }
        }
    }

    private fun showMessage(message: String) {
        Toast
            .makeText(baseContext,
                message,
                Toast.LENGTH_LONG)
            .show()
    }
}