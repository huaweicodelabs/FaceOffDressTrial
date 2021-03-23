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
package com.huawei.faceoffdresstrial.java.repo;

import android.content.Context;

import com.huawei.faceoffdresstrial.java.common.Constants;
import com.huawei.faceoffdresstrial.java.common.Util;
import com.huawei.faceoffdresstrial.java.model.DressData;

import java.util.ArrayList;
import java.util.List;


public class RoomRepo {


    private int DRESS_TYPE_1_DRESS_DATA_1_SETX_1 = 333;
    private int DRESS_TYPE_1_DRESS_DATA_1_SETY_1 = 0;
    private int DRESS_TYPE_1_DRESS_DATA_1_SETX_2 = 695;
    private int DRESS_TYPE_1_DRESS_DATA_1_SETY_2 = 550;
    private int DRESS_TYPE_1_DRESS_DATA_2_SETX_1 = 333;
    private int DRESS_TYPE_1_DRESS_DATA_2_SETY_1 = 0;
    private int DRESS_TYPE_1_DRESS_DATA_2_SETX_2 = 682;
    private int DRESS_TYPE_1_DRESS_DATA_2_SETY_2 = 533;
    private int DRESS_TYPE_2_DRESS_DATA_1_SETX_1 = 321;
    private int DRESS_TYPE_2_DRESS_DATA_1_SETY_1 = 290;
    private int DRESS_TYPE_2_DRESS_DATA_1_SETX_2 = 742;
    private int DRESS_TYPE_2_DRESS_DATA_1_SETY_2 = 752;
    private int DRESS_TYPE_2_DRESS_DATA_2_SETX_1 = 379;
    private int DRESS_TYPE_2_DRESS_DATA_2_SETY_1 = 290;
    private int DRESS_TYPE_2_DRESS_DATA_2_SETX_2 = 717;
    private int DRESS_TYPE_2_DRESS_DATA_2_SETY_2 = 779;
    private int DRESS_TYPE_3_DRESS_DATA_6_SETX_1 = 333;
    private int DRESS_TYPE_3_DRESS_DATA_6_SETY_1 = 0;
    private int DRESS_TYPE_3_DRESS_DATA_6_SETX_2 = 679;
    private int DRESS_TYPE_3_DRESS_DATA_6_SETY_2 = 550;
    private int DRESS_TYPE_3_DRESS_DATA_8_SETX_1 = 333;
    private int DRESS_TYPE_3_DRESS_DATA_8_SETY_1 = 0;
    private int DRESS_TYPE_3_DRESS_DATA_8_SETX_2 = 695;
    private int DRESS_TYPE_3_DRESS_DATA_8_SETY_2 = 550;
    private int DRESS_TYPE_4_DRESS_DATA_1_SETX_1 = 363;
    private int DRESS_TYPE_4_DRESS_DATA_1_SETY_1 = 55;
    private int DRESS_TYPE_4_DRESS_DATA_1_SETX_2 = 705;
    private int DRESS_TYPE_4_DRESS_DATA_1_SETY_2 = 615;
    private int DRESS_TYPE_4_DRESS_DATA_2_SETX_1 = 320;
    private int DRESS_TYPE_4_DRESS_DATA_2_SETY_1 = 60;
    private int DRESS_TYPE_4_DRESS_DATA_2_SETX_2 = 770;
    private int DRESS_TYPE_4_DRESS_DATA_2_SETY_2 = 633;
    private static final String mShirt_One = "shirt_1.png", mShirt_Two = "shirt_2.png", mTShirt_Two = "tshirt_2.png",
            mTShirt_One = "tshirt_1.png", mKids_1 = "kids_1.PNG", mKids_2 = "kids_2.PNG", mImage_One = "suits_2.png", mImage_Two = "suits_1.png";


