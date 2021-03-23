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
package com.huawei.faceoffdresstrial.kotlin.repo

import android.content.Context
import com.huawei.faceoffdresstrial.kotlin.common.Constants
import com.huawei.faceoffdresstrial.kotlin.common.Util
import com.huawei.faceoffdresstrial.kotlin.model.DressData
import java.util.*

class RoomRepo {

    private val DRESS_TYPE_1_DRESS_DATA_1_SETX_1 = 333
    private val DRESS_TYPE_1_DRESS_DATA_1_SETY_1 = 0
    private val DRESS_TYPE_1_DRESS_DATA_1_SETX_2 = 695
    private val DRESS_TYPE_1_DRESS_DATA_1_SETY_2 = 550
    private val DRESS_TYPE_1_DRESS_DATA_2_SETX_1 = 333
    private val DRESS_TYPE_1_DRESS_DATA_2_SETY_1 = 0
    private val DRESS_TYPE_1_DRESS_DATA_2_SETX_2 = 682
    private val DRESS_TYPE_1_DRESS_DATA_2_SETY_2 = 533
    private val DRESS_TYPE_2_DRESS_DATA_1_SETX_1 = 321
    private val DRESS_TYPE_2_DRESS_DATA_1_SETY_1 = 290
    private val DRESS_TYPE_2_DRESS_DATA_1_SETX_2 = 742
    private val DRESS_TYPE_2_DRESS_DATA_1_SETY_2 = 752
    private val DRESS_TYPE_2_DRESS_DATA_2_SETX_1 = 379
    private val DRESS_TYPE_2_DRESS_DATA_2_SETY_1 = 290
    private val DRESS_TYPE_2_DRESS_DATA_2_SETX_2 = 717
    private val DRESS_TYPE_2_DRESS_DATA_2_SETY_2 = 779
    private val DRESS_TYPE_3_DRESS_DATA_6_SETX_1 = 333
    private val DRESS_TYPE_3_DRESS_DATA_6_SETY_1 = 0
    private val DRESS_TYPE_3_DRESS_DATA_6_SETX_2 = 679
    private val DRESS_TYPE_3_DRESS_DATA_6_SETY_2 = 550
    private val DRESS_TYPE_3_DRESS_DATA_8_SETX_1 = 333
    private val DRESS_TYPE_3_DRESS_DATA_8_SETY_1 = 0
    private val DRESS_TYPE_3_DRESS_DATA_8_SETX_2 = 695
    private val DRESS_TYPE_3_DRESS_DATA_8_SETY_2 = 550
    private val DRESS_TYPE_4_DRESS_DATA_1_SETX_1 = 363
    private val DRESS_TYPE_4_DRESS_DATA_1_SETY_1 = 55
    private val DRESS_TYPE_4_DRESS_DATA_1_SETX_2 = 705
    private val DRESS_TYPE_4_DRESS_DATA_1_SETY_2 = 615
    private val DRESS_TYPE_4_DRESS_DATA_2_SETX_1 = 320
    private val DRESS_TYPE_4_DRESS_DATA_2_SETY_1 = 60
    private val DRESS_TYPE_4_DRESS_DATA_2_SETX_2 = 770
    private val DRESS_TYPE_4_DRESS_DATA_2_SETY_2 = 633


