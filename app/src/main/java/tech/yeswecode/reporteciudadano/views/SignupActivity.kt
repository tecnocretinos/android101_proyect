package tech.yeswecode.reporteciudadano.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import tech.yeswecode.reporteciudadano.databinding.ActivitySignupBinding
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import java.util.*

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        this.handleEmailExtra()
        binding.createUserBtn.setOnClickListener {
            this.goToHome()
        }
    }

    private fun goToHome() {
        val name = binding.nameTxt.text.toString()
        val email = binding.emailTxt.text.toString()
        val password = binding.passwordSignupTxt.text.toString()
        val repeatPassword = binding.repeatPasswordTxt.text.toString()

        if(name.isNotEmpty() && name.isNotBlank() &&
            email.isNotEmpty() && email.isNotBlank() &&
            password.isNotEmpty() && password.isNotBlank() &&
            repeatPassword.isNotEmpty() && repeatPassword.isNotBlank()) {
            if(password == repeatPassword) {
                val user = User(UUID.randomUUID().toString(),
                    name,
                    email)
                val homeIntent = Intent(this, HomeActivity::class.java).apply {
                    putExtra(ExtrasConstants.USER, user)
                }
                startActivity(homeIntent)
            } else {
                this.showMessage("Las contraseñas no coinciden.")
            }
        } else {
            this.showMessage("Por favor complete todos los campos para continuar.")
        }
    }

    private fun handleEmailExtra() {
        val emailExtra = intent.extras?.getString(ExtrasConstants.EMAIL)
        emailExtra?.let { binding.emailTxt.setText(it) }
        binding.backToLoginBtn.setOnClickListener {
            this.goBackToLogin()
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

    private fun showMessage(message: String) {
        Toast
            .makeText(baseContext,
                message,
                Toast.LENGTH_LONG)
            .show()
    }
}