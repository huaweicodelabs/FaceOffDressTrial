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
package com.huawei.faceoffdresstrial.kotlin.model

import android.graphics.Bitmap

/**
 * Dress Data Detail
 */
class DressData {
    var id = 0
    var dressType = 0
    var imageUrl: String? = null
    var bitmap: Bitmap? = null
    var dressDataX1 = 0
    var dressDataY1 = 0
    var dressDataX2 = 0
    var dressDataY2 = 0

    override fun toString(): String {
        return ("DressData{"
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
                + '}')
    }
}