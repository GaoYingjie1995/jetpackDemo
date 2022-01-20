package com.example.myjetpack.net.okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myjetpack.base.BaseVBindingActivity
import com.example.myjetpack.databinding.ActivityOkhttpBinding
import okhttp3.OkHttpClient
import okhttp3.Request

class OkhttpActivity : BaseVBindingActivity<ActivityOkhttpBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVBinding.btGet.setOnClickListener {
            val httpClient = OkHttpClient();

        }
        mVBinding.btPost.setOnClickListener {

        }
    }
    override fun getViewBinding(): ActivityOkhttpBinding {
        return ActivityOkhttpBinding.inflate(layoutInflater)
    }
}