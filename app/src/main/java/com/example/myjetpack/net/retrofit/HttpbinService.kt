package com.example.myjetpack.net.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

val base_url = "https://www.wanandroid.com"
interface HttpbinService {




    @GET("/get")
    fun get (@Query("account") account:String,@Query("password") pwd:String):Call<ResponseBody>

    @POST("/post")
    @FormUrlEncoded
    fun post(@Field("account") account:String, @Field("password") pwd:String):Call<ResponseBody>

    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") account:String, @Field("password") pwd:String):Call<HttpResponse<LoginResult>>


}