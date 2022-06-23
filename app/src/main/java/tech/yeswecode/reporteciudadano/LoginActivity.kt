package tech.yeswecode.reporteciudadano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

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
                val homeIntent = Intent(this, HomeActivity::class.java)
                startActivity(homeIntent)
            } else {
                Toast
                    .makeText(baseContext,
                    "Por favor complete todos los campos para continuar.",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}