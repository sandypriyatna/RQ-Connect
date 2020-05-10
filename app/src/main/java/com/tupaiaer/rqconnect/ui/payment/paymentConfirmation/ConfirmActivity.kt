package com.tupaiaer.rqconnect.ui.payment.paymentConfirmation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.content.PermissionChecker
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.RqconnectApplication.Companion.prefManager
import com.tupaiaer.rqconnect.databinding.ActivityConfirmBinding
import com.tupaiaer.rqconnect.ui.main.MainActivity
import com.tupaiaer.rqconnect.util.gone
import com.tupaiaer.rqconnect.util.show
import com.tupaiaer.rqconnect.util.toast
import kotlinx.android.synthetic.main.activity_confirm.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ConfirmActivity : AppCompatActivity(), KodeinAware,
    ConfirmListener {

    override val kodein by kodein()
    private val factory: ConfirmViewModelFactory by instance()

    private var cameraPath: String? = null
    private var finalPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityConfirmBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_confirm)
        val viewModel = ViewModelProviders.of(this, factory).get(ConfirmViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.confirmListener = this

        tv_payment_code.text = prefManager.spRefKey

        btn_upload.setOnClickListener {
            if (isGranted()) {
                openCamera()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_REQUEST
                )
            }
        }

        btn_confirm.setOnClickListener {
            if (finalPath != null) {
                viewModel.sendData(finalPath!!)
            } else {
                this.toast("Maaf, bukti foto masih kosong")
            }
        }

        iv_back.setOnClickListener { onBackPressed() }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                }
            }
        }
    }

    private fun isGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        } else {
            PermissionChecker.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PermissionChecker.PERMISSION_GRANTED
        }
    }

    private fun openCamera() {
        var photo: File? = null
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            try {
                photo = createImageFile()
            } catch (e: Exception) {
                Log.e("CameraFoto : ", e.message.toString())
            }

            if (photo != null) {
                val imageUri =
                    FileProvider.getUriForFile(this, "com.tupaiaer.rqconnect.provider", photo)
                Log.v("Image Path ", imageUri.lastPathSegment.toString())
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(
                    intent,
                    CAMERA_REQUEST
                )
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val myImage = File.createTempFile(fileName, ".jpg", storageDir)
        cameraPath = myImage.absolutePath
        return myImage
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Log.v("Image Path: ", cameraPath.toString())

            Glide.with(this)
                .load(cameraPath)
                .into(iv_evidence)

            iv_evidence.visibility = View.VISIBLE
            tv_evidence.visibility = View.VISIBLE

            finalPath = cameraPath
        }
    }

    override fun onStarted() {
        btn_confirm.visibility = View.GONE
        pb_confirm.show()
    }

    override fun onSucces(message: String) {
        this.cl_confirm.visibility = View.GONE
        this.ll_success_payment.visibility = View.VISIBLE

        Handler().postDelayed(
            {
                startActivity(Intent(this@ConfirmActivity, MainActivity::class.java))
                finish()
            }, 3000
        )
    }

    override fun onFailure(message: String) {
        pb_confirm.gone()
        this.cl_confirm.visibility = View.VISIBLE
        this.toast(message)
    }

    companion object {
        private const val CAMERA_REQUEST = 1000
    }
}
