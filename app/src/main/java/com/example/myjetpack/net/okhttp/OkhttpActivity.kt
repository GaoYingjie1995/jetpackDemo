package com.example.myjetpack.net.okhttp
import org.junit.Test
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myjetpack.base.BaseVBindingActivity
import com.example.myjetpack.databinding.ActivityOkhttpBinding
import okhttp3.*
import org.jetbrains.annotations.TestOnly
import java.io.IOException
import kotlin.concurrent.thread

class OkhttpActivity : BaseVBindingActivity<ActivityOkhttpBinding>() {

    val httpClient = OkHttpClient();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //异步请求  okhttp会自动为我们开启线程
        mVBinding.btGetAsync.setOnClickListener {

            val httpClient = OkHttpClient();
            val request = Request.Builder()
                .url("http://httpbin.org/get")
                .build()
            httpClient.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        Log.e("OkhttpActivity", "onResponse success: "+response.body?.string() )
                    }else {
                        Log.e("OkhttpActivity", "onResponse failed:  $response")
                    }

                }

            })
            Log.e("OkhttpActivity", "async get", )
        }
        //同步请求 需开启线程
        mVBinding.btGetSync.setOnClickListener {
            Thread {
                val httpClient = OkHttpClient();
                val request = Request.Builder()
                    .url("http://httpbin.org/get")
                    .build()
                val call = httpClient.newCall(request)
                val response = call.execute()
                if (response.isSuccessful) {
                    Log.d("OkhttpActivity", "sync get response: "+response.body?.string())
                }

            }.start()

        }
        mVBinding.btPostAsync.setOnClickListener {
            val httpClient = OkHttpClient();


        }
        mVBinding.btPostSync.setOnClickListener {

        }
        mVBinding.btGetQuery.setOnClickListener {
            getWithQuery()
        }
    }

    override fun getViewBinding(): ActivityOkhttpBinding {
        return ActivityOkhttpBinding.inflate(layoutInflater)
    }

    private fun getWithQuery() {
        val url = "http://httpbin.org/get?&account=leo&password=123456"
        val request = Request.Builder()
            .url(url).build();
        httpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OkhttpActivity", "getWithQuery: failed")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.e("OkhttpActivity", "onResponse: "+response.body?.string() )
                }
            }

        })
    }


}