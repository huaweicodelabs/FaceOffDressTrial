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
package com.huawei.faceoffdresstrial.kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.huawei.faceoffdresstrial.R
import com.huawei.faceoffdresstrial.kotlin.common.Constants

/**
 * MainActivity - there are 4 types(shirt, kids, slawar, T-shirt ) of room for trial dress
 * user can enter any types of trial room for try dresses
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            Log.d(TAG, Constants.NULL_POINTER_EXCEPTION, e)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val llRoom1 = findViewById<LinearLayout>(R.id.ll_room1)
        llRoom1.setOnClickListener {
            val i = Intent(this@MainActivity, DressTrialActivity::class.java)
            i.putExtra(Constants.DRESS_TYPE_STR, Constants.INDEX_ONE)
            startActivity(i)
        }
        val llRoom2 = findViewById<LinearLayout>(R.id.ll_room2)
        llRoom2.setOnClickListener {
            val i = Intent(this@MainActivity, DressTrialActivity::class.java)
            i.putExtra(Constants.DRESS_TYPE_STR, Constants.INDEX_TWO)
            startActivity(i)
        }
        val llRoom3 = findViewById<LinearLayout>(R.id.ll_room3)
        llRoom3.setOnClickListener {
            val i = Intent(this@MainActivity, DressTrialActivity::class.java)
            i.putExtra(Constants.DRESS_TYPE_STR, Constants.INDEX_THREE)
            startActivity(i)
        }
        val llRoom4 = findViewById<LinearLayout>(R.id.ll_room4)
        llRoom4.setOnClickListener {
            val i = Intent(this@MainActivity, DressTrialActivity::class.java)
            i.putExtra(Constants.DRESS_TYPE_STR, Constants.INDEX_FOUR)
            startActivity(i)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}