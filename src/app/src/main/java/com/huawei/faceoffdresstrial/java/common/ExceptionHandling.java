package com.huawei.faceoffdresstrial.java.common;

import android.util.Log;

public class ExceptionHandling
{

    public ExceptionHandling(){}
    public  void PrintExceptionInfo(String tag, Exception e)
    {
        Log.d(tag,Constants.EXCEPTION_MSG,e);
    }
}
