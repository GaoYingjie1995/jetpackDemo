package com.example.myjetpack

import com.example.myjetpack.net.retrofit.HttpResponse
import com.example.myjetpack.net.retrofit.HttpbinService
import com.example.myjetpack.net.retrofit.LoginResult
import com.example.myjetpack.net.retrofit.base_url
import okhttp3.*
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUnitTest {

    val cookieMap :MutableMap<String,List<Cookie>> = mutableMapOf()

    fun testCookieJar() {
        val client = OkHttpClient.Builder()
            .cookieJar(object :CookieJar{
                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    return if (cookieMap.get(url.host) != null) cookieMap.get(url.host)!! else listOf<Cookie>()
                }

                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {

                    cookieMap.put(url.host,cookies)
                }
            })
    }

    @Test
    fun retrofitGetTest() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://httpbin.org")
            .build()
        val httpbinService = retrofit.create(HttpbinService::class.java)
        val call = httpbinService.get("leopold", "123456")
//        call.enqueue(object :Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                println(response.body()?.string())
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                println(t)
//            }
//
//        })
        val response = call.execute();
        if (response.isSuccessful) {
            println(response.body()?.string())
        }

    }
    @Test
    fun retrofitPostTest() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://httpbin.org")
            .build()
        val httpbinService = retrofit.create(HttpbinService::class.java)
        val call = httpbinService.post("leopold", "123456")

        val response = call.execute();
        if (response.isSuccessful) {
            println(response.body()?.string())
        }

    }

    @Test
    fun loginTest() {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val httpbinService = retrofit.create(HttpbinService::class.java)
        val call : Call<HttpResponse<LoginResult>> = httpbinService.login("leopold", "123456")

        val response = call.execute();
        if (response.isSuccessful) {
            println(response.body()?.data)
        }

    }


}