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
package com.huawei.faceoffdresstrial.java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huawei.faceoffdresstrial.R;
import com.huawei.faceoffdresstrial.java.model.DressData;
import com.huawei.faceoffdresstrial.java.model.DressData;

import java.util.List;

public class DressAdapter extends RecyclerView.Adapter<DressAdapter.MyViewHolder>
{
    public interface ImageViewOnClick {
        void onClick(DressData dressData);
    }

    private List<DressData> dressDataList;
    ImageViewOnClick imageViewOnClick;

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageview_image);
        }
    }

    public DressAdapter(List<DressData> dressDataList, ImageViewOnClick imageViewOnClick) {
        this.dressDataList = dressDataList;
        this.imageViewOnClick = imageViewOnClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DressData dressData = dressDataList.get(position);
        if (dressData != null) {
            holder.imageView.setImageBitmap(dressData.getBitmap());
            holder.imageView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageViewOnClick.onClick(dressData);
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return dressDataList.size();
    }
}
