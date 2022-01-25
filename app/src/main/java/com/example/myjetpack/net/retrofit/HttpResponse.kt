package com.example.myjetpack.net.retrofit

data class HttpResponse<T>(val errorMsg:String, val errorCode:Int, val data:T)
