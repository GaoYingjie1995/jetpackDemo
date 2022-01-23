package com.example.myjetpack.net.okhttp
import android.os.Bundle
import android.util.Log
import com.example.myjetpack.base.BaseVBindingActivity
import com.example.myjetpack.databinding.ActivityOkhttpBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.IOException
import java.io.RandomAccessFile
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class OkhttpActivity : BaseVBindingActivity<ActivityOkhttpBinding>() {

    val httpClient = OkHttpClient();

    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .build()


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
                val call :Call = httpClient.newCall(request)
                val response = call.execute()
                if (response.isSuccessful) {
                    Log.d("OkhttpActivity", "sync get response: "+response.body?.string())
                }

            }.start()

        }
        mVBinding.btPostAsync.setOnClickListener {

//            postStringSync()
//            postFile()
//            postFormBody()
//            postMultiPartBody()
            thread {
                cancelCall()
            }

        }
        mVBinding.btPostSync.setOnClickListener {
            thread {
                postWithCache()
            }

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
        getCachingHttpClient().newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OkhttpActivity", "getWithQuery: failed")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.e("OkhttpActivity", "onResponse: "+response.body?.string() )
                    Log.e("OkhttpActivity", "cacheResponse: "+response.cacheResponse )
                    Log.e("OkhttpActivity", "networkResponse: "+response.networkResponse)
                }
            }

        })
    }

    private fun postString() {

        val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaTypeOrNull()
        val postBody = "这是一个 post  string  请求"
        val request = Request.Builder()
            .url("http://httpbin.org/post")
            .post(postBody.toRequestBody(MEDIA_TYPE_MARKDOWN))
            .build()
        httpClient.newCall(request).enqueue(object :Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.e("OkhttpActivity", "onResponse: "+response.body?.string())
                }else {
                    Log.e("OkhttpActivity", "onResponse: "+response.message )
                }
            }

        })
    }
    private fun postFile() {

        val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaTypeOrNull()

        val file = File(getExternalFilesDir(""),"testFile.md");
        if (!file.exists()) {
            file.createNewFile();
        }
        val randomAccessFile = RandomAccessFile(file,"rw")
        randomAccessFile.writeUTF("这是一个 post 文件")
        val request = Request.Builder()
            .url("http://httpbin.org/post")
            .post(file.asRequestBody(MEDIA_TYPE_MARKDOWN))
            .build()
        httpClient.newCall(request).enqueue(object :Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.e("OkhttpActivity", "onResponse: "+response.body?.string())
                }else {
                    Log.e("OkhttpActivity", "onResponse: "+response.message )
                }
            }

        })
    }

    private fun postFormBody() {

        val formBody = FormBody.Builder()
            .add("account","leopold")
            .add("password","123abc")
            .build()
        val request = Request.Builder()
            .url("http://httpbin.org/post")
            .post(formBody)
            .build()
        httpClient.newCall(request).enqueue(object :Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.e("OkhttpActivity", "onResponse: "+response.body?.string())
                }else {
                    Log.e("OkhttpActivity", "onResponse: "+response.message )
                }
            }

        })
    }

    private fun postMultiPartBody() {
        val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaTypeOrNull()
        val file = File(getExternalFilesDir(""),"testFile.md");
        if (!file.exists()) {
            file.createNewFile();
        }
        val randomAccessFile = RandomAccessFile(file,"rw")
        randomAccessFile.writeUTF("这是一个 post 文件")

        val multiPartBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("key1","value1")
            .addFormDataPart("key2","mdFile",file.asRequestBody(MEDIA_TYPE_MARKDOWN))
            .build();

        val request = Request.Builder()
            .url("http://httpbin.org/post")
            .post(multiPartBody)
            .build()
        getCachingHttpClient().newCall(request).enqueue(object :Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.e("OkhttpActivity", "onResponse: "+response.body?.string())

                }else {
                    Log.e("OkhttpActivity", "onResponse: "+response.message )
                }

            }

        })
    }


    private fun getCachingHttpClient() :OkHttpClient{
        val cacheDir = getExternalFilesDir("cache");
        if (cacheDir?.exists() == false) {
            cacheDir.mkdir()
        }
        Log.d("OkhttpActivity", "getCachingHttpClient: "+cacheDir?.absolutePath)

        val client = OkHttpClient.Builder()
            .cache(Cache(cacheDir!!,10*1024*1024))
            .build();
        return client;
    }

    private fun postWithCache () {
        val client: OkHttpClient = OkHttpClient.Builder()
            .cache(Cache(
                directory = cacheDir,
                maxSize = 10L * 1024L * 1024L // 10 MiB
            ))
            .build()

        val request = Request.Builder()
            .url("http://publicobject.com/helloworld.txt")
            .build()

        val response1Body = client.newCall(request).execute().use {
            if (!it.isSuccessful) throw IOException("Unexpected code $it")

            println("Response 1 response:          $it")
            println("Response 1 cache response:    ${it.cacheResponse}")
            println("Response 1 network response:  ${it.networkResponse}")
            return@use it.body!!.string()
        }

        val response2Body = client.newCall(request).execute().use {
            if (!it.isSuccessful) throw IOException("Unexpected code $it")

            println("Response 2 response:          $it")
            println("Response 2 cache response:    ${it.cacheResponse}")
            println("Response 2 network response:  ${it.networkResponse}")
            return@use it.body!!.string()
        }
        Log.e("OkhttpActivity", "Response 2 equals Response 1? " + (response1Body == response2Body))
    }


    private fun cancelCall() {
        val executor = Executors.newScheduledThreadPool(1)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
            .build()
        
        val call = client.newCall(request)
        // 一秒后取消请求
        executor.schedule({
            Log.e("OkhttpActivity", "canceling", )
            call.cancel()
            Log.e("OkhttpActivity", "canceled", )
           
        }, 1, TimeUnit.SECONDS)
        Log.e("OkhttpActivity", "calling")
        call.enqueue(object :Callback {
            //取消请求将收到该条回调
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OkhttpActivity", "onFailure: $e")
            }
            override fun onResponse(call: Call, response: Response) {
                Log.e("OkhttpActivity", "onResponse: ", )
            }
        })
    }


    /**
     val request = Request.Builder()
        .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
        .tag(tag)
        .build()
     * 通过 Request 传入TAG
     *
     */
    fun cancelWithTag(httpClient: OkHttpClient,tag:Any){

        val queuedCalls = httpClient.dispatcher.queuedCalls()
        for (call in queuedCalls) {
            if (tag == call.request().tag()) {
                call.cancel()
            }
        }
        for (runningCall in httpClient.dispatcher.runningCalls()) {
            if (tag == runningCall.request().tag()) {
                runningCall.cancel()
            }
        }
    }

}

