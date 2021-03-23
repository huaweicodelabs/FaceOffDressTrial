package com.huawei.faceoffdresstrial.kotlin.common

import android.util.Log

class ExceptionHandling {
    fun PrintExceptionInfo(tag: String?, e: Exception?) {
        Log.d(tag, Constants.EXCEPTION_MSG, e)
    }
}