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

package com.huawei.faceoffdresstrial.java.imageseg;

import android.graphics.Bitmap;
import android.util.Log;

import com.huawei.faceoffdresstrial.java.common.Constants;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.mlsdk.MLAnalyzerFactory;
import com.huawei.hms.mlsdk.common.MLFrame;
import com.huawei.hms.mlsdk.imgseg.MLImageSegmentation;
import com.huawei.hms.mlsdk.imgseg.MLImageSegmentationAnalyzer;
import com.huawei.hms.mlsdk.imgseg.MLImageSegmentationScene;
import com.huawei.hms.mlsdk.imgseg.MLImageSegmentationSetting;

import java.io.IOException;


public class ImageSegmentation {
    private static final String TAG = "ImageSegmentation";

    private static final boolean IsStatus = false;
    ImageSegmentationResultListner isListner;
    MLImageSegmentationAnalyzer analyzer;

    public ImageSegmentation(ImageSegmentationResultListner isListner) {
        this.isListner = isListner;
    }

    public void createAnalyzer() {

        MLImageSegmentationSetting setting =
                new MLImageSegmentationSetting.Factory()
                        .setExact(IsStatus)
                        .setAnalyzerType(MLImageSegmentationSetting.BODY_SEG)
                        .setScene(MLImageSegmentationScene.FOREGROUND_ONLY)
                        .create();
        analyzer = MLAnalyzerFactory.getInstance().getImageSegmentationAnalyzer(setting);
    }


    public void processSegmantationResult(Bitmap bitmap) {
        MLFrame frame = MLFrame.fromBitmap(bitmap);
        Task<MLImageSegmentation> task = analyzer.asyncAnalyseFrame(frame);
        task.addOnSuccessListener(
                new OnSuccessListener<MLImageSegmentation>() {
                    @Override
                    public void onSuccess(MLImageSegmentation segmentation) {
                        if (segmentation != null) {
                            displaySuccess(segmentation);
                        } else {
                            Log.d(TAG,Constants.NULL_MSG);
                        }
                    }
                })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {
                                Log.d(TAG, Constants.ON_FAILURE_STR, e);
                            }
                        });
    }

    private void displaySuccess(MLImageSegmentation imageSegmentationResult) {
        Bitmap bitmapFore = imageSegmentationResult.getForeground();
        if (bitmapFore != null) {
            isListner.getSegmentationImage(bitmapFore);
            stopAnalyser();
        } else {
            Log.d(TAG, Constants.BITMAP_NULL);
        }
    }


    public void stopAnalyser() {
        if (analyzer != null) {
            try {
                analyzer.stop();
            } catch (IOException e) {
                Log.d(TAG,Constants.EXCEPTION_MSG, e);
            }
        }
    }
}
