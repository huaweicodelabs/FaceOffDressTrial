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

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huawei.faceoffdresstrial.java.ImageCaptureActivity;import com.huawei.faceoffdresstrial.R;
import com.huawei.faceoffdresstrial.java.adapter.DressAdapter;
import com.huawei.faceoffdresstrial.java.common.Constants;
import com.huawei.faceoffdresstrial.java.common.Util;
import com.huawei.faceoffdresstrial.java.imageseg.ImageSegmentation;
import com.huawei.faceoffdresstrial.java.imageseg.ImageSegmentationResultListner;
import com.huawei.faceoffdresstrial.java.model.DressData;
import com.huawei.faceoffdresstrial.java.preferences.SharedPref;
import com.huawei.faceoffdresstrial.java.repo.RoomRepo;

import java.util.ArrayList;
import java.util.List;


public class DressTrialActivity extends AppCompatActivity {
    private static final String TAG = "DressTrialActivity";

    private static final String NULL_POINTER    =   "Null_Pointer_Exception";
    private RecyclerView recyclerView;
    private List<DressData> dressDataList = new ArrayList<>();
    private RecyclerView.LayoutManager RecyclerViewLayoutManager;
    private DressAdapter dressAdapter;
    private LinearLayoutManager HorizontalLayout;
    private ImageView imageview_image;
    private ImageView imageview_dress;
    private SharedPref mSharedPref;

    private Bitmap imageBitmap;
    private DressData currentDressData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            Log.d(TAG,NULL_POINTER, e);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress_trial);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            this.requestCameraPermission();
        }

        Intent intent = getIntent();
        Constants.DRESS_TYPE = intent.getIntExtra(Constants.DRESS_TYPE_STR, Constants.INDEX_ONE);

        mSharedPref.init(this);

        imageview_image = findViewById(R.id.imageview_image);
        ImageView imageviewCamera = findViewById(R.id.imageview_camera);
        ImageView imageviewBack = findViewById(R.id.imageview_back);
        imageview_dress = (ImageView) findViewById(R.id.imageview_dress);

        imageviewCamera.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openCamera();
                    }
                });
        imageviewBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        addDressData(Constants.DRESS_TYPE);
        dressAdapter =
                new DressAdapter(
                        dressDataList,
                        new DressAdapter.ImageViewOnClick() {
                            @Override
                            public void onClick(DressData dressData) {
                                imageview_dress.setImageBitmap(dressData.getBitmap());
                                currentDressData = dressData;
                                meargeImage(currentDressData.getBitmap());
                            }
                        });
        HorizontalLayout = new LinearLayoutManager(DressTrialActivity.this, LinearLayoutManager.HORIZONTAL, Constants.STATUS_FAILURE);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(dressAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSharedPref.getCropImageString() != null) {
            imageview_image.setImageBitmap(new Util().StringToBitMap(mSharedPref.getCropImageString()));
            imageBitmap = new Util().StringToBitMap(mSharedPref.getCropImageString());
            meargeImage(currentDressData.getBitmap());
        }
    }

    private void requestCameraPermission() {
        final String[] permissions = new String[]{Manifest.permission.CAMERA};

        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, Constants.CAMERA_PERMISSION_CODE);
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != Constants.CAMERA_PERMISSION_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        if (grantResults.length != Constants.INDEX_ZERO && grantResults[Constants.INDEX_ZERO] == PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }

    private void openCamera() {
        Intent captureIntent = new Intent(DressTrialActivity.this, ImageCaptureActivity.class);
        startActivity(captureIntent);
    }

    public void addDressData(int dressType) {
        dressDataList.clear();
        dressDataList = new RoomRepo().addDressDataList(dressType, DressTrialActivity.this);
        if (dressDataList.size() > Constants.INDEX_ZERO) {
            currentDressData = dressDataList.get(Constants.INDEX_ZERO);
            imageview_dress.setImageBitmap(currentDressData.getBitmap());
        }
    }

    private void meargeImage(Bitmap dressForground) {
        if (currentDressData == null) return;
        int dressDataX1 = currentDressData.getDressDataX1();
        int dressDataX2 = currentDressData.getDressDataX2();
        int dressDataY1 = currentDressData.getDressDataY1();
        int dressDataY2 = currentDressData.getDressDataY2();

        if (dressForground == null || imageBitmap == null) return;
        Bitmap background = Util.getImageFromAssetsFile(DressTrialActivity.this, Constants.IMAGE_NAME);
        Bitmap bmp = Bitmap.createScaledBitmap(background, dressForground.getWidth(), dressForground.getHeight(), Constants.STATUS_SUCCESS);
        Bitmap overlayBitmap = Bitmap.createBitmap(dressForground.getWidth(), dressForground.getHeight(), dressForground.getConfig());
        Canvas canvas = null;
        Bitmap dest = null;
        dest = new Util().setScaleImage(imageBitmap, (dressDataX2 - dressDataX1), (dressDataY2 - dressDataY1));
        if (dest == null) return;
        canvas = new Canvas(overlayBitmap);
        int newX = dressDataX1 + (((dressDataX2 - dressDataX1) - dest.getWidth()) / Constants.DIVIDE_VALUE);
        int newY = dressDataY2 - dest.getHeight();
        Log.d(TAG, String.valueOf(newX));
        canvas.drawBitmap(bmp, new Matrix(), null);
        canvas.drawBitmap(dest, newX, newY, new Paint());
        canvas.drawBitmap(dressForground, new Matrix(), null);

        imageview_dress.setImageBitmap(overlayBitmap);
    }
}
