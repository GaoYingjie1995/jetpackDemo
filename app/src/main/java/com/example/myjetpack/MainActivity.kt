package com.example.myjetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.example.myjetpack.base.BaseVBindingActivity

import com.example.myjetpack.databinding.ActivityMainBinding
import com.example.myjetpack.lifeclycle.LifecycleActivity

import com.example.myjetpack.livedata.LiveDataVM
import com.example.myjetpack.livedata.UserInfo
import com.example.myjetpack.viewbinding.VBindingActivity
import com.example.myjetpack.viewmodel.MyViewModel

class MainActivity : BaseVBindingActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVBinding.btViewBinding.setOnClickListener {
            startActivity(Intent(this,VBindingActivity::class.java))
        }
        mVBinding.btLifecycle.setOnClickListener {
            startActivity(Intent(this,LifecycleActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}