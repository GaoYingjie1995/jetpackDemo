package com.example.myjetpack.livedata


import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataVM : ViewModel() {

    val liveData : MutableLiveData<String> = MutableLiveData()
    val count : MutableLiveData<Int> = MutableLiveData<Int>(0)
    val data1 : MutableLiveData<UserInfo> = MutableLiveData();
    val data2 : MutableLiveData<UserInfo> = MutableLiveData();
    val mediatorMerger = MediatorLiveData<UserInfo>()



    fun add(){
        count.value = count.value?.plus(1);

    }
    fun reduce() {
        count.value = count.value?.minus(1);
    }


}