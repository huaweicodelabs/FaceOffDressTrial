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
package com.huawei.faceoffdresstrial.kotlin.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.IOException

class Util {
    fun BitMapToString(bitmap: Bitmap): String? {
        var temp: String? = null
        try {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, Constants.IMAGE_QUALITY, baos)
            val b = baos.toByteArray()
            temp = Base64.encodeToString(b, Base64.DEFAULT)
            baos.close()
        } catch (e: IOException) {
        }
        return temp
    }

    fun StringToBitMap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte = Base64.decode(encodedString, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(encodeByte, Constants.BITMAP_OFFSET, encodeByte.size)
        } catch (e: NullPointerException) {
            ExceptionHandling().PrintExceptionInfo(Constants.NULL_POINTER_EXCEPTION, e)
            null
        } catch (e: IllegalArgumentException) {
            ExceptionHandling().PrintExceptionInfo(Constants.ILLEGAL_ARGUMENT_EXCEPTION, e)
            null
        }
    }

    fun setScaleImage(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val xScale = newWidth.toFloat() / width
        val yScale = newHeight.toFloat() / height
        val scale = if (xScale <= yScale) xScale else yScale
        val matrix = Matrix()
        matrix.postScale(scale, scale)
        return Bitmap.createBitmap(bitmap, Constants.BITMAP_X, Constants.BITMAP_Y, width, height, matrix, Constants.STATUS_SUCCESS)
    }

    fun rotateImage(source: Bitmap?, angle: Float): Bitmap? {
        if (source == null) return null
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, Constants.BITMAP_X, Constants.BITMAP_Y, source.width, source.height, matrix, Constants.STATUS_SUCCESS)
    }

    companion object {
        fun getImageFromAssetsFile(mContext: Context, fileName: String?): Bitmap? {
            var image: Bitmap? = null
            val am = mContext.resources.assets
            try {
                val `is` = am.open(fileName!!)
                image = BitmapFactory.decodeStream(`is`)
                `is`.close()
            } catch (e: IOException) {
                ExceptionHandling().PrintExceptionInfo(Constants.IOEXCEPTION, e)
            }
            return image
        }
    }
}