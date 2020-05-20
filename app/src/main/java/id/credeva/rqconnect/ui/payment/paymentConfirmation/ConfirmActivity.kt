package id.credeva.rqconnect.ui.payment.paymentConfirmation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.content.PermissionChecker
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.databinding.ActivityConfirmBinding
import id.credeva.rqconnect.ui.main.MainActivity
import id.credeva.rqconnect.ui.payment.paymentConfirmation.deposit.DepositViewModel
import id.credeva.rqconnect.ui.payment.paymentConfirmation.deposit.DepositViewModelFactory
import id.credeva.rqconnect.ui.payment.paymentConfirmation.infaq.InfaqViewModel
import id.credeva.rqconnect.ui.payment.paymentConfirmation.infaq.InfaqViewModelFactory
import id.credeva.rqconnect.util.gone
import id.credeva.rqconnect.util.show
import id.credeva.rqconnect.util.toast
import kotlinx.android.synthetic.main.activity_confirm.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ConfirmActivity : AppCompatActivity(), KodeinAware,
    ConfirmListener {

    override val kodein by kodein()
    private val factory: ConfirmViewModelFactory by instance()
    private val depositFactory: DepositViewModelFactory by instance()
    private val infaqFactory: InfaqViewModelFactory by instance()

    private var cameraPath: String? = null
    private var finalPath: String? = null
    private var galleryPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityConfirmBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_confirm)
        val viewModel = ViewModelProviders.of(this, factory).get(ConfirmViewModel::class.java)
        val viewModelDeposit =
            ViewModelProviders.of(this, depositFactory).get(DepositViewModel::class.java)
        val viewModelInfaq =
            ViewModelProviders.of(this, infaqFactory).get(InfaqViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.confirmListener = this

        tv_payment_code.text = prefManager.spRefKey

        btn_upload.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.dialog_layout, null)

            val camera = view.findViewById<LinearLayout>(R.id.ll_camera)
            val gallery = view.findViewById<LinearLayout>(R.id.ll_gallery)

            dialog.setContentView(view)
            dialog.show()

            camera.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_DENIED
                    ) {
                        val permissions = arrayOf(Manifest.permission.CAMERA);
                        requestPermissions(permissions, CAMERA_REQUEST);
                    } else {
                        openCamera();
                    }
                } else {
                    openCamera();
                }
                dialog.dismiss()
            }

            gallery.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED
                    ) {
                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        pickImageFromGallery();
                    }
                } else {
                    pickImageFromGallery();
                }
                dialog.dismiss()
            }
        }

        btn_confirm.setOnClickListener {
            if (finalPath != null) {
                viewModel.sendData(finalPath!!)
                viewModelDeposit.sendDeposit()
                viewModelInfaq.sendInfaq()
                prefManager.spStatusPayment = "active"
            } else {
                this.toast("Maaf, bukti foto masih kosong")
            }
        }

        iv_back.setOnClickListener { onBackPressed() }

    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
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
                } else {
                    toast("Permission di tolak")
                }
            }
        }

        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    pickImageFromGallery()
                } else {
                    toast("Permission di tolak")
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
                    FileProvider.getUriForFile(this, "id.credeva.rqconnect.provider", photo)
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


        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val selectedImage = data?.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

            val cursor =
                contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
            assert(cursor != null)
            cursor!!.moveToFirst()

            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            galleryPath = cursor.getString(columnIndex)
            iv_evidence.setImageBitmap(BitmapFactory.decodeFile(galleryPath))
            cursor.close()

            finalPath = galleryPath
            Log.v("Gallery Path: ", finalPath.toString())

            tv_evidence.visibility = View.VISIBLE
        }
    }

    override fun onStarted() {
        btn_confirm.visibility = View.GONE
        pb_confirm.show()
    }

    override fun onSucces() {
        this.cl_confirm.visibility = View.GONE
        this.ll_success_payment.visibility = View.VISIBLE

        Handler().postDelayed(
            {
                val i = Intent(applicationContext, MainActivity::class.java)        // Specify any activity here e.g. home or splash or login etc
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
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
        private val IMAGE_PICK_CODE = 1001;
        private val PERMISSION_CODE = 1002;
    }
}