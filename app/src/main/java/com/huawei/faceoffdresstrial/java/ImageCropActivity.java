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
package com.huawei.faceoffdresstrial.java;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.faceoffdresstrial.R;
import com.huawei.faceoffdresstrial.java.common.Constants;
import com.huawei.faceoffdresstrial.java.common.Util;
import com.huawei.faceoffdresstrial.java.imageseg.ImageSegmentation;
import com.huawei.faceoffdresstrial.java.imageseg.ImageSegmentationResultListner;
import com.huawei.faceoffdresstrial.java.preferences.SharedPref;
import com.huawei.hms.image.vision.crop.CropLayoutView;


public class ImageCropActivity extends AppCompatActivity {
    private static final String TAG = "ImageCropActivity";

    CropLayoutView cropLayoutView;
    SharedPref mSharedPref;
    int ASPECT_RATIO_X = 150;
    int ASPECT_RATIO_Y = 150;
    int ROTATION_ANGLE_IN_DEGREES = 270;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_crop);
        getSupportActionBar().setTitle(Constants.TITLE_IMAGE_CROP);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSharedPref.init(ImageCropActivity.this);
        Bitmap bmp = null;

        String stringArray = mSharedPref.getImageString();
        if (stringArray != null) {
            byte[] bytes = Base64.decode(stringArray, Base64.DEFAULT);
            bmp = BitmapFactory.decodeByteArray(bytes, Constants.INDEX_ZERO, bytes.length);
        } else {
            Log.d(TAG, Constants.ARRAY_NULL);
        }

        cropLayoutView = findViewById(R.id.cropImageView);
        if(bmp!=null)
        cropLayoutView.setImageBitmap(new Util().rotateImage(bmp, ROTATION_ANGLE_IN_DEGREES));
        cropLayoutView.setAspectRatio(ASPECT_RATIO_X, ASPECT_RATIO_Y);
        cropLayoutView.setFixedAspectRatio(Constants.STATUS_FAILURE);

        Button btnSave = findViewById(R.id.btn_save);
        Button btnRotation = findViewById(R.id.btn_rotation);
        Button btnFlipHorizonal = findViewById(R.id.btn_flip_horizonal);
        final ImageView imageView = findViewById(R.id.imageview);

        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bitmap croppedImage = cropLayoutView.getCroppedImage();
                        imageView.setImageBitmap(croppedImage);
                        processImageSegment(croppedImage);
                    }
                });

        btnRotation.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cropLayoutView.rotateClockwise();
                    }
                });
        btnFlipHorizonal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cropLayoutView.flipImageHorizontally();
                    }
                });
    }

    private void processImageSegment(Bitmap bitmap) {
        ImageSegmentation imageSegmentation =
                new ImageSegmentation(
                        new ImageSegmentationResultListner() {
                            @Override
                            public void getSegmentationImage(Bitmap bitmap) {
                                String croppedEncodedString = new Util().BitMapToString(bitmap);
                                mSharedPref.saveCropImageString(croppedEncodedString);
                                finish();
                            }
                        });
        imageSegmentation.createAnalyzer();
        imageSegmentation.processSegmantationResult(bitmap);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
