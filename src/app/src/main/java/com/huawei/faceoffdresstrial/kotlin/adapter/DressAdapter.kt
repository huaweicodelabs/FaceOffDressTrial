/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.huawei.faceoffdresstrial.kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.huawei.faceoffdresstrial.R
import com.huawei.faceoffdresstrial.kotlin.adapter.DressAdapter.MyViewHolder
import com.huawei.faceoffdresstrial.kotlin.model.DressData

class DressAdapter(private val dressDataList: List<DressData?>?, var imageViewOnClick: ImageViewOnClick) : RecyclerView.Adapter<MyViewHolder>() {
    interface ImageViewOnClick {
        fun onClick(dressData: DressData)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView

        init {
            imageView = view.findViewById(R.id.imageview_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dressData = dressDataList!![position]
        if (dressData != null) {
            holder.imageView.setImageBitmap(dressData.bitmap)
            holder.imageView.setOnClickListener { imageViewOnClick.onClick(dressData) }
        }
    }

    override fun getItemCount(): Int {
        return dressDataList!!.size
    }

}