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

package com.huawei.faceoffdresstrial.java.common;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Util {

    public static Bitmap getImageFromAssetsFile(Context mContext, String fileName) {
        Bitmap image = null;
        AssetManager am = mContext.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            new ExceptionHandling().PrintExceptionInfo(Constants.IOEXCEPTION, e);
        }
        return image;
    }

    public String BitMapToString(Bitmap bitmap)
    {
       String temp = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, Constants.IMAGE_QUALITY, baos);
            byte[] b = baos.toByteArray();
            temp = Base64.encodeToString(b, Base64.DEFAULT);
            baos.close();
        } catch (IOException e) {
        }
        return temp;
    }


    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, Constants.BITMAP_OFFSET, encodeByte.length);
            return bitmap;
        } catch (NullPointerException e) {
            new ExceptionHandling().PrintExceptionInfo(Constants.NULL_POINTER_EXCEPTION, e);
            return null;
        } catch (IllegalArgumentException e) {
            new ExceptionHandling().PrintExceptionInfo(Constants.ILLEGAL_ARGUMENT_EXCEPTION, e);
            return null;
        }
    }


    public Bitmap setScaleImage(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float xScale = ((float) newWidth) / width;
        float yScale = ((float) newHeight) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, Constants.BITMAP_X, Constants.BITMAP_Y, width, height, matrix, Constants.STATUS_SUCCESS);
        return scaledBitmap;
    }


    public Bitmap rotateImage(Bitmap source, float angle) {
        if(source==null) return null;
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, Constants.BITMAP_X, Constants.BITMAP_Y, source.getWidth(), source.getHeight(), matrix,Constants.STATUS_SUCCESS);
    }
}
