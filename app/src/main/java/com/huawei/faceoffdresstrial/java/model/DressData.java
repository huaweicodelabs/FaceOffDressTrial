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
package com.huawei.faceoffdresstrial.java.model;

import android.graphics.Bitmap;

/**
 * Dress Data Detail
 */
public class DressData {
    int id;
    int dressType;
    String imageUrl;
    Bitmap bitmap;
    int dressDataX1 = 0;
    int dressDataY1 = 0;
    int dressDataX2 = 0;
    int dressDataY2 = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDressType() {
        return dressType;
    }

    public void setDressType(int dressType) {
        this.dressType = dressType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getDressDataX1() {
        return dressDataX1;
    }

    public void setDressDataX1(int dressDataX1) {
        this.dressDataX1 = dressDataX1;
    }

    public int getDressDataY1() {
        return dressDataY1;
    }

    public void setDressDataY1(int dressDataY1) {
        this.dressDataY1 = dressDataY1;
    }

    public int getDressDataX2() {
        return dressDataX2;
    }

    public void setDressDataX2(int dressDataX2) {
        this.dressDataX2 = dressDataX2;
    }

    public int getDressDataY2() {
        return dressDataY2;
    }

    public void setDressDataY2(int dressDataY2) {
        this.dressDataY2 = dressDataY2;
    }

    @Override
    public String toString() {
        return "DressData{"
                + "id="
                + id
                + ", dressType="
                + dressType
                + ", imageUrl='"
                + imageUrl
                + '\''
                + ", bitmap="
                + bitmap
                + ", x1="
                + dressDataX1
                + ", y1="
                + dressDataY1
                + ", x2="
                + dressDataX2
                + ", y2="
                + dressDataY2
                + '}';
    }
}
