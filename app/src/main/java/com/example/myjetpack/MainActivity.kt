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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myjetpack.adapter.FuncListAdapter
import com.example.myjetpack.base.BaseVBindingActivity
import com.example.myjetpack.bean.FuncBean

import com.example.myjetpack.databinding.ActivityMainBinding
import com.example.myjetpack.lifeclycle.LifecycleActivity
import com.example.myjetpack.livedata.LiveDataActivity

import com.example.myjetpack.livedata.LiveDataVM
import com.example.myjetpack.livedata.UserInfo
import com.example.myjetpack.net.okhttp.OkhttpActivity
import com.example.myjetpack.viewbinding.VBindingActivity
import com.example.myjetpack.viewmodel.MyViewModel

class MainActivity : BaseVBindingActivity<ActivityMainBinding>() {
    val funcList  = mutableListOf<FuncBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mVBinding.btViewBinding.setOnClickListener {
//            startActivity(Intent(this,VBindingActivity::class.java))
//        }
//        mVBinding.btLifecycle.setOnClickListener {
//            startActivity(Intent(this,LifecycleActivity::class.java))
//        }
        funcList.add(FuncBean("LiveData") {
            startActivity(Intent(this, LiveDataActivity::class.java))
        })
        funcList.add(FuncBean("ViewBinding") {
            startActivity(Intent(this, VBindingActivity::class.java))
        })
        funcList.add(FuncBean("Lifecycle") {
            startActivity(Intent(this, LifecycleActivity::class.java))
        })
        funcList.add(FuncBean("okHttp") {
            startActivity(Intent(this, OkhttpActivity::class.java))
        })
        val adapter = FuncListAdapter(funcList)
        mVBinding.rvFunc.adapter = adapter
        mVBinding.rvFunc.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter.setOnItemClick {
            funcList.get(it).click()
        }
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}