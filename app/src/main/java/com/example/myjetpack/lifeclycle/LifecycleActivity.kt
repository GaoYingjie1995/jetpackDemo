package com.example.myjetpack.lifeclycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myjetpack.R
import com.example.myjetpack.base.BaseVBindingActivity
import com.example.myjetpack.databinding.ActivityLifecycleBinding

class LifecycleActivity : BaseVBindingActivity<ActivityLifecycleBinding>() {
    private lateinit var myObserve: MyObserve
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myObserve = MyObserve();
        this.lifecycle.addObserver(myObserve)



    }

    override fun getViewBinding(): ActivityLifecycleBinding = ActivityLifecycleBinding.inflate(layoutInflater)
}