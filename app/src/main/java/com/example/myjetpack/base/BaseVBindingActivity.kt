package com.example.myjetpack.base

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import android.os.Bundle
import android.os.PersistableBundle


abstract class BaseVBindingActivity<VB : ViewBinding?> : AppCompatActivity() {

    protected val mVBinding :VB by lazy { getViewBinding() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mVBinding!!.root)
    }

    protected abstract fun getViewBinding(): VB
}