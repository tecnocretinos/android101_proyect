package tech.yeswecode.reporteciudadano.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tech.yeswecode.reporteciudadano.databinding.ActivitySignupBinding
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import tech.yeswecode.reporteciudadano.utilities.FirestoreConstants
import java.util.*

class SignupActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        this.handleEmailExtra()
        binding.createUserBtn.setOnClickListener {
            this.signup()
        }
    }

    private fun signup() {
        val name = binding.nameTxt.text.toString()
        val email = binding.emailTxt.text.toString()
        val password = binding.passwordSignupTxt.text.toString()
        val repeatPassword = binding.repeatPasswordTxt.text.toString()

        if(name.isNotEmpty() && name.isNotBlank() &&
            email.isNotEmpty() && email.isNotBlank() &&
            password.isNotEmpty() && password.isNotBlank() &&
            repeatPassword.isNotEmpty() && repeatPassword.isNotBlank()) {
            if(password == repeatPassword) {
                createAuthUser(email, name, password)
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

    private fun createAuthUser(email: String, name: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    auth.currentUser?.let {
                        val user = User(it.uid, name, email)
                        createNewUser(user)
                    }
                } else {
                    showMessage("Error with the signup")
                }
            }
    }

    private fun createNewUser(user: User) {
        db.collection(FirestoreConstants.USERS)
            .document(user.id)
            .set(user)
            .addOnSuccessListener {
                goToHome(user)
            }
            .addOnFailureListener { e ->
                e.message?.let { showMessage(it) }
            }
    }

    private fun goToHome(user: User) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra(ExtrasConstants.USER, user)
        }
        startActivity(homeIntent)
    }
}