package tech.yeswecode.reporteciudadano.views

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import tech.yeswecode.reporteciudadano.R
import tech.yeswecode.reporteciudadano.databinding.ActivityNewReportBinding

class NewReportActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    private val REQUEST_PERMISSION = 100
    private var imageUri: Uri? = null

    private var _binding: ActivityNewReportBinding? = null
    private val binding get() = _binding!!

    private val startCameraForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            showImage()
        }
    }

    private val startGalleryForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            imageUri = it.data?.data
            showImage()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addPicBtn.setOnClickListener {
            showOptionsDialog()
        }
        // TODO: Do something when the permisson are not gatenteed key: use onRequestPermissionsResult
        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION)
        }
    }

    private fun showImage() {
        binding.reportImg.setImageURI(imageUri)
        if(binding.reportImg.visibility != View.VISIBLE) {
            binding.reportImg.visibility = View.VISIBLE
        }
    }

    private fun openCamera() {
        imageUri = this.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues())
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startCameraForResult.launch(intent)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startGalleryForResult.launch(intent)
    }

    private fun showOptionsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choose_image_option))
        val animals = arrayOf(getString(R.string.use_camera), getString(R.string.use_gallery))
        builder.setItems(animals) { _, which ->
            when (which) {
                0 -> openCamera()
                1 -> openGallery()
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}