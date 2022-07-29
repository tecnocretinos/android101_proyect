package tech.yeswecode.reporteciudadano.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import tech.yeswecode.reporteciudadano.databinding.ActivityLoginBinding
import tech.yeswecode.reporteciudadano.models.Report
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants
import tech.yeswecode.reporteciudadano.utilities.FirestoreConstants
import tech.yeswecode.reporteciudadano.utilities.NotificationUtil
import java.util.*

class LoginActivity : AppCompatActivity() {

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)
    private lateinit var binding: ActivityLoginBinding
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            this.validateLogin()
        }

        binding.signupBtn.setOnClickListener {
            this.goToSignup()
        }

        binding.recoverBtn.setOnClickListener {
            this.goToRecoverPassword()
        }

        Firebase.messaging.subscribeToTopic("reports")
    }

    private fun validateLogin() {
        val email = binding.emailTxt.text.toString()
        val password = binding.passwordTxt.text.toString()

        if(email.isNotEmpty() &&
            email.isNotBlank() &&
            password.isNotEmpty() &&
            password.isNotBlank()
        ) {
            /* TODO: Suscribe to the user id as a topic
                in order to receive notifications just for the logged user

                Note: This is not the best way, search for the fcm token
                and how to use it to receive custom notification
                for the user that has that token device
            */
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        auth.currentUser?.let {
                            getUser(it.uid)
                        }
                    } else {
                        showMessage("Error with the signup")
                    }
                }
        } else {
            this.showMessage("Por favor complete todos los campos para continuar.")
        }
    }

    private fun getUser(id: String) {
        db.collection(FirestoreConstants.USERS)
            .document(id)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                val homeIntent = Intent(this, HomeActivity::class.java).apply {
                    putExtra(ExtrasConstants.USER, user)
                }
                startActivity(homeIntent)
            }
            .addOnCanceledListener {
                showMessage("User not found error")
            }
    }

    private fun goToSignup() {
        val email = binding.emailTxt.text.toString()
        val recoveryIntent = Intent(this, SignupActivity::class.java).apply {
            putExtra(ExtrasConstants.EMAIL, email)
        }
        launcher.launch(recoveryIntent)
    }

    private fun goToRecoverPassword() {
        val email = binding.emailTxt.text.toString()
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
            newEmail.let { binding.emailTxt.setText(it) }
        }
    }
}