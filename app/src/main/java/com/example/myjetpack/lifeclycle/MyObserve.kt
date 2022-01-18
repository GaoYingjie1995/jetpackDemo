package com.example.myjetpack.lifeclycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyObserve : DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.d("MyObserve", "onResume: ")

    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.d("MyObserve", "onPause: ")
    }
}