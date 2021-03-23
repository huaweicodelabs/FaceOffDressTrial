/*
Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.huawei.faceoffdresstrial.kotlin

import android.Manifest.permission
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.hardware.Camera.CameraInfo
import android.hardware.Camera.PictureCallback
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.view.MenuItem
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.huawei.faceoffdresstrial.R
import com.huawei.faceoffdresstrial.kotlin.common.Constants
import com.huawei.faceoffdresstrial.kotlin.common.ExceptionHandling
import com.huawei.faceoffdresstrial.kotlin.preferences.SharedPref
import java.io.IOException
import java.util.*

class ImageCaptureActivity : AppCompatActivity(), SurfaceHolder.Callback, PictureCallback {
    private val neededPermissions = arrayOf(permission.CAMERA, permission.WRITE_EXTERNAL_STORAGE)
    private var surfaceHolder: SurfaceHolder? = null
    private var camera: Camera? = null
    private var surfaceView: SurfaceView? = null
    private val mSharedPref: SharedPref? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_captute)
        supportActionBar!!.title = Constants.IMAGE_CAPTURE
        supportActionBar!!.setDisplayHomeAsUpEnabled(Constants.STATUS_SUCCESS)
        SharedPref.init(this@ImageCaptureActivity)
        surfaceView = findViewById(R.id.surfaceView)
        if (surfaceView != null) {
            val result = checkPermission()
            if (result) {
                setupSurfaceHolder()
            }
        }
    }

    private fun checkPermission(): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            val permissionsNotGranted = ArrayList<String>()
            for (permission in neededPermissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsNotGranted.add(permission)
                }
            }
            if (permissionsNotGranted.size > Constants.INDEX_ZERO) {
                var shouldShowAlert = Constants.STATUS_FAILURE
                for (permission in permissionsNotGranted) {
                    shouldShowAlert = ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
                }
                if (shouldShowAlert) {
                    showPermissionAlert(permissionsNotGranted.toTypedArray())
                } else {
                    requestPermissions(permissionsNotGranted.toTypedArray())
                }
                return false
            }
        }
        return true
    }

    private fun showPermissionAlert(permissions: Array<String>) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setCancelable(Constants.STATUS_SUCCESS)
        alertBuilder.setTitle(R.string.permission_required)
        alertBuilder.setMessage(R.string.permission_message)
        alertBuilder.setPositiveButton(
                android.R.string.yes
        ) { dialog, which -> requestPermissions(permissions) }
        val alert = alertBuilder.create()
        alert.show()
    }

    private fun requestPermissions(permissions: Array<String>) {
        ActivityCompat.requestPermissions(this@ImageCaptureActivity, permissions, Constants.REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            Constants.REQUEST_CODE -> {
                for (result in grantResults) {
                    if (result == PackageManager.PERMISSION_DENIED) {
                        return
                    }
                }
                setupSurfaceHolder()
            }
            else -> {
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun setViewVisibility(id: Int, visibility: Int) {
        val view = findViewById<View>(id)
        if (view != null) {
            view.visibility = visibility
        }
    }

    private fun setupSurfaceHolder() {
        setViewVisibility(R.id.startBtn, View.VISIBLE)
        setViewVisibility(R.id.surfaceView, View.VISIBLE)
        surfaceHolder = surfaceView!!.holder
        surfaceHolder?.addCallback(this)
        setBtnClick()
    }

    private fun setBtnClick() {
        val startBtn = findViewById<Button>(R.id.startBtn)
        startBtn?.setOnClickListener { captureImage() }
    }

    fun captureImage() {
        if (camera != null) {
            camera!!.takePicture(null, null, this)
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        startCamera()
    }

    private fun startCamera() {
        camera = Camera.open(getCameraId(camera))
        camera?.setDisplayOrientation(Constants.DISPLAY_ORIENTATION)
        try {
            camera?.setPreviewDisplay(surfaceHolder)
            camera?.startPreview()
        } catch (e: IOException) {
            ExceptionHandling().PrintExceptionInfo(Constants.IOEXCEPTION, e)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        resetCamera()
    }

    fun resetCamera() {
        if (surfaceHolder!!.surface == null) {
            return
        }
        if (camera != null) {
            camera!!.stopPreview()
            try {
                camera!!.setPreviewDisplay(surfaceHolder)
            } catch (e: IOException) {
                ExceptionHandling().PrintExceptionInfo(Constants.IOEXCEPTION, e)
            }
            camera!!.startPreview()
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        releaseCamera()
    }

    private fun releaseCamera() {
        if (camera != null) {
            camera!!.stopPreview()
            camera!!.release()
            camera = null
        }
    }

    override fun onPictureTaken(data: ByteArray, camera: Camera) {
        getImage(data)
        resetCamera()
    }

    private fun getImage(bytes: ByteArray) {
        val data = Base64.encodeToString(bytes, Base64.DEFAULT)
        SharedPref.saveImageString(data)
        val intent = Intent(this, ImageCropActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "ImageCaptureActivity"
        fun getCameraId(camera: Camera?): Int {
            val numberOfCameras = Camera.getNumberOfCameras()
            val cameraInfo = CameraInfo()
            for (i in Constants.INDEX_ZERO until numberOfCameras) {
                Camera.getCameraInfo(i, cameraInfo)
                if (cameraInfo.facing == CameraInfo.CAMERA_FACING_FRONT) {
                    return i
                }
            }
            return 0
        }
    }
}