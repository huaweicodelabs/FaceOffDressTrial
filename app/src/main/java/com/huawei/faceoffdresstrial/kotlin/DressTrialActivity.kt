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
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.huawei.faceoffdresstrial.R
import com.huawei.faceoffdresstrial.kotlin.adapter.DressAdapter
import com.huawei.faceoffdresstrial.kotlin.adapter.DressAdapter.ImageViewOnClick
import com.huawei.faceoffdresstrial.kotlin.common.Constants
import com.huawei.faceoffdresstrial.kotlin.common.Util
import com.huawei.faceoffdresstrial.kotlin.model.DressData
import com.huawei.faceoffdresstrial.kotlin.preferences.SharedPref
import com.huawei.faceoffdresstrial.kotlin.repo.RoomRepo
import java.util.*

class DressTrialActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var dressDataList: List<DressData?>? = ArrayList()
    private var RecyclerViewLayoutManager: RecyclerView.LayoutManager? = null
    private var dressAdapter: DressAdapter? = null
    private var HorizontalLayout: LinearLayoutManager? = null
    private var imageview_image: ImageView? = null
    private var imageview_dress: ImageView? = null
    //private val mSharedPref: SharedPref? = null
    private var imageBitmap: Bitmap? = null
    private var currentDressData: DressData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            Log.d(TAG, NULL_POINTER, e)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dress_trial)
        if (ActivityCompat.checkSelfPermission(this, permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission()
        }
        val intent = intent
        Constants.DRESS_TYPE = intent.getIntExtra(Constants.DRESS_TYPE_STR, Constants.INDEX_ONE)
        SharedPref.init(this)
        imageview_image = findViewById(R.id.imageview_image)
        val imageviewCamera = findViewById<ImageView>(R.id.imageview_camera)
        val imageviewBack = findViewById<ImageView>(R.id.imageview_back)
        imageview_dress = findViewById<View>(R.id.imageview_dress) as ImageView
        imageviewCamera.setOnClickListener { openCamera() }
        imageviewBack.setOnClickListener { finish() }
        recyclerView = findViewById<View>(R.id.recyclerview) as RecyclerView
        RecyclerViewLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = RecyclerViewLayoutManager
        addDressData(Constants.DRESS_TYPE)
        dressAdapter = DressAdapter(
                dressDataList,
                object : ImageViewOnClick {
                    override fun onClick(dressData: DressData) {
                        imageview_dress!!.setImageBitmap(dressData.bitmap)
                        currentDressData = dressData
                        meargeImage(currentDressData!!.bitmap)
                    }
                })
        HorizontalLayout = LinearLayoutManager(this@DressTrialActivity, LinearLayoutManager.HORIZONTAL, Constants.STATUS_FAILURE)
        recyclerView!!.layoutManager = HorizontalLayout
        recyclerView!!.adapter = dressAdapter
    }

    override fun onResume() {
        super.onResume()
        if (SharedPref.cropImageString != null) {
            imageview_image!!.setImageBitmap(Util().StringToBitMap(SharedPref.cropImageString))
            imageBitmap = Util().StringToBitMap(SharedPref.cropImageString)
            meargeImage(currentDressData!!.bitmap)
        }
    }

    private fun requestCameraPermission() {
        val permissions = arrayOf(permission.CAMERA)
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, Constants.CAMERA_PERMISSION_CODE)
            return
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != Constants.CAMERA_PERMISSION_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        if (grantResults.size != Constants.INDEX_ZERO && grantResults[Constants.INDEX_ZERO] == PackageManager.PERMISSION_GRANTED) {
            return
        }
    }

    private fun openCamera() {
        val captureIntent = Intent(this@DressTrialActivity, ImageCaptureActivity::class.java)
        startActivity(captureIntent)
    }

    fun addDressData(dressType: Int) {
        dressDataList= ArrayList()
      //  dressDataList = RoomRepo().addDressDataList(dressType, this@DressTrialActivity)

        val  dt =RoomRepo()
        dressDataList= dt.addDressDataList(dressType,this@DressTrialActivity)
        //dressDataList = dressType.addDressDataList(dressType, this@DressTrialActivity)
        if (dressDataList?.size!! > Constants.INDEX_ZERO) {
            currentDressData = dressDataList?.get(Constants.INDEX_ZERO)
            imageview_dress!!.setImageBitmap(currentDressData?.bitmap)
        }
    }

    private fun meargeImage(dressForground: Bitmap?) {
        if (currentDressData == null) return
        val dressDataX1 = currentDressData!!.dressDataX1
        val dressDataX2 = currentDressData!!.dressDataX2
        val dressDataY1 = currentDressData!!.dressDataY1
        val dressDataY2 = currentDressData!!.dressDataY2
        if (dressForground == null || imageBitmap == null) return
        val background: Bitmap? = Util.Companion.getImageFromAssetsFile(this@DressTrialActivity, Constants.IMAGE_NAME)
        val bmp = Bitmap.createScaledBitmap(background!!, dressForground.width, dressForground.height, Constants.STATUS_SUCCESS)
        val overlayBitmap = Bitmap.createBitmap(dressForground.width, dressForground.height, dressForground.config)
        var canvas: Canvas? = null
        var dest: Bitmap? = null
        dest = Util().setScaleImage(imageBitmap!!, dressDataX2 - dressDataX1, dressDataY2 - dressDataY1)
        if (dest == null) return
        canvas = Canvas(overlayBitmap)
        val newX = dressDataX1 + (dressDataX2 - dressDataX1 - dest.width) / Constants.DIVIDE_VALUE
        val newY = dressDataY2 - dest.height
        Log.d(TAG, newX.toString())
        canvas.drawBitmap(bmp, Matrix(), null)
        canvas.drawBitmap(dest, newX.toFloat(), newY.toFloat(), Paint())
        canvas.drawBitmap(dressForground, Matrix(), null)
        imageview_dress!!.setImageBitmap(overlayBitmap)
    }

    companion object {
        private const val TAG = "DressTrialActivity"
        private const val NULL_POINTER = "Null_Pointer_Exception"
    }
}