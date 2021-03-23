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
package com.huawei.faceoffdresstrial.kotlin.imageseg

import android.graphics.Bitmap
import android.util.Log
import com.huawei.faceoffdresstrial.kotlin.common.Constants
import com.huawei.hms.mlsdk.MLAnalyzerFactory
import com.huawei.hms.mlsdk.common.MLFrame
import com.huawei.hms.mlsdk.imgseg.MLImageSegmentation
import com.huawei.hms.mlsdk.imgseg.MLImageSegmentationAnalyzer
import com.huawei.hms.mlsdk.imgseg.MLImageSegmentationScene
import com.huawei.hms.mlsdk.imgseg.MLImageSegmentationSetting
import java.io.IOException

class ImageSegmentation(var isListner: ImageSegmentationResultListner) {
    var analyzer: MLImageSegmentationAnalyzer? = null
    fun createAnalyzer() {
        val setting = MLImageSegmentationSetting.Factory()
                .setExact(IsStatus)
                .setAnalyzerType(MLImageSegmentationSetting.BODY_SEG)
                .setScene(MLImageSegmentationScene.FOREGROUND_ONLY)
                .create()
        analyzer = MLAnalyzerFactory.getInstance().getImageSegmentationAnalyzer(setting)
    }

    fun processSegmantationResult(bitmap: Bitmap?) {
        val frame = MLFrame.fromBitmap(bitmap)
        val task = analyzer!!.asyncAnalyseFrame(frame)
        task.addOnSuccessListener { segmentation ->
            segmentation?.let { displaySuccess(it) } ?: Log.d(TAG, Constants.NULL_MSG)
        }
                .addOnFailureListener { e -> Log.d(TAG, Constants.ON_FAILURE_STR, e) }
    }

    private fun displaySuccess(imageSegmentationResult: MLImageSegmentation) {
        val bitmapFore = imageSegmentationResult.getForeground()
        if (bitmapFore != null) {
            isListner.getSegmentationImage(bitmapFore)
            stopAnalyser()
        } else {
            Log.d(TAG, Constants.BITMAP_NULL)
        }
    }

    fun stopAnalyser() {
        if (analyzer != null) {
            try {
                analyzer!!.stop()
            } catch (e: IOException) {
                Log.d(TAG, Constants.EXCEPTION_MSG, e)
            }
        }
    }

    companion object {
        private const val TAG = "ImageSegmentation"
        private const val IsStatus = false
    }

}