    public List<DressData> addDressDataList(int dressType, Context context) {
        List<DressData> dressDataList = new ArrayList<>();

        dressDataList.clear();

        if (dressType == Constants.TYPE_ONE) {
            DressData dressData1 = new DressData();
            dressData1.setId(Constants.ID_ONE);
            dressData1.setDressType(Constants.TYPE_ONE);
            dressData1.setDressDataX1(DRESS_TYPE_1_DRESS_DATA_1_SETX_1);
            dressData1.setDressDataY1(DRESS_TYPE_1_DRESS_DATA_1_SETY_1);
            dressData1.setDressDataX2(DRESS_TYPE_1_DRESS_DATA_1_SETX_2);
            dressData1.setDressDataY2(DRESS_TYPE_1_DRESS_DATA_1_SETY_2);
            dressData1.setBitmap(Util.getImageFromAssetsFile(context, mShirt_One));
            dressDataList.add(dressData1);
            DressData dressData2 = new DressData();
            dressData2.setId(Constants.ID_TWO);
            dressData2.setDressType(Constants.TYPE_ONE);
            dressData2.setDressDataX1(DRESS_TYPE_1_DRESS_DATA_2_SETX_1);
            dressData2.setDressDataY1(DRESS_TYPE_1_DRESS_DATA_2_SETY_1);
            dressData2.setDressDataX2(DRESS_TYPE_1_DRESS_DATA_2_SETX_2);
            dressData2.setDressDataY2(DRESS_TYPE_1_DRESS_DATA_2_SETY_2);
            dressData2.setBitmap(Util.getImageFromAssetsFile(context, mShirt_Two));
            dressDataList.add(dressData2);

        } else if (dressType == Constants.TYPE_TWO) {
            DressData dressData1 = new DressData();
            dressData1.setId(Constants.ID_ONE);
            dressData1.setDressType(Constants.TYPE_TWO);
            dressData1.setDressDataX1(DRESS_TYPE_2_DRESS_DATA_1_SETX_1);
            dressData1.setDressDataY1(DRESS_TYPE_2_DRESS_DATA_1_SETY_1);
            dressData1.setDressDataX2(DRESS_TYPE_2_DRESS_DATA_1_SETX_2);
            dressData1.setDressDataY2(DRESS_TYPE_2_DRESS_DATA_1_SETY_2);
            dressData1.setBitmap(Util.getImageFromAssetsFile(context, mKids_1));
            dressDataList.add(dressData1);
            DressData dressData2 = new DressData();
            dressData2.setId(Constants.ID_TWO);
            dressData2.setDressType(Constants.TYPE_TWO);
            dressData2.setDressDataX1(DRESS_TYPE_2_DRESS_DATA_2_SETX_1);
            dressData2.setDressDataY1(DRESS_TYPE_2_DRESS_DATA_2_SETY_1);
            dressData2.setDressDataX2(DRESS_TYPE_2_DRESS_DATA_2_SETX_2);
            dressData2.setDressDataY2(DRESS_TYPE_2_DRESS_DATA_2_SETY_2);
            dressData2.setBitmap(Util.getImageFromAssetsFile(context, mKids_2));
            dressDataList.add(dressData2);
        } else if (dressType == Constants.TYPE_THREE) {
            DressData dressData6 = new DressData();
            dressData6.setId(Constants.ID_TWO);
            dressData6.setDressType(Constants.TYPE_THREE);
            dressData6.setDressDataX1(DRESS_TYPE_3_DRESS_DATA_6_SETX_1);
            dressData6.setDressDataY1(DRESS_TYPE_3_DRESS_DATA_6_SETY_1);
            dressData6.setDressDataX2(DRESS_TYPE_3_DRESS_DATA_6_SETX_2);
            dressData6.setDressDataY2(DRESS_TYPE_3_DRESS_DATA_6_SETY_2);
            dressData6.setBitmap(Util.getImageFromAssetsFile(context, mImage_One));
            dressDataList.add(dressData6);
            DressData dressData8 = new DressData();
            dressData8.setId(Constants.ID_EIGHT);
            dressData8.setDressType(Constants.TYPE_THREE);
            dressData8.setDressDataX1(DRESS_TYPE_3_DRESS_DATA_8_SETX_1);
            dressData8.setDressDataY1(DRESS_TYPE_3_DRESS_DATA_8_SETY_1);
            dressData8.setDressDataX2(DRESS_TYPE_3_DRESS_DATA_8_SETX_2);
            dressData8.setDressDataY2(DRESS_TYPE_3_DRESS_DATA_8_SETY_2);
            dressData8.setBitmap(Util.getImageFromAssetsFile(context, mImage_Two));
            dressDataList.add(dressData8);

        } else if (dressType == Constants.TYPE_FOUR) {
            DressData dressData1 = new DressData();
            dressData1.setId(Constants.ID_ONE);
            dressData1.setDressDataX1(DRESS_TYPE_4_DRESS_DATA_1_SETX_1);
            dressData1.setDressDataY1(DRESS_TYPE_4_DRESS_DATA_1_SETY_1);
            dressData1.setDressDataX2(DRESS_TYPE_4_DRESS_DATA_1_SETX_2);
            dressData1.setDressDataY2(DRESS_TYPE_4_DRESS_DATA_1_SETY_2);
            dressData1.setBitmap(Util.getImageFromAssetsFile(context, mTShirt_One));
            dressDataList.add(dressData1);
            DressData dressData2 = new DressData();
            dressData2.setId(Constants.ID_TWO);
            dressData2.setDressDataX1(DRESS_TYPE_4_DRESS_DATA_2_SETX_1);
            dressData2.setDressDataY1(DRESS_TYPE_4_DRESS_DATA_2_SETY_1);
            dressData2.setDressDataX2(DRESS_TYPE_4_DRESS_DATA_2_SETX_2);
            dressData2.setDressDataY2(DRESS_TYPE_4_DRESS_DATA_2_SETY_2);
            dressData2.setBitmap(Util.getImageFromAssetsFile(context, mTShirt_Two));
            dressDataList.add(dressData2);

        }
        return dressDataList;
    }

}