    fun addDressDataList(dressType: Int, context: Context): List<DressData> {
        val dressDataList: MutableList<DressData> = ArrayList()
        dressDataList.clear()
        if (dressType == Constants.TYPE_ONE) {
            val dressData1 = DressData()
            dressData1.id = Constants.ID_ONE
            dressData1.dressType = Constants.TYPE_ONE
            dressData1.dressDataX1 = DRESS_TYPE_1_DRESS_DATA_1_SETX_1
            dressData1.dressDataY1 = DRESS_TYPE_1_DRESS_DATA_1_SETY_1
            dressData1.dressDataX2 = DRESS_TYPE_1_DRESS_DATA_1_SETX_2
            dressData1.dressDataY2 = DRESS_TYPE_1_DRESS_DATA_1_SETY_2
            dressData1.bitmap = Util.Companion.getImageFromAssetsFile(context, mShirt_One)
            dressDataList.add(dressData1)
            val dressData2 = DressData()
            dressData2.id = Constants.ID_TWO
            dressData2.dressType = Constants.TYPE_ONE
            dressData2.dressDataX1 = DRESS_TYPE_1_DRESS_DATA_2_SETX_1
            dressData2.dressDataY1 = DRESS_TYPE_1_DRESS_DATA_2_SETY_1
            dressData2.dressDataX2 = DRESS_TYPE_1_DRESS_DATA_2_SETX_2
            dressData2.dressDataY2 = DRESS_TYPE_1_DRESS_DATA_2_SETY_2
            dressData2.bitmap = Util.Companion.getImageFromAssetsFile(context, mShirt_Two)
            dressDataList.add(dressData2)
        } else if (dressType == Constants.TYPE_TWO) {
            val dressData1 = DressData()
            dressData1.id = Constants.ID_ONE
            dressData1.dressType = Constants.TYPE_TWO
            dressData1.dressDataX1 = DRESS_TYPE_2_DRESS_DATA_1_SETX_1
            dressData1.dressDataY1 = DRESS_TYPE_2_DRESS_DATA_1_SETY_1
            dressData1.dressDataX2 = DRESS_TYPE_2_DRESS_DATA_1_SETX_2
            dressData1.dressDataY2 = DRESS_TYPE_2_DRESS_DATA_1_SETY_2
            dressData1.bitmap = Util.Companion.getImageFromAssetsFile(context, mKids_1)
            dressDataList.add(dressData1)
            val dressData2 = DressData()
            dressData2.id = Constants.ID_TWO
            dressData2.dressType = Constants.TYPE_TWO
            dressData2.dressDataX1 = DRESS_TYPE_2_DRESS_DATA_2_SETX_1
            dressData2.dressDataY1 = DRESS_TYPE_2_DRESS_DATA_2_SETY_1
            dressData2.dressDataX2 = DRESS_TYPE_2_DRESS_DATA_2_SETX_2
            dressData2.dressDataY2 = DRESS_TYPE_2_DRESS_DATA_2_SETY_2
            dressData2.bitmap = Util.Companion.getImageFromAssetsFile(context, mKids_2)
            dressDataList.add(dressData2)
        } else if (dressType == Constants.TYPE_THREE) {
            val dressData6 = DressData()
            dressData6.id = Constants.ID_TWO
            dressData6.dressType = Constants.TYPE_THREE
            dressData6.dressDataX1 = DRESS_TYPE_3_DRESS_DATA_6_SETX_1
            dressData6.dressDataY1 = DRESS_TYPE_3_DRESS_DATA_6_SETY_1
            dressData6.dressDataX2 = DRESS_TYPE_3_DRESS_DATA_6_SETX_2
            dressData6.dressDataY2 = DRESS_TYPE_3_DRESS_DATA_6_SETY_2
            dressData6.bitmap = Util.Companion.getImageFromAssetsFile(context, mImage_One)
            dressDataList.add(dressData6)
            val dressData8 = DressData()
            dressData8.id = Constants.ID_EIGHT
            dressData8.dressType = Constants.TYPE_THREE
            dressData8.dressDataX1 = DRESS_TYPE_3_DRESS_DATA_8_SETX_1
            dressData8.dressDataY1 = DRESS_TYPE_3_DRESS_DATA_8_SETY_1
            dressData8.dressDataX2 = DRESS_TYPE_3_DRESS_DATA_8_SETX_2
            dressData8.dressDataY2 = DRESS_TYPE_3_DRESS_DATA_8_SETY_2
            dressData8.bitmap = Util.Companion.getImageFromAssetsFile(context, mImage_Two)
            dressDataList.add(dressData8)
        } else if (dressType == Constants.TYPE_FOUR) {
            val dressData1 = DressData()
            dressData1.id = Constants.ID_ONE
            dressData1.dressDataX1 = DRESS_TYPE_4_DRESS_DATA_1_SETX_1
            dressData1.dressDataY1 = DRESS_TYPE_4_DRESS_DATA_1_SETY_1
            dressData1.dressDataX2 = DRESS_TYPE_4_DRESS_DATA_1_SETX_2
            dressData1.dressDataY2 = DRESS_TYPE_4_DRESS_DATA_1_SETY_2
            dressData1.bitmap = Util.Companion.getImageFromAssetsFile(context, mTShirt_One)
            dressDataList.add(dressData1)
            val dressData2 = DressData()
            dressData2.id = Constants.ID_TWO
            dressData2.dressDataX1 = DRESS_TYPE_4_DRESS_DATA_2_SETX_1
            dressData2.dressDataY1 = DRESS_TYPE_4_DRESS_DATA_2_SETY_1
            dressData2.dressDataX2 = DRESS_TYPE_4_DRESS_DATA_2_SETX_2
            dressData2.dressDataY2 = DRESS_TYPE_4_DRESS_DATA_2_SETY_2
            dressData2.bitmap = Util.Companion.getImageFromAssetsFile(context, mTShirt_Two)
            dressDataList.add(dressData2)
        }
        return dressDataList
    }

    companion object {
        private const val mShirt_One = "shirt_1.png"
        private const val mShirt_Two = "shirt_2.png"
        private const val mTShirt_Two = "tshirt_2.png"
        private const val mTShirt_One = "tshirt_1.png"
        private const val mKids_1 = "kids_1.PNG"
        private const val mKids_2 = "kids_2.PNG"
        private const val mImage_One = "suits_2.png"
        private const val mImage_Two = "suits_1.png"
    }

}