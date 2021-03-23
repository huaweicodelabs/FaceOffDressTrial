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
package com.huawei.faceoffdresstrial.kotlin.preferences

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.huawei.faceoffdresstrial.kotlin.common.Constants

class SharedPref {
    companion object{
        private var mSharedPref: SharedPreferences? = null
        fun init(context: Context) {
            if (mSharedPref == null) mSharedPref = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
        }

        val imageString: String?
            get() = mSharedPref!!.getString(Constants.IMAGE_STRING, null)

        fun saveImageString(data: String?) {
            val prefsEditor = mSharedPref!!.edit()
            prefsEditor.putString(Constants.IMAGE_STRING, data)
            prefsEditor.commit()
        }

        val cropImageString: String?
            get() = mSharedPref!!.getString(Constants.CROP_IMAGE_STRING, null)

        fun saveCropImageString(data: String?) {
            val prefsEditor = mSharedPref!!.edit()
            prefsEditor.putString(Constants.CROP_IMAGE_STRING, data)
            prefsEditor.commit()
        }
    }
}