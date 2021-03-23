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

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.huawei.faceoffdresstrial.R
import com.huawei.faceoffdresstrial.kotlin.common.Constants
import com.huawei.faceoffdresstrial.kotlin.common.Util
import com.huawei.faceoffdresstrial.kotlin.imageseg.ImageSegmentation
import com.huawei.faceoffdresstrial.kotlin.imageseg.ImageSegmentationResultListner
import com.huawei.faceoffdresstrial.kotlin.preferences.SharedPref
import com.huawei.hms.image.vision.crop.CropLayoutView

class ImageCropActivity : AppCompatActivity() {
    var cropLayoutView: CropLayoutView? = null
    var mSharedPref: SharedPref? = null
    var ASPECT_RATIO_X = 150
    var ASPECT_RATIO_Y = 150
    var ROTATION_ANGLE_IN_DEGREES = 270
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_crop)
        supportActionBar!!.title = Constants.TITLE_IMAGE_CROP
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        SharedPref.init(this@ImageCropActivity)
        var bmp: Bitmap? = null
        val stringArray = SharedPref!!.imageString
        if (stringArray != null) {
            val bytes = Base64.decode(stringArray, Base64.DEFAULT)
            bmp = BitmapFactory.decodeByteArray(bytes, Constants.INDEX_ZERO, bytes.size)
        } else {
            Log.d(TAG, Constants.ARRAY_NULL)
        }
        cropLayoutView = findViewById(R.id.cropImageView)
        if (bmp != null) cropLayoutView?.setImageBitmap(Util().rotateImage(bmp, ROTATION_ANGLE_IN_DEGREES.toFloat()))
        cropLayoutView?.setAspectRatio(ASPECT_RATIO_X, ASPECT_RATIO_Y)
        cropLayoutView?.setFixedAspectRatio(Constants.STATUS_FAILURE)
        val btnSave = findViewById<Button>(R.id.btn_save)
        val btnRotation = findViewById<Button>(R.id.btn_rotation)
        val btnFlipHorizonal = findViewById<Button>(R.id.btn_flip_horizonal)
        val imageView = findViewById<ImageView>(R.id.imageview)
        btnSave.setOnClickListener {
            val croppedImage = cropLayoutView?.getCroppedImage()
            imageView.setImageBitmap(croppedImage)
            if (croppedImage != null) {
                processImageSegment(croppedImage)
            }
        }
        btnRotation.setOnClickListener { cropLayoutView?.rotateClockwise() }
        btnFlipHorizonal.setOnClickListener { cropLayoutView?.flipImageHorizontally() }
    }

    private fun processImageSegment(bitmap: Bitmap) {
        val imageSegmentation = ImageSegmentation(
                object : ImageSegmentationResultListner {
                    override fun getSegmentationImage(bitmap: Bitmap) {
                        val croppedEncodedString = Util().BitMapToString(bitmap)
                        SharedPref.saveCropImageString(croppedEncodedString)
                        finish()
                    }
                })

        imageSegmentation.createAnalyzer()
        imageSegmentation.processSegmantationResult(bitmap)
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
        private const val TAG = "ImageCropActivity"
    }
